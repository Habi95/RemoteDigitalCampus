package com.company;

import java.io.IOException;
import java.util.Scanner;

public class PlayDriveCar {
    Scanner scanner = new Scanner(System.in);


    public PlayDriveCar(Car audi, GasStation ggogh) throws IOException {
        System.out.println("how much km you want to drive?");
        int input = scanner.nextInt();
        audi.drive(input);
        audi.goFillUpTheFuel(ggogh);
    }
}
