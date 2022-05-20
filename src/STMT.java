import java.util.Scanner;

public class STMT implements RobotProgramNode{

    public static final String endLine = ";";

    String statement;
    
    public STMT(Scanner s){
        String str = s.next();
        if(!str.equals(";")){
            Parser.fail("STMT Fail", s);
        }
        this.statement = str;

    }

    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
       
        
    }
}