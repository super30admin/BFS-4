# Time Complexity : O(m*n)
# Space Complexity :O(m*n)

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:


        dirs = [[0, -1], [-1, -1], [-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1]]

        
        def mines(board, click):
            count = 0
            for dr, dc in dirs:
                r,c = click[0] + dr, click[1] + dc
                if 0 <= r < len(board) and 0 <= c < len(board[0]) and board[r][c] == "M":
                    count+=1
            return count
        
        q = deque([click])
        
        while q:
            r,c = q.popleft()
            if board[r][c] == "M":
                board[r][c] = "X"
                return board
            if board[r][c] == "E":
                minesCount = mines(board, (r,c))
                if minesCount > 0:
                    board[r][c] = str(minesCount) 
                else:
                    board[r][c] = "B"
                    for dr, dc in dirs:
                        nr, nc = r + dr, c + dc
                        if 0 <= nr < len(board) and 0<= nc < len(board[0]) and board[nr][nc] == "E":
                            q.append((nr, nc))
        return board
    
             
   
