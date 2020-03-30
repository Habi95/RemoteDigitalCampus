public enum Color {

    BLACK ("Black"),
    BLUE("Blue"),
    GRAY("Gray"),
    RED("Red"),
    WHITE("White");

private String name;

    Color(String colorName) {
        this.name = colorName;
    }

    public String getName() {
        return name;
    }
}
