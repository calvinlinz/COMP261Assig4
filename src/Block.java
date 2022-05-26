import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Block implements RobotProgramNode {

    Queue<RobotProgramNode> q = new LinkedList<> ();


    public Block(Queue<RobotProgramNode> q){
       this.q = q;
    }

    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
            q.poll().execute(robot);
    }
    
}
