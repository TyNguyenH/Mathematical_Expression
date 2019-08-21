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

public class Expression {
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

    public void copy(Expression newExpression) {
        listOfOperands = newExpression.listOfOperands;
        size = newExpression.size;
    }

    public static Expression parseExpression(String inputString) {
        // remove unnecessary spaces
        inputString = inputString.replace(" ", "");
        
        // get all operators in inputString
        LinkedList<String> operators = new LinkedList<String> ();
        for (int i = 0; i < inputString.length(); i++) {
            if (inputString.charAt(i) == '+' || inputString.charAt(i) == '-') {
                operators.add(String.valueOf(inputString.charAt(i)));
            }
        }
    
        // replace all operators with spaces
        inputString = inputString.replace("-", " ");
        inputString = inputString.replace("+", " ");

        inputString = inputString.trim();

        // get all operands in inputString after it has been proccessed
        String operands[] = inputString.split(" ");
       
        // concatenate operators in front of operands
        if (operands.length == operators.size()) {
            for (int i = 0; i < operands.length; i++) {
                operands[i] = operators.get(i) + operands[i];
            }
        } else {
            if (operands.length > operators.size()) {
                for (int i = 0; i < operators.size(); i++) {
                    operands[i + 1] = operators.get(i) + operands[i + 1];
                }   
            }
        }
      
        // create new expression
        Expression expression = new Expression();
        for (int i = 0; i < operands.length; i++) {
            expression.addOperand(Operand.parseOperand(operands[i]));
        }

        return expression;
    }

    public void addOperand(Operand A) {
        listOfOperands.add(A);
        size = listOfOperands.size();
    }

    // print only all operands
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

        // print first operand
        listOfOperands.get(0).printOperand();
        System.out.print(" ");
        
        // print from second operand
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
        Operand temp = new Operand();

        int sizeOfExpression = expression.size;

        // first scan to find operands have coefficient larger than 0
        for (int i = 0; i < sizeOfExpression - 1; i++) {
            for (int k = i + 1; k < sizeOfExpression; k++) {
                // if 2 operands have exponents larger than 0
                if (expression.listOfOperands.get(k).getExponent() > expression.listOfOperands.get(i).getExponent()) {
                    // this is just a swapping data action between 2 operands in a linked list expression
                    temp.copy(expression.listOfOperands.get(i));
                    expression.listOfOperands.get(i).copy(expression.listOfOperands.get(k));
                    expression.listOfOperands.get(k).copy(temp);
                }
            }
        }

        // second scan to find operands have coefficient equal to 0
        for (int i = 0; i < sizeOfExpression - 1; i++) {
            for (int k = i + 1; k < sizeOfExpression; k++) {
                // if 1 of the operands has the coefficient equal to 0
                if (expression.listOfOperands.get(i).getCoefficient() == 0 && expression.listOfOperands.get(k).getCoefficient() > 0) {
                    // this is just a swapping data action between 2 operands in a linked list expression
                    temp.copy(expression.listOfOperands.get(i));
                    expression.listOfOperands.get(i).copy(expression.listOfOperands.get(k));
                    expression.listOfOperands.get(k).copy(temp);
                }
            }
        }

        return expression;
    }

    // simplify standardized expression and return simplified expression
    public static Expression simplifyExpression(Expression rawExpression) {
        Expression standardizedExpression = standardizeExpression(rawExpression);
        Expression simplifiedExpression = new Expression();

        double currentCoefficient;
        int currentExponent;

        int i = 0;
        while (i < standardizedExpression.size) {
            currentCoefficient = 0;
            currentExponent = standardizedExpression.listOfOperands.get(i).getExponent();
            
            while (i < standardizedExpression.size && currentExponent == standardizedExpression.listOfOperands.get(i).getExponent()) {
                currentCoefficient += standardizedExpression.listOfOperands.get(i).getCoefficient();
                i++;
            }
            
            simplifiedExpression.addOperand(new Operand(currentCoefficient, currentExponent));
        }

        return simplifiedExpression;
    }

    public LinkedList<Operand> getListOfOperands() {
        return listOfOperands;
    }
}