package Student;


import Branches.DUAL;
import Branches.FD;

import java.util.Arrays;
import java.util.Comparator;

public class Branch {
   public  Branches.FD single;
    public Branches.DUAL dual;


    public Branch(FD single, DUAL dual) {
        this.single = single;
        this.dual = dual;
    }

    public static boolean compareBranch(Branch b1, Branch b2)
    {
        if(b1.dual==b2.dual && b1.single == b2.single)
        {
            return true;
        }
        else {
            return false;
        }
    }


    public static Branch branchDetector(String s)
    {
        boolean contain=s.contains("+");
        if(contain==true){
            String [] str = s.split("\\+");
            String dual = str[0];
            String single = str[1];
            DUAL x=DUAL.valueOf(dual);
            FD y=FD.valueOf(single);
            Branch b = new Branch(y,x);
            return b;
        }
        else{
            FD mainbranch=FD.valueOf(s);
            DUAL d = DUAL.NULL;
            Branch b = new Branch(mainbranch, d);
            return  b;
        }



    }
    public static String branchPrinter(Branch b)
    {
        if(b.dual==DUAL.NULL){
            String branch1=b.single.name();
            return branch1;

        }
        else{
            String branch2=b.dual.name()+"\\+"+b.single.name()  ;
            return branch2;
        }
        // single = CS
        // DUAL = ECO;CS

    }




    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
