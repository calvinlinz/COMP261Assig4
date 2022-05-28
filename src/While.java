import java.util.Queue;

public class While implements RobotProgramNode {
    
    private Conditions cond;
    private RobotProgramNode block;
    
    public While(Conditions cond,RobotProgramNode block){
        this.cond = cond;
        this.block = block;
    }
    
    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        while(cond.evaluate(robot)){
            block.execute(robot);
        }
    }
    
    public String toString(){
        return "while(" + cond.toString() + "){" + sen.toString() + "}";
    }

}
