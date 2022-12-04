package Station;

import Branches.DUAL;
import Branches.FD;
import Student.Branch;
import Student.Student;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
public class Station {

    public static Station nullStation;
    public static ArrayList<Station> stations = new ArrayList<Station>();
    String name;
    public double CGCrtieria;
    ArrayList<Branch> branchesallowed;
    int vacancy;

    public ArrayList<Student> stationMeStudents = new ArrayList<Student>() ;


    public ArrayList<Branch> getBranchesallowed() {
        return branchesallowed;
    }



    @Override
    public String toString() {
        return this.name;
    }
    public static void readStation() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\Chandrashekar\\IdeaProjects\\hack\\stations.txt");
        BufferedReader br = new BufferedReader(fr);
        String newLine = "";

        while((newLine = br.readLine())!=null)
        {
            String[] str = newLine.split(",");
            Station s;
            String name = str[0];
            double cg = Double.parseDouble(str[1]);
            String[] branches = str[2].split(";");
            ArrayList<Branch> branchArrayList = new ArrayList<Branch>();
            for (int i = 0; i < branches.length; i++) {
                Branch b = Branch.branchDetector(branches[i]);
                branchArrayList.add(b);
            }
            int vac = Integer.parseInt(str[3]);

            s= new Station(name,cg,branchArrayList,vac);

        }
        br.close();
        for (int i = 0; i < Station.stations.size(); i++) {
            Station s= Station.stations.get(i);
            System.out.println(s.name);
            System.out.println(s.CGCrtieria);
            System.out.println(s.vacancy);
            System.out.println(s.branchesallowed);
        }

    }
    public static void writeStation() throws IOException {
        File f = new File("C:\\Users\\Chandrashekar\\IdeaProjects\\hack\\stationAllotment.txt");
        FileWriter fr = new FileWriter(f);
        BufferedWriter writer = new BufferedWriter(fr);
        for (int i = 0; i <stations.size() ; i++) {
            Station s =stations.get(i);
            writer.write(s.name+",");
            writer.write(s.CGCrtieria+",");
            for (int j = 0; j < s.branchesallowed.size(); j++) {
                Branch b = s.branchesallowed.get(j);
                if(j!=s.branchesallowed.size()-1)
                {
                    writer.write(Branch.branchPrinter(b)+";");
                }
                else {
                    writer.write(Branch.branchPrinter(b));
                }
            }
            System.out.println(s.stationMeStudents.size());
            for (int j = 0; j < s.stationMeStudents.size(); j++) {
                Student st = s.stationMeStudents.get(j);
                if (j != s.stationMeStudents.size() - 1) {
                    writer.write(st.getName() + ";");
                }
                else {
                    writer.write(st.getName());
                }
            }
            writer.write("\n");

        }
        writer.close();
    }

    public static ArrayList<String> toArrayOfString(ArrayList<Station> s)
    {
        ArrayList<String> stringOf = new ArrayList<>();
        for (int i = 0; i <s.size() ; i++) {
            String str= s.get(i).toString();
            stringOf.add(str);
        }
        return stringOf;

    }

        public boolean requirmentsMatch(Student x)
        {
            Branch b = x.getBranch();
            ArrayList<Branch> arr = new ArrayList<Branch>(this.branchesallowed);

            int flag =0;
            for (int i = 0; i < arr.size(); i++) {
                Branch ballowed = branchesallowed.get(i);
                if(Branch.compareBranch(ballowed,b))
                {
                    if(this.CGCrtieria<=x.getCgpa()) {

                            flag = 1;
                            break;

                    }

                }
            }
            if(flag==0)
            {
                return false;
            }
            else {return true;

            }
        }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public void setBranchesallowed(ArrayList<Branch> branchesallowed) {
        this.branchesallowed = branchesallowed;
    }

    public Station(String name, double CGCrtieria, ArrayList<Branch> b, int vacancy) {
        this.name = name;
        this.CGCrtieria = CGCrtieria;
        branchesallowed = b;
        this.vacancy = vacancy;
        stations.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCGCrtieria() {
        return CGCrtieria;
    }

    public void setCGCrtieria(double CGCrtieria) {
        this.CGCrtieria = CGCrtieria;
    }

   public static void outputStationList()
    {
    // display station list to student in student menu
    }


//    public String getLocation() {
//        return location;
//    }

//    public void setLocation(String location) {
//        this.location = location;
//    }

    private void removeStation(Station s) {
        boolean remove = stations.remove(s);
        if (!remove) {
            System.out.println("Station doesn't exist");
        } else {
            System.out.println("Station removed.");
        }
        //stations.txt remove also
    }

    public static void provideStationList() throws IOException {
        FileReader fr = new FileReader("stations.txt");
        BufferedReader br = new BufferedReader(fr);
        String newLine;
        while ((newLine = br.readLine()) != null) {
            String[] arr = newLine.split(",");
            String[] b = arr[2].substring(1, arr[2].length() - 1).split(";");

            Station s = null;

                s.name = arr[0].trim().toLowerCase();
                s.CGCrtieria = Double.parseDouble(arr[1]);
                s.vacancy = Integer.parseInt(arr[3]);
                for (int i = 0; i < b.length; i++) {
                    try {
                        DUAL x1 = DUAL.valueOf(b[i]);
                        for (FD fd : FD.values()) {
                            Branch e = null;
                            e.dual = x1;
                            e.single = fd;
                            s.branchesallowed.add(e);
                        }

                    } catch (IllegalArgumentException e) {

                        try {
                            FD x2 = FD.valueOf(b[i]);
                            for (DUAL dd : DUAL.values()) {
                                Branch e3 = null;
                                e3.dual = dd;
                                e3.single = x2;
                            }
                        } catch (IllegalArgumentException empty) {

                            Branch doublecr = null;

                            String[] doublecriteria = b[i].split("\\+");

                            try {
                                doublecr.dual = DUAL.valueOf(doublecriteria[0]);
                                doublecr.single = FD.valueOf(doublecriteria[1]);
                            }
                            catch(IllegalArgumentException err)
                            {
                                System.out.println("Incorrect Input");
                            }
                        }
                    }
                }
            }
        }

    }

