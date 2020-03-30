


public class Car implements Cloneable {
    String brand;
    String model;
    Color color;
    String idNumber;
    int horsePower;
    double fuelCapicity;
    double ustage100km;
    boolean hasSeatHeater;
    boolean keyLess;
    boolean hasNavi;
    Wheel myNewWheels;

    public Car(String brand, String model, Color color, String idNumber, int horsePower, double fuelCapicity, boolean hasSeatHeater, boolean keyLess, boolean hasNavi) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.idNumber = idNumber;
        this.horsePower = horsePower;
        this.fuelCapicity = fuelCapicity;
        this.hasSeatHeater = hasSeatHeater;
        this.keyLess = keyLess;
        this.hasNavi = hasNavi;
        this.ustage100km = horsePower / 100 * fuelCapicity / 7;
    }

    @Override
    protected Car clone() throws CloneNotSupportedException {
        Car myClone = (Car) super.clone();
        Wheel clonedWheel = this.myNewWheels.clone();
        myClone.setMyNewWheels(clonedWheel);
        return myClone;


    }

    public void setMyNewWheels(Wheel myNewWheels) {


        this.myNewWheels = myNewWheels;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setHasSeatHeater(boolean hasSeatHeater) {
        this.hasSeatHeater = hasSeatHeater;
    }

    public void setHasNavi(boolean hasNavi) {
        this.hasNavi = hasNavi;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
