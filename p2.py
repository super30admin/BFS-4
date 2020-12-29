#Time: O(n^2)
#Space: O(n^2)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        if board is None or len(board)==0: 
            return 0
        
        r = len(board)
        moves = [0]*(r*r)
        idx =0
        i = r-1
        j = 0
        even = 0
        #flatten matrix
        while i>=0 and j>=0:
            if board[i][j]==-1:
                moves[idx] =-1
            else:
                moves[idx] = board[i][j]-1
            
            idx+=1
            if even%2==0:
                j+=1
            else:
                j-=1
            if j==r:
                i-=1
                even+=1
                j-=1
            if j<0:
                i-=1
                even+=1
                j+=1
        
        minMoves=0
        q = collections.deque()
        
        q.append(0)
        moves[0] = -2
        while q:
            size = len(q)
            for _ in range(size):
                cur = q.popleft()
                if cur == len(moves)-1:
                    return minMoves
                for k in range(1,7):
                    child = cur + k
                    if child <= len(moves)-1 and  moves[child]!=-2:
                        if moves[child]==-1:
                            q.append(child)
                            
                        else:
                            q.append(moves[child])
                            
                        moves[child]=-2
                        
            minMoves+=1
        
        return -1
                        
        
        
