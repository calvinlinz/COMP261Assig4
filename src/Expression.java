public class Expression implements RobotEvaluateNode<Integer>{
    public int value = Integer.MIN_VALUE;
    public Sen sen = null;
    public Operators op = null;

    public Expression(Sen sen){
        this.sen = sen;
    }

    public Expression(int value){
        this.value = value;
    }

    public Expression(Operators op){
        this.op = op;
    }

    @Override
    public Integer evaluate(Robot robot) {
        // TODO Auto-generated method stub
        if(sen!= null){
            return sen.evaluate(robot);
        }
        else if(op != null){
            return op.evaluate(robot);
        }
        return value;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        if(sen!= null){
            return sen.toString();
        }
        else if(op != null){
            return op.toString();
        }
        return Integer.toString(value);
    }
    
}
