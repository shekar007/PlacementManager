import Admin.Admin;
import Branches.FD;
import Station.Station;
import Student.Student;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.rmi.StubNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

import static Admin.Admin.admin;
import static Student.Student.StudentCred;

public class Main {


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char c = 0;
        boolean login = false;
        while((int)c != 'Q' && !login) {
            System.out.println("HOMEPAGE");

            System.out.println("Enter '1' to provide Station list");
            System.out.println("2 to write student list in studentlisttxt");
            System.out.println("Enter 4 to read from station list ");
            System.out.println("Enter 5 to write in stationListAllotment");
            System.out.println("Enter S to Login/Register as Student");
            System.out.println("Enter A to Login/Register as Admin");
            System.out.println("Enter Q to quit");
            System.out.println("3 to start allotment");
            c = sc.nextLine().charAt(0);
            if (c == 'S') {
                System.out.println("Enter L to Login or R to Register");
                char c2 = sc.nextLine().charAt(0);
                if (c2 == 'L') {

                    System.out.println("Enter username/email:");
                    String s1 = sc.nextLine();
                    System.out.println("Enter password");
                    String s2 = sc.nextLine();
                    if (StudentCred.containsKey(s1) && StudentCred.get(s1).equals(s2)) {
                        login = true;
                        Student.displayStudentMenu();

                    } else {
                        System.out.println("Wrong Credentials.");
                    }

                } else if (c2 == 'R') {

                    Student.createStudent();

                }

            } else if (c == 'A') {
                Admin.adminLoginPage();

            } else if (c=='1') {
                Student.readStudent(); //ioexcetion

            } else if (c=='2') {
                Student.writeStudent();

            } else if (c=='3') {
                Admin.startRound();

            } else if (c=='4') {
        Station.readStation();
            } else if (c=='5') {
                Station.writeStation();
            } else if(c=='6')
            {
                ArrayList<Student>arr = Student.getStudents();
                for (int i = 0; i < arr.size(); i++) {
                    Student s= arr.get(i);
                    System.out.println(s.getStationAlloted());
                }
                ArrayList<Station> brr = Station.stations;
                for (int i = 0; i < brr.size(); i++) {
                    Station s = brr.get(i);
                    System.out.println(s.stationMeStudents);
                }
            }
            else if(c == 'Q') {

            } else if (c=='Z') {
                System.out.println(Student.getStudents());

            } else if (c=='2') {

            } else {
                System.out.println("Enter Valid Input");
            }
        }

    }

}

