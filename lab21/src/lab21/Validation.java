/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab21;

import java.util.ArrayList;
import java.util.Scanner;
import static lab21.Manager.ListById;

/**
 *
 * @author nguoitamxa
 */
public class Validation {

    private final static Scanner in = new Scanner(System.in);

    //check user input number limit
    public static int checkInputIntLimit(int min, int max) {
        //loop until user input correct
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();

                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in rage [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }

    }

    //check user input Int
    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine());
                return result;
            } catch (NumberFormatException ex) {
                System.err.println("Re-input");
            }
        }
    }

    //check user input string
    public static String InputString() {
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    //check user input yes/ no
    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            String result = InputString();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    //check user input u / d
    public static boolean checkInputUD() {
        //loop until user input correct
        while (true) {
            String result = InputString();
            //return true if user input u/U
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            //return false if user input d/D
            if (result.equalsIgnoreCase("D")) {
                return false;
            }
            System.err.println("Please input u/U or d/D.");
            System.out.print("Enter again: ");
        }
    }

    //check user input course
    public static String checkInputCourse() {
        //loop until user input correct
        while (true) {
            System.out.print("Enter course name: ");
            String result = InputString();
            //check input course in java/ .net/ c/c++
            if (result.equalsIgnoreCase("java")
                    || result.equalsIgnoreCase(".net")
                    || result.equalsIgnoreCase("c/c++")) {
                return result;
            }
            System.err.println("There are only three courses: Java, .Net, C/C++");
            System.out.print("Enter again: ");
        }
    }
//
//    public boolean isYear(String year) {
//
//        if (true) {
//
//            return true;
//        }
//        return false;
//    }

    public static boolean checkInputSemester(String input, String season) {
        if (input.indexOf(season) == 0) {
            for (int i = season.length(); i < input.length(); i++) {
                if (!Character.isDigit(input.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String checkInputSemester() {
        //loop until user input correct
        while (true) {
            System.out.print("Enter semester: ");
            String result = InputString();
            if (!checkInputSemester(result, "spring") && !checkInputSemester(result, "summer") && !checkInputSemester(result, "fall") && !checkInputSemester(result, "winter")) {
                System.err.println("There are only four semester: Spring, Summer, Fall, Winter");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    // check input Semester
//    public static String checkInputSemester() {
//        //loop until user input correct
//        while (true) {
//            System.out.println("Enter semester: ");
//            String result = InputString();
//            if ((result.startsWith("spring")
//                    || result.startsWith("summer")
//                    || result.startsWith("fall")
//                    || result.startsWith("winter"))) {
//                String[] arr = {"spring", "summer", "fall", "winter"};
//                for (int i = 0; i < arr.length; i++) {
//                    // Substring to get numer form last index of word 
//                    String number = result.substring(result.indexOf(arr[i])+1);
//                    boolean exist = false;
//                    for (int j = 0; j < number.length()-1; j++) {
//                        // If exist one value true
//                        if (!Character.isDigit(number.charAt(j))) {
//                            exist = true;
//                        }
//                    }
//                    // return Message
//                    if (!exist) {
//                        return result;
//                    }
//                }
//                System.err.println("Again");
//            }
//            System.err.println("There are only four semester: Spring, Summer, Fall, Winter");
//            System.out.print("Enter again: ");
//        }
//    }
    //check id and exist
    public static boolean checkIdExist(ArrayList<Student> ls, String id) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSemesterExist(ArrayList<Student> ls, String semester, String id) {
        if (!checkIdExist(ls, id)) {
            for (Student student : ls) {
                // Find data in old list and compare
                if (semester.equalsIgnoreCase(student.getSemester())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String InputCourseNotExisted(ArrayList<Student> list) {
        String course = checkInputCourse();
        for (int i = 0; i < list.size(); i++) {
//                            courseExist = ListById(ls, id).get(i).getCourseName();
            while (list.get(i).getCourseName().equalsIgnoreCase(course)) {
                System.err.println("duplicate course, Re - input");
                course = Validation.checkInputCourse();
            }
        }
        return course;
    }

    //check List Empty 
    public static void checkListEmpty(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
    }

}
