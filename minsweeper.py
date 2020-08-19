# STEPS:
# Begin by checking if the click location is the same as the mine location. (Edge case)
# update it to 'X', and return.
# add the first mine location to the queue.
# Begin by processing its neighbors
# count whether any of its 8 neighbors have a mine.
# update the cell location to blank if no mine found in its neighbors, and add its neighbors to the queue.
# update this cell value to "B"
# if mines are present in the neighbors, update the cell value to the count.
# return the board when the queue is empty.
# Time complexity - O(nm)
# Space complexity - O(nm) -- queue
# did this solution run on leetcode? - yes

from collections import deque

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        # no of rows and columns in a board
        rows, cols = len(board), len(board[0])
        
        # if the click is on the mine, replace the clicked location with a 'X', and return the board.
        if board[click[0]][click[1]]=="M":
            board[click[0]][click[1]]="X"
            return board
        
        q = deque([click]) # begin by index (0, 0)
        # neighbor cells
        neighbors = [[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
        
        while q:
            node = q.popleft()
            
            # count number of mines around the cell, and update it in the board.
            count = 0
            for neigh in neighbors:
                new_cell = [neigh[0]+node[0], neigh[1]+node[1]]
                if 0<=new_cell[0]<rows and  0<=new_cell[1]<cols:
                    if board[new_cell[0]][new_cell[1]]=="M":
                        count+=1
                        
            if count==0: # add the nrighbors to the queue only when none of its neighboring cells contain mines, else skip it.
                board[node[0]][node[1]]="B"
                # add the neighboring cells inside the queue.
                for neigh in neighbors:
                    new_cell = [neigh[0]+node[0], neigh[1]+node[1]]
                    if 0<=new_cell[0]<rows and 0<=new_cell[1]<cols and board[new_cell[0]][new_cell[1]]=="E":
                        q.append(new_cell)
                        board[new_cell[0]][new_cell[1]]="B"

            else: board[node[0]][node[1]]=str(count)
        
        return board
        