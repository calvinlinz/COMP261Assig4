import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;

public class STMT implements RobotProgramNode{

    public static final String endLine = ";";
    Queue<RobotProgramNode> statements = new LinkedList<>();
    public HashSet<String> acts = new HashSet<>();
    RobotProgramNode temp = null;
    static Pattern RELOP = Pattern.compile("lt|gt|eq");
	static Pattern SEN = Pattern.compile("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist");

    String s;


    
    public STMT(String s){
        acts.add("turnL");
        acts.add("turnR");
        acts.add("move");
        acts.add("wait");
        acts.add("takeFuel");
        acts.add("shieldOn");
        acts.add("turnAround");
        acts.add("shieldOff");
       this.s = s;
    }

    public Boolean isStatement(){
        if(checkAction() || checkLoop() || checkIf() || checkWhile() || checkRelop() || checkSen() || isOperator() || isCond() || checkElse() || checkAction2()){
            return true;
        }
        else return false;
    }
    public Boolean isOperator(){
        if(s.equals("add") || s.equals("mul") || s.equals("sub") || s.equals("div")){
            return true;
        }
        else return false;
    }
    public Boolean isCond(){
        if(s.equals("and") || s.equals("or")){
            return true;
        }
        else return false;
    }

    public Boolean checkAction(){
       if(acts.contains(this.s)){
          return true;
       }
       return false;
    }

    public Boolean checkAction2(){
        if(this.s.contains("move") || this.s.contains("wait")){
            return true;
        }
        return false;
    }

    public Boolean checkElse(){
        if(this.s.equals("else")){
           return true;
        }
        return false;
     }
 

    public Boolean checkRelop(){
        Matcher rt = RELOP.matcher(s);
        if(rt.matches()){
           return true;
        }
        return false;
     }
     public Boolean checkSen(){
        Matcher st = SEN.matcher(s);
        if(st.matches()){
           return true;
        }
        return false;
     }
    public Boolean checkLoop(){
        if(this.s.equals("loop")){
           return true;
        }
        return false;
     }

    public Boolean checkIf(){
        if(this.s.equals("if")){
            return true;
        }
        return false;
    }
    public Boolean checkWhile(){
        if(this.s.equals("while")){
            return true;
        }
        return false;
    }

    public String toString(){
        return s;
    }


        


    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        while(!statements.isEmpty()){
            statements.poll().equals(robot);
        }
        
    }
}