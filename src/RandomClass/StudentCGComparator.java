package RandomClass;

import Student.Student;

import java.util.Comparator;

public class StudentCGComparator implements Comparator<Student> {


    @Override
    public int compare(Student o1, Student o2) {
        if(o2.getCgpa() - o1.getCgpa() ==0)
        {
            return 0;
        }
        else if(o2.getCgpa() -  o1.getCgpa() <0){
            return -1;
        }
        else {
            return 1;
        }
    }


}
