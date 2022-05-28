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
        for(RobotProgramNode node : q){
            node.execute(robot);
        } 
    
     
    }
    
    public String toString(){
        String string = "";
        for(RobotProgramNode node : q){
            string += node.toString();
        }
        return string;
    }
}
