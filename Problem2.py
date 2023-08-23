
'''
Problem: Minesweeper
Time Complexity: O(m*n), 
Space Complexity: O(m*n)
Did this code successfully run on Leetcode: Yes
Any problem you faced while coding this: No
Your code here along with comments explaining your approach:
        Applied BFS as there are connected components
        If encountered mine then do not process neighbors
        else process the neighbors  
'''
class Solution:
    def countMines(self,board,r, c, m , n, dirs):
        count = 0
        for d in dirs:
            nr = r + d[0]
            nc = c + d[1]
            if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='M':
                count+=1
        return count

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        m = len(board)
        n = len(board[0])

        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board

        dirs=[(1,0), (0,1), (-1,0), (0,-1), (-1,-1), (-1,1), (1,-1), (1,1)]

        queue = deque()
        queue.append((click[0], click[1]))
        board[click[0]][click[1]] = 'B'

        while queue:
            cr, cc = queue.popleft()
            count = self.countMines(board, cr, cc, m , n, dirs)
            if count==0:
                for d in dirs:
                    nr = cr + d[0]
                    nc = cc + d[1]
                    if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='E':
                        board[nr][nc] = 'B'
                        queue.append((nr,nc))
            else:
                board[cr][cc] = str(count)
        
        return board
