public class OP{

    public static final String ADD = "add";
    public static final String SUB = "sub";
    public static final String MUL = "mul";
    public static final String DIV = "div";
    
    public String arg;
    public int num1;
    public int num2;

    public OP(String arg, int num1, int num2){
        this.arg = arg;
        this.num1 = num1;
        this.num2 = num2;
    }

    public String toString(){
        return arg;
    }


    public int evaluate(Robot robot) {
        // TODO Auto-generated method stub
        switch(arg){
            case ADD:
                return num1+num2;
            case SUB:
                return num1 - num2;
            case MUL:
                return num1*num2;
            case DIV:
                return num1/num2;
                default:
                return 0;
        }
    }
    
}
