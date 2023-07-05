import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //creating a 3x3 board
        char[][] board = new char[3][3];
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++ ){
                board[row][col] = ' ';
            }
        }

        // main loop
        char player = 'X';
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);

        while (!gameOver) {
            printBoard(board);
            System.out.print("Player " + player + " enter : ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            System.out.println();

            if (!isfull(board)) {
                if (board[row][col] == ' ') {
                    board[row][col] = player; // place the element at the place
                    gameOver = haveWon(board, player);
                    if (gameOver) {
                        System.out.println("Player " + player + " has won!!");
                    } else {
                        if (player == 'X') {
                            player = 'O';
                        } else {
                            player = 'X';
                        }
                    }
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("Game Draw!!");
                break;
            }
        }
        printBoard(board);
    }

    public static void printBoard(char[][] board){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++ ){
                System.out.print(board[row][col]+" | ");
            }
            System.out.println();
        }
    }

    public static boolean haveWon(char[][] board, char player){
        //check the rows
        for(int row = 0; row < board.length; row++){
            if(board[row][0] == player && board[row][1] == player && board[row][2] == player){
                return true;
            }
        }

        //check for columns
        for(int col = 0; col < board[0].length; col++){
            if(board[0][col] == player && board[1][col] == player && board[2][col] == player){
                return true;
            }
        }

        //check diagonal 00 11 22
        if(board[0][0] == player && board[1][1] == player && board[2][2] == 0){
            return true;
        }

        //check diagonal 02 11 20
        if(board[0][2] == player && board[1][1] == player && board[2][0] == 0){
            return true;
        }

        //none are true than return false
        return false;
    }

    public static boolean isfull(char board[][]){
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++ ){
                if(board[row][col] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
}