# Time Complexity : O(mxn) 
# Space Complexity :O(mxn)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
class Solution:
    dirs = [[-1,0],[1,0],[0,-1],[0,1],[-1,-1],[1,-1],[-1,1],[1,1]]
    def updateBoard(self, board, click):
        if len(board) == 0 :
            return board 
        
        m , n = len(board), len(board[0])
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board 
        
        board[click[0]][click[1]] = 'B'
        
        q = [click]
        
        while len(q) != 0:
            curr = q.pop(0)
            res = self.helper(board, curr[0], curr[1])
            
            if res == 0:
                for dir in self.dirs:
                    r = dir[0] + curr[0]
                    c = dir[1] + curr[1]
                    
                    if r >= 0 and c >= 0 and r < m and c < n and board[r][c] == 'E':
                        q.append([r,c])
                        board[r][c] = 'B'
            else:
                board[curr[0]][curr[1]] = str(res)
        
        return board 
    
    def helper(self, board, r, c):
        count = 0 
        for dir in self.dirs:
            i = r + dir[0]
            j = c + dir[1]
            if i >= 0 and j >= 0 and i < len(board) and j < len(board[0]) and board[i][j] == 'M':
                count += 1 
        
        return count 
            
            
            
            
            
if __name__ == "__main__":
    s = Solution()
    
    # Test case 1 
    board1 = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]]
    click1  = [3,0]
    res = s.updateBoard(board1, click1)
    output1 = [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
    assert output1 == res
    
    # # Test case 2 
    board2 = [["B","B","B","B","B","B","1","E"],["B","1","1","1","B","B","1","M"],["1","2","M","1","B","B","1","1"],["M","2","1","1","B","B","B","B"],["1","1","B","B","B","B","B","B"],["B","B","B","B","B","B","B","B"],["B","1","2","2","1","B","B","B"],["B","1","M","M","1","B","B","B"]]
    click2 = [0,7]
    output2 = [["B","B","B","B","B","B","1","1"],["B","1","1","1","B","B","1","M"],["1","2","M","1","B","B","1","1"],["M","2","1","1","B","B","B","B"],["1","1","B","B","B","B","B","B"],["B","B","B","B","B","B","B","B"],["B","1","2","2","1","B","B","B"],["B","1","M","M","1","B","B","B"]]
    res2 = s.updateBoard(board2, click2)
    assert output2 == res2 
    