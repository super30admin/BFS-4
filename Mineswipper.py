#All TC passed on leetcode

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:

        #BFS approach
        #We add cells which do not have any mine neighbors to queue.
        #DFS can also be done
        #Time complexity - O(m.n) - size of matrix
        #Space complexity - O(m.n) - size of matrix
        m = len(board)
        n = len(board[0])
        directions = [[-1,0], [1,0], [0,1], [0,-1], [1,1], [-1,-1], [-1,1], [1,-1]]

        q = collections.deque()
        q.append((click[0], click[1]))

        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]] = 'X'
            return board
        board[click[0]][click[1]] = 'B'
        while q:
            r, c = q.popleft()
            count = self.getMineCount(board, r, c, directions, m, n)
            if count>0:
                board[r][c] = str(count)
            else:
                for dr, dc in directions:
                    nr = r+dr
                    nc = c+dc
                    if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='E':
                        q.append((nr,nc))
                        board[nr][nc]='B'
        return board

    def getMineCount(self, board, r, c, directions, m, n):
        count=0
        for dr, dc in directions:
            nr = r+dr
            nc = c+dc
            if nr>=0 and nc>=0 and nr<m and nc<n and board[nr][nc]=='M':
                count+=1
        return count





        