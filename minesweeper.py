class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        ## Approach 2 : DFS
        ## T.C = O(m.n)
        ## S.C = O(m.n)

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        ROWS = len(board)
        COLS = len(board[0])

        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]

        def count_mines(curr_x, curr_y):
            cnt = 0
            for i, j in dirs:
                p, q = curr_x + i, curr_y + j
                if p in range(ROWS) and q in range(COLS) and board[p][q] == 'M':
                    cnt += 1
            return cnt
        
        def dfs(curr_x, curr_y):
            ## base case
            if curr_x not in range(ROWS) or curr_y not in range(COLS) or board[curr_x][curr_y] != 'E':
                return
                        
            mines = count_mines(curr_x, curr_y)
            
            if mines != 0:
                board[curr_x][curr_y] = str(mines)
            else:
                board[curr_x][curr_y] = 'B'
                for i, j in dirs:
                    p, q = curr_x + i, curr_y + j
                    dfs(p, q)
        
        dfs(click[0], click[1])
        return board

        #######################################################################################
        ## Approach 1 : BFS
        ## T.C = O(m.n)
        ## S.C = O(m.n)

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        ROWS = len(board)
        COLS = len(board[0])

        queue = [click]
        board[click[0]][click[1]] = 'B'

        dirs = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]

        def count_mines(curr_x, curr_y):
            cnt = 0

            for i, j in dirs:
                p, q = curr_x + i, curr_y + j
                if p in range(ROWS) and q in range(COLS) and board[p][q] == 'M':
                    cnt += 1
            return cnt

        while queue:
            curr_x, curr_y = queue.pop(0)
            mines = count_mines(curr_x, curr_y)
            
            if mines != 0:
                board[curr_x][curr_y] = str(mines)
            else:
                for i, j in dirs:
                    p, q = curr_x + i, curr_y + j
                    if p in range(ROWS) and q in range(COLS) and board[p][q] == 'E':
                        board[p][q] = 'B'
                        queue.append([p, q])
        
        return board





