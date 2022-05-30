import java.util.HashSet;

public class ActNode implements RobotProgramNode{

    public static final String TURNL = "turnL";
    public static final String TURNR = "turnR";
    public static final String MOVE = "move";
    public static final String TAKEFUEL = "takeFuel";
    public static final String WAIT = "wait";
    public static final String SHIELDON = "shieldOn";
    public static final String SHIELDOFF = "shieldOff";
    public static final String TURNAROUND = "turnAround";

 

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
            case TURNAROUND: 
                robot.turnAround();
                break;
            case SHIELDON:
                robot.setShield(true);
                break;
            case SHIELDOFF: 
                robot.setShield(false);
                break;
        }
        

    }

    public String toString(){
        return actionType;
    }

}
