from queue import Queue
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board==None or len(board)==0:
            return 0
        minMoves=0
        r=len(board)
        n=r*r
        moves=[0]*n
        idx=0
        even=0
        i=r-1
        j=0
        while i>=0 and j>=0:
            if board[i][j]==-1:
                moves[idx]=-1
            else:
                moves[idx]=board[i][j]-1
            idx+=1
            if even%2==0:
                j+=1
            else:
                j-=1
            #reached beyond the edge of the board during even turn, bring pointer back
            if j==r:
                i-=1
                even+=1
                j-=1
    
            #reached beyond the edge of the board during odd turn to j=-1, bring pointer back
            if j<0:
                i-=1
                even+=1
                j+=1
        #BFS
        q=Queue()
        q.put(0)
        moves[0]=-2
        while(not(q.empty())):
            size=q.qsize()
            
            for l in range(size):
                curr=q.get()
                if curr==n-1:
                    return minMoves
                for k in range(1,7):
                    child=k+curr
                    #if not visited before so it should be -2 and child is <36(max index)
                    if child<n and moves[child] !=-2:
                        if moves[child]==-1:
                            q.put(child)
                        else:
                            q.put(moves[child])
                        moves[child]=-2
            minMoves+=1    
        return -1
        
Space is O(n2) becuase of visited array size and queue max depth could be O(n2)
Time is O(n2)
