public enum WheelBrand {
    CONTINENTAL ("Continental"),
    MICHELIN("Michelin"),
    GOODYEAR("GoodYear"),
    FIRESTONE("Firestone");

    private String name;

    WheelBrand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
