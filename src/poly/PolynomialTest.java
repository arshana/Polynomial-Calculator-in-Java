package poly;

import junit.framework.*;

public class PolynomialTest extends TestCase {
	private Polynomial testPoly;
	
	public void setUp(){
		testPoly = new Polynomial("");
		testPoly.addTerm(3, 5);
		testPoly.addTerm(-2.3, 8);
		testPoly.addTerm(5.2, 0);
	}
	
	/**test constructor**/
	public void testConstructor(){
		Polynomial a = new Polynomial("");
		Polynomial d = new Polynomial("-3 2 4.5 8 22 0");
		assertEquals(0, a.terms());
		assertEquals(3, d.terms());
		Polynomial i = new Polynomial(d);
		assertEquals(3, i.terms());
	}
	
	/**test constructor**/
	public void testConstructorA(){
		try{
			Polynomial b = new Polynomial("0 3");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	/**test constructor**/
	public void testConstructorB(){
		try{
			Polynomial c = new Polynomial("8 -2");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	/**test constructor**/
	public void testConstructorC(){
		try{
			Polynomial c = new Polynomial("ahd");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	/**test constructor**/
	public void testConstructorD(){
		try{
			Polynomial e = new Polynomial("3");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	/**test constructor**/
	public void testConstructorE(){
		try{
			Polynomial f = new Polynomial("adj sahjdf");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	/**test constructor**/
	public void testConstructorF(){
		try{
			Polynomial g = new Polynomial("shd 3");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	/**test constructor**/
	public void testConstructorG(){
		try{
			Polynomial h = new Polynomial("3 dhfj");
			fail("caught error");
		}
		catch(IllegalArgumentException e){}
		catch(Exception e){
			fail("wrong error");
		}
	}
	
	/**test terms method**/
	public void testTerms(){
		assertEquals(3, testPoly.terms());
		Polynomial a = new Polynomial("-3 2 4.5 8 22 0");
		assertEquals(3, a.terms());
		Polynomial b = new Polynomial(testPoly);
		assertEquals(3, b.terms());
		b.deleteTerm(4);
		assertEquals(3, b.terms());
		b.addTerm(23.2, 65);
		assertEquals(4, b.terms());
	}
	
	/**test addTerm method
	 * Placement is tested in toString method and sum method
	**/
	public void testAddTerm(){
		Polynomial a = new Polynomial("-3 2 4.5 8 22 0");
		assertEquals(3, a.terms());
		a.addTerm(-22, 0);
		assertEquals(2, a.terms());
		a.addTerm(-2, 1);
		assertEquals(3, a.terms());
		a.addTerm(3, 2);
		assertEquals(2, a.terms());
		a.addTerm(4, 4);
		assertEquals(3, a.terms());
		Polynomial b = new Polynomial(a);
		assertEquals(3, a.terms());
		b.addTerm(-2, 8);
		assertEquals(3, b.terms());
		b.addTerm(9, 7);
		assertEquals(4, b.terms());
		assertEquals(3, a.terms());
	}
	
	/**test deleteTerm method**/
	public void testDeleteTerm(){
		Polynomial a = new Polynomial("-3 2 4.5 8 22 1");
		assertEquals(22.0, a.deleteTerm(1));
		assertEquals(2, a.terms());
		a.addTerm(5, 5);
		a.addTerm(6, 6);
		assertEquals(4, a.terms());
		assertEquals(4.5, a.deleteTerm(8));
		assertEquals(3, a.terms());
		assertEquals(0.0, a.deleteTerm(7));
		assertEquals(3, a.terms());
		a.addTerm(3, 0);
		assertEquals(4, a.terms());
		assertEquals(3.0, a.deleteTerm(0));
		assertEquals(3, a.terms());
	}
	
	/**test getCoefficient method**/
	public void testGetCoefficient(){
		Polynomial a = new Polynomial("-3 2 4.5 8 22 1");
		assertEquals(3, a.terms());
		assertEquals(22.0, a.getCoefficient(1));
		assertEquals(4.5, a.getCoefficient(8));
		assertEquals(-3.0, a.getCoefficient(2));
		a.addTerm(-2.3, 0);
		assertEquals(-2.3, a.getCoefficient(0));
		assertEquals(4, a.terms());
	}
	
	/**test evaluate method**/
	public void testEvaluate(){
		Polynomial a = new Polynomial("-3 3 4.5 8 22 1");
		double x = 1.35;
		assertEquals(Math.round((-3*Math.pow(x, 3)) + (4.5*Math.pow(x, 8)) + (22 * x)), Math.round(a.evaluate(x)));
		x = 13.5;
		assertEquals(Math.round((-3*Math.pow(x, 3)) + (4.5*Math.pow(x, 8)) + (22 * x)), Math.round(a.evaluate(x)));
		x = 135;
		assertEquals(Math.round((-3*Math.pow(x, 3)) + (4.5*Math.pow(x, 8)) + (22 * x)), Math.round(a.evaluate(x)));
		a.deleteTerm(1);
		a.addTerm(16, 0);
		x = 1.35;
		assertEquals(Math.round((-3*Math.pow(x, 3)) + (4.5*Math.pow(x, 8)) + (16)), Math.round(a.evaluate(x)));
		x = 13.5;
		assertEquals(Math.round((-3*Math.pow(x, 3)) + (4.5*Math.pow(x, 8)) + (16)), Math.round(a.evaluate(x)));
		x = 135;
		assertEquals(Math.round((-3*Math.pow(x, 3)) + (4.5*Math.pow(x, 8)) + (16)), Math.round(a.evaluate(x)));
		assertEquals(3, a.terms());
	}
	
	/**test equals method**/
	public void testEquals(){
		Polynomial a = new Polynomial("-3 2 4.5 8 22 0");
		Polynomial p = new Polynomial( "2.0 0" );
		assertFalse(a.equals(p));
		
		Polynomial b = new Polynomial("-3 2 4.5 8 22 0");
		assertTrue(a.equals(b));
		
		b.deleteTerm(2);
		b.deleteTerm(8);
		b.addTerm(-20.0, 0);
		assertTrue(b.equals(p));
	}
	
	/**test derivative method**/
	public void testDerivative(){
		Polynomial a = new Polynomial("-3 2 4.5 8 22 0");
		Polynomial deriv = a.derivative();
		assertEquals(2, deriv.terms());
		a.addTerm(-21, 1);
		assertEquals(2, deriv.terms());
		deriv = a.derivative();
		assertEquals(3, deriv.terms());
		a.addTerm(-22, 0);
		deriv = a.derivative();
		assertEquals(3, deriv.terms());
	}
	
	/**test toString method
	 * also partially tests addTerm method
	**/
	public void testToString(){
	    Polynomial p = new Polynomial( "2.0 0" );
	    assertEquals( "2.0", p.toString() );
	    p = new Polynomial( "2 0" );
	    assertEquals( "2.0", p.toString() );
	    p = new Polynomial( "2.1 1" );
	    assertEquals( "2.1x", p.toString() );
	    p = new Polynomial( "2.1 2" );
	    assertEquals( "2.1x^2", p.toString() );
	    p = new Polynomial( "2.1 10" );
	    assertEquals( "2.1x^10", p.toString() );
	    p = new Polynomial( "1.0 0" );
	    assertEquals( "1.0", p.toString() );
	    p = new Polynomial( "-1.0 0" );
	    assertEquals( "-1.0", p.toString() );
	    p = new Polynomial( "1.0 1" );
	    assertEquals( "x", p.toString() );
	    p = new Polynomial( "1.0 2" );
	    assertEquals( "x^2", p.toString() );
	    p = new Polynomial( "-1.0 0" );
	    assertEquals( "-1.0", p.toString() );
	    p = new Polynomial( "-1.0 1" );
	    assertEquals( "-x", p.toString() );
	    p = new Polynomial( "-1.0 2" );
	    assertEquals( "-x^2", p.toString() );
	    p = new Polynomial( "1 2 2.1 0" );
	    assertEquals( "x^2 + 2.1", p.toString() );
	    p = new Polynomial( "1 2 -2.1 0" );
	    assertEquals( "x^2 - 2.1", p.toString() );
	    p = new Polynomial( "-1 2 2.1 0" );
	    assertEquals( "-x^2 + 2.1", p.toString() );
	    p = new Polynomial( "-1 2 -2.1 0" );
	    assertEquals( "-x^2 - 2.1", p.toString() );
	    p = new Polynomial( "2.5 3 -1.0 2" );
	    assertEquals( "2.5x^3 - x^2", p.toString() );
	    p = new Polynomial( "2.5 3 -1.0 2 1.0 1 -1.0 0" );
	    assertEquals( "2.5x^3 - x^2 + x - 1.0", p.toString() );
	    p = new Polynomial( "2.5 3 -1 2 1 1 -1 0" );
	    assertEquals( "2.5x^3 - x^2 + x - 1.0", p.toString() );
	    p = new Polynomial( "2.5 3 1 2 -1 1 1 0" );
	    assertEquals( "2.5x^3 + x^2 - x + 1.0", p.toString() );
	    p = new Polynomial( "" );
	    assertEquals( "0.0", p.toString() );
	}
	
	/**test description method**/
	public void testDescription(){
	    Polynomial p = new Polynomial( "2.0 0" );
	    assertEquals( "constant term 2.0", p.description() );
	    p = new Polynomial( "2 0" );
	    assertEquals( "constant term 2.0", p.description() );
	    p = new Polynomial( "2.1 1" );
	    assertEquals( "exponent 1, coefficient 2.1", p.description() );
	    p = new Polynomial( "2.1 2" );
	    assertEquals( "exponent 2, coefficient 2.1", p.description() );
	    p = new Polynomial( "2.1 10" );
	    assertEquals( "exponent 10, coefficient 2.1", p.description() );
	    p = new Polynomial( "1.0 0" );
	    assertEquals( "constant term 1.0", p.description() );
	    p = new Polynomial( "-1.0 0" );
	    assertEquals( "constant term -1.0", p.description() );
	    p = new Polynomial( "1.0 1" );
	    assertEquals( "exponent 1, coefficient 1.0", p.description() );
	    p = new Polynomial( "1.0 2" );
	    assertEquals( "exponent 2, coefficient 1.0", p.description() );
	    p = new Polynomial( "-1.0 0" );
	    assertEquals( "constant term -1.0", p.description() );
	    p = new Polynomial( "-1.0 1" );
	    assertEquals( "exponent 1, coefficient -1.0", p.description() );
	    p = new Polynomial( "-1.0 2" );
	    assertEquals( "exponent 2, coefficient -1.0", p.description() );
	    p = new Polynomial( "1 2 2.1 0" );
	    assertEquals( "constant term 2.1" + System.lineSeparator() + "exponent 2, coefficient 1.0", p.description() );
	    p = new Polynomial( "1 2 -2.1 0" );
	    assertEquals( "constant term -2.1" + System.lineSeparator() + "exponent 2, coefficient 1.0", p.description() );
	    p = new Polynomial( "-1 2 2.1 0" );
	    assertEquals( "constant term 2.1" + System.lineSeparator() + "exponent 2, coefficient -1.0", p.description() );
	    p = new Polynomial( "-1 2 -2.1 0" );
	    assertEquals( "constant term -2.1" + System.lineSeparator() + "exponent 2, coefficient -1.0", p.description() );
	    p = new Polynomial( "2.5 3 -1.0 2" );
	    assertEquals( "exponent 2, coefficient -1.0" + System.lineSeparator() + "exponent 3, coefficient 2.5", p.description() );
	    p = new Polynomial( "2.5 3 -1.0 2 1.0 1 -1.0 0" );
	    assertEquals( "constant term -1.0" + System.lineSeparator() + "exponent 1, coefficient 1.0" + System.lineSeparator() + "exponent 2, coefficient -1.0" 
	    		+ System.lineSeparator() + "exponent 3, coefficient 2.5", p.description() );
	    p = new Polynomial( "2.5 3 -1 2 1 1 -1 0" );
	    assertEquals( "constant term -1.0" + System.lineSeparator() + "exponent 1, coefficient 1.0" + System.lineSeparator() + "exponent 2, coefficient -1.0" 
	    		+ System.lineSeparator() + "exponent 3, coefficient 2.5", p.description() );
	    p = new Polynomial( "2.5 3 1 2 -1 1 1 0" );
	    assertEquals( "constant term 1.0" + System.lineSeparator() + "exponent 1, coefficient -1.0" + System.lineSeparator() + "exponent 2, coefficient 1.0" 
	    		+ System.lineSeparator() + "exponent 3, coefficient 2.5", p.description() );
	    p = new Polynomial( "" );
	    assertEquals( "0.0", p.description() );
	}
	
	/**test sum method
	 * also partially tests addTerm method
	**/
	public void testSum(){
		Polynomial a = new Polynomial("-3 2 4.5 8 22 0");
		Polynomial b = new Polynomial("2.5 3 4 2 -1 1 -21 0 -4.5 8");
		Polynomial c = Polynomial.sum(a, b);
		assertEquals("2.5x^3 + x^2 - x + 1.0", c.toString());
		
		c = Polynomial.sum(c, new Polynomial("26 26"));
		assertEquals("26.0x^26 + 2.5x^3 + x^2 - x + 1.0", c.toString());
	}
	
	/**test product method**/
	public void testProduct(){
		Polynomial a = new Polynomial("1 1");
		Polynomial b = new Polynomial("1 1");
		Polynomial c = Polynomial.product(a, b);
		assertEquals("x^2", c.toString());
		assertEquals(16.0, c.evaluate(4));
		
		c = Polynomial.product(a, new Polynomial("-1 1 2 0 3.2 4"));
		assertEquals("3.2x^5 - x^2 + 2.0x", c.toString());
		assertEquals(3268.8, c.evaluate(4));
		
		c = Polynomial.product(new Polynomial("-1 1 2 0 3.2 4"), c);
		assertEquals("x^9 - 6.4x^6 + 12.8x^5 + x^3 - 4.0x^2 + 4.0x", c.toString().substring(c.toString().indexOf('x')));
		assertEquals("10.24", c.toString().substring(0, 5));
		assertEquals(2671263.36, c.evaluate(4), 0.001);
	}
}