public class Operators implements RobotEvaluateNode<Integer>{

    private String op;
    private Expression ex1 = null;
    private Expression ex2 = null;

    public Operators(String op, Expression ex1, Expression ex2){
        this.op = op;
        this.ex1 = ex1;
        this.ex2 = ex2;
    }


    @Override
    public Integer evaluate(Robot robot) {
        // TODO Auto-generated method stub
        // if its op return that etc
        if(op.equals("add")){
            return ex1.evaluate(robot) + ex2.evaluate(robot);
        }
        if(op.equals("sub")){
            return ex1.evaluate(robot) - ex2.evaluate(robot);
        }
        if(op.equals("div")){
            return ex1.evaluate(robot) / ex2.evaluate(robot);
        }
        if(op.equals("mul")){
            return ex1.evaluate(robot) * ex2.evaluate(robot);
        }
        return null;
    }
    
}
