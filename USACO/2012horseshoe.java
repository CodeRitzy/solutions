import java.util.Scanner;

public class Horseshoe {

    static final int MAXN = 10;
    static char[][] matrix = new char[MAXN][MAXN];
    static int n = 0;

    static int closeCountSoFar = 0;
    static int maxBalanced = 0;


    public static void findMaxOpen(int row, int column, int openCountSoFar) {
      
      // if matrix is out of bounds or if is collected
      if (row < 0 || row >= n || column < 0 || column >= n || matrix[row][column] == 'C')
        return;

      // keep track of collected for backtracking later
      char collected = matrix[row][column];
      
      // count open bracket
      if (matrix[row][column] == '(' && closeCountSoFar == 0)
        openCountSoFar++;

      // stop counting this direction
      else if(matrix[row][column] == '(' && closeCountSoFar > 0)
        return;

      // count closed
      else if (matrix[row][column] == ')')
      {
        // not balanced
        if (openCountSoFar < closeCountSoFar + 1)
          return;
        closeCountSoFar++;
      }

      // mark tile as collected
      matrix[row][column] = 'C';


      // check if they are equal
      if (openCountSoFar == closeCountSoFar)
      {
        // find if it is max value of balanced
        maxBalanced = Math.max(maxBalanced, openCountSoFar * 2);

        // start backtracking
        matrix[row][column] = collected;
        if (collected == ')')
          closeCountSoFar--;
        return;
      }

      // down
      findMaxOpen(row + 1, column, openCountSoFar);

      // up
      findMaxOpen(row - 1, column, openCountSoFar);

      // right
      findMaxOpen(row, column + 1, openCountSoFar);

      // left
      findMaxOpen(row, column - 1, openCountSoFar);
      
      // end reached, backtrack
      matrix[row][column] = collected;
      if (collected == ')')
        closeCountSoFar--;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        findMaxOpen(0, 0, 0);
        System.out.println(maxBalanced);
    }
}
