class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        visited={1}
        nboard=[]
        q=[(1,0)]
        n=len(board)
        '''this loop is to rearrange the board in 2D manner'''
        for i in range(n-1,-1,-1):
            if i%2!=n%2:
                for j in range(n):
                    nboard.append(board[i][j])
            else:
                for j in range(n-1,-1,-1):
                    nboard.append(board[i][j])          
        #BFS
        while q:
            a=q.pop(0)
            for i in range(6):
                try: 
                    if nboard[a[0]+i]==-1:
                        t=a[0]+i+1
                    else:
                        t=nboard[a[0]+i]
                except:
                   break     
                if t in visited:
                    continue 
                visited.add(t)       
                if t==n*n:
                    return a[1]+1   
                q.append((t,a[1]+1))        
        return -1        
                        

        