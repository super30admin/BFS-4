#Time Complexity:O(mn)
#Space Complexity:O(n)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if len(board)==0:
            return 0
        r=len(board)
        moves=[None]*(r*r)                                          #use the moves array as the visited array to keep track of moves from a position on board
        idx=0
        i=r-1
        j=0
        even=0
        while i>=0 and j>=0:
            if board[i][j]==-1:                                     #if value on board is -1 at a position,append -1 at that position in moves
                moves[idx]=-1
            else:
                moves[idx]=board[i][j]-1
            idx+=1
            if even%2 ==0:                                          #depending on whivh direction we are moving on board check boundary conditions
                j+=1                                                #and move accordingly
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
        d=deque()                                                   #declare a queue
        minMoves=0                                                  #a variable that holds the minimum number of moves
        d.append(0)                                                 #start from position 1 on board
        moves[0]=-2                                                 #let moves be -2 indicating visited
        while d:
            size=len(d)
            for k in range(size):                                   #for every element currently in queue, if current element is the last position on board
                curr=d.popleft()
                if curr==len(moves)-1:                              #return minimum
                    return minMoves
                for l in range(1,7):                                #else check possible 6 moves from current position
                    child=curr+l
                    if child<len(moves) and moves[child]!=-2:       #if the new position is less within board and no other moves at that position , append child else append next move from that point
                        if moves[child]==-1:
                            d.append(child)
                        else:
                            d.append(moves[child])
                        moves[child]=-2
            minMoves+=1                                             #increment minmoves by one 
        return -1