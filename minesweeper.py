'''
time complexity: O(m+n)
space complexity: O(m+n)
'''
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        global dirs,r,c
        r = len(board)
        c = len(board[0])
        dirs = [(0,1),(0,-1),(1,0),(-1,0),(-1,-1),(1,1),(1,-1),(-1,1)]
        if(board[click[0]][click[1]] == 'M'):
            board[click[0]][click[1]] = 'X'
            return board
        q = deque([])
        q.append(click)
        board[click[0]][click[1]] = 'B' 
        while(len(q)!=0):
            curr = q.popleft()
            totalMines = self.CountMines(board,curr[0],curr[1])
            if(totalMines > 0):
                board[curr[0]][curr[1]] = str(totalMines)
            else:
                for d1,d2 in dirs:
                    newd1 = curr[0] + d1
                    newd2 = curr[1] + d2
                    
                    if(newd1>=0 and newd1<r and newd2>=0 and newd2<c and board[newd1][newd2] == 'E'):
                        board[newd1][newd2] = 'B' 
                        q.append([newd1,newd2])
        
        return board
    
    def CountMines(self,board,i,j):
        global dirs,r,c
        count = 0
        for d1,d2 in dirs:
            newd1 = i + d1
            newd2 = j + d2
            if(newd1>=0 and newd1<r and newd2>=0 and newd2<c and board[newd1][newd2] == 'M'): count+=1
        return count