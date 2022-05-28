import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.namespace.QName;

public class STMT implements RobotProgramNode{

    public static final String endLine = ";";
    Queue<RobotProgramNode> statements = new LinkedList<>();
    public HashSet<String> acts = new HashSet<>();
    RobotProgramNode temp = null;

    String s;


    
    public STMT(String s){
        acts.add("turnL");
        acts.add("turnR");
        acts.add("move");
        acts.add("wait");
        acts.add("takeFuel");
       this.s = s;
    }

    public Boolean checkAction(){
       if(acts.contains(this.s)){
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