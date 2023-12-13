# Time complexity: O(mn)
# Space complexity: O(mn)
# Did it run on Leetcode: yes

# we do bfs at each level and update it to B. if it has no mines newr, then process its babies n the next level. if it has mnes, then instead of processing its babies,
# update he value form B to the count of mines near
from Queue import Queue
class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        def countMines(board,r,c,dirs):
            count=0
            for dir in dirs:
                nr=r+dir[0]
                nc=c+dir[1]
                if(nr>=0 and nr<len(board) and nc>=0 and nc<len(board[0]) and board[nr][nc]=='M'):
                    count+=1
            return count

        m=len(board)
        n=len(board[0])
        dirs=[[0,1], [0,-1], [1,0],[1,1], [-1,0],[1,-1],[-1,1],[-1,-1]]
        if(board[click[0]][click[1]]=='M'):
            board[click[0]][click[1]]='X'
            return board
        q=Queue()
        q.put(click)
        board[click[0]][click[1]]='B'
        while not q.empty():
            curr=q.get()
            count=countMines(board,curr[0],curr[1],dirs)
            if(count==0):
                for dir in dirs:
                    nr=curr[0]+dir[0]
                    nc=curr[1]+dir[1]
                    if(nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=='E'):
                        q.put([nr,nc])
                        board[nr][nc]='B'
            else:
                board[curr[0]][curr[1]]=chr(count+ord('0'))
        return board

