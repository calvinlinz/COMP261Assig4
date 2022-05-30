public class Cond implements RobotEvaluateNode<Boolean>{

    Cond cond1 = null;
    Cond cond2 = null;
    private Relop relop;
    private Expression ex1;
    private Expression ex2;
    
    public Cond(Relop relop, Expression ex1,Expression ex2){
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.relop = relop;
    }
    public Cond(Relop relop, Cond cond1, Cond cond2){
        this.cond1 = cond1;
        this.cond2 = cond2;
        this.relop = relop;
    }

    


    @Override
    public Boolean evaluate(Robot robot){
        switch(relop.toString()){
            case "and":
                return cond1.evaluate(robot) && cond2.evaluate(robot);
            case "or":
                return cond1.evaluate(robot) || cond2.evaluate(robot);
            case "not":
                return !cond1.evaluate(robot);

        }
           
        if(relop.toString().equals("eq")){
            if(ex2.evaluate(robot) == ex1.evaluate(robot)){
                return true;
            }
        }
        if(relop.toString().equals("gt")){
            if(ex1.evaluate(robot) > ex2.evaluate(robot)){
                return true;
            }
        }
        if(relop.toString().equals("lt")){
            if(ex1.evaluate(robot) < ex2.evaluate(robot)){
                return true;
            }
        }
        return false;
    }
}
