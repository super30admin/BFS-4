from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        """BFS implementation
        Time complexity-O(n^2) where n is the number of rows or columns as both are equal
        Space complexity-O(n^2)"""
        linearboard=[None for _ in range(len(board)*len(board))]
        row=len(board)-1
        col=0
        even=0
        cnt=0
        index=0
        while row>=0 and col>=0:
            if board[row][col]==-1:
                linearboard[index]=-1
            else:
                linearboard[index]=board[row][col]-1
            index+=1
            if even%2==0:
                col+=1
                if col==len(board[0]):
                    even+=1
                    row-=1
                    col-=1
            else:
                col-=1
                if col==-1:
                    even+=1
                    col+=1
                    row-=1
        q=deque()
        q.append(0)
        linearboard[0]=-2
        while q:
            size=len(q)
            for i in range(size):
                curr=q.popleft()
                if curr==len(linearboard)-1:
                    return cnt
                for j in range(1,7):
                    nextele=curr+j
                    if nextele>len(linearboard)-1:
                        break
                    if nextele>=0 and nextele<len(linearboard) and linearboard[nextele]!=-2:
                        if linearboard[nextele]==-1:
                            q.append(nextele)
                        else:
                            q.append(linearboard[nextele])
                        linearboard[nextele]=-2
            cnt+=1
        return -1
                                
                        
                        
                        
                    
            
                
                
            
                
                
                
                
        