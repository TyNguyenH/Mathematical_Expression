import MathematicalExpression.*;

class Test {
    // generate random operand, (-100 < coefficient < 100 && 0 <= exponent < 10)
    public static Operand generateRandomOperand() {
        double coefficient = Math.random() * 100 + -100;
        int exponent = (int) Math.round((Math.random() * 10) + 0);

        return new Operand(coefficient, exponent);
    }
    
    public static void main(String[] args) {
        System.out.print(".--------------------------------------------------------." + "\n"
                    +    "| Operand format:      ax^n                              |" + "\n"
                    +    "| Expression format:   ax^m +(-) bx^n +(-) ... +(-) cx^p |" + "\n"
                    +    "|                          a, b, c, ... are real numbers |" + "\n"
                    +    "|                          m, n, p, ... are integers     |" + "\n"
                    +    "'--------------------------------------------------------'" + "\n");
        
        Operand arrayOfOperands[] = new Operand[10];
        
        arrayOfOperands[0] = Operand.parseOperand("1.1x^2");
        arrayOfOperands[1] = Operand.parseOperand("3x");
        arrayOfOperands[2] = Operand.parseOperand("5.6x");
        arrayOfOperands[3] = Operand.parseOperand("6x^3");
        arrayOfOperands[4] = Operand.parseOperand("-7.9x^3");
        arrayOfOperands[5] = Operand.parseOperand("2x^2");
        arrayOfOperands[6] = Operand.parseOperand("4.2x^4");
        arrayOfOperands[7] = Operand.parseOperand("0x");
        arrayOfOperands[8] = Operand.parseOperand("-9x^5");
        arrayOfOperands[9] = Operand.parseOperand("0.9x^5");
        
        Expression expression = new Expression();
        for (int i = 0; i < 10; i++) {
            expression.addOperand(arrayOfOperands[i]);
        }
        
        System.out.print("\n" + "Original input: ");
        expression.printExpression();
    
        Expression standardizedExpression = new Expression(Expression.standardizeExpression(expression));
        System.out.print("\n\n" + "After standardized: ");
        standardizedExpression.printExpression();

        Expression simplifiedExpression = new Expression(Expression.simplifyExpression(expression));
        System.out.print("\n\n" + "After simplified: ");
        simplifiedExpression.printExpression();

        // Operand operand;
        // for (int i = 1; i <= 10; i++) {
        //     operand = generateRandomOperand();
        //     operand.printOperand();
        //     System.out.println();
        // }
    }
}