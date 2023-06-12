import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {    // variable
    int rowNumber;
    int colNumber;
    int mineNumber;
    String[][] matrix;
    String[][] userField;
    String[][] winMatrix;
    boolean stop;


    MineSweeper(int rowNumber,int colNumber){   // constructor method
        this.rowNumber=rowNumber;
        this.colNumber=colNumber;
        this.matrix = new String[rowNumber][colNumber];
        this.userField = new String[rowNumber][colNumber];
        this.mineNumber=(rowNumber*colNumber)/4;
        this.stop=false;
        this.winMatrix = new String[rowNumber][colNumber];
    }



    private void userFieldCreate(){                      // Create the matrix to be used in the game
        for (String[] strings : this.userField) {
            Arrays.fill(strings, "-");
        }
    }

    private void matrisCreation(){          // mine zones have been defined and the numbers required in the remaining zones have been defined
        Random rand = new Random();
        int value=this.mineNumber;
        while(value>0){                             // placing mines
            int row=rand.nextInt(this.rowNumber);
            int col=rand.nextInt(this.colNumber);
            if(!isEmpty(row,col)){
                this.matrix[row][col]="*";
                this.winMatrix[row][col]="-";
                value--;
            }
        }
        for(int i=0;i<this.matrix.length;i++){             // placing number
            for(int j=0;j<this.matrix[i].length;j++){
                if (!isEmpty(i,j)){
                    isMineControlCreation(i,j);
                }
            }
        }
    }

    private void isMineControlCreation(int row, int col) {      // find the number of mines in the given area
        int total = 0;
        int numRows = this.matrix.length;
        int numCols = this.matrix[0].length;

        for (int i = Math.max(row - 1, 0); i <= Math.min(row + 1, numRows - 1); i++) {
            for (int j = Math.max(col - 1, 0); j <= Math.min(col + 1, numCols - 1); j++) {
                if (i == row && j == col) {
                    continue;
                }

                if (Objects.equals(this.matrix[i][j], "*")) {
                    total++;
                }
            }
        }

        this.matrix[row][col] = String.valueOf(total);
        this.winMatrix[row][col] = String.valueOf(total);
    }

    private boolean isEmpty(int row,int col){                 // are there mines in the given value
        return Objects.equals(this.matrix[row][col], "*");
    }
    private void getMatris(String[][] matrix){                // function to print minefield
        for(String[] row:matrix){
            for(String col:row){
                System.out.print(col+" ");
            }
            System.out.println();
        }
    }


    private void Control(int row,int col){                  // query and synchronize the value entered by the user

        if(!isEmpty(row,col)){
            this.userField[row][col]=this.matrix[row][col];
            getMatris(this.userField);
            if(Arrays.deepEquals(this.winMatrix,this.userField)){
                System.out.println();
                System.out.println("You Win the game!");
                this.stop=true;
            }

        }else{
            System.out.println("Game Over!");
            this.stop=true;
            System.out.println();
            getMatris(this.matrix);
       }
    }

    public void run() {                     // the method by which we interact with the user
        Scanner scan = new Scanner(System.in);
        matrisCreation();
        userFieldCreate();
        //getMatris(this.matrix);

        int row, col;
        while (!this.stop) {
            System.out.print("Enter row number: ");
            if (scan.hasNextInt()) {
                row = scan.nextInt();
                System.out.print("Enter col number: ");
                if (scan.hasNextInt()) {
                    col = scan.nextInt();
                    if ((row >= 0) && (row < this.matrix.length) && (col >= 0) && (col < this.matrix[row].length)) {
                        if (this.userField[row][col].equals("-")) {
                            Control(row, col);
                        } else {
                            System.out.println("This value has already been entered");
                        }
                    } else {
                        System.out.println("Consider the dimensions of the matrix!");
                    }
                } else {
                    System.out.println("The value entered is not an int.");
                    scan.nextLine();
                    continue;
                }
            } else {
                System.out.println("The value entered is not an int.");
                scan.nextLine();
                continue;
            }
        }

        scan.close();
    }

}
