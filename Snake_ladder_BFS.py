# BFS Approach
# Time complexity : O(n*n)
# Space complexity : O(n*n)
# Leetcode : Solved and submitted

from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
      
        # flag to indicate which way we are traversing
        right_to_left = False
        n = len(board)
        
        # starting point will be the last row and first column
        row, col = n-1, 0
        
        # checking for null case
        if not board:
            return 0
        
        # flatten the 2-d matrix in 1-d
        # start with an empty array and index as 0
        arr = []
        idx = 0
        
        # we fill the matrix in to 1-d array
        while idx < n*n:
            # if the value in board is not -1, which means it is a ladder or snake, we just append to new arr the value - 1 as we are using index
            if board[row][col] != -1:
                arr.append(board[row][col] - 1)
            else:
                # else append -1 for a normal move
                arr.append(-1)
            idx += 1
            
            # if I'm traversing from right to left
            if right_to_left:
               # move to the left by decrementing the col
                col -= 1
                # if we reach the end of the columns
                if col == -1:
                    # increment col by 1, move to the upper row and change the flag so that we can move from left to right now
                    col += 1; row -= 1
                    right_to_left = False
            else:
              # increment the col as we are moving from left to right
                col += 1
                # if we reach the end
                if col == n:
                  # decrement the col by 1, move to the upper row and change the flag for next direction
                    col -= 1; row -= 1
                    right_to_left = True
        
        # initalize a queue
        q = deque([])
        
        # add the first index in the queue and mark it visited
        q.append(0)
        arr[0] = -2
        
        # keeping track of the moves made to reach the end
        moves = 0
        
        # traverse until the q is empty
        while q:
            
            # maintain the size of q as we need the minimum moves
            size = len(q)
            for i in range(size):
               # fetch the current element from queue
                curr = q.popleft()
                
                # check for 6 values as in the dice
                for d in range(1,7):
                    next_step = curr + d
                    
                    # if we see that the rolling dice value is less than the n*n and also it is not visited
                    if next_step < n*n and arr[next_step] != -2:
                        # if the value of the next step is greater than 0
                        if arr[next_step] >= 0:
                           # check if we have reached the end, if so then return the moves + 1
                            if arr[next_step] == n*n - 1:
                                return moves + 1
                            # append to the queue the current step
                            q.append(arr[next_step])
                        else:
                          # if we reach the end, return the moves
                            if next_step == n*n - 1:
                                return moves + 1
                            # append to the queue the current step
                            q.append(next_step)
                        # mark the index as visited
                        arr[next_step] = -2
            # increment the move after one iteration size of queue
            moves += 1
         # return -1 if no moves found
        return -1
