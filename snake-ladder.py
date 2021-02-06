class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        #O(boardsize) for both
        if not board:
            return 0
        n=len(board)
        q=deque()
        q.append(0)
        boardsize=n*n
        #flatten 2d to 1d arary
        moves=[-1]*(boardsize+1)
        #state change>visited info
        moves[0]=-2
        #start with bottomleft corner(value 1)
        i=n-1
        j=0
        #falg as direction
        flag=0
        index=0
        #flattening
        while i>=0 and j>=0:
            #if snake/ladder: enter corresponding index
            if board[i][j]!=-1:
                moves[index]=board[i][j]-1
            index+=1
            #if even row>going from left to right
            if flag%2==0:
                j+=1
            #if odd row> reverse direction
            else:
                j-=1
            #if reached right boundary>
            #flip direction> move from R to L now
            if j==n:
                flag+=1
                i-=1
                j-=1
             #if reached right boundary>
            #flip direction >move in reverse direction
            if j<0:
                i-=1
                j+=1
                flag+=1
        #bfs
        level=0
        while q:
            size=len(q)
            for i in range(size):
                cur=q.popleft()
                #if reached 35>return answer
                if cur==n*n-1:
                    return level
                #each level(1 to 6 dice roll)
                for l in range(1,7):
                    #(1> 2,3,4,5,6,7)
                    baby=cur+l
                    #check boundary and if visited 
                    if baby<boardsize and moves[baby]!=-2:
                        #if normal value> append to queue the corresponding index
                        if moves[baby]==-1:
                            q.append(baby)
                        else:
                            #if snake/ladder> enter that new index
                            q.append(moves[baby])
                        #mark visited
                        moves[baby]=-2
            level+=1
        return -1
        
        
                
        