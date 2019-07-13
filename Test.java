import MathematicalExpression.*;

class Test {
    // public static Operand generateRandomOperand() {

    // }
    
    public static void main(String[] args) {
        System.out.print(".--------------------------------------------------------." + "\n"
                    +    "| Operand format:      ax^n                              |" + "\n"
                    +    "| Expression format:   ax^m +(-) bx^n +(-) ... +(-) cx^p |" + "\n"
                    +    "|                          a, b, c, ... are real numbers |" + "\n"
                    +    "|                          m, n, p, ... are integers     |" + "\n"
                    +    "'--------------------------------------------------------'" + "\n");
        
        Operand arrayOfOperands[] = new Operand[10];
        
        arrayOfOperands[0] = new Operand(1.1, 2);
        arrayOfOperands[1] = new Operand(3, 1);
        arrayOfOperands[2] = new Operand(5.6, 1);
        arrayOfOperands[3] = new Operand(6, 3);
        arrayOfOperands[4] = new Operand(-7.9, 3);
        arrayOfOperands[5] = new Operand(2, 2);
        arrayOfOperands[6] = new Operand(4.2, 4);
        arrayOfOperands[7] = new Operand(0, 1);
        arrayOfOperands[8] = new Operand(-9, 5);
        arrayOfOperands[9] = new Operand(0.9, 5);
        
        Expression expression = new Expression();
        for (int i = 0; i < 10; i++) {
            expression.addOperand(arrayOfOperands[i]);
        }
        
        System.out.print("\n" + "Original input: ");
        expression.printExpression();        

        Expression standardizedExpression = new Expression(Expression.standardizeExpression(expression));
        System.out.print("\n\n" + "After standardized: ");
        standardizedExpression.printExpression();
        
        Expression simplifiedExpression = new Expression(Expression.standardizeExpression(expression));
        System.out.print("\n\n" + "After simplified: ");
        simplifiedExpression.printExpression();
    }
}