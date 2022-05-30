import java.util.Queue;

public class IfNode implements RobotProgramNode{
        
    private Cond cond;
    private Queue<RobotProgramNode> block;
    private Queue<RobotProgramNode> elseBlock;
    
    public IfNode(Queue<RobotProgramNode> block){
        this.block = block;
    }

    public void setConditions(Cond cond) {
        this.cond = cond;
    }

    public void setElseBlock(Queue<RobotProgramNode> elseBlock){
        this.elseBlock = elseBlock;
    }
    
    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        if(cond.evaluate(robot)){
            for(RobotProgramNode node : block){
                node.execute(robot);
            }
        }else{
            if(elseBlock != null){
                for(RobotProgramNode node : elseBlock){
                    node.execute(robot);
                }
            }
        }
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
