# BFS-4

## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

## Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)

//Time Complexity = O(M*N)
//Space Complexity = O(M*N)

class Solution {
int[][] dirs;
int m;
int n;
public char[][] updateBoard(char[][] board, int[] click) {
if(board == null || board.length == 0) {
return board;
}
m = board.length;
n = board[0].length;
dirs = new int[][] {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
if(board[click[0]][click[1]] == 'M') {
board[click[0]][click[1]] = 'X';
return board;
}
Queue<int[]> queue = new LinkedList<>();
queue.add(click);
board[click[0]][click[1]] = 'B';

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int mines = getMines(board,current[0],current[1]);

            if(mines != 0) {
                board[current[0]][current[1]] =(char) (mines + '0');
            } else {
                for(int[] dir : dirs) {
                    int nr = dir[0] + current[0];
                    int nc = dir[1] + current[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n &&board[nr][nc] =='E') {
                        queue.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }

                }
            }
        }
        return board;
    }

    private int getMines(char[][] board, int i, int j) {
        int mines = 0;

        for(int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] =='M') {
                mines++;
            }
        }
        return mines;
    }

}

//Time Complexity = O(M*N)
//Space Complexity = O(M*N)

class Solution {
public int snakesAndLadders(int[][] board) {
if(board.length == 0 || board == null) {
return 0;
}
Queue<Integer> queue = new LinkedList<>();
int[] moves = flattenBoard(board);

        queue.add(1);
        moves[1] = -2;
        int min = 0;
        int n = board.length;
        while(!queue.isEmpty()) {
            int sz = queue.size();
            for(int i = 0; i < sz; i++) {
                int current = queue.poll();
                if(current == n * n) {
                    return min;
                }
                for(int j = 1; j < 7; j++) {
                    int child = current + j;
                    if(child > n*n) {
                        break;
                    }
                    if(moves[child] != -2) {
                        if(moves[child] == -1) {
                            queue.add(child);
                        } else {
                            queue.add(moves[child]);
                        }
                        moves[child] = -2;
                    }
                }
            }
            min++;
        }
        return -1;
    }

    private int[] flattenBoard(int[][] board) {
        int n = board.length;
        int[] moves = new int[n*n + 1];

        int i = n - 1;
        int j = 0;
        int idx = 1;
        int even = 0;
        while(i >= 0 && j < n) {
            moves[idx] = board[i][j];
            idx++;
            if(even % 2 == 0) {
                j++;
                if( j == n) {
                    i--;
                    even++;
                    j--;
                }
            } else {
                j--;
                if(j == -1) {
                    i--;
                    even++;
                    j++;
                }
            }
        }
        return moves;
    }

}
