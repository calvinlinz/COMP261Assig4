import java.util.Queue;
import java.util.Scanner;

public class Loop implements RobotProgramNode{

    public static final String loopConst = "loop";

    
    Block block = null;

    public Loop(Queue<RobotProgramNode> q){
        block = new Block(q);
    }



    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        while(robot.getFuel()>0){
            block.execute(robot);
        }
        
        
        
    }

    public String toString(){
        return loopConst; 
    }
}
