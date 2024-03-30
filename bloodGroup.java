import java.util.*;

public class bloodGroup {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);  //Scanner Object

        //Variables which will input values
        String father, mother;

        /*
         * Punnet Square Format: (Example Father: B, Mother: A)
         * || \ |  B  |  O  |  B  |  B  ||
         * || A |                       ||
         * || O |     Stuff to be       ||
         * || A |           Filled      ||
         * || A |               Here    ||
         *  =============================
         */
        String[][] punnetSq = new String[5][5];

        //This stores the Blood Group Percents.
        //Format | A | B | AB | O |
        int[] bloodCount = {0, 0, 0, 0};
        float[] bloodPercent = {0f, 0f, 0f, 0f};
        String[] groups = {"A", "B", "AB", "O"};

        //Input Part

        //Input Father's Details
        System.out.println("Please Enter Father's Blood Group without rH: ");
        father = scan.next();

        //Input Mother's Details
        System.out.println();
        System.out.println("Please Enter Mother's Blood Group without rH: ");
        mother = scan.next();
        
        //Shift to Upper Case
        father = father.toUpperCase();
        mother = mother.toUpperCase();

        /*
         * Now, the Blood Group inputs must be changed into a useable format for Punnet Sq.
         * A  --> AOAA
         * B  --> BOBB
         * AB --> ABAB
         * O  --> OOOO
         * This is done as the genotypes for A and B are AO and AA; BO and BB respectively.
         * Method is declared below.
         */

        father = toUsable(father);
        mother = toUsable(mother);

        //Check if any error is present in input. Restarts the main method if present
        if(mother.equals("Error") || father.equals("Error")) {
            System.out.println("Error in Input. Try Again... \n");
            main(null);
            System.exit(0);
        }

        //Now let's setup the Punnet Square

        punnetSq[0][0] = "x";   //Can be any value. It is useless

        //Loop for father
        for(int i = 1; i <= 4; i++ ) {
            punnetSq[i][0] = Character.toString( father.charAt( (i -1) ) );
        }

        //Loop for mother
        for(int i = 1; i <= 4; i++ ) {
            punnetSq[0][i] = Character.toString( mother.charAt( (i - 1) ) );
        }

        //Now finish the Punnet Square
        for(int y = 1; y <= 4; y++ ) {
            for(int x = 1; x <= 4; x++ ) {
                punnetSq[x][y] = punnetSq[x][0] + punnetSq[0][y];
                punnetSq[x][y] = correctSeq(punnetSq[x][y]);    //Corrects the Sequence Mistakes if any

                //Increments the Counter (bloodCount)
                String buf = punnetSq[x][y];
                if(buf.equals("AA") || buf.equals("AO")) {
                    bloodCount[0] += 1;
                }
                else if(buf.equals("BB") || buf.equals("BO")) {
                    bloodCount[1] += 1;
                }
                else if(buf.equals("AB")) {
                    bloodCount[2] += 1;
                }
                else if(buf.equals("OO")) {
                    bloodCount[3] += 1;
                }

            }
        }

        //Finally, find the Percentage.
        for (int i = 0; i < 4; i++ ) {
            bloodPercent[i] = ((float)bloodCount[i] / 16f) * 100f;
        }

        //Output
        System.out.println();
        System.out.println("The Probabilities are: ");
        for (int i = 0; i < 4; i++ ) {
            System.out.println(groups[i] + ": " + bloodPercent[i] + "% ");
        }

        scan.close();
    }



    public static String toUsable(String inp) {

        String out = "";

        switch(inp) {
            case "A":
                out = "AOAA";
                break;

            case "B":
                out = "BOBB";
                break;

            case "AB":
                out = "ABAB";
                break;

            case "O":
                out = "OOOO";
                break;

            default:
                out = "Error";
                break;
        }

        return out;
    }

    public static String correctSeq(String inp) {
        String out;

        switch(inp) {
            case "OA":
                out = "AO";
                break;

            case "OB":
                out = "BO";
                break;

            case "BA":
                out = "AB";
                break;

            default:
                out = inp;
                break;
        }

        return out;
    }
}