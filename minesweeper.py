"""
Time Complexity : O(M*N)
Space Complexity : O(M*N)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Explaination
METHOD 1: BFS
we start queue with the click index 
we keep the visited by making house "B"
at 8 directions we check how many mines are there
if mines are not there then only we append surrounding houses into the queue
else we make that index = mines

METHOD 2: DFS
we do same as above in DFS with recursion 


"""
class Solution:
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board == None or len(board) == 0: return board
        m,n = len(board), len(board[0])
        dirs = [(1,0),(0,1),(-1,0),(0,-1),(-1,-1),(1,1),(1,-1),(-1,1)]
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board
        def getMines(i,j):
            count = 0
            for x,y in dirs:
                r = i + x
                c = j + y
                if r>=0 and c>=0 and r<m and c<n and board[r][c] == 'M':
                    count += 1
            return count
        
        def dfs(i,j):
            #base
            if i<0 or j<0 or i>=m or j>=n or board[i][j] == 'B':
                return
            #logic
            mines = getMines(i,j)
            if mines > 0:
                board[i][j] = str(mines)
                return 

            board[i][j] = "B"
            for x,y in dirs:
                r = x + i
                c = y + j
                dfs(r,c)
                
        
        dfs(click[0],click[1])
        return board
        
        
#         while q:
#             cur = q.popleft()
#             mines = getMines(cur[0],cur[1])
#             if mines == 0:
#                 # board[cur[0]][cur[1]] = "B"
#                 for x,y in dirs:
#                     r = cur[0] + x
#                     c = cur[1] + y
#                     if r>=0 and c>=0 and r<m and c<n and board[r][c] == 'E':
#                         q.append((r,c))
#                         board[r][c] = "B"
#             else:
#                 board[cur[0]][cur[1]] = str(mines)
        
#         return board
    
                    
        
        
        