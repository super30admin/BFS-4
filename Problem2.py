# Problem 2: Minesweeper
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m = len(board)
        n = len(board[0])
        dirs = [[0,1],[0,-1],[1,0],[-1,0],[-1,-1],[1,-1],[-1,1],[1,1]]
        q = deque()

        def getMines(board,i,j):
            count = 0
            for Dir in dirs:
                r = Dir[0] + i
                c = Dir[1] + j
                if r >= 0 and c >= 0 and r <= m-1 and c <= n-1 and board[r][c] == "M":
                    count += 1
            return count

        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board
        
        q.append(click)
        board[click[0]][click[1]] = "B"

        while q:
            curr = q.popleft()
            count = getMines(board,curr[0],curr[1])
            if count != 0:
                board[curr[0]][curr[1]] = str(count)
            else:
                for Dir in dirs:
                    r = Dir[0] + curr[0]
                    c = Dir[1] + curr[1]
                    if r >= 0 and c >= 0 and r <= m-1 and c <= n-1 and board[r][c] == "E":
                        q.append([r,c])
                        board[r][c] = "B"

        return board

        
        
        