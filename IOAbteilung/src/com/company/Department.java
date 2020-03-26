package com.company;


public class Department {
    String departmentName = " ";
    Person employees;
    String parentDepartment= " ";
    Department[] subDepartments = new Department[50];
    Person[] headOf = new Person[10];
    int headCounter = 0;

    int mySubCounter = 0;

    public Department(String departmentName, Person employer) {
        this.departmentName = departmentName;
        this.employees = employer;
        this.headOf[headCounter] = employer;
        this.headCounter++;

    }

    public void print(String tabString) {
        tabString = tabString + "\t";
        for (int i = 0; i < subDepartments.length ; i++) {
            if (subDepartments[i] != null) {
                System.out.println(tabString + subDepartments[i].departmentName  + " (" + departmentName + " " + employees.firstName + ")");
                for (int j = 0; j < subDepartments[i].headOf.length ; j++) {
                    if (subDepartments[i].headOf[j] != null) {
                        System.out.println(tabString + "( " + subDepartments[i].headOf[j].firstName + " )");
                    }
                }
                subDepartments[i].print(tabString);
            }
        }
    }



//    public void print(String tabString) {
//        tabString = tabString + "\t";
////        for (int i = 0; i < subDepartments.length; i++) {
//            if (subDepartments[i] != null) {
//                System.out.println(tabString + subDepartments[i].name + "(" + subDepartments[i].headOfDepartment + ")");
//                for (int j = 0; j < subDepartments[i].employees.length; j++) {
//                    if (subDepartments[i].employees[j] != null) {
//                        System.out.println(tabString + "+ " + subDepartments[i].employees[j].name);
//                    }
//                }
//                subDepartments[i].print(tabString);
//            }
//        }
//    }

    public void addSubDepartment(Department department) {
        this.subDepartments[mySubCounter] = department;
        mySubCounter++;
    }


}
