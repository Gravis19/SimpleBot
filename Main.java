package tictactoe;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] cell = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        boolean notFinished = true;
        char ch = 'O';
        int x = 0;
        int o = 0;
        gameField(cell, ' ', 0);
        while (notFinished) {
            if (wins(cell)) {
                System.out.println(ch + " wins");
                break;
            }
            System.out.println("Enter the coordinates: ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            ch = ch == 'O' ? 'X' : 'O';
            if(notFinished(cell)) {
                if (a < 4 && a > 0 && b < 4 && b > 0) {
                    if (occupied(cell, coordinates(a, b))){
                        System.out.println("This cell is occupied! Choose another one!");
                    }else {
                        gameField(cell, ch, coordinates(a, b));
                    }
                }
                else {
                    System.out.println("Coordinate should be from 1 to 3!");
                }
            } else {
                notFinished = notFinished(cell);
                System.out.println("Draw");
            }
        }
    }
    public static int coordinates(int a, int b) {
        {
            if (a == 1) {
                return b == 1 ? 6 : b == 2 ? 3 : 0;
            }
            if (a == 2) {
                return b == 1 ? 7 : b == 2 ? 4 : 1;
            }
            else {
                return b == 1 ? 8 : b == 2 ? 5 : 2;
            }
        }
    }
    public static char[] gameField(char[] cell, char ch, int coordinate) {
        cell[coordinate] = ch;
        System.out.println("---------------------------");
        System.out.println("| " + cell[0] + " " + cell[1] + " " + cell[2] + " |");
        System.out.println("| " + cell[3] + " " + cell[4] + " " + cell[5] + " |");
        System.out.println("| " + cell[6] + " " + cell[7] + " " + cell[8] + " |");
        System.out.println("---------------------------");
        return cell;
    }
    public static boolean wins(char[] cell) {
        boolean wins = false;
        for (int i = 0; i < 7; i += 3) {
            if (cell[i] != '_' && cell[i] != ' ') {
                if (cell[i] == cell[i + 1] && cell[i] == cell[i + 2]) {
                    wins = true;
                    break;
                }
                else wins =  false;
            }
        } if (!wins) {
            for (int i = 0; i < 3; i++) {
                if (cell[i] != '_' && cell[i] != ' ') {
                    if (cell[i] == cell[i + 3] && cell[i + 3] == cell[i + 6]) {
                        wins = true;
                        break;
                    } else wins = false;
                }
            }
        }
        if (cell[4] != '_' && cell[4] != ' ' && !wins) {
            if ((cell[0] == cell[4] && cell[0] == cell[8]) || (cell[2] == cell[4] && cell[2] == cell[6])) {
                wins = true;
            }
            else wins =  false;
        }
        return wins;
    }
    public static boolean notFinished(char[] cell) {
        for (int i = 0; i < 9; i++) {
            if (cell[i] == '_' || cell[i] == ' ') {
                return true;
            }
        } return false;
    }
    public static boolean occupied (char[] cell, int coordinate) {
        if (cell[coordinate] == 'X' || cell[coordinate] == 'O') {
            return true;
        }
        else return false;
    }
}
