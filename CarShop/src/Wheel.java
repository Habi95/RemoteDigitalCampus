

public class Wheel implements Cloneable {

    WheelBrand brand;
    boolean alu;

    public Wheel(WheelBrand brand, boolean alu) {
        this.brand = brand;
        this.alu = alu;
    }

    @Override
    protected Wheel clone() throws CloneNotSupportedException {
        return (Wheel) super.clone();
    }

    public WheelBrand getBrand() {
        return brand;
    }

    public void setBrand(WheelBrand brand) {
        this.brand = brand;
    }

    public boolean isAlu() {
        return alu;
    }

    public void setAlu(boolean alu) {
        this.alu = alu;
    }
}
