/*
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return 0
        n = len(board)    
        # flatten the board so that we can directly goto that index in array
        flatten = [0] * (n*n)
        i = n-1
        j = 0
        even = 0
        index = 0
        while i >=0:
            if board[i][j] == -1:
                flatten[index] = -1
            else:
                flatten[index] = board[i][j] - 1
            
            index += 1
            if even % 2== 0:
                j += 1
            else:
                j -= 1
            
            if j == n:
                i -= 1
                even += 1
                j -= 1
                
            if j < 0:
                i -= 1
                even += 1
                j += 1
                
        queue = collections.deque()
        queue.append(0)
        flatten[0] = -2 # mark visited
        moves = 0
        while len(queue) > 0:
            size = len(queue)
            for i in range(size):
                popped = queue.popleft()
                if popped == n*n-1:
                    return moves
                for j in range(1,7):
                    future_index = popped + j
                    if future_index < n*n and flatten[future_index] != -2:      # if not visited
                        if flatten[future_index] == -1:
                            queue.append(future_index)
                        else:
                            queue.append(flatten[future_index])
                        
                        flatten[future_index] = -2
            moves += 1
        return -1
*/

// time - O(n^2)
// space - O(n^2)
// logic - first flatten the board so that whenever there is snake or ladder we can directly jump to that index and then performed bfs
// and explored all possible combinations
class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0)
            return 0;
        int n = board.length;
        int[] flatten = new int[n*n];
        int i = n-1, j = 0, index = 0, even = 0;
        while (i >= 0){
            if (board[i][j] == -1)
                flatten[index] = -1;
            else
                flatten[index] = board[i][j] - 1;
            index ++;
            if (even % 2 == 0)
                j++;
            else
                j--;
            if (j == n){
                i--;
                j--;
                even++;
            }
            if (j < 0){
                i--;
                j++;
                even++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        flatten[0] = -2;
        int moves = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int k=0; k<size; k++){
                int popped = q.poll();
                if (popped == n*n-1)
                    return moves;
                for (int l=1; l<=6; l++){
                    int future_index = popped + l;
                    if (future_index < n*n && flatten[future_index] != -2){
                        if (flatten[future_index] == -1)
                            q.add(future_index);
                        else
                            q.add(flatten[future_index]);
                    flatten[future_index] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}