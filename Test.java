import Operand.*;
import java.util.LinkedList;

class Test {
    // sort expression decreasingly by operands' exponents
    public static LinkedList<Operand> standardizeExpression(LinkedList<Operand> expression) {
        int len = expression.size();
        Operand buffer = new Operand();

        // first scan to find operands have coefficient larger than 0
        for (int i = 0; i < len - 1; i++) {
            for (int k = i + 1; k < len; k++) {
                // if 2 operands have exponents larger than 0
                if (expression.get(k).getExponent() > expression.get(i).getExponent()) {
                    // this is just a swapping action between 2 operands in a linked list expression
                    buffer.copy(expression.get(i));
                    expression.get(i).copy(expression.get(k));
                    expression.get(k).copy(buffer);
                }
            }
        }

        // second scan to find operands have coefficient equal to 0
        for (int i = 0; i < len - 1; i++) {
            for (int k = i + 1; k < len; k++) {
                // if 1 of the operands has the coefficient equal to 0
                if (expression.get(i).getCoefficient() == 0 && expression.get(k).getCoefficient() > 0) {
                    // this is just a swapping action between 2 operands in a linked list expression
                    buffer.copy(expression.get(i));
                    expression.get(i).copy(expression.get(k));
                    expression.get(k).copy(buffer);
                }
            }
        }
        
        return expression;
    }

    // public LinkedList<Operand> simplifyExpression(LinkedList<Operand>
    // rawExpression) {

    // }

    public static void main(String[] args) {
        System.out.print(".------------------------------." + "\n"
                    +    "|   ax^n : (double)x^(int)     |" + "\n"
                    +    "'------------------------------'" + "\n");
        
        Operand arrayOfOperands[] = new Operand[10];
        
        arrayOfOperands[0] = new Operand(1.1, 2);
        arrayOfOperands[1] = new Operand(3, 1);
        arrayOfOperands[2] = new Operand(5.6, 6);
        arrayOfOperands[3] = new Operand(6, 3);
        arrayOfOperands[4] = new Operand(-7.9, 4);
        arrayOfOperands[5] = new Operand(2, 5);
        arrayOfOperands[6] = new Operand(4.2, 8);
        arrayOfOperands[7] = new Operand(0, 7);
        arrayOfOperands[8] = new Operand(-9, 10);
        arrayOfOperands[9] = new Operand(0.9, 9);
        
        LinkedList<Operand> expression = new LinkedList<Operand> ();
        for (int i = 0; i < 10; i++) {
            expression.add(arrayOfOperands[i]);
        }
        
        System.out.print("\n" + "Before sorting: ");
        for (int i = 0; i < arrayOfOperands.length; i++) {
            expression.get(i).printOperand();
            System.out.print(", ");
        }

        expression = standardizeExpression(expression);
        System.out.print("\n" + "After sorting: ");
        for (int i = 0; i < expression.size(); i++) {
            expression.get(i).printOperand();
            System.out.print(", ");
        }
    }
}