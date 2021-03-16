/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab21;

import java.util.ArrayList;

/**
 *
 * @author nguoitamxa
 */
public class Main {

    //Show menu
    public static void menu() {
        System.out.println(" 1.	Create");
        System.out.println(" 2.	Find and Sort");
        System.out.println(" 3.	Update/Delete");
        System.out.println(" 4.	Report");
        System.out.println(" 5.	Exit");
        System.out.print(" Enter your choice: ");
    }

    public static void main(String[] args) {
        ArrayList<Student> ls = new ArrayList<>();
        Validation validation = new Validation();
        int countDefault = 0;
        //loop until user want to exit program
        while (true) {
            //Show menu option
            menu();
            int choice = validation.checkInputIntLimit(1, 5);
            switch (choice) {
                case 1:
                    while (countDefault <= 1) {
                        Manager.createStudent(ls);
                        countDefault = Manager.CountStudent(ls);
                    }
                    System.out.print("Do you want to continue (Y/N): ");
                    while (Validation.checkInputYN()) {
                        Manager.createStudent(ls);
                        System.out.print("Do you want to continue (Y/N): ");
                    }
                    break;
                case 2:
                    Manager.findAndSort(ls);
                    break;
                case 3:
                    Manager.updateOrDelete(ls);
                    break;
                case 4:
                    Manager.report(ls);
                    break;
                case 5:
                    return;
            }
        }

    }

}
