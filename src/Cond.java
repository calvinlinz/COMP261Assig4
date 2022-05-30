public class Cond implements RobotEvaluateNode<Boolean>{
    
    private int num;
    private Relop relop;
    private Sen sen;
    private int senValue;
    private OP op;
    
    public Cond(Relop relop, Sen sen,int num){
        this.num = num;
        this.sen = sen;
        this.relop = relop;
    }

    public void setOP (OP op){
        this.op = op;
    }
    
    @Override
    public Boolean evaluate(Robot robot){
        senValue = sen.evaluate(robot);
        if(relop.toString().equals("eq")){
            if(num == senValue){
                return true;
            }
        }
        if(relop.toString().equals("gt")){
            if(senValue > num){
                return true;
            }
        }
        if(relop.toString().equals("lt")){
            if(senValue < num){
                return true;
            }
        }
        return false;
    }

  
    
    public String toString(){
        return relop.toString() + "(" + sen.toString() + "," + num + ")";
    }
    
    
}
