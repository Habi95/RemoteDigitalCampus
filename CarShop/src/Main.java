public class Main {

    public static void main(String[] args) {

        Customer hans = new Customer("Peter Fritz", 42,20_000);

        CarDealerShip carWorld = new CarDealerShip("CarWorld", "WaldStr. 53" , "Götzis");
        Owner carWorldOwner = new Owner("Hans Meyer" , 58, 100_000);
        carWorld.setOwner(carWorldOwner);

        Wheel michelin = new Wheel(WheelBrand.MICHELIN , true);
        Car mazda = new Car("Mazda", "3 sport",Color.BLACK,"35871002",120,45,true,true,true);
        mazda.setMyNewWheels(michelin);
        carWorld.addToShowRoom(mazda);

        try {
            Car myOtherMazda = mazda.clone();
            myOtherMazda.setIdNumber("33587006");
            carWorld.addToShowRoom(myOtherMazda);

            Car mazdaCX3 = mazda.clone();
            mazdaCX3.setIdNumber("3208967");
            mazdaCX3.setModel("CX 3");
            mazdaCX3.myNewWheels.setBrand(WheelBrand.CONTINENTAL);
            carWorld.addToShowRoom(mazdaCX3);
            System.out.println();

        } catch (CloneNotSupportedException ex) {
            System.out.println("hmm..");
        }


        carWorld.choiceYourCar(hans);






        System.out.println("");

    }
}
