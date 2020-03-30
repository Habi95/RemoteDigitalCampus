import java.io.CharArrayReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class CarDealerShip {

    String name;
    String street;
    String town;
    Owner owner;
    String choiceToBuy;
    ArrayList<Car> carToShow = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    ArrayList<Exception> exceptionArrayList = new ArrayList<>();
    int whichIndex;
    DecimalFormat df = new DecimalFormat("#.##");

    File exFile = new File("C:\\Users\\DCV\\Desktop\\HelloWorld\\RemoteDigitalCampus\\Exceptions.txt");
    FileWriter exFileWriter;

    {
        try {
            exFileWriter = new FileWriter(exFile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public CarDealerShip(String name, String street, String town) {
        this.name = name;
        this.street = street;
        this.town = town;
    }


    public void listArray() {
        for (int i = 0; i < carToShow.size(); i++) {
            System.out.println("Auto in Austellung \n" + carToShow.get(i).brand + " " + carToShow.get(i).model + "\n"
                    + "Ausstattung " + "(" + "Farbe: " + carToShow.get(i).color.getName()
                    + ", PS: " + carToShow.get(i).horsePower +
                    "\n\t\t\t Tankgröße: " + carToShow.get(i).fuelCapicity + "l"
                    +"\n\t\t\t Durchschnittlicher Verbauch : "+ df.format( carToShow.get(i).ustage100km) +"l / 100km"
                    + "\n\t\t\t Sitzheizung: " + carToShow.get(i).hasSeatHeater
                    + "\n\t\t\t Konaktloser Schlüssel: " + carToShow.get(i).keyLess
                    + "\n\t\t\t Navigations System: " + carToShow.get(i).hasNavi
                    + "\n\t\t\t Reifen: " + carToShow.get(i).myNewWheels.brand.getName()
                    +"\n\t\t\t Aluminium Reifen : "+ carToShow.get(i).myNewWheels.alu + "\n");
        }
    }


    public void choiceYourCar(Customer Customer) {
        String tempString;

        listArray();
        System.out.println("Welches Model wollen Sie??");
        choiceToBuy = scanner.nextLine();
        searchInArray(choiceToBuy);
        showCar();
        System.out.println("wollen Sie das Auto?: JA / Nein ");

        tempString = scanner.nextLine();
        if (tempString.equalsIgnoreCase("Nein")) {
            choiceYourCar(Customer);
        }

        changeEquipment();
        System.out.println("willst du noch was ändern? Ja / Nein");
        tempString = scanner.nextLine();
        if (tempString.equalsIgnoreCase("JA")) {
            changeEquipment();

        } else if (tempString.equalsIgnoreCase("nein")) {

                //bestehdendes Auto mit custmorCar vegleichen ob es auf lager ist oder bestellen muss equalc
        }
    }




    public void searchInArray(String choiceToBuy) {


        for (int i = 0; i < carToShow.size() ; i++) {
            if ( carToShow.get(i).model.equalsIgnoreCase(choiceToBuy)){
                this.whichIndex = i;

                }

        }

            }


    public void showCar() {
        System.out.println("Marke + Model \n" + carToShow.get(this.whichIndex).brand + " " + carToShow.get(this.whichIndex).model + "\n"
                + "Ausstattung " + "(" + "Farbe: " + carToShow.get(this.whichIndex).color.getName()
                + ", PS: " + carToShow.get(this.whichIndex).horsePower +
                "\n\t\t\t Tankgröße: " + carToShow.get(this.whichIndex).fuelCapicity + "l"
                +"\n\t\t\t Durchschnittlicher Verbauch : "+ df.format( carToShow.get(this.whichIndex).ustage100km) +"l / 100km"
                + "\n\t\t\t Sitzheizung: " + carToShow.get(this.whichIndex).hasSeatHeater
                + "\n\t\t\t Konaktloser Schlüssel: " + carToShow.get(this.whichIndex).keyLess
                + "\n\t\t\t Navigations System: " + carToShow.get(this.whichIndex).hasNavi
                + "\n\t\t\t Reifen: " + carToShow.get(this.whichIndex).myNewWheels.brand.getName()
                +"\n\t\t\t Aluminium Reifen : "+ carToShow.get(this.whichIndex).myNewWheels.alu + "\n");
    }

    public Car changeEquipment ()  {
        Car customCar = null;
        try {
             customCar = carToShow.get(this.whichIndex).clone();
            customCar.setIdNumber("xxxx");
            System.out.println("Willst du was ändern? Farbe / Sitzheizung / Navi / Reifen");
            String tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("Farbe")) {
                showColorSampleBoard();
                tempString = scanner.nextLine();
                choiceColor(tempString, customCar);
                return customCar;

            } else if (tempString.equalsIgnoreCase("Sitzheizung")) {
                System.out.println("Ja / Nein");
                tempString = scanner.nextLine();
                if (tempString.equalsIgnoreCase("ja")) {
                    customCar.setHasSeatHeater(true);
                    return customCar;
                } else if (tempString.equalsIgnoreCase("nein")) {
                    customCar.setHasSeatHeater(false);
                    return customCar;
                }
            } else if (tempString.equalsIgnoreCase("Navi")) {
                System.out.println("Ja / Nein");
                tempString = scanner.nextLine();
                if (tempString.equalsIgnoreCase("ja")) {
                    customCar.setHasNavi(true);
                    return customCar;
                } else if (tempString.equalsIgnoreCase("nein")) {
                    customCar.setHasNavi(false);
                    return customCar;
                }
            } else if (tempString.equalsIgnoreCase("Reifen")) {
                showWheels();
                tempString = scanner.nextLine();
                choiceWheels(tempString,customCar);
                return customCar;
            }

          



        } catch (CloneNotSupportedException ex ) {
            exceptionArrayList.add(ex);
            try {
                exFileWriter.write(String.valueOf(ex));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return customCar;
    }

    public void showWheels() {
        System.out.println("folgende Reifen haben wir mit Alu oder Stahlfelgen " + WheelBrand.CONTINENTAL.getName() + ", " + WheelBrand.MICHELIN.getName() + ", "
                + WheelBrand.GOODYEAR.getName() + ", "+ WheelBrand.FIRESTONE.getName() + ", ");
    }

    public Car choiceWheels ( String choice, Car customCar) {
        String tempString;
        if (choice.equalsIgnoreCase(WheelBrand.CONTINENTAL.getName())) {
            System.out.println("Alu oder Stahl felgen?");
            tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("Alu")) {
                customCar.myNewWheels.setBrand(WheelBrand.CONTINENTAL);
                customCar.myNewWheels.setAlu(true);
                return customCar;
            } else if (tempString.equalsIgnoreCase("stahl")){
                customCar.myNewWheels.setBrand(WheelBrand.CONTINENTAL);
                customCar.myNewWheels.setAlu(false);
                return customCar;
            }
        } else if (choice.equalsIgnoreCase(WheelBrand.MICHELIN.getName())) {
            System.out.println("Alu oder Stahl felgen?");
            tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("Alu")) {
                customCar.myNewWheels.setBrand(WheelBrand.MICHELIN);
                customCar.myNewWheels.setAlu(true);
                return customCar;
            } else if (tempString.equalsIgnoreCase("stahl")){
                customCar.myNewWheels.setBrand(WheelBrand.MICHELIN);
                customCar.myNewWheels.setAlu(false);
                return customCar;
            }
        } else if (choice.equalsIgnoreCase(WheelBrand.GOODYEAR.getName())) {
            System.out.println("Alu oder Stahl felgen?");
            tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("Alu")) {
                customCar.myNewWheels.setBrand(WheelBrand.GOODYEAR);
                customCar.myNewWheels.setAlu(true);
                return customCar;
            } else if (tempString.equalsIgnoreCase("stahl")){
                customCar.myNewWheels.setBrand(WheelBrand.GOODYEAR);
                customCar.myNewWheels.setAlu(false);
                return customCar;
            }
        } else if (choice.equalsIgnoreCase(WheelBrand.FIRESTONE.getName())) {
            System.out.println("Alu oder Stahl felgen?");
            tempString = scanner.nextLine();
            if (tempString.equalsIgnoreCase("Alu")) {
                customCar.myNewWheels.setBrand(WheelBrand.FIRESTONE);
                customCar.myNewWheels.setAlu(true);
                return customCar;
            } else if (tempString.equalsIgnoreCase("stahl")){
                customCar.myNewWheels.setBrand(WheelBrand.FIRESTONE);
                customCar.myNewWheels.setAlu(false);
                return customCar;
            }
        }

        return customCar;

    }





    public void addToShowRoom(Car car) {
        carToShow.add(car);
    }

    public void showColorSampleBoard() {
        System.out.println("Farben zur auswahl: " + Color.BLACK.getName() + ", " + Color.BLUE.getName()
                + ", " + Color.GRAY.getName() + ", " + Color.RED.getName() + ", " + Color.WHITE.getName());
    }

    public Car choiceColor (String choice, Car customCar) {

        if (choice.equalsIgnoreCase(Color.BLACK.getName())) {
            customCar.setColor(Color.BLACK);
        } else  if (choice.equalsIgnoreCase(Color.BLUE.getName())) {
            customCar.setColor(Color.BLACK);
        } else  if (choice.equalsIgnoreCase(Color.GRAY.getName())) {
            customCar.setColor(Color.BLACK);
        } else  if (choice.equalsIgnoreCase(Color.RED.getName())) {
            customCar.setColor(Color.BLACK);
        } else  if (choice.equalsIgnoreCase(Color.WHITE.getName())) {
            customCar.setColor(Color.BLACK);
        }

            return customCar;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}


