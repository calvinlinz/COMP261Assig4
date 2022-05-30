import java.util.Queue;

public class WhileNode implements RobotProgramNode {
    
    private Cond cond;
    private Queue<RobotProgramNode> block;
    
    public WhileNode(Queue<RobotProgramNode> block){
        this.block = block;
    }

    public void setConditions(Cond cond) {
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
        String insert = "";
        for(RobotProgramNode node : block){
            insert += "     "+ node.toString() + ";";
        }
        String str =   "while(" + cond.toString() + "){" + "\n" + insert + "\n}";

        return str;
    }

}
