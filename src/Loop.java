import java.util.Scanner;

public class Loop implements RobotProgramNode{

    public static final String loopConst = "loop";

    String loop;

    public Loop (Scanner s){
        String str = s.next();
        if(!str.equals(loopConst)){
            Parser.fail("Failed To Read Loop", s);
        }
        this.loop = str;
    }
    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
            
        
    }
}
