import java.util.Queue;

public class IfNode implements RobotProgramNode{
        
    private Conditions cond;
    private Queue<RobotProgramNode> block;
    
    public IfNode(Queue<RobotProgramNode> block){
        this.block = block;
    }

    public void setConditions(Conditions cond) {
        this.cond = cond;
    }
    
    @Override
    public void execute(Robot robot) {
        System.out.println("getting to if statment");
        // TODO Auto-generated method stub
        if(cond.evaluate(robot)){
            for(RobotProgramNode node : block){
                System.out.println(node.toString());
                node.execute(robot);
            }
        }
    }
    
        
        public String toString(){
            return "if(" + cond.toString() + "){" + block.toString() + "}";
        }
        
    
}
