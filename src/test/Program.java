package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        /**
         * Write an input validation that asks the user to enter 'Y', 'y', 'N', or 'n'.
         */


//        String input, Y = "Y", N = "N";
//
//        System.out.println("Please enter the letter 'Y' or 'N'.");
//        input = keyboard.nextLine();
//
//
//        while (!(input.equalsIgnoreCase(Y) || input.equalsIgnoreCase(N)))
//        //|| input !=y || input !=N ||input !=n)
//
//        {
//            System.out.println("This isn't a valid entry. Please enter the letters Y or N" );
//            input = keyboard.nextLine();
//        }
            for (int i=0;i<10;i++){
                int h=i;
                while (h<9){
                    System.out.println(h);
                }
            }

    }
}
