public class Sen implements RobotEvaluateNode<Integer>{
    public static final String fuelLeft = "fuelLeft";
    public static final String oppLR = "oppLR";
    public static final String oppFB = "oppFB";
    public static final String numBarrels = "numBarrels";
    public static final String barrelLR = "barrelLR";
    public static final String barrelFB = "barrelFB";
    public static final String wallDist = "wallDist";

 

    String sen = null;
    
    public Sen(String str){
        this.sen = str;
    }

    public Integer evaluate(Robot robot) {
        // TODO Auto-generated method stub
        switch(this.sen){
            case fuelLeft:
                return robot.getFuel();
            case oppLR:
                return robot.getOpponentLR();
            case oppFB:
                return robot.getOpponentFB();
            case numBarrels:
                return robot.numBarrels();
            case barrelLR: 
                return robot.getClosestBarrelLR();
            case barrelFB:
                return robot.getClosestBarrelFB();
            case wallDist: 
                return robot.getDistanceToWall();
        }
        
        return 0;

    }

    public String toString(){
        return sen;
    }

    
}
