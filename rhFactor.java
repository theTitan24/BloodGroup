import java.util.Scanner;

public class rhFactor {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String rhFather, rhMother;

        /*
        * Checkerboard Array Format: Example- Father(+), Mother(-)
        *  =====================
        * || x | + | - | + | + ||
        * || - |               ||
        * || - | Stuff Goes    ||
        * || - |    Here       ||
        * || - |               ||
        *  =====================
        */
        String[][] punnetSq = new String[5][5];

        /*
        * Output Array format:
        *  || %age of + | %age of - ||
        */
        float[] rhOut = {0f, 0f};


        System.out.println("Please Input Either '+' pr '-' ");
        System.out.println();

        //Input Part
        System.out.println("Please Enter Father's rH Factor: ");
        rhFather = scan.next();
        System.out.println("Please Enter Mother's rH Factor:  ");
        rhMother = scan.next();

        //Convert Inputs to Usable Form for Punnet Square
        rhFather = toUsable(rhFather);
        rhMother = toUsable(rhMother);

        //Debug for any errors
        if(rhFather == "Error" || rhMother == "Error") {
            System.out.println("Invalid Input!! Please Try Again... ");
            main(null);
            System.exit(0);
        }

        //Now Let us setup the Punnet Square
        punnetSq[0][0] = "x";
        
        //Set the X axis (Father)
        for(int i = 1; i <= 4; i ++ ) {
            punnetSq[i][0] = Character.toString( rhFather.charAt(i - 1) );
        }

        //Now set the Y axis (Mother)
        for(int i = 1; i <= 4; i ++) {
            punnetSq[0][i] = Character.toString( rhMother.charAt(i - 1) );
        }

        //Now fill the Punnet Square
        for(int x = 1; x <= 4; x++ ) {
            for(int y = 1; y <= 4; y++ ) {
                punnetSq[x][y] = punnetSq[x][0] + punnetSq[0][y];
                punnetSq[x][y] = correctSeq(punnetSq[x][y]);

                String buf = punnetSq[x][y];
                if(buf.equals("++") || buf.equals("+-")) {
                    rhOut[0] += 1f;
                }
                else if(buf.equals("--")) {
                    rhOut[1] += 1f;
                }
            }
        }

        //Finally Calulate the Percentages
        rhOut[0] = (rhOut[0]/16f) * 100f;
        rhOut[1] = (rhOut[1]/16f) * 100f;

        //Now Output
        System.out.println();
        System.out.println("The Probabilities are: ");
        System.out.println("rH Positive (+) : " + rhOut[0] + "% ");
        System.out.println("rH Negative (-) : " + rhOut[1] + "% ");



        scan.close();
    }

    public static String toUsable(String inp) {
        String out;

        switch(inp) {
            case "+":
                out = "+-++";
                break;

            case "-":
                out = "----";
                break;

            default:
                out = "Error";
                break;
        }

        return out;
    }

    public static String correctSeq(String inp) {
        String out = inp;

        switch(inp) {
            case "-+" :
                out = "+-";
                break;
            
        }

        return out;
    }
}
