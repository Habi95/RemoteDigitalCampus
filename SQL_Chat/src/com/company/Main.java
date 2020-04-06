package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        User1 user1 = new User1();
        User2 user2 = new User2();
        boolean chaating = true;




        System.out.println("Welcome to the Messenger Chat :D\nwhich account you want to use?");
        System.out.println("Peter or Luca?\npress 1 for Peter and 2 for Luca");
        String tempString = scanner.nextLine();

        if (tempString.equalsIgnoreCase("1")) {
            while (chaating == true) {

                user2.writeMessage();
                System.out.println(" ");
                user2.readMessage();
                System.out.println(" ");
            }

        } else if (tempString.equalsIgnoreCase("2")) {

            while (chaating == true) {
                user1.writeMessage();
                System.out.println(" ");
                user1.readMessage();
                System.out.println(" ");
            }

        }


//        while (chaating == true){
//
//            try {
//
//                server.readMessage();
//                user1.writeMessage();
//                Thread.sleep(1000);
//                server.readMessage();
//                user2.writeMessage();
//                Thread.sleep(1000);
//                server.readMessage();
//                } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }





    }
}
