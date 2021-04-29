#Time Complexity= O(n^2)
#Space complexity= O(n^2)


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n=len(board)
        m=len(board[0])
        i=n-1
        j=0
        counter=0
        even=0
        grid=[0 for i in range(m*n)] 
        #print(grid)
        
        while i>=0 and j>=0:
            if board[i][j]==-1:
                grid[counter]=counter
            else:
                grid[counter]=board[i][j]-1
            counter+=1
            if even%2==0:
                j+=1
            else:
                j-=1
                
            if j>=m:
                j-=1
                even+=1
                i-=1
            elif j<0:
                j+=1
                even+=1
                i-=1
        print(grid)
        
        queue=deque()
        queue.append(0)
        grid[0]=-2
        level=0
        
        while queue:
            size=len(queue)
            for i in range(size):
                front=queue.popleft()

                for k in range(1,7):
                    i=front+k

                    if grid[i]==len(grid)-1:
                        return level+1

                    if i<len(grid) and grid[i]!=-2:
                        queue.append(grid[i])
                        grid[i]=-2
                    
            level+=1
        return -1
                
                
                
        
        
            
            
        

                
