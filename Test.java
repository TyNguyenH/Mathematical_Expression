import Operand.*;

class Test {
    public static void main(String[] args) {
        System.out.print(".------------------------------." + "\n"
                    +    "|   ax^n : (double)x^(int)     |" + "\n"
                    +    "'------------------------------'" + "\n");
        
        Operand a = new Operand(-6.7, 3);
        
        a.printOperand();
    }
}