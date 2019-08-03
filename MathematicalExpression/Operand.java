/*
 *              ax^n,
 * 
 *      where
 *          a is coefficient,
 *          x is variable,
 *          n is exponent.
 */

package MathematicalExpression;

import java.text.DecimalFormat;

public class Operand {
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

    // copy data from operand A to current instance of operand
    public void copy(Operand A) {
        this.coefficient = A.coefficient;
        this.exponent = A.exponent;
    }

    public static Operand parseOperand(String inputString) {
        if (inputString.length() == 0 || (!inputString.contains("x") && !inputString.contains("^"))) {
            return null;
        } else {
            String coefficient = "";
            String exponent = "";

            // ax^n
            if (inputString.contains("^")) {
                int indexOfX = inputString.indexOf("x");
                // get coefficient of inputString
                for (int i = 0; i < indexOfX; i++) {
                    if (inputString.charAt(i) == ' ') {
                        i++;
                    } else {
                        coefficient += String.valueOf(inputString.charAt(i));
                    }
                }
                if (coefficient.length() == 0) {
                    coefficient = "1";
                }

                // get exponet of inputString
                for (int i = (indexOfX += 2); i < inputString.length(); i++) {
                    if (inputString.charAt(i) == ' ') {
                        i++;
                    } else {
                        exponent += String.valueOf(inputString.charAt(i));
                    }
                }
            }
            // ax
            else if (inputString.contains("x")) {
                int indexOfX = inputString.indexOf("x");
                // get coefficient
                for (int i = 0; i < indexOfX; i++) {
                    if (inputString.charAt(i) == ' ') {
                        i++;
                    } else {
                        coefficient += String.valueOf(inputString.charAt(i));
                    }
                }
                if (coefficient.length() == 0) {
                    coefficient = "1";
                }

                exponent = "1";
            }
            // a
            else {
                for (int i = 0; i < inputString.length(); i++) {
                    if (inputString.charAt(i) == ' ') {
                        i++;
                    } else {
                        coefficient += String.valueOf(inputString.charAt(i));
                    }
                }
                if (coefficient.length() == 0) {
                    coefficient = "1";
                }

                exponent = "0";
            }

            return new Operand(Double.parseDouble(coefficient), Integer.parseInt(exponent));
        }
    }

    // print operand in the form of ax^n
    public void printOperand() {
        DecimalFormat formatedCoefficient = new DecimalFormat("0.##");
        
        // ax^n, (-infinity < a < +infinity, -infinity < n < +infinity)
        if (coefficient != 0 && exponent != 0) {
            // ax, (-infinity < a < +infinity)
            if (coefficient != 0 && exponent == 1) {
                // x
                if (coefficient == 1 && exponent == 1) {
                    System.out.print("x");
                } 
                // ax
                else {
                    if (Math.abs(coefficient - (int) coefficient) > 0) {
                        System.out.print("(" + formatedCoefficient.format(coefficient) + ")" + "x");
                    } else {
                        System.out.print((int) coefficient + "x");
                    }
                }                
            }

            // x^n, (1 < n < +infinity)
            if (coefficient == 1 && exponent > 1) {
                System.out.print("x^" + exponent);
            }
            
            // ax^n, (-infinity < a < +infinity, 0 < n < +infinity)
            if (coefficient != 0  && coefficient != 1  && exponent > 1) {
                if (Math.abs(coefficient - (int) coefficient) > 0) {
                    System.out.print("(" + formatedCoefficient.format(coefficient) + ")");
                } else {
                    System.out.print((int) coefficient);
                }

                System.out.print("x^");

                System.out.print(exponent);
            }
        }
        
        // a, (-infinity < a < +infinity)
        if (coefficient != 0 && exponent == 0) {
            if (Math.abs(coefficient - (int) coefficient) > 0) {
                System.out.print("(" + formatedCoefficient.format(coefficient) + ")");
            } else {
                System.out.print((int)coefficient);
            }
        }

        // 0
        if (coefficient == 0) {
            System.out.print("0");    
        }
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }
}