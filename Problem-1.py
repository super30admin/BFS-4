#Time Complexity :o(n*n) 
#Space Complexity :o(n*n)
#Did this code successfully run on Leetcode :yes
#Any problem you faced while coding this :no

class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        if(board==None or len(board)==0):
            return 0
        n= len(board)
        grid=[0]*(n*n)
        
        even=0
        i=n-1
        j=0
        indx=0
        while(j>=0 and i>=0 and i<n and j<n):
            if(board[i][j]==-1):
                grid[indx]=-1
            else:
                grid[indx]=board[i][j]-1
            indx+=1
            if(even%2==0):
                j+=1
            else:
                j-=1
            if(j==n):
                i-=1
                j-=1
                even+=1
            elif(j<0):
                i-=1
                j+=1
                even+=1
        
        queue=collections.deque()
        queue.append(0)
        res=0
        while queue:
            size=len(queue)
            for i in range(size):
                curr = queue.popleft()
                if(curr==(n*n-1)):
                    return res
                for k in range(1,7):
                    newIndex=curr+k
                    if(newIndex<(n*n)):
                        if(grid[newIndex]!=-1 and grid[newIndex]!=-2):
                            queue.append(grid[newIndex])
                            grid[newIndex]=-2
                        elif(grid[newIndex]==-1):
                            queue.append(newIndex)
                            grid[newIndex]=-2
            res+=1
        return -1
                    
        