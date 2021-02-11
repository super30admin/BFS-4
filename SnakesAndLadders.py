"""
Approach: BFS

Here we will first flatten the board to 1D array of r*r, where each index will contain the value present in the board at that place. 

We are doing this because in board we for snake or a ladder we are given a direct value to a cell on the board. Now for we can not reach that index directly on a 2D board but we can do the same in a 1D array.

Now we will do a normal BFS on this 1D array.

TC: O(n*n)
SC: O(n*n)

n = size of the board
"""

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board == None or len(board) == 0:
            return 0
        
        r = len(board)
        c = len(board[0])
        moves = [0] * (r * r)
        i = r-1
        j = 0
        even = 0
        idx = 0
        
        # Flatten the board from 2D to 1D
        while i >= 0 and j >= 0:
            if board[i][j] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[i][j] - 1
                
            idx += 1
            
            # Decide on the direction
            if even % 2 == 0:
                j += 1
            else:
                j -= 1
              
            # Change row
            if j == r:
                i -= 1
                even += 1
                j -= 1   
                
            if j < 0:
                i -= 1
                even += 1
                j += 1
        
        q = []
        q.append(0)
        moves[0] = -2
        minMoves = 0
        
        while q:
            size = len(q)
            
            for k in range(size):
                curr = q.pop(0)
                
                if curr == (r*r) - 1:
                    return minMoves
                
                for l in range(1,7):
                    baby =  curr + l
                    if baby < r*r and moves[baby] != -2:
                        if  moves[baby] == -1:
                            q.append(baby)
                        else:
                            q.append(moves[baby])
                            
                        moves[baby] = -2
                        
                        
            minMoves += 1
            
        return -1