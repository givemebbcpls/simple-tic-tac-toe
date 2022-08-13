import java.util.Scanner;

public class SimpleTicTacToe {
    static String[][] grid = new String[3][3];
    static int emptyCells = 9;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        makeGrid(grid, " ");
        draw(grid);

        String input;
        int counter = 0;
        boolean isGameEnded = false;
        boolean success = false;
        while (!isGameEnded) {
            while (!success) {
                input = scanner.nextLine();
                if (counter == 0) {
                    success = addSign(input, "X");
                    if (success) {
                        counter++;
                    }
                } else {
                    success = addSign(input, "O");
                    if (success) {
                        counter--;
                    }
                }
            }
            isGameEnded = analyze(grid);
            success = false;
        }
    }
    
    private static boolean addSign(String input, String sign) {
        String[] coordinates = input.split(" ");
        boolean result = false;

        if (coordinates.length != 2) {
            System.out.println("You should enter numbers!");
        } else {
            try {
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                
                if (x > 0 && x < 4 && y > 0 && y < 4) {
                    if (!(grid[x - 1][y - 1].equals("X") || grid[x - 1][y - 1].equals("O"))) {
                        grid[x - 1][y - 1] = sign;
                        emptyCells--;
                        draw(grid);
                        result = true;
                    } else {
                        System.out.println("This cell is occupied! Choose another one!");
                    }
                } else {
                    System.out.println("Coordinates should be from 1 to 3!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("You should enter numbers!");
            }
        }

        return result;
    }
    
    private static boolean analyze(String[][] grid) {
        boolean result = false;
        
        boolean XWin = checkForWin("X", grid);
        boolean OWin = checkForWin("O", grid);
        
        if (XWin) {
            System.out.println("X wins");
            result = true;
        } else if (OWin) {
            System.out.println("O wins");
            result = true;
        } else if (XWin == OWin && emptyCells == 0) {
            System.out.println("Draw");
            result = true;
        }

        return result;
    }
    
    private static boolean checkForWin(String mark, String[][] grid) {
        boolean winCondition = false;
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i].equals(mark) && grid[1][i].equals(mark) && grid[2][i].equals(mark)) {
                winCondition = true;
            } else if (grid[i][0].equals(mark) && grid[i][1].equals(mark) && grid[i][2].equals(mark)) {
                winCondition = true;
            }
        }
        
        if (grid[0][0].equals(mark) && grid[1][1].equals(mark) && grid[2][2].equals(mark)) {
            winCondition = true;
        } else if (grid[0][2].equals(mark) && grid[1][1].equals(mark) && grid[2][0].equals(mark)) {
            winCondition = true;
        }
        
        return winCondition;
    }
    
    private static void draw(String[][] grid) {
        dashes();
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.println("|");
        }
        dashes();
        System.out.println();
    }
    
    private static void dashes() {
        for (int i = 0; i < 9; i++) {
            System.out.print('-');
        }
    }

    private static void makeGrid(String[][] grid, String placeholder) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = placeholder;
            }
        }
    }
}
