
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum VehicleType {
    CAR, BIKE, TRUCK
}
class Vehicle {
    private String licensePlate;
    private VehicleType type;

    Vehicle(String licensePlate,VehicleType type) {
        this.licensePlate=licensePlate;
        this.type=type;
    }
    public String getNum(){
        return licensePlate;
    }
    public VehicleType getType(){
        return type;
    }
}
class Spot{
    private int spotId;
    private VehicleType supportedType;
    private Vehicle parkedV;
    private boolean occupied;

    Spot(int spotId, VehicleType supportedType) {
        this.spotId=spotId;
        this.supportedType=supportedType;
        this.occupied=false;
    }

    public boolean canPark(Vehicle v){
        return !occupied && v.getType() == supportedType;
    }

    public void park(Vehicle v) {
        occupied = true;
        parkedV = v;
    }
    public void remove(){
        parkedV = null;
        occupied = false;
    }
    public boolean isAvailable(){
        return !occupied;
    }
    public int spotId(){
        return spotId;
    }
    
    public Vehicle getParkedVehicle(){
        return parkedV;
    }
}
class Ticket{
    private static int cnt=1;
    private int ticketId;
    private Vehicle v;
    private Spot s;
    private LocalDateTime entryT;

    Ticket(Vehicle v, Spot s) {
        this.ticketId = cnt++;
        this.v = v;
        this.s = s;
        this.entryT = LocalDateTime.now();
    }
    public int getTicketId(){
        return ticketId;
    }
    public Vehicle getVehicle(){
        return v;
    }
    public Spot getSpot(){
        return s;
    }
    public LocalDateTime getEntryTime(){
        return entryT;
    }
}
class parkingLot {
    List<Spot> spots = new ArrayList<>();
    Map<Integer,Ticket> tickets = new HashMap<>();

    public parkingLot(){
        spots.add(new Spot(1, VehicleType.CAR));
        spots.add(new Spot(2, VehicleType.CAR));
        spots.add(new Spot(3, VehicleType.BIKE));
        spots.add(new Spot(4, VehicleType.BIKE));
        spots.add(new Spot(5, VehicleType.BIKE));
        spots.add(new Spot(6, VehicleType.TRUCK));
    }

    public Ticket parkVehicle(Vehicle v){
        for(Spot s:spots){
            if(s.canPark(v)){
                s.park(v);
                Ticket t = new Ticket(v, s);
                tickets.put(t.getTicketId(),t);
                System.out.println("Vehicle parked at Spot " + s.spotId());
                return t;
            }
        }
        System.out.println("Parking Full!");
        return null;
    }

    public void removeVehicle(int tId){
        Ticket t = tickets.get(tId);
        if(t==null){
            System.out.println("Invalid Ticket"); 
            return;
        }
        t.getSpot().remove();
        tickets.remove(tId);
        System.out.println("Vehicle exited from Spot " + t.getSpot().spotId());
    }
    
    public void displayAvailableSpots(){
        System.out.println("\nAvailable Spots:");
        for(Spot s:spots){
            if(s.isAvailable()){
                System.out.println("Spot " + s.spotId());
            }
        }
    }
}
public class  Parking{ 
 
   public static void main(String[] args) { 
 
       parkingLot parkingLot = new parkingLot(); 
 
       Vehicle car1 = new Vehicle("KA01AB1234", VehicleType.CAR); 
       Vehicle bike1 = new Vehicle("KA09XY9999", VehicleType.BIKE); 
       Vehicle truck1 = new Vehicle("KA55TR1111", VehicleType.TRUCK); 
 
       Ticket t1 = parkingLot.parkVehicle(car1); 
       Ticket t2 = parkingLot.parkVehicle(bike1); 
       Ticket t3 = parkingLot.parkVehicle(truck1); 
 
       parkingLot.displayAvailableSpots(); 
 
       parkingLot.removeVehicle(t2.getTicketId()); 
 
       parkingLot.displayAvailableSpots(); 
   } 
} 
