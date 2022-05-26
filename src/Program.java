import java.util.LinkedList;
import java.util.Queue;

public class Program implements RobotProgramNode {
    
    Queue<RobotProgramNode> q = new LinkedList<>();

    public Program(Queue<RobotProgramNode> q){
        this.q = q;
    }

    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        while(!q.isEmpty()){
            q.poll().execute(robot);
        }
    }
    
}
