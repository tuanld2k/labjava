/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab21;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author nguoitamxa
 */
public class Manager {

    public static String InputString(String typeStr) {
        System.out.print("Enter " + typeStr + ": ");
        return Validation.InputString();
    }

    //Allow user create new student
    public static void createStudent(ArrayList<Student> ls) {
        //if number of students greater than 10 ask user continue or not
        //loop until user input not duplicate

        while (true) {
            String id = InputString("id");
            if (!Validation.checkIdExist(ls, id)) {
                // same id, same name
                String name = ListById(ls, id).get(0).getStudentName();
                System.err.println("Student name: " + name);
                String semester = InputString("semester");
                if (!Validation.checkSemesterExist(ListById(ls, id), semester, id)) {

                    String courseExist = null;
                    // get course name value from List that have all record same Id
                    for (int i = 0; i < ListById(ls, id).size(); i++) {
                        if (ListById(ls, id).get(i).getSemester().equalsIgnoreCase(semester)) {
                            courseExist = ListById(ls, id).get(i).getCourseName();
                        }
                    }
                    System.err.println("Course name: " + courseExist);
                    System.err.println("Student exist. Each Student has only 1 course each semester. Re-input");

                    return;
                }
                String course = Validation.checkInputCourse();
                ls.add(new Student(id, name, semester, course));
                System.err.println("Add student success.");
                return;
            }
            String name = InputString("name");
            String semester = InputString("semester");
            String course = Validation.checkInputCourse();
            ls.add(new Student(id, name, semester, course));
            System.err.println("Add student success.");
            return;
        }
    }

    //Allow user create find,sort and display
    public static void findAndSort(ArrayList<Student> ls) {
        //check list empty 
        Validation.checkListEmpty(ls);
        ArrayList<Student> Result = new ArrayList<>();

        Result.addAll(ListByName(ls));
        while (Result.isEmpty()) {
            System.err.println("Re-input");
            Result.addAll(ListByName(ls));
        }
        Collections.sort(Result);
        System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
        //loop from first to last element of list student
        for (Student student : Result) {
            student.printId();
        }
    }

    public static ArrayList<Student> ListByName(ArrayList<Student> ls) {
        ArrayList<Student> searchResult = new ArrayList<>();
        String name = InputString("name to search");
        for (Student student : ls) {
            //check student have name contains input and add to List
            if (student.getStudentName().contains(name)) {
                searchResult.add(student);
            }
        }
        return searchResult;
    }

