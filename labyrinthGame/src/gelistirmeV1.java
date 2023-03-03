import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class gelistirmeV1 {
    static int[][] labyrinth = new int[20][20];
    static boolean[][] visited = new boolean[20][20];
    static int kX, kY;

    public static void main(String[] args) {

        // initialize visited array
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                visited[i][j] = false;
            }
        }

        // read labyrinth from file
        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\ulku_\\OneDrive\\Masaüstü\\labirent.txt"));
            for (int i = 0; i < 20; i++) {
                String line = scanner.nextLine();
                for (int j = 0; j < 20; j++) {
                    labyrinth[i][j] = Integer.parseInt(line.charAt(j) + "");
                    if (labyrinth[i][j] == 9) { // labirent sonunu kayit et
                        kX = i;
                        kY = j;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            return;
        }

        // find the path
        boolean pathFound = findPath(0, 0);

        // Print the result
        if (pathFound) {
            System.out.println("LABİRENT ÇÖZÜLDÜ!");
            printPath();
        } else {
            System.out.println("LABİRENT ÇÖZÜMSÜZ!");
        }
    }

    public static boolean findPath(int x, int y) {

        // check if x,y is out of labyrinth or it is a wall
        if (x < 0 || x >= 20 || y < 0 || y >= 20 || labyrinth[x][y] == 0) {
            return false;
        }

        // check if x,y is already visited
        if (visited[x][y]) {
            return false;
        }

        // mark x,y as visited
        visited[x][y] = true;

        // check if x,y is the exit
        if (labyrinth[x][y] == 9) {
            return true;
        }

        // check the path in all four directions
        if (findPath(x - 1, y) || findPath(x + 1, y) || findPath(x, y - 1) || findPath(x, y + 1)) {
            return true;
        }

        // backtrack
        visited[x][y] = false;

        return false;
    }

    public static void printPath() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (visited[i][j]) {
                    if (i == kX && j == kY) {
                        System.out.print("9");
                    } else {
                        System.out.print("1");
                    }
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}