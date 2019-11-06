/* Problem Statement:

VERIFIED ON LEETCODE PLATFORM 
529. MINESWEEPER

Medium

Let's play the minesweeper game (Wikipedia, online game)!

You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.

Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:

    If a mine ('M') is revealed, then the game is over - change it to 'X'.
    If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
    If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
    Return the board when no more squares will be revealed.

 

Example 1:

Input: 

[['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'M', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E'],
 ['E', 'E', 'E', 'E', 'E']]

Click : [3,0]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

Example 2:

Input: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'M', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Click : [1,2]

Output: 

[['B', '1', 'E', '1', 'B'],
 ['B', '1', 'X', '1', 'B'],
 ['B', '1', '1', '1', 'B'],
 ['B', 'B', 'B', 'B', 'B']]

Explanation:

 

Note:

    The range of the input matrix's height and width is [1,50].
    The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
    The input board won't be a stage when game is over (some mines have been revealed).
    For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.


 * Solution 1: using Depth First Search technique as here we dont want the shortest path :) 
   Time Complexity : O(n*n)  
 * Space complexity :O(n*n) 
 */

class Solution {
public:
    int dir[8][2]={{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int row = click[0];
        int col = click[1];
        
        /* Processing of rule 1 : if mine is revealed, game is over !! */
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }
        process_dfs_mode(board, row, col);
        return board;
    }
    
    void process_dfs_mode(vector<vector<char>>& board, int r, int c) {
        int mine_count = 0;
        int idx,row,col;

        if (board[r][c] == 'E') {
            /*check for adjacent mines */
            mine_count = get_count_for_adjacent_mines(board,r,c);
            
            /* rule 2 : if no adjacent mines found then change it to 'B'*/
            if (mine_count == 0) {
                board[r][c] = 'B';
                
                for (idx = 0; idx < 8; idx++) {
                    row = r + dir[idx][0];
                    col = c + dir[idx][1];

                    /* I got row and col, lets validate it if its valid or not for the board */
                    if (row >= 0 && row < board.size() && col >= 0 && col < board[0].size() && board[row][col] != 'B') {
                        process_dfs_mode(board, row, col);
                    }        
                }
                
            } else {
                /* rule 3 : change value to reflect the digits */
                board[r][c] = (char)(mine_count + '0');
            }
        } else if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return;
        }   
    }
    
    int get_count_for_adjacent_mines(vector<vector<char>>& board, int r, int c) {
        int idx;
        int mine_count = 0;
        int row, col;
        
        /* neighbor directions :total 8*/
        for (idx = 0; idx < 8; idx++) {
            row = r + dir[idx][0];
            col = c + dir[idx][1];
            
            /* I got row and col, lets validate it if its valid or not for the board */
            if (row >= 0 && row < board.size() && col >= 0 && col < board[0].size() && board[row][col] == 'M') {
                mine_count++;
            } 
        }
        return mine_count;
    }
};/* Execute on leetcode platform */


