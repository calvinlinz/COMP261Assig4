public class If implements RobotProgramNode{
        
    private Conditions cond;
    private RobotProgramNode block;
    
    public If(Conditions cond,RobotProgramNode block){
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
    
        
        // public String toString(){
        //     return "if(" + cond.toString() + "){" + sen.toString() + "}";
        // }
        
    
}
