public class Cond implements Conditions{
    
    private int num;
    private Relop relop;
    private Sen sen;
    private int senValue;
    
    public Cond(Relop relop, Sen sen,int num){
        this.num = num;
        this.sen = sen;
        this.relop = relop;
    }
    
    @Override
    public boolean evaluate(Robot robot){
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

  
    
    // public String toString(){
    //     return "if(" + cond.toString() + "){" + sen.toString() + "}";
    // }
    
    
}
