#time complexity o(m*n)
#space complexity o(m*n)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        dirs = [
                (0, 1),  (0, -1),  (1, 0),  (-1, 0),  # up, down, right, left
                (1, 1),  (1, -1),  (-1, 1), (-1, -1)  # diagonals
                ]

        m = len(board)
        n = len(board[0])

        q = deque()

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        q.append(click)

        while q:
            i,j = q.popleft()
            count = self.countMines(board, i,j,dirs)
            if count == 0:
                board[i][j] = 'B'
                for dire in dirs:
                    nr = dire[0] + i
                    nc = dire[1] + j
                    if 0 <= nr < m and 0 <= nc < n and board[nr][nc] == 'E':
                        q.append((nr,nc))
                        board[nr][nc] = 'B'
            else:
                board[i][j] = str(count)
        return board
                
                

    def countMines(self, board: List[List[str]], i: int, j: int, dirs: List[Tuple[int, int]]) -> int:
        count = 0

        for dire in dirs:
            nr = dire[0] + i
            nc = dire[1] + j
            #bounds check
            if 0 <= nr < len(board) and 0 <= nc < len(board[0]) and board[nr][nc] == 'M':
                count += 1
        
        return count
        