# // Time Complexity :o(n*n)
# // Space Complexity :O(n*n) 
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n=len(board)
        moves=[0 for i in range(n*n)]
        r=n-1
        c=0
        even=0
        idx=0
        while r>=0 and c>=0:
            if board[r][c]==-1:
                moves[idx]=-1
            else:
                moves[idx]=board[r][c]-1
            idx+=1
            if even%2==0:
                c+=1
                if c==n:
                    c=c-1
                    r-=1
                    even+=1
            else:
                c-=1
                if c==-1:
                    c+=1
                    r-=1
                    even+=1
        q=[]
        count=0
        q.append(0)
        moves[0]=-2
        while q:
            size=len(q)
            for i in range(size):
                curr=q.pop(0)
                if curr==(n*n)-1:
                    return count
                for j in range(1,7):
                    child=curr+j
                    if child > (n*n)-1:
                        continue
                    if moves[child]!=-2:
                        if moves[child]==-1:
                            q.append(child)
                        else:
                            q.append(moves[child])
            
                    moves[child]=-2
            count+=1
        return -1
                            
                    
            