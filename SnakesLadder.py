# Time Complexity - O(N^2)
# Space Complexity - O(N^2)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if(board==None or len(board)==0):
            return 0
        N = len(board) * len(board[0])
        r = len(board)
        c = len(board[0])
        moves = [0]*N
        i = r-1
        j = 0
        index = 0 
        even = 0
        while(i>=0 and j>=0):
            # Filling the moves array
            if(board[i][j]!=-1):
                moves[index]=board[i][j]-1
            else:
                moves[index]=-1
            index+=1
                
            # Moving through the board
            if (even % 2 ==0):
                j+=1
            else:
                j-=1
            if(j>=c):
                i-=1
                even+=1
                j-=1
            elif(j<0):
                i-=1
                j+=1
                even+=1
                
        queue=[]
        queue.append(0)
        moves[0] = -2
        minNoOfMoves = 0
        while queue:
            size= len(queue)
            for i in range(size):
                curr=queue.pop(0)
                if(curr == N-1):
                    return minNoOfMoves
                for k in range(1,7):
                    child = curr + k
                    if(child < N and moves[child] != -2):
                        if(moves[child]> -1):
                            queue.append(moves[child])
                            moves[child] = -2
                        else:
                            queue.append(child)
                            moves[child] = -2
            minNoOfMoves+=1
        return -1
                            
                         
