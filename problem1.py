#time complexity: O(m*n)
#spce complexity: O(m*n)
#ran on leetcode: yes
#start from index click and do BFS. Check the number of mines among currrent nodes 8 neighbors. If this is 0 then current index on board should be changed to B and all its neighbors added to the queue. If not then the nukbe rof mines should be inserted in the current position. 
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #Q=[]
        dirs=[[0,-1],[0,+1],[-1,0],[+1,0],[-1,-1],[-1,+1],[+1,-1],[+1,+1]]
        if(board[click[0]][click[1]]=='M'):
            board[click[0]][click[1]]='X'
            return board

        Q=[click]
        while(Q):
            curr=Q[0]
            del(Q[0])
            count_mine=0
            if(board[curr[0]][curr[1]]!='E'):
                continue
            for dir in dirs:
                #print(curr)
                #print(dir)
                x=curr[0]+dir[0]
                y=curr[1]+dir[1]
                if(x>=0 and x<len(board) and y>=0 and y<len(board[0]) and (board[x][y]=='M' or board[x][y]=='X')):
                    count_mine+=1
                    #board[x][y]='X'
            if(count_mine>0):
                board[curr[0]][curr[1]]=str(count_mine)
            else:
                board[curr[0]][curr[1]]='B'
                for dir in dirs:
                    x=curr[0]+dir[0]
                    y=curr[1]+dir[1]
                    if(x>=0 and x<len(board) and y>=0 and y<len(board[0]) and board[x][y]=='E'):
                        Q.append([x,y])
        return board


                
