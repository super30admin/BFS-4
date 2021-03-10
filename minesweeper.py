# Time Complexity : O(MN)
# Space Complexity : O(MN)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        """
        BFS approach do DFS later
        """
        if not board:
            return board
        
        dirs = [[0,1],
                [-1,0],
               [0,-1],
               [1,0],
               [1,1], [1,-1],[-1,1],[-1,-1]] 
        
        m = len(board)
        n = len(board[0])
        #check if curr click is mine
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        queue = deque([])
        board[click[0]][click[1]] = 'B'
        queue.append(([click[0], click[1]]))
        while queue:
            curr = queue.popleft()
            #check mines in vicinity
            mines = self.getMines(board, curr[0], curr[1], dirs)
            #if no mines then add to queue and mark them visited
            if mines == 0:
                for direction in dirs:
                    r = curr[0] + direction[0]
                    c = curr[1] + direction[1]
                    if r >= 0 and r < m and c >= 0 and c < n and board[r][c] == 'E':
                        #not visited then add to queue and mark visited
                        board[r][c] = 'B'
                        queue.append(([r,c]))
            else:
                #put number of mines 
                board[curr[0]][curr[1]] = str(mines)
                
        return board
    
    def getMines(self, board, i, j, dirs):
        res = 0
        for direction in dirs:
            r = i + direction[0]
            c = j + direction[1]
            if r >= 0 and r < len(board) and c >= 0 and c < len(board[0]) and board[r][c] == 'M':
                res += 1
                
        return res