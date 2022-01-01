#Time O(n*2), space O(n*n)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n=len(board)
        s=n*n
        moves=[0]*n*n
      
    
        q=deque()
        r=n-1
        c=0
        e=0
        i=0
        minmoves=0
        
        #Preprocessing to create moves array
        while r>=0:
            
            if board[r][c] == -1:
                moves[i] = i
            else:
                moves[i]=board[r][c] - 1
                
            i+=1
            
            if e%2==0:
                c+=1
            else:
                c-=1
                
            if c==n:
                c-=1
                r-=1
                e+=1
                
            if c==-1:
                c+=1
                r-=1
                e+=1
               
        q.append(moves[0])
        moves[0]=-2
 
#BFS Queue traversal
        while q:
            for j in range(len(q)):
                cur = q.popleft()
                print(cur)
                #Base
                if cur == n*n-1:
                    return minmoves
                
                for k in range(1,7):
                    ne = cur + k
                    print(ne)
                    if ne < n*n and moves[ne]!=-2:
                        q.append(moves[ne])
                        moves[ne]=-2
                        
            minmoves+=1
            
        return -1
                
