# BFS-4

## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)
# Time Complexity =O(mn)
# Space Complexity =O(m+n)


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        row=deque()
        col=deque()
        row.append(click[0])
        col.append(click[1])
        board[click[0]][click[1]]='B'
        dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
        while row and col:
            curr=row.popleft()
            curc=col.popleft()
            mines=0
            for i in dirs:
                newr1=curr+i[0]
                newc1=curc+i[1]
                if newr1>=0 and newr1<len(board) and newc1>=0 and newc1<len(board[0]) and board[newr1][newc1]=='M':
                    mines+=1
            if mines!=0:
                board[curr][curc]=chr(mines+ord('0'))
            else:
                for i in dirs:
                    newr=curr+i[0]
                    newc=curc+i[1]
                    if newr>=0 and newr<len(board) and newc>=0 and newc<len(board[0]) and board[newr][newc]=='E':
                        row.append(newr)
                        col.append(newc)
                        board[newr][newc]='B'
        return board 
                    
        
        
        

## Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)

# Time Complexity =O(n)
# Space Complexity =O(n)

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n=len(board)
        moves=[0 for _ in range(n*n)]
        row=n-1
        col=0
        even=0
        index=0
        while row>=0:
            if board[row][col]==-1:
                moves[index]=index
            else:
                moves[index]=board[row][col]-1
            index+=1
            if even%2==0:
                col+=1
            else:
                col-=1
            if col==n:
                col-=1
                row-=1
                even+=1
            if col==-1:
                col+=1
                row-=1
                even+=1
        q=deque()
        q.append(moves[0])
        moves[0]=-2
        mini=0
        while q:
            s=len(q)
            for i in range(s):
                curr=q.popleft()
                if curr==(n*n)-1:
                        return mini
                for k in range(1,7):
                    late=curr+k
                    if late<n*n and moves[late]!=-2 :
                        q.append(moves[late])
                        moves[late]=-2
            mini+=1
        return -1
                        
                        
                
            
        
        

