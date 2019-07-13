/*
 *      An expression consists of two or more operands.
 * 
 *  What this expression class can do:
 *  1. Identify operands (coefficient and exponent) from an expression.
 *  2. Standardize and simplify expression.
 *  3. Calculate value of x.
 */ 

package MathematicalExpression;

import java.util.LinkedList;

public class Expression{
    private LinkedList<Operand> listOfOperands;
    private int size;

    public Expression() {
        listOfOperands = new LinkedList<Operand> ();
        size = listOfOperands.size();
    }

    public Expression(Expression expression) {
        listOfOperands = expression.listOfOperands;
        size = expression.size;
    }

    // copy data from newExpression to current instance of expression
    public void copy(Expression newExpression) {
        listOfOperands = newExpression.listOfOperands;
        size = newExpression.size;
    }

    // add a new operand A to expression
    public void addOperand(Operand A) {
        listOfOperands.add(A);
        size = listOfOperands.size();
    }

    // print all operands
    public void printOperands() { 
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                listOfOperands.get(i).printOperand();
                System.out.print(", ");
            } else {
                listOfOperands.get(i).printOperand();
            }
        }
    }

    // print all operands in the form of mathematical expression
    public void printExpression() {
        Operand temp = new Operand();
        
        listOfOperands.get(0).printOperand();
        for (int i = 1; i < size; i++) {
            if (listOfOperands.get(i).getCoefficient() > 0) {
                System.out.print("+ ");
                listOfOperands.get(i).printOperand();
                System.out.print(" ");
            } else if (listOfOperands.get(i).getCoefficient() == 0) {
                System.out.print("");
            } else {
                double currentCoefficient = Math.abs(listOfOperands.get(i).getCoefficient());
                int currentExponent = listOfOperands.get(i).getExponent();
                
                temp.copy(new Operand(currentCoefficient, currentExponent));
                
                System.out.print("- ");
                temp.printOperand();
                System.out.print(" ");
            }
        }
    }

    // sort all operands in expression decreasingly by operands' exponents
    public static Expression standardizeExpression(Expression expression) {
        Operand buffer = new Operand();

        // first scan to find operands have coefficient larger than 0
        for (int i = 0; i < expression.size - 1; i++) {
            for (int k = i + 1; k < expression.size; k++) {
                // if 2 operands have exponents larger than 0
                if (expression.listOfOperands.get(k).getExponent() > expression.listOfOperands.get(i).getExponent()) {
                    // this is just a swapping action between 2 operands in a linked list expression
                    buffer.copy(expression.listOfOperands.get(i));
                    expression.listOfOperands.get(i).copy(expression.listOfOperands.get(k));
                    expression.listOfOperands.get(k).copy(buffer);
                }
            }
        }

        // second scan to find operands have coefficient equal to 0
        for (int i = 0; i < expression.size - 1; i++) {
            for (int k = i + 1; k < expression.size; k++) {
                // if 1 of the operands has the coefficient equal to 0
                if (expression.listOfOperands.get(i).getCoefficient() == 0 && expression.listOfOperands.get(k).getCoefficient() > 0) {
                    // this is just a swapping action between 2 operands in a linked list expression
                    buffer.copy(expression.listOfOperands.get(i));
                    expression.listOfOperands.get(i).copy(expression.listOfOperands.get(k));
                    expression.listOfOperands.get(k).copy(buffer);
                }
            }
        }

        return expression;
    }

    // simplify standardized expression and return simplified expression
    public static Expression simplifyExpression(Expression rawExpression) {
        Expression standardizedExpression = standardizeExpression(rawExpression),
                   simplifiedExpression = new Expression();

        int currentExponent;
        double currentCoefficient;

        int i = 0;
        while (i < standardizedExpression.size) {
            currentCoefficient = 0;
            currentExponent = standardizedExpression.listOfOperands.get(i).getExponent();
            while (currentExponent == standardizedExpression.listOfOperands.get(i).getExponent()) {
                currentCoefficient += standardizedExpression.listOfOperands.get(i).getCoefficient();
                i++;

                // this line is meant to stop variable i from getting larger than the size of standardizedExpression
                if (i == standardizedExpression.size)
                    break;
            }
            simplifiedExpression.addOperand(new Operand(currentCoefficient, currentExponent));
        }

        return simplifiedExpression;
    }
}