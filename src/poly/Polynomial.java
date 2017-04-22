package poly;

import java.math.BigDecimal;

/**
 * LinkedList form of Polynomials
 * @author Arshana Jain
 * @version November 20, 2016
 */
public class Polynomial{
	private Term highest, lowest;	//Terms with the highest and lowest terms in Polynomial
	private int numTerms;	//number of Terms in Polynomial
	
	/**
	 * Creates a Polynomial
	 * @param s holds the values for the Polynomial separated by spaces; every odd value in a coefficient; every even value is an exponent
	 */
	public Polynomial(String s){
		try{
			highest = null;
			lowest = null;
			numTerms = 0;
			//automatically checks for null
			if(s.length() > 0){
				String[] values = s.split(" ");
				//makes sure there are pairs of values
				if(values.length % 2 != 0){
					throw new IllegalArgumentException();
				}
				for(int i = 0; i < values.length; i += 2){	//separated by Terms
					addTerm(Double.parseDouble(values[i]), Integer.parseInt(values[i + 1]));
				}
				//setting lowest
				lowest = highest;
				while(lowest.next != null){
					lowest = lowest.next;
				}
			}
		}
		catch(Exception e){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a Polynomial
	 * @param poly the Polynomial to make a copy of
	 */
	public Polynomial(Polynomial poly){
		numTerms = 0;
		//if poly is empty
		if(poly.highest == null){
			highest = null;
			lowest = null;
		}
		else{
			Term p = poly.highest;
			while(p != null){
				addTerm(p.coefficient, p.exponent);
				p = p.next;
			}
			lowest = p;
		}
	}
	
	/**
	 * Returns number of Terms in Polynomial
	 * @return number of Terms
	 */
	public int terms(){
		return numTerms;
	}
	
	/**
	 * Adds a Term to Polynomial
	 * @param coef coefficient of Term to add
	 * @param exp exponent of Term to add
	 */
	public void addTerm(double coef, int exp){
		//if Polynomial is empty
		if(highest == null){
			highest = new Term(coef, exp, null);
			numTerms++;
			lowest = highest;
		}
		
		//if only one value
		else if(highest.next == null && highest.exponent > exp){
			highest.next = new Term(coef, exp, null);
			numTerms++;
			lowest = highest.next;
		}
		
		//if first Term already contains exponent
		else if(highest.exponent == exp){
			highest.coefficient += coef;
			if(highest.coefficient == 0){
				deleteTerm(highest.exponent);
			}
		}
		
		//if first Term must be changed
		else if(highest.exponent < exp){
			highest = new Term(coef, exp, highest);
			numTerms++;
		}
		else{
			Term p = highest;
			while(p.next != null && p.next.exponent > exp){
				p = p.next;
			}
			//if exponent is already present
			if(p.next != null && p.next.exponent == exp){
				p.next.coefficient += coef;
				if(p.next.coefficient == 0){
					deleteTerm(p.next.exponent);
				}
			}

			else{
				p.next = new Term(coef, exp, p.next);
				numTerms++;
				if(p.next != null && p.next.next == null){
					lowest = p.next;
				}
			}
		}
	}
	
	/**
	 * Deletes a Term from Polynomial
	 * @param exp exponent of Term to delete
	 * @return coefficient corresponding with deleted Term
	 */
	public double deleteTerm(int exp){
		if(highest != null){
			//if deleting first Term
			if(highest.exponent == exp){
				double coef = highest.coefficient;
				highest = highest.next;
				numTerms--;
				return coef;
			}
			//if deleting from middle or end of Polynomial
			if(exp <= highest.exponent && lowest != null && exp >= lowest.exponent){
				Term p = highest;
				while(p.next != null){
					if(p.next.exponent == exp){
						double coef = p.next.coefficient;
						p.next = p.next.next;
						if(p.next == null){
							lowest = p;
						}
						numTerms--;
						return coef;
					}
					p = p.next;
				}
			}
		}
		//default
		return 0.0;
	}
	
	/**
	 * Returns coefficient related to given exponent
	 * @param exp exponent client wants the coefficient for
	 * @return coefficient corresponding to given exponent; returns 0.0 if Term with exponent does not exist
	 */
	public double getCoefficient(int exp){
		//check if beyond exponents first
		if((highest != null && highest.exponent >= exp) || (lowest != null && lowest.exponent <= exp)){
			Term p = highest;
			while(p != null){
				if(p.exponent == exp){
					return p.coefficient;
				}
				p = p.next;
			}
		}
		//default
		return 0.0;
	}
	
	/**
	 * Returns value of Polynomial after evaluating for x
	 * @param x value of x in Polynomial
	 * @return value of Polynomial after evaluating for x
	 */
	public double evaluate(double x){
		Term p = highest;
		double val = 0.0;
		while(p != null){
			val += Math.pow(x, p.exponent) * p.coefficient;
			p = p.next;
		}
		return val;
	}
	
	@Override
	public boolean equals(Object obj){
		if((obj instanceof Polynomial) && toString().equals(obj.toString())){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the derivative of the Polynomial
	 * @return Polynomial-form derivative of the Polynomial
	 */
	public Polynomial derivative(){
		//if Polynomial is empty
		if(highest == null){
			return new Polynomial("");
		}
		else{
			String deriv = "";
			Term p = highest;
			while(p != null){
				double coef = p.coefficient * p.exponent;
				int exp = p.exponent - 1;
				if(coef != 0 || exp >= 0){	//checking to avoid IllegalArgumentExceptions
					deriv += coef + " " + exp + " ";
				}
				p = p.next;
			}
			return new Polynomial(deriv);
		}
	}
	
	@Override
	public String toString(){
		Term p = highest;
		String rep = "";
		//if Polynomial is empty
		if(p == null){
			rep = "0.0";
		}
		else{
			if(p.coefficient < 0){
				rep += "-";
			}
			rep += absValOfTermToString(p);
			while(p.next != null){
				p = p.next;
				if(p.coefficient < 0){
					rep += " - ";
				}
				else{
					rep += " + ";
				}
				rep += absValOfTermToString(p);
			}
		}
		return rep;
	}
	
	/**
	 * Returns the absolute value String-form of a given Term
	 * @param p given Term for which to find absolute value String-form
	 * @return absolute value String-form of a given Term
	 */
	private String absValOfTermToString(Term p){
		String rep = "";
		//deals with coefficient
		if(p.coefficient != -1 && p.coefficient != 1){
			if(p.coefficient < 0){
				rep += (p.coefficient * -1);
			}
			else{
				rep += p.coefficient;
			}
		}
		else if(p.exponent == 0){
			rep += 1.0;
		}
		
		//deals with exponent
		if(p.exponent > 0){
			rep += "x";
			if(p.exponent > 1){
				rep += "^" + p.exponent;
			}
		}
		return rep;
	}
	
	/**
	 * Returns a description of the Polynomial
	 * @return description of the Polynomial
	 */
	public String description(){
		if(numTerms == 0){
			return "0.0";
		}
		//recursive call
		return description(highest);
	}
	
	/**
	 * Returns a description of the Polynomial
	 * @param p Term to describe
	 * @return description of Terms so far described
	 */
	private String description(Term p){
		//base case
		if(p.exponent == 0){
			return "constant term " + p.coefficient;
		}
		//recursive case
		String descrip = "";
		if(p.next != null){
			descrip = description(p.next) + System.lineSeparator();
		}
		return descrip + "exponent " + p.exponent + ", coefficient " + p.coefficient;
	}
	
	/**
	 * Returns a Polynomial-form sum of two Polynomials
	 * @param a first Polynomial
	 * @param b second Polynomial
	 * @return sum of the two given Polynomials
	 */
	public static Polynomial sum(Polynomial a, Polynomial b){
		//makes a deep copy
		Polynomial sum = new Polynomial(a);
		Term p = b.highest;
		//adds terms to copy
		while(p != null){
			sum.addTerm(p.coefficient, p.exponent);
			p = p.next;
		}
		return sum;
	}
	
	/**
	 * Returns a Polynomial-form product of two Polynomials
	 * @param a first Polynomial
	 * @param b second Polynomial
	 * @return product of the two given Polynomials
	 */
	public static Polynomial product(Polynomial a, Polynomial b){
		Polynomial product = new Polynomial("");
		Term pOfA = a.highest;
		//multiplies one term of a through b then adds to the sum of the previous multiplications
		while(pOfA != null){
			Term pOfB = b.highest;
			Polynomial temp = new Polynomial("");
			while(pOfB != null){
				temp.addTerm(pOfA.coefficient * pOfB.coefficient, pOfA.exponent + pOfB.exponent);
				pOfB = pOfB.next;
			}
			product = sum(product, temp);
			pOfA = pOfA.next;
		}
		return product;
	}
	
	/**
	 * Creates a Term
	 */
	private class Term{
		double coefficient;
		int exponent;
		Term next;
		Term(double coef, int exp, Term next){
			if(exp < 0 || coef == 0){
				throw new IllegalArgumentException();
			}
			coefficient = coef;
			exponent = exp;
			this.next = next;
		}
	}
}