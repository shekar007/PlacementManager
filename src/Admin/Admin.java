package Admin;

import RandomClass.StudentCGComparator;
import Station.Station;
import Student.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class Admin {

    public static Admin admin;
    private static String name;
    private static String ID;
    private static String password;
    private Admin(String name,String ID,String password) {
        this.name = name;
        this.ID = ID;
        this.password = password;
    }


    void editStation(Station s)
    {

        System.out.println("Enter N to edit station name-");
        System.out.println("Enter C to edit CG Criteria");
        System.out.println("Enter B to edit Branch Criteria");
        System.out.println("Enter L to edit Location of Station");
}

    public static Admin getInstance(String name,String ID,String password) {
        if (admin == null) {
            synchronized (Admin.class) {
                if (admin == null) {
                    admin = new Admin(name,ID,password);
                }
            }
        }
        return admin;
    }
    public  static void adminLoginPage()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter L to Login");
        System.out.println("Enter R to Register as Admin");
        char s = sc.nextLine().charAt(0);
        if(s=='L')
        {
            System.out.println("Enter ID");
           String id = sc.nextLine();
            System.out.println("Enter pass-");
            String pass = sc.nextLine();
            if(admin!=null && Admin.password==pass)
            {
                System.out.println("Login Successful");
                admin.adminCanDo();
            }
            else {
                System.out.println("Login Failed");
            }

        } else if (s =='R') {
            System.out.println("Enter name-");
            String s2;
            s2= sc.nextLine();
            System.out.println("ID-");
            String s3;
            s3 = sc.nextLine();
            System.out.println("Choose pass -");
            String s4;
            s4 = sc.nextLine();
            Admin.getInstance(s2,s3,s4);
        }
        else {
            System.out.println("Invalid Input.");

        }

    }

    private void adminCanDo() {
        System.out.println("Enter CS to change station attributes");
        System.out.println("Enter CR to remove station");
        System.out.println("Edit Student");
    }
    public static void  startRound() throws IOException {
//        Student.provideStudentList();


        // getStudentListFromText();//gets student list from text into arraylist
//        Student.provideStudentList();

        ArrayList<Student> sortedStudents = new ArrayList<Student>(Student.getStudents());
        Collections.sort(sortedStudents, new StudentCGComparator());
        for (int i = 0; i < sortedStudents.size(); i++) {
            Student s = sortedStudents.get(i);

            System.out.println(s.getName());
            System.out.println(s.getCgpa());
        }
        for (int i = 0; i < sortedStudents.size(); i++) {
            Student a = sortedStudents.get(i);
            if (!a.alloted) {
                ArrayList<Station> b = a.getPreferenceList();
                for (int j = 0; j < a.getPreferenceList().size(); j++) {

                    Station s = b.get(j);
                    if (s.requirmentsMatch(a)) {

                        if (s.getVacancy() > 0) {
                            a.setStationAlloted(s);
                            s.setVacancy(s.getVacancy() - 1);
                            s.stationMeStudents.add(a);
                            break;

                        } else {

                        }


                    }
                }
            }
        }

    }
}