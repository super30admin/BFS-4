# Using breadth first search
# begin by adding first position to the queue
# traverse the queue until all the nodes have been traversed or we reach the end node.
# mark the visited node in the board matrix as value -2.
# add the 6 next neighboring nodes from the current in the queue.
# as soon as the current reaches the end, return the steps.
# Time complexity - O(N^2) - worst case visit all nodes
# Space complexity - O(N^2) - for maintaining the queue
# did this solution run on leetcode? - yes
from collections import deque

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        N = len(board)       # no of rows = no of columns
        q = deque([(0, 0)])  # queue of nodes
       
        while q:
            node, step = q.popleft()
            r = N-1-(node//N)                # calculate row index
            rem = node%N 
            c = rem if r%2!=N%2 else N-1-rem # calculate col index
            
            # if the board location is visited, do not process it.
            if board[r][c] == -2:
                continue
                
            if board[r][c] != -1:
                node = board[r][c]-1
            
            # mark the board location as visited.
            board[r][c] = -2
            
            # return step if we have reached the end.
            if node == N*N-1:
                return step
            
            # add the next step index to the queue.
            for i in range(node+1, min(node+7, N*N)):
                q.append([i, step+1])
    
        return -1