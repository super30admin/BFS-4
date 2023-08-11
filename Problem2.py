#Time complexity is: O(m*n)
#Space complexity is: O(m*n)
#No issues faced while coding
#Code ran successfully on leetcode
class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        #base condition
        if(board==None or len(board)==0):
            return board
        #here m is the number of rows and n is no of columns
        m=len(board)
        n=len(board[0])
        #Directions array
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
        #If the value in the board is M at a particular index, we change that to X and we will return the board
        if(board[click[0]][click[1]]=="M"):
            board[click[0]][click[1]]="X"
            return board
        #Creating a queue and adding the initial click values into that and updating the value to B
        q=deque()
        q.append([click[0],click[1]])
        board[click[0]][click[1]]="B"
        #iterating through queue
        while(q):
            #Popping queue values
            curr=q.popleft()
            #Finding the number of mines around the curr place
            count=self.countMines(board,curr[0],curr[1],m,n)
            #If the count is 0
            if(count==0):
                #We will go in all directions and we will calculate new rows and columns
                for dir in self.dirs:
                    nr=dir[0]+curr[0]
                    nc=dir[1]+curr[1]
                    #Condition below checks boundaries and checks if it is E or not
                    if(nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=="E"):
                        #WE will append that to queue and we will update that to B
                        q.append([nr,nc])
                        board[nr][nc]="B"
            else:
                #Else we will udpate the value to count
                board[curr[0]][curr[1]]=str(count)
        #Finally  we will return the board
        return board

    #Function to count the number of mines 
    def countMines(self,board,i,j,m,n):
        count=0
        #We will check in all the 8 direction if there is any unrevealed mine or not
        for dir in self.dirs:
            nr=dir[0]+i
            nc=dir[1]+j
            if(nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=="M"):
                count+=1
        #Finally we will return the count of mines
        return count

