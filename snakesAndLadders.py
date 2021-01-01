#TC=O(N*N)
#SC=O(N*N)
#BFS
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return 0
        r=len(board)
        moves=[0]*(r*r) #board is converted into 1D moves array
        #print(moves)
        idx=0 
        i=r-1
        j=0
        even=0
        while (i>=0 and j>=0):
            if board[i][j]==-1:
                moves[idx]=-1
            else:
                #print(idx,board[i][j]-1)
                moves[idx]=board[i][j]-1
            
            if(even%2==0):#row 0,2,4,---->col counted left to right 
                j+=1
            else:
                j-=1
            if(j==r):
                i-=1
                even+=1
                j-=1
            if(j<0):
                i-=1
                even+=1
                j+=1
            idx+=1
        
        minMoves=0
        q=[]
        q.append(0)
        moves[0]=-2
        while q:
            size=len(q)
            for _ in range(size):
                curr=q.pop(0)
                if(curr==len(moves)-1):
                    return minMoves
                for j in range(1,7):
                    baby= curr+j
                    if(baby<=len(moves)-1 and moves[baby]!=-2):
                        if(moves[baby]==-1):
                            q.append(baby)
                        else:
                            q.append(moves[baby])
                            #here we dont mark moves[moves[baby]] because we have some index there that will be changed to -2 without adding thst index to Q. 
                        moves[baby]=-2 
            #increment level
            minMoves+=1
        return -1
                        
                
            
                
        