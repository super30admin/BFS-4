class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        #bfs
        #O(mn) for both
        if not board:
            return board
        #get count of neighbour 8 cells if anything has mine
        def getMines(board,i,j):
            mines=0
            for diri in self.dirs:
                r=i+diri[0]
                c=j+diri[1]
                if r>=0 and r<self.m and c>=0 and c<self.n and board[r][c]=='M':
                    mines+=1
            return mines
        
        
        
        res=[]
        self.dirs=[[0,-1],[-1,0],[1,1],[-1,-1],[-1,1],[1,-1],[0,1],[1,0]]
        self.m=len(board)
        self.n=len(board[0])
        q=deque()
        #if start cell is mine itself> game over
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]="X"
            return board
        #begin with startcell given
        q.append((click[0],click[1]))
        #mark it visited> state change
        board[click[0]][click[1]]="B"
        while q:
            cur=q.popleft()
            #find number of neighbor mines
            mines=getMines(board,cur[0],cur[1])
            
            #case E
            if mines==0:
                #if current cell is E> then go to all its neighbor E cells and mark visted recursively
                for diri in self.dirs:
                    r=cur[0]+diri[0]
                    c=cur[1]+diri[1]
                    if r>=0 and r<self.m and c>=0 and c<self.n and board[r][c]=="E":
                        q.append((r,c))
                        board[r][c]="B"
            # according to no. of mines detected> replace with char(1...8)      
            else:
                board[cur[0]][cur[1]]=chr(ord(str(mines)))
        #when no more squares to be revealed
        return board 
    
    
    """"
    #dfs#
    
    def dfs(board,click):
    if (invalid boundary conditions)  or [click[0]][click[1]]!='E':
        return
    mines=getMines(board,click[0],click[1])
    board[click[0]][click[1]]='B'
    if mines==0:
        for dir in self.dirs:
        r=
        c=
        dfs(board,(r,c))
    else:
        board[click[0]][click[1]]=chr(ord(str(mines)))
    
    """"
        
        