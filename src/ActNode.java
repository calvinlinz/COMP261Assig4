import java.util.Scanner;

public class ActNode implements RobotProgramNode{

    public static final String TURNL = "turnL";
    public static final String TURNR = "turnR";
    public static final String MOVE = "move";
    public static final String TAKEFUEL = "takeFuel";
    public static final String WAIT = "wait";

    String actionType;
    
    public ActNode(Scanner s){
        String str = s.next();
        switch(str){
            case MOVE:
                break;
            case TURNL:
                break;
            case TURNR:
                break;
            case TAKEFUEL:
                break;
            case WAIT: 
                break;
            default:
                Parser.fail(" fail", s);
        }
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
                try {
                    robot.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

}
