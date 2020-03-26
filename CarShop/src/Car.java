


public class Car implements Cloneable {
    String brand;
    Color color;
    String idNumber;
    int horsePower;
    double fuelCapicity;
    boolean hasSeatHeater;
    boolean keyLess;
    boolean hasNavi;
    Wheel myNewWheels;

    public Car(String brand, Color color, String idNumber, int horsePower, double fuelCapicity, boolean hasSeatHeater, boolean keyLess, boolean hasNavi) {
        this.brand = brand;
        this.color = color;
        this.idNumber = idNumber;
        this.horsePower = horsePower;
        this.fuelCapicity = fuelCapicity;
        this.hasSeatHeater = hasSeatHeater;
        this.keyLess = keyLess;
        this.hasNavi = hasNavi;
    }

    @Override
    protected Car clone() throws CloneNotSupportedException {
        Car myOtherCar = this.clone();



        return (Car) super.clone();
    }

    public void setMyNewWheels(Wheel myNewWheels) {
        this.myNewWheels = myNewWheels;
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
