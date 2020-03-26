package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {



        File departmentFile = new File("C:\\Users\\DCV\\Desktop\\CodePapes\\Abteilungen1.txt");
        File test = new File("C:\\Users\\DCV\\Desktop\\CodePapes\\Abteilungen");
        FileReader fileReader = new FileReader(departmentFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Department[] departments = new Department[10];
        Person[] people = new Person[10];
        String parentDepartment = null;

        int depCounter = 0;
        int perCounter = 0;
        int headCounter = 0;

        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitt = line.split(";");
                String name = splitt[0];
                String departmentName = splitt[1];
                 parentDepartment = null;
                if (splitt.length > 2) {
                    parentDepartment = splitt[2];

                }
                Person person = new Person(name,departmentName);
                people[perCounter] = person;
                perCounter++;
               Department department = new Department(person.department,person);
               departments[depCounter] = department;
                depCounter++;


                for (int i = 0; i < departments.length ; i++) {
                    if (departments[i] != null && departments[i].departmentName.equalsIgnoreCase(parentDepartment)){
                        departments[i].addSubDepartment(department);
                    }
                }
//                for (Department dep : departments){
//                    if (dep != null && dep.departmentName.equals(parentDepartment)) {
//                        dep.addSubDepartment(department);
//                    }
//                }
            }
        departments[0].print("");

        } catch (Exception ex) {
            System.out.println("hmm");
        }

//        try {
//
//
//        }
//
////            for (int i = 0; i < departments.length ; i++) {
////                if (departments[i] != null) {
////                    System.out.println(departments[i].departmentName + ", " + departments[i].employees.firstName);
////                }
////            }
//
//        } catch (Exception ex) {
//            System.out.println("hmm......");
//        }





        System.out.println("Deeebug <3");


    }
}
