class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
        Queue<int[]> q = new LinkedList<>();
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add( new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()) {
            int[] a = q.poll();
            if (board[a[0]][a[1]] == 'M') {
                board[a[0]][a[1]] = 'X';
                return board;
            }
            int count = countAdjacentMines(a, dirs, board, m , n);
            if (count >= 1) {
                board[a[0]][a[1]] = (char)(count + '0');
            } else if ( count == 0) {
                for (int[] dir :dirs) {
                    int nr = dir[0] + a[0];
                    int nc = dir[1] + a[1];
                    if (nr >=0 && nr < m && nc >=0 && nc < n && (board[nr][nc] != 'B' || Character.isDigit(board[nr][nc]))) {
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }
        return board;
    }
    private int countAdjacentMines(int[] a, int[][] dirs, char[][] board, int m , int n) {
        int count=0;
        for (int[] dir:dirs) {
            int nr = a[0] + dir[0];
            int nc = a[1] + dir[1];
            if(nr >=0 && nr <m && nc >=0 && nc < n && board[nr][nc] == 'M') {
                count++;
            }
        }
        System.out.println("here end");
        return count;
    }
}


class Solution {
    public int snakesAndLadders(int[][] board) {
        int moves=0;
        int size=0;
        int n = board.length;
        int[] nums = new int[n*n];
        int i=0;
        int even=0;
        int row=n-1;
        int col=0;
        Queue<Integer> q = new LinkedList<>();
        while(i < n*n) {
            if(board[row][col] != -1) {
                nums[i] = board[row][col]-1;
            } else {
                nums[i] = -1;
            }
            if(even%2 == 0) {
                col++;
                if (col == n) {
                    row--;
                    col = n-1;
                    even++;
                }

            } else {
                col--;
                if (col == -1) {
                    row--;
                    col=0;
                    even++;
                }
            }
            i++;
        }
        q.add(0);
        while(!q.isEmpty()) {
            size = q.size();
            for (int index=0;index<size;index++) {
                int curr = q.poll();
                if (curr == n*n-1) {
                    System.out.println(moves);
                    return moves;
                }
                for(int j=1;j<7;j++) {
                    int child = curr + j;
                    if (child >= n*n) {
                        continue;
                    }
                    if (nums[child] != -2) {
                        if (nums[child] != -1) {
                            q.add(nums[child]);
                            nums[child] = -2;
                        } else {
                            q.add(child);
                            nums[child] = -2;
                        }
                    } 
                }
            }
            moves++;
        }
        return -1;
    }
}
