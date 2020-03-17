import java.util.Scanner;


// change made to code for lab 6

public class ConnectFour {
    public static void printBoard(char[][] array) {

        for(int i = array.length - 1; i >= 0; i--){ //prints row and columns needed for the board

            for(int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void initializeBoard(char[][] array) { // initializes the dash as array[i][j] which will be used in printBoard();
        for(int i = 0;i < array.length; i++){
            for(int j = 0;j < array[0].length; j++){
                array[i][j] = '-';
            }
        }
    }
    public static int insertChip(char[][] array,int col,char chipType) { // inserts chip to empty (-) spot
        for(int i = 0;i < array.length; i++){
            if(array[i][col] == '-'){
                array[i][col] = chipType;
                return i; //returns the chip input into empty space
            }
        }
        return -1; // returns -1 if there is a chip already inserted, goes back to main to ask for another col
    }
    public static boolean checkIfWinner(char[][] array,int col,int row,char chipType) {
        int count = 0;

        for(int i = 0;i < array.length; i++){ // goes through each column if the space in the column is same as chip, increments count by 1 , if there is 4 consecutive chips back to back then wins
            if(array[i][col] == chipType){
                count++;
                if(count == 4){
                    return true;
                }
            }else{
                count = 0;
            }
        }
        count = 0; //initialize back to 0 because chips weren't back to back at least 4 times, so when it goes back to method count is back to 0
        for(int i = 0;i < array[0].length; i++){ // goes through each row if the space in the row is same as chip, increments count by 1 , if there is 4 consecutive chips back to back then wins
            if(array[row][i] == chipType){
                count++;
                if(count == 4){
                    return true;
                }
            }else{
                count = 0;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row,col;
        while(true){ // input height of row making sure it is greater than 4
            System.out.print("What would you like the height of the board to be ? ");
            row = sc.nextInt();
            if(row>=4){
                break;
            }else{
                System.out.println("Height should be at least 4. Please try again!");
            }
        }
        while(true){ // input length of board making sure it is greater than 4
            System.out.print("What would you like the length of the board to be ? ");
            col = sc.nextInt();
            if(col >= 4){
                break;
            }else{
                System.out.println("Length should be at least 4. Please try again!");
            }
        }
        char board[][] = new char[row][col];
        initializeBoard(board); // given the height and length, methods will be used to initialize and print the board
        printBoard(board);
        System.out.println("Player 1 : x");// gives user info on what their chiptype will be
        System.out.println("Player 2 : o");
        boolean player1 = true;
        char player;
        int choiceCol = 0;
        int result = 0;
        boolean isGameDone = false; //initializing to false to return true if someone wins
        int totalPlay = 0;
        while(true){
            if(player1){ // method gives user info what they're chip type will be again because it is inside a loop
                System.out.print("Player 1:");
                player = 'x';
            }
            else{
                System.out.print("Player 2:");
                player = 'o';
            }
            System.out.print("Which column would you like to choose ? ");
            choiceCol = sc.nextInt();
            if(choiceCol < 0 || choiceCol >= col){ // Makes sure choice is within the column size
                System.out.println("Please enter choice between 0 and "+(col-1));
            }
            else{
                result = insertChip(board,choiceCol,player);
                if (result == -1){ //in insertchip method -1 gets returned back if there is no room to insert
                    System.out.println("There is no room to insert.Please try again!!!");
                }else{
                    printBoard(board); //prints board after inserting the chip
                    isGameDone = checkIfWinner(board,choiceCol,result,player); //checks winner each time until someone has won
                    if(isGameDone){
                        if(player1){
                            System.out.print("Player 1 won the game!");
                        }else{
                            System.out.print("Player 2 won the game!");
                        }
                        break;
                    }
                    player1 = !player1;
                    totalPlay++;
                }
            }
            if(totalPlay == row*col){ //method for a tie
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
        sc.close();
    }
}

