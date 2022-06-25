class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board or len(board)==0 or len(board[0])==0:
            return 0
        m,n=len(board),len(board[0])
        
        i,j=m-1,0
        even=0
        counter=0
        moves=[0 for i in range(m*n)]
        
        while i>=0:
            if board[i][j]==-1:
                moves[counter]=counter
            else:
                moves[counter]=board[i][j]-1
                
            counter+=1
            if even%2==0:
                j+=1
            else:
                j-=1
            
            if j>n-1:
                even+=1
                j-=1
                i-=1
            elif j<0:
                even+=1
                j+=1
                i-=1
        q=deque()
        q.append(0)
        moves[0]=-1
        level=0
        while q:
            size=len(q)            
            for i in range(size):
                curr=q.popleft()
                
                if curr==n*m-1:
                    return level
                
                for j in range(1,7):
                    next_pos=curr+j
                    if next_pos<n*m and moves[next_pos]!=-1:
                        print([next_pos,moves[next_pos]])
                        q.append(moves[next_pos])
                        moves[next_pos]=-1
                        
            level+=1
            
        return -1
                