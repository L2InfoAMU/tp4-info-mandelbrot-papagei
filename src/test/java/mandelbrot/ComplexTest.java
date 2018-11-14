package mandelbrot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComplexTest {
    private final Complex onePlusI = new Complex(1, 1);
    private final Complex minusI = new Complex(0, -1);
    private final Complex minusOne = new Complex(-1, 0);
    private final Complex oneMinusI = new Complex(1, -1);
    private final Complex twoI = new Complex(0, 2);
    private final Complex two = new Complex(2, 0);
    private final double real = -12;
    private final double imaginary = 10;


    @Test
    void testConstructor() {
        assertEquals(0., twoI.real, Helpers.EPSILON);
        assertEquals(2., twoI.imaginary, Helpers.EPSILON);
        assertEquals(1., oneMinusI.real, Helpers.EPSILON);
        assertEquals(-1., oneMinusI.imaginary, Helpers.EPSILON);
        assertEquals(2., two.real, Helpers.EPSILON);
        assertEquals(0., two.imaginary, Helpers.EPSILON);
    }

    @Test
    void testGetReal() {
        assertEquals(2., two.getReal(), Helpers.EPSILON);
        assertEquals(1., oneMinusI.getReal(), Helpers.EPSILON);
        assertEquals(-1., new Complex(-1, 1).getReal(), Helpers.EPSILON);
        assertEquals(real, new Complex(real, imaginary).getReal(), Helpers.EPSILON);
    }

    @Test
    void testGetImaginary() {
        assertEquals(2., twoI.getImaginary(), Helpers.EPSILON);
        assertEquals(1., new Complex(-1, 1).getImaginary(), Helpers.EPSILON);
        assertEquals(-1., oneMinusI.getImaginary(), Helpers.EPSILON);
        assertEquals(imaginary, new Complex(real, imaginary).getImaginary(), Helpers.EPSILON);
    }

    @Test
    void testOne() {
        assertEquals(1., Complex.ONE.getReal());
        assertEquals(0., Complex.ONE.getImaginary());
    }

    @Test
    void testI() {
        assertEquals(0, Complex.I.getReal());
        assertEquals(1, Complex.I.getImaginary());
    }

    @Test
    void testZero() {
        assertEquals(0, Complex.ZERO.getReal());
        assertEquals(0, Complex.ZERO.getImaginary());
    }

    @Test
    void testNegate() {
        assertEquals(minusOne, Complex.ONE.negate());
        assertEquals(Complex.I, minusI.negate());
        assertEquals(new Complex(-1, 1), oneMinusI.negate());
        assertEquals(new Complex(real, imaginary), new Complex(-real, -imaginary).negate());
    }

    @Test
    void testReciprocal() {
        assertEquals(Complex.ONE, Complex.ONE.reciprocal());
        assertEquals(Complex.I, minusI.reciprocal());
        assertEquals(new Complex(0.5, 0), two.reciprocal());
        assertEquals(new Complex(0.5, 0.5), oneMinusI.reciprocal());
    }

    @Test
    void testReciprocalOfZero() {
        assertThrows(ArithmeticException.class, () -> Complex.ZERO.reciprocal());
    }

    @Test
    void testSubstract() {
        assertEquals(minusOne, Complex.ZERO.subtract(Complex.ONE));
        assertEquals(oneMinusI, Complex.ONE.subtract(Complex.I));
        assertEquals(new Complex(real - 1, imaginary - 1),
                new Complex(real, imaginary).subtract(onePlusI));
    }

    @Test
    void testDivide() {
        assertEquals(onePlusI, onePlusI.divide(Complex.ONE));
        assertEquals(new Complex(0.5, 0), Complex.ONE.divide(two));
        assertEquals(minusI, oneMinusI.divide(onePlusI));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> Complex.ONE.divide(Complex.ZERO));
    }

    @Test
    void testConjugate() {
        assertEquals(Complex.ZERO, Complex.ZERO.conjugate());
        assertEquals(Complex.ONE, Complex.ONE.conjugate());
        assertEquals(onePlusI, oneMinusI.conjugate());
        assertEquals(new Complex(real, -imaginary), new Complex(real, imaginary).conjugate());
    }

    @Test
    void testRotation() {
        assertEquals(Complex.I, Complex.rotation(Math.PI / 2));
        assertEquals(minusI, Complex.rotation(-Math.PI / 2));
        assertEquals(Complex.ONE, Complex.rotation(0));
        assertEquals(new Complex(Math.sqrt(2) / 2., Math.sqrt(2) / 2.),
                Complex.rotation(Math.PI / 4));
        assertEquals(new Complex(1. / 2., Math.sqrt(3) / 2.),
                Complex.rotation(Math.PI / 3));
    }

    @Test
    void testToString() {
        assertEquals("Complex{real=1.0, imaginary=-1.0}", oneMinusI.toString());
        assertEquals("Complex{real=" + real + ", imaginary=" + imaginary + "}", new Complex(real, imaginary).toString());
    }

    @Test
    void testHashCode() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    /* ***************************** TEST Created ************************** */
    @Test
    void testEquals() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        assertEquals(true, c1.equals(c2));
        c2 = new Complex(real, -imaginary);
        assertEquals(false, c1.equals(c2));
        c2 = new Complex(-real, imaginary);
        assertEquals(false, c1.equals(c2));
        c2 = new Complex(-real, -imaginary);
        assertEquals(false, c1.equals(c2));

    }

    @Test
    void testReal() {
        Complex c1 = new Complex(real, imaginary);
        Complex expected = new Complex(real, 0);
        assertEquals(expected, c1.real(real));
        c1 = new Complex(0, 0);
        assertEquals(expected, c1.real(real));
    }

    @Test
    void testAdd() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        Complex c3 = new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
        assertEquals(c3, c1.add(c2));
        c2 = new Complex(3, 16);
        c3 = new Complex(c1.real + 3, c1.imaginary + 16);
        assertEquals(c3, c1.add(c2));
        assertEquals(c3, c2.add(c1));

    }

    @Test
    void testSubtract() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        Complex c3 = new Complex(c1.real - c2.real, c1.imaginary - c2.imaginary);
        assertEquals(c3, c1.subtract(c2));

        c2 = new Complex(3, 16);
        c3 = new Complex(c1.real - 3, c1.imaginary - 16);
        Complex c4 = new Complex(3 - c1.real, 16 - c1.imaginary);

        assertEquals(c3, c1.subtract(c2));
        assertEquals(c4, c2.subtract(c1));

    }

    @Test
    void testMultiply() {
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = new Complex(real, imaginary);
        Complex c3 = new Complex(this.real * c2.real - this.imaginary * c2.imaginary,
                this.real * c2.imaginary + this.imaginary * c2.real);
        assertEquals(c3, c1.multiply(c2));
        assertEquals(c3, c2.multiply(c1));

        c2 = new Complex(3, 16);
        c3 = new Complex(this.real * 3 - this.imaginary * 16,
                this.real * 16 + this.imaginary * 3);

        assertEquals(c3, c1.multiply(c2));
    }

    @Test
    void testSquaredModulus() {
        Complex c1 = new Complex(real, imaginary);
        double c2 = real * real + imaginary * imaginary;
        assertEquals(c2, c1.squaredModulus());
    }
    @Test
    void testModulus() {
        Complex c1 = new Complex(real, imaginary);
        double c2 = c1.squaredModulus(); //squareModulus deja testé et fonctionnel
        assertEquals(Math.sqrt(c2), c1.modulus());
    }

    @Test
    void testPow(){
        int p = 13; //modifier p pour tester toute valeur possible. ET p>0 d'apres la methode.
        Complex c1 = new Complex(real, imaginary);
        Complex c2 = c1;
        if (p==0){
            c2 = new Complex(1,0);
        }
        else{
            while (p > 1){
                c2.multiply(c1);
                p--;
            }
        }

        assertEquals(c2,c1.pow(p));

    }

}
