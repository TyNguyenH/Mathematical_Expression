import MathematicalExpression.*;
import java.util.Scanner;

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

        Scanner keyboard = new Scanner(System.in);
        
        // System.out.print(">>> ");
        // 1.1x^2 + 3x + 5.6x +6x^3 -7.9x^3 + 2x^2
        // String expressionInString = keyboard.nextLine();
        
        Expression expression = Expression.parseExpression(" 1.1x^2 +  3x^1 +  5.6x^0 +  6x^3 -  7.9x^3 +   2x^2  +4.2x^4 + 0x-9x^5+   0.9x^5  ");
        // Expression expression = Expression.parseExpression(expressionInString);
        
        System.out.print("\n" + "Processed input: ");
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

        keyboard.close();
    }
}