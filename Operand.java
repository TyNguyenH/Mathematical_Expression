/**
 *              ax^n,
 * 
 *      where
 *          a is coefficient,
 *          x is variable,
 *          n is exponent.
 */
class Operand {
    private double coefficient;
    private int exponent;

    public Operand() {
        coefficient = 0;
        exponent = 0;
    }

    public Operand(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public void printOperand() {
        // ax^n, (-infinity < a < +infinity, -infinity < n < +infinity)
        if (coefficient != 0 && exponent != 0) {
            // x^n
            if (coefficient == 1 && exponent != 0)
                System.out.print("x^" + exponent);
            
            // ax^n
            else {
                if (coefficient - (int) coefficient != 0)
                    System.out.print("(" + coefficient + ")");
                else
                    System.out.print((int)coefficient);

                System.out.print("x^");

                System.out.print(exponent);
            }
        }
                    
        // ax, (-infinity < a < +infinity)
        if (coefficient != 0 && exponent == 1) {
            if (coefficient - (int) coefficient != 0)
                System.out.print("(" + coefficient + ")" + "x");
            else 
                System.out.print((int)coefficient + "x");

            // x
            if (coefficient == 1 && exponent == 1)
                System.out.print("x");
        }
        
        // a, (-infinity < a < +infinity)
        if (coefficient != 0 && exponent == 0) {
            if (coefficient - (int) coefficient != 0)
                System.out.print(coefficient);
            else
                System.out.print((int)coefficient);
        }

        if (coefficient == 0)
            System.out.print("0");
    }

    public double getCoefficient() {
        return coefficient;
    }

    public double getExponent() {
        return exponent;
    }
}