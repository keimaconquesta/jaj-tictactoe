package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String cells = "_________";
        char winner = 0;
        boolean hasEnded = false;
        int turns = 0;

        // box
        System.out.println("---------");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("|       |");
        System.out.println("---------");

        do {
            // input
            System.out.print("Enter the coordinates: ");
            int xCoordinate = 0;
            int yCoordinate = 0;
            int insertAt = -1;

            while (insertAt == -1) {
                try {
                    xCoordinate = scanner.nextInt();
                    yCoordinate = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if (xCoordinate > 3 || xCoordinate <= 0 || yCoordinate > 3 || yCoordinate <= 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else {
                    switch (yCoordinate) {
                        case 3:
                            if (cells.charAt(xCoordinate - 1) == '_') {
                                insertAt = xCoordinate - 1;
                                break;
                            }
                        case 2:
                            if (yCoordinate == 2 && cells.charAt(xCoordinate + yCoordinate) == '_') {
                                insertAt = xCoordinate + yCoordinate;
                                break;
                            }
                        case 1:
                            if (yCoordinate == 1 && cells.charAt(xCoordinate + yCoordinate + 4) == '_') {
                                insertAt = xCoordinate + yCoordinate + 4;
                                break;
                            }
                        default:
                            System.out.println("This cell is occupied! Choose another one!");
                            break;
                    }
                }
            }

            // reassigning cells
            char[] newCells = cells.toCharArray();
            newCells[insertAt] = turns % 2 == 0 ? 'X' : 'O';
            cells = String.valueOf(newCells);

            // new box
            System.out.println("---------");
            for (int i = 0; i < cells.length(); i++) {
                if (i % 3 == 0) {
                    System.out.print("| ");
                }

                System.out.print(cells.charAt(i) == '_' ? "  " : cells.charAt(i) + " ");

                if ((i + 1) % 3 == 0) {
                    System.out.println("|");
                }
            }
            System.out.println("---------");

            // check winner - diagonal
            if ((cells.charAt(0) == cells.charAt(4) && cells.charAt(0) == cells.charAt(8)
                    || cells.charAt(2) == cells.charAt(4) && cells.charAt(2) == cells.charAt(6))
                    && cells.charAt(4) != '_') {
                winner = cells.charAt(4);
                hasEnded = true;
            }

            // check winner - horizontal
            for (int i = 0; i < cells.length(); i += 3) {
                if ((cells.charAt(i) == cells.charAt(i + 1) && cells.charAt(i) == cells.charAt(i + 2))
                        && cells.charAt(i) != '_') {
                    winner = cells.charAt(i);
                    hasEnded = true;
                    break;
                }
            }

            // check winner - vertical
            for (int i = 0; i < 3; i++) {
                if ((cells.charAt(i) == cells.charAt(i + 3) && cells.charAt(i) == cells.charAt(i + 6))
                        && cells.charAt(i) != '_') {
                    winner = cells.charAt(i);
                    hasEnded = true;
                    break;
                }
            }

            // check draw
            if (!cells.contains("_") && !hasEnded) {
                System.out.println("Draw");
                hasEnded = true;
            }

            turns++;
        } while (!hasEnded);

        if (winner != 0) {
            System.out.println(winner + " wins");
        }
    }
}
