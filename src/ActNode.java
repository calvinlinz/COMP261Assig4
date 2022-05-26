import java.util.HashSet;

public class ActNode implements RobotProgramNode{
    public HashSet<String> acts = new HashSet<>();

    public static final String TURNL = "turnL";
    public static final String TURNR = "turnR";
    public static final String MOVE = "move";
    public static final String TAKEFUEL = "takeFuel";
    public static final String WAIT = "wait";

 

    String actionType = null;
    
    public ActNode(String str){
        this.actionType = str;
    }


    @Override
    public void execute(Robot robot) {
        // TODO Auto-generated method stub
        switch(this.actionType){
            case MOVE:
                robot.move();
                break;
            case TURNL:
                robot.turnLeft();
                break;
            case TURNR:
                robot.turnRight();
                break;
            case TAKEFUEL:
                robot.takeFuel();
                break;
            case WAIT: 
                robot.idleWait();
                break;
        }
        

    }

    public String toString(){
        return actionType;
    }

}
