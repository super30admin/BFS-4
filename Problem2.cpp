class Solution { 
public:
//TC : O(N2)
//SC : O(N2)
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        vector<int> arr(n * n, 0);
        int idx = 0, r = n - 1, c = 0;
        bool dir = true;

        while (idx < n * n) {
            if (board[r][c] == -1) {
                arr[idx] = -1;
            } else {
                arr[idx] = board[r][c] - 1;
            }
            idx++;

            if (dir) {
                c++;
                if (c == n) {
                    c--;
                    r--;
                    dir = false;
                }
            } else {
                c--;
                if (c < 0) {
                    c++;
                    r--;
                    dir = true;
                }
            }
        }

        queue<int> q;
        q.push(0);
        arr[0] = -2;
        int moves = 0;

        while (!q.empty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.front();
                q.pop();

                if (curr == n * n - 1) {
                    return moves;
                }

                for (int j = 1; j <= 6; j++) {
                    int baby = curr + j;
                    if (baby >= n * n) {
                        break;
                    }

                    if (arr[baby] != -2) {
                        if (arr[baby] == -1) {
                            q.push(baby);
                        } else {
                            q.push(arr[baby]);
                        }
                        arr[baby] = -2;
                    }
                }
            }
            moves++;
        }

        return -1;
    }
};