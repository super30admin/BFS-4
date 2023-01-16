class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        #flatten the 2d array first
        n = len(board)
        grid = [0 for x in range(n*n)]
        even = 0
        index = 0
        row = n-1
        col = 0

        while index<n*n:
            if board[row][col]==-1:
                grid[index] = -1
            else:
                grid[index] = board[row][col]-1
            if even%2==0:
                col+=1
                if col==n:
                    col-=1
                    row-=1
                    even+=1
            else:
                col-=1
                if col==-1:
                    col+=1
                    row-=1
                    even+=1
            index+=1
            
            
            
            
        print(grid)

        q = []
        q.append(0)
        moves = 1
        while q:
            s = len(q)
            for i in range(s):
                curr = q.pop(0)
                for j in range(1,7):
                    baby = curr+j
                    if baby>n*n-1:
                        continue
                    if baby == n*n-1 or grid[baby]==n*n-1:
                        return moves
                    if grid[baby]>0:
                        q.append(grid[baby])
                        grid[baby] = -2
                    elif grid[baby]==-1:
                        q.append(baby)
                        grid[baby] = -2
                    else:
                        continue
            moves+=1
        return -1


            