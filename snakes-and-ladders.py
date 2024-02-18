# TC - O(N*N)  iterates over each cell of the n x n board once
# SC - O(N*N)  1D array, worst case BFS(n*n) calls
# Importing necessary libraries
from typing import List
from collections import deque

# Defining a class named Solution
class Solution:
    # Defining a method named snakesAndLadders which takes a 2D list named board as input and returns an integer
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # Determining the size of the board
        n = len(board)
        
        # Creating a 1D array to represent the board
        arr = [0]*(n*n)
        
        # Initializing variables for iterating through the board
        flag = True
        i = n-1
        j = 0
        idx = 0
        
        # Filling the 1D array according to the board's configuration
        while idx < (n*n):
            if board[i][j] == -1:
                arr[idx] = -1
            else:
                arr[idx] = board[i][j] - 1
            idx += 1
            if flag:
                j += 1
                if j == n:
                    i -= 1
                    j -= 1
                    flag = False
            else:
                j -= 1
                if j < 0:
                    i -= 1
                    j += 1
                    flag = True
        
        # Using a deque to implement BFS (Breadth-First Search)
        q = deque()
        # Adding the starting position (index 0) to the deque
        q.append(0)
        # Marking the starting position as visited by changing its value to -2
        arr[0] = -2
        # Initializing variable to count moves
        moves = 0
        
        # Performing BFS
        while q:
            # Getting the number of nodes at the current level
            size = len(q)
            # Processing nodes at the current level
            for _ in range(size):
                # Removing the current node from the deque
                curr = q.popleft()
                # Exploring all possible moves (dice rolls)
                for l in range(1, 7):
                    newIdx = curr + l  # Calculating the new index after the move
                    # If the new index reaches the end of the board, return the number of moves
                    if newIdx == n*n - 1:
                        return moves + 1
                    # If the new index is not visited
                    if arr[newIdx] != -2:
                        # If the new index leads to an empty cell (-1 on the board), add it to the deque
                        if arr[newIdx] == -1:
                            q.append(newIdx)
                        # If the new index leads to a ladder or a snake, add its destination to the deque
                        else:
                            # If the destination is the last cell, return the number of moves
                            if arr[newIdx] == n*n-1:
                                return moves+1
                            q.append(arr[newIdx])
                        # Marking the new index as visited
                        arr[newIdx] = -2
            # Incrementing the number of moves after processing all nodes at the current level
            moves += 1
        
        # If the end of the board is not reachable
        return -1
