import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private String parkingLotId;
    private List<List<Slot>> slots;

   public ParkingLot(String parkingLotId,int nFloors,int noOfSlotsPerFlr){
       this.parkingLotId = parkingLotId;
       slots =new ArrayList<>();
       for (int i = 0; i<nFloors;i++){
           slots.add(new ArrayList<>());
           List<Slot> floorSlots = slots.get(i);
           floorSlots.add(new Slot("truck"));
           floorSlots.add(new Slot("bike"));
           floorSlots.add(new Slot("bike"));
           for (int j =3; j<noOfSlotsPerFlr;j++){
               slots.get(i).add(new Slot("car"));
           }
       }
   }

   public String parkVehicle(String type, String regNo, String color){
       Vehicle vehicle = new Vehicle(type , regNo , color);
      try {
           for (int i = 0; i < slots.size(); i++) {
               for (int j = 0; j < slots.get(i).size(); j++) {
                   Slot slot = slots.get(i).get(j);
                   if (slot.type == type && slot.vehicle == null) {
                       slot.vehicle = vehicle;
                       slot.ticketId = generateTicketId(i + 1, j + 1);
                       return slot.ticketId;
                   }
               }
           }
       }
      catch (Exception ex){
          System.out.println(ex);
      }

       return null;
   }

   private String generateTicketId(int floor ,int slot){
       return parkingLotId+"_"+floor+"_"+slot;
   }

   public void unPark(String tickId){
     try {
           String[] extract = tickId.split("_");
           int floorIndex = Integer.parseInt(extract[1]) - 1;
           int slotIndex = Integer.parseInt(extract[2]) - 1;

     for (int i = 0; i<slots.size();i++){
         for (int j = 0;j<slots.get(i).size(); j++){
             if(i == floorIndex && j == slotIndex ){
                 Slot slot =slots.get(i).get(j);
                 slot.vehicle=null;
                 slot.ticketId=null;
                 System.out.println("Unparked Vehicle");
             }
         }
     }
     }
     catch (Exception ex){
         System.out.println(ex);
     }
   }

   public void getNoOfOpenSlots(String type){
        int count=0;
        for(List<Slot> floor: slots){
            for(Slot slot: floor){
                if(slot.vehicle == null && slot.type.equals(type)) count++;
            }
        }
       System.out.println("the number of available slots for " +type+" = " +count);

   }

    void displayOpenSlots(String type){
        for(int i=0;i<slots.size();i++){
            for(int j=0;j<slots.get(i).size();j++){
                Slot slot=slots.get(i).get(j);
                if(slot.vehicle == null && slot.type.equals(type))
                    System.out.println("Floor " + (i+1) + " slot " + (j+1));
            }
        }
    }

   public void displayOccupiedSlots(String type){
       for(int i=0;i<slots.size();i++){
           for(int j=0;j<slots.get(i).size();j++){
               Slot slot=slots.get(i).get(j);
               if(slot.vehicle != null && slot.type.equals(type))
                   System.out.println("Floor " + (i+1) + " slot " + (j+1));
           }
       }
   }
}
