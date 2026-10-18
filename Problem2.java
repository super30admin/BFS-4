// snakes and ladders
// covert the 2d matix in one dimensional arry and using bfs add elements to queue for the next possibilityy.



class Solution {

    public int snakesAndLadders(int[][] board) {

        int m = board.length;
        int[] arr = new int[m * m];

        // Step 1: Convert 2D board to 1D array

        int r = m - 1;
        int c = 0;

        boolean flag = true;

        for (int i = 0; i < m * m; i++) {

            if (board[r][c] == -1) {
                arr[i] = -1;
            } else {
                arr[i] = board[r][c] - 1;
            }

            if (flag) {
                c++;

                if (c == m) {
                    r--;
                    c = m - 1;
                    flag = false;
                }

            } else {
                c--;

                if (c == -1) {
                    r--;
                    c = 0;
                    flag = true;
                }
            }
        }

        // Step 2: BFS

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        HashSet<Integer> set = new HashSet<>();
        set.add(0);

        int level = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int currIdx = q.poll();

                if (currIdx == m * m - 1) {
                    return level;
                }

                for (int dice = 1; dice <= 6; dice++) {

                    int curPos = currIdx + dice;

                    if (curPos >= m * m) {
                        break;
                    }

                    int next = curPos;

                    // Snake or ladder
                    if (arr[curPos] != -1) {
                        next = arr[curPos];
                    }

                    // Reached final square
                    if (next == m * m - 1) {
                        return level + 1;
                    }

                    // Visit destination
                    if (!set.contains(next)) {
                        q.add(next);
                        set.add(next);
                    }
                }
            }

            level++;
        }

        return -1;
    }
}
