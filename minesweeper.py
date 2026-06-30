
# time O(m*n), space O(m*n) call stack
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
    
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        directionsToSearch =[(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]

        def countMines(row,col):
            count = 0
            for r,c in directionsToSearch:
                if (row + r) < len(board) and (row + r) >= 0:
                    if (col+c) < len(board[0]) and (col+c) >= 0:
                        if board[row+r][col+c] == 'M':
                            count += 1
            return count

        def dfs(row,col):
            # check if current cell is out of bounds
            if row < 0 or row > len(board)-1:
                return
            if col < 0 or col > len(board[0])-1:
                return
            
            # check if current cell already revealed
            if board[row][col] != 'E':
                return
            # we haven't visited this cell yet, it's not an E 

            # count adjacent mines in relation to current position
            mines = countMines(row, col)
            # update the the cell to a integer if mines found in vicinity
            if mines > 0:
                board[row][col] = str(mines)
            # otherwise, no adjacent mines
            else:
                # set to B
                board[row][col]='B'
                # When mines == 0, it means the current square is completely safe, and all 8 of its neighbors are also completely safe from being mines. Because there is no danger of accidentally revealing a mine in the immediate neighborhood, the game safely automates the next clicks for you, expanding outward until it hits a "stop sign" (a number).
                for r,c in directionsToSearch:
                    dfs(row+r,col+c)
        
        dfs(click[0],click[1])
        return board


            
        
