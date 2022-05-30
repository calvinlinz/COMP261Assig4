import java.util.Queue;

public class ElseNode implements RobotProgramNode{

    public Queue<RobotProgramNode> block;

    public ElseNode(Queue<RobotProgramNode> block){
        this.block = block;
    }
    

    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        for(RobotProgramNode node : block){
            System.out.println(node.toString());
            node.execute(robot);
        }
    }
    
}
