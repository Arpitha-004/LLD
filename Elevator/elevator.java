import java.util.*;

enum DIR {UP,DOWN}

class request{
    int floor_no;
    DIR direction;
    public request(int floor_no, DIR direction){
        this.floor_no=floor_no;
        this.direction=direction;
    }
    public int getFloor(){
        return floor_no;
    }
    public DIR getDirection(){
        return direction;
    }
}
class floor{
    int floor_no;
    public floor(int floor_no){
        this.floor_no = floor_no;
    }
    public int getFloorNum(){
        return floor_no;
    }
}
class elevatorBox{
    
    int current=0;

    public void moveToFloor(int destination){
        System.out.println("Moving from floor "+current+" towards "+destination);
        
        current = destination;
        System.out.println("Reached "+current);
    }
    
}
class elevatorSystem{
    List<floor> flrs = new ArrayList<>();

    public elevatorSystem(int total) {
        for(int i=0;i<total;i++){
            flrs.add(new floor(i));
        }
    }
    elevatorBox ele = new elevatorBox();

    public void moveAsPerReq(request req){
        System.out.println("User request to Floor "+req.getFloor());
        ele.moveToFloor(req.getFloor());
    }
}
public class elevator {
    public static void main(String[] args) {
        elevatorSystem sys = new elevatorSystem(5);
        sys.moveAsPerReq(new request(5,DIR.UP));
        sys.moveAsPerReq(new request(2,DIR.DOWN));
        sys.moveAsPerReq(new request(8,DIR.UP));
    }
}