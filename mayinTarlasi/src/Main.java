import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int rowNumber,colNumber;

        System.out.println("MİNEFİELD GAME WELCOME");

        while (true) {
            try {
                System.out.print("How many rows will the matrix have: ");
                rowNumber = scan.nextInt();
                if (rowNumber <= 0) {
                    throw new IllegalArgumentException("Number of rows should be greater than 0.");
                }

                System.out.print("How many columns will the matrix have: ");
                colNumber = scan.nextInt();
                if (colNumber <= 0) {
                    throw new IllegalArgumentException("Number of columns should be greater than 0.");
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scan.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println();

        MineSweeper m1 = new MineSweeper(rowNumber,colNumber);
        m1.run();
    }
}