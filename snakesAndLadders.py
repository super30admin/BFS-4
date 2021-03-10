# Time Complexity : O(N^2)
# Space Complexity : O(N^2)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return 0
        
        r = len(board)
        #transform to 1D array
        moves = [0 for i in range(r*r)]
        i = r-1 
        j = 0
        even = 0
        idx = 0
        while i >= 0 and j >= 0:
            if board[i][j] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[i][j] - 1
            idx += 1
            if even % 2 == 0:
                j += 1
            else:
                j-=1
            #boundary check
            if j == r:
                i-=1
                even += 1
                j -=1
            if j < 0:
                i-=1
                even += 1
                j += 1
                
        queue = deque([])
        queue.append(0)
        moves[0] = -2
        minMoves = 0
        while queue:
            size = len(queue)
            for i in range(size):
                curr = queue.popleft()
                #if we reach result
                if curr == len(moves)-1:
                    return minMoves
                #next set of moves (dice simulation)
                for j in range(1,7):
                    child = curr + j 
                    if child <= len(moves)-1 and moves[child] != -2:
                        #add to queue if -1 or positive number
                        if moves[child] == -1:
                            queue.append(child)
                        else:
                            queue.append(moves[child])
                        moves[child] = -2
        
            minMoves += 1
        
        return -1
        
        