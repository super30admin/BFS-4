#time: O(m*n)
#space: O(m*n)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        m=len(board)
        n=len(board[0])
        #flatten the array
        flat=[]
        dirs='L-R'
        for i in range(m-1,-1,-1):
            if dirs == 'L-R':
                for j in range(n):
                    if (board[i][j] != -1):
                        flat.append(board[i][j]-1)
                    else:
                        flat.append(board[i][j])
                dirs='R-L'
            else:
                for j in range(n-1,-1,-1):
                    if (board[i][j] != -1):
                        flat.append(board[i][j]-1)
                    else:
                        flat.append(board[i][j])
                dirs='L-R'
        q=[0]
        flat[0]=-2
        mn=0
        f_len= len(flat)
        while len(q)!=0:
            
            size=len(q)
            for i in range(size): 
                curr=q.pop(0)
                if curr== f_len-1:
                    return mn  
                for j in range(1,7):
                    next_move= curr+j
                    if next_move < f_len and flat[next_move] != -2:
                        if flat[next_move] >-1:
                            q.append(flat[next_move])
                        else:
                            q.append(next_move)
                        flat[next_move]=-2
            mn+=1
        return -1
            

                    
        


        # add index to queue
        
