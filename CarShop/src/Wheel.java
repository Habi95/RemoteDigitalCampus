

public class Wheel implements Cloneable {

    String brand;
    boolean alu;

    public Wheel(String brand, boolean alu) {
        this.brand = brand;
        this.alu = alu;
    }

    @Override
    protected Wheel clone() throws CloneNotSupportedException {
        return (Wheel) super.clone();
    }
}
