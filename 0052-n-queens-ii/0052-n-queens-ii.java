class Solution {
    public char board[][]; 
    public int solutionCount = 0;

    public boolean attackCheck(int row, int column) {
        for (int i = 0; i < row; i++)
        {
            if (board[i][column] == 'Q')
                return false;
        }
        for (int i = row, j = column; i >= 0 && j >= 0; i--, j--)
        {
            if (board[i][j] == 'Q')
                return false;
        }
        for (int i = row, j = column; i >= 0 && j < board.length; i--, j++)
        {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    public void placeQueens(int row) {
        if (row == board.length)
        {
            solutionCount++;
            return;
        }
        
        for (int i = 0; i < board.length; i++)
        {
            if (attackCheck(row, i))
            {
                board[row][i] = 'Q';
                placeQueens(row + 1);
                board[row][i] = ' ';
            }   
        }
        
    }

    public int totalNQueens(int n) {

        board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = ' ';

        // start with the first row & count # of valid configurations.
        placeQueens(0); 
        return solutionCount;
    }
}




