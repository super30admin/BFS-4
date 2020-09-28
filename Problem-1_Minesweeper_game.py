# APPROACH 1: BFS
# Time Complexity : O(m * n), m, n: rows and columns of the given board
# Space Complexity : 
# Did this code successfully run on Leetcode : 
# Any problem you faced while coding this : 
#
#
# Your code here along with comments explaining your approach
# 1. Firstly, check if the given click position is on a mine or not. If so, change it to 'X' and return the board
# 2. Else, initially add click position to the queue and change it to 'B' (acts like a visited array also with this mark). Keep processing till queue is empty
# 3. For each node dequeued -
#       - check the number of mines in it's neighborhood (all 8 directions)
#       - If mines exist then replace the cell (currently 'B') with number of mines
#       - If there were no mines, then add it's neighborhood cell's position to queue (if they were not visited, not marked as 'B'). and then mark them as 'B'. 

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        queue = deque()
        dirs = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]
        queue.append(click)
        board[click[0]][click[1]] = 'B'
        
        while queue:
            size = len(queue)
            while size > 0:
                curr = queue.popleft()
                mine_count = 0
                
                for nei in dirs:
                    nei_r = curr[0] + nei[0]
                    nei_c = curr[1] + nei[1]
                    if nei_r >= 0 and nei_r < len(board) and nei_c >= 0 and nei_c < len(board[0]) and board[nei_r][nei_c] == 'M':
                        mine_count += 1
                        
                if mine_count == 0:
                    for nei in dirs:
                        nei_r = curr[0] + nei[0]
                        nei_c = curr[1] + nei[1]
                        if nei_r >= 0 and nei_r < len(board) and nei_c >= 0 and nei_c < len(board[0]) and board[nei_r][nei_c] == 'E':
                            queue.append([nei_r, nei_c])
                            board[nei_r][nei_c] = 'B'
                            
                else:
                    board[curr[0]][curr[1]] = str(mine_count)
                    
                size -= 1
                    
        return board
