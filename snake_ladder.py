'''
Time complexity - O(n^2)
Space  complexity - O(n^2) n is the board size by n * n
Approach -  first flatten the board  and then performed bfs and explore all possible combinations
'''
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return 0
        n = len(board)    
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
        flatten[0] = -2 
        moves = 0
        while len(queue) > 0:
            size = len(queue)
            for i in range(size):
                popped = queue.popleft()
                if popped == n*n-1:
                    return moves
                for j in range(1,7):
                    future_index = popped + j
                    if future_index < n*n and flatten[future_index] != -2:    
                        if flatten[future_index] == -1:
                            queue.append(future_index)
                        else:
                            queue.append(flatten[future_index])
                        
                        flatten[future_index] = -2
            moves += 1
        return -1