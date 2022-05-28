import java.util.Queue;

public class WhileNode implements RobotProgramNode {
    
    private Conditions cond;
    private Queue<RobotProgramNode> block;
    
    public WhileNode(Queue<RobotProgramNode> block){
        this.block = block;
    }


    public void setConditions(Conditions cond) {
        this.cond = cond;
    }
    
    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        while(cond.evaluate(robot)){
            for(RobotProgramNode node : block){
                System.out.println(node.toString());
                node.execute(robot);
            }
        }
    }
    
    public String toString(){
        return "while(" + cond.toString() + "){" + block.toString() + "}";
    }

}