    //List student include All student have same name - user input
    //Allow user update and delete   
    public static void updateOrDelete(ArrayList<Student> ls) {
        //if list empty 
        Validation.checkListEmpty(ls);
        String id = InputString("id");
        ArrayList<Student> listStudentSameId = ListById(ls, id);
        //check list empty
        if (listStudentSameId.isEmpty()) {
            System.err.println("Not found student.");
            return;
        } else {
            System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
            for (Student listStudentSameId1 : listStudentSameId) {
                listStudentSameId1.printId();
            }
            System.out.print("Do you want to update (U) or delete (D) student: ");
            //check user want to update or delete
            if (Validation.checkInputUD()) {
                //?
                if (!Validation.checkIdExist(ls, id)) {

                    System.out.println("What you want to change?");
                    System.out.println("1. Name");
                    System.out.println("2. Semester");
                    System.out.println("3. Course Name");
                    int choice = Validation.checkInputIntLimit(1, 3);
                    switch (choice) {
                        case 1:
                            String name = listStudentSameId.get(0).getStudentName();
                            name = InputString("name");
                            for (Student listStudentSameId1 : listStudentSameId) {
                                listStudentSameId1.setStudentName(name);
                            }
                            for (Student student : ls) {
                                if (student.getId().equalsIgnoreCase(id)) {
                                    student.setStudentName(name);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Enter Numer of Record you want to update");
                            int choiceRecordsemester = Validation.checkInputIntLimit(1, listStudentSameId.size());
                            String semester = listStudentSameId.get(choiceRecordsemester - 1).getSemester();
                            semester = InputString("semester");
                            ls.get(ls.indexOf(listStudentSameId.get(choiceRecordsemester - 1))).setSemester(semester);
                            listStudentSameId.get(choiceRecordsemester - 1).setSemester(semester);

                            break;
                        case 3:
                            System.out.println("Enter Numer of Record you want to update");
                            int choiceRecordCourse = Validation.checkInputIntLimit(1, listStudentSameId.size());
                            String course = listStudentSameId.get(choiceRecordCourse - 1).getCourseName();
                            course = Validation.checkInputCourse(); //check student exist or not
                            ls.get(ls.indexOf(listStudentSameId.get(choiceRecordCourse - 1))).setCourseName(course);
                            listStudentSameId.get(choiceRecordCourse - 1).setCourseName(course);
                            break;
                    }
                    System.err.println("Update success.");
                    //loop from first to last element of list student
                    System.out.printf("%-15s%-15s%-15s\n", "Student name", "semester", "Course Name");
                    for (Student listStudentSameId1 : listStudentSameId) {
                        listStudentSameId1.printId();
                    }
                }
            } else {
                System.out.println("Enter Numer of Record you want to Delete");
                int choiceRecordDelete = Validation.checkInputIntLimit(1, listStudentSameId.size());
                ls.remove(listStudentSameId.get(choiceRecordDelete - 1));
                listStudentSameId.remove(listStudentSameId.get(choiceRecordDelete - 1));
                for (Student listStudentSameId1 : listStudentSameId) {
                    listStudentSameId1.printId();
                }
                System.err.println("Delete success.");
            }
        }
    }

    // Count the amount of student by the key: Id.
    public static int CountStudent(ArrayList<Student> ls) {
        int count = 0;
        for (int i = 0; i < ls.size(); i++) {
            boolean existed = false;
            for (int j = 0; j < i; j++) {
                if (ls.get(i).getId().equalsIgnoreCase(ls.get(j).getId())) {
                    existed = true;
                }
            }
            if (!existed) {
                count++;
            }
        }
        return count;
    }

//    public static ArrayList FindId(ArrayList<Student> ls) {
//        ArrayList<String> fuck = new ArrayList<>();
//        for (Student student : ls) {
//            String id = student.getId();
//            ArrayList<Student> list = new ArrayList<>();
//            list.addAll(ListById(ls, id));
//            for (int i = 0, j = 0; i < CountStudent(ls); i++) {
//                int count = 0;
//                for (; j < list.size(); j++) {
//                    boolean exist = false;
//                    for (int k = 0; k < j; k++) {
//                        if (list.get(j).getId().equalsIgnoreCase(list.get(k).getId())
//                                && list.get(j).getCourseName().equalsIgnoreCase(list.get(k).getCourseName())) {
//                            exist = true;
//                        }
//                    }
//                    if (!exist) {
//                        count++;
//                    }
//                }
//                fuck.add(list.get(i).getStudentName() + " " + list.get(i).getCourseName() + " " + count);
//            }
//        }
//        return fuck;
//    }
    public static int CountCourse(ArrayList<Student> ls) {
//        ArrayList<Student> course = new ArrayList<>();
//        for (Student l : ls) {
//            course.addAll(ListById(ls, l.getId()));
//            course.
//        }
        int count = 0;
        for (int i = 0; i < ls.size(); i++) {
            boolean existed = false;
            for (int j = 0; j < i; j++) {
                if (ls.get(i).getCourseName().equalsIgnoreCase(ls.get(j).getCourseName())) {
                    existed = true;
                }
            }
            if (!existed) {
                count++;
            }
        }
        return count;
    }

    //Get list student find by id
    public static ArrayList<Student> ListById(ArrayList<Student> ls, String id) {
        ArrayList<Student> getListStudentById = new ArrayList<>();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())) {
                getListStudentById.add(student);
            }
        }
        return getListStudentById;
    }

    public static void report(ArrayList<Student> ls) {
        if (ls.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        ArrayList<String> copyReport = new ArrayList<>();
        ArrayList<String> ListReport = new ArrayList<>();
        int size = ls.size();
        for (int i = 0; i < size; i++) {
            for (Student student1 : ls) {
                int total = 0;
                for (Student student2 : ls) {
                    if (student1.getId().equalsIgnoreCase(student2.getId())
                            && student1.getCourseName().equalsIgnoreCase(student2.getCourseName())) {
                        total++;
                    }
                }
                ListReport.add(student1.getStudentName()+ " " + student1.getCourseName()+ " " + total);
            }
        }
        copyReport.addAll(CountDupliStr(ListReport));
        for (String listSpecial : copyReport) {
            System.out.println(listSpecial);
        }
    }
    
        public static ArrayList CountDupliStr(ArrayList<String> ls) {
        ArrayList<String> Unique = new ArrayList<>();
        for (int i = 0; i < ls.size(); i++) {
            boolean existed = false;
            for (int j = 0; j < i; j++) {
                if (ls.get(i).equalsIgnoreCase(ls.get(j))) {
                    existed = true;
                }
            }
            if (!existed) {
                Unique.add(ls.get(i));
            }
        }
        return Unique;
    }
    
}
