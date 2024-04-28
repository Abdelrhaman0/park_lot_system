public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot("Lot",4,15);
        String ticket1 = parkingLot.parkVehicle("car", "MH-03", "red");
        String ticket2 = parkingLot.parkVehicle("car", "MH-04", "purple");
        parkingLot.displayOccupiedSlots("car");
        parkingLot.getNoOfOpenSlots("car");
        parkingLot.getNoOfOpenSlots("bike");
        parkingLot.getNoOfOpenSlots("truck");
    }
}
