import java.util.Queue;

public class IfNode implements RobotProgramNode{
        
    private Cond cond;
    private Queue<RobotProgramNode> block;
    private RobotProgramNode elseNode;
    
    public IfNode(Queue<RobotProgramNode> block){
        this.block = block;
    }

    public void setConditions(Cond cond) {
        this.cond = cond;
    }

    public void setElseBlock(RobotProgramNode elseBlock){
        this.elseNode = elseBlock;
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
        // }else{
        //     elseNode.execute(robot);
        // }
    }
    
        
        public String toString(){
            String insert = "";
            for(RobotProgramNode node : block){
                insert += "     "+ node.toString() + ";";
            }
            String str =   "if(" + cond.toString() + "){" + "\n" + insert + "\n}";
    
            return str;
        }
        
    
}
