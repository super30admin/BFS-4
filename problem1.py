# Time Complexity :
# O(m*n) 


# Space Complexity :  
# O(m*n) 


# Approach:
# BFS, according to given game rules and conditions.



class Solution(object):
    def __init__(self):
        self.m = 0   # rows
        self.n = 0   # cols
        self.dirs = []

    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        # ===========> TC = O(m*n), SC = O(m*n) ================#
        if not board or len(board) == 0:
            return board

        self.m = len(board)      # rows
        self.n = len(board[0])   # cols

        queue = []
        self.dirs = [[-1,0],[1,0],[0,-1], [0,1],[-1,-1],[-1,1],[1,-1], [1,1]]     # U D L R UL UR LL LR

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        # append the click position to queue, to start BFS
        queue.append([click[0], click[1]])
        board[click[0]][click[1]] = 'B'

        # start BFS
        while queue:
            curr = queue.pop(0)
            mines = self.countMines(board, curr)
            if mines == 0:
                for dir in self.dirs:
                    nr = curr[0] + dir[0]
                    nc = curr[1] + dir[1]
                    if nr>=0 and nr<self.m and nc>=0 and nc <self.n and board[nr][nc] == 'E':
                        queue.append([nr, nc])
                        board[nr][nc] = 'B'
            else:
                board[curr[0]][curr[1]] =chr(mines + ord('0'))
        
        return board