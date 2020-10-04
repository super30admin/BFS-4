# Time complexity : O(n^2)
# Space complexity : O(n^2)
# Works on leetcode : Yes
#Approach - We just use BFS to find the shortest path from 1 to n*nth position. We use ltopos function to calculate 
#correct position since we need to crawl through the board in alternate column direction. 
class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n = len(board)
        def ltopos(label):
            r,c = divmod(label-1,n)
            if r%2==0:
                return n-1-r, c
            else:
                return n-1-r, n-1-c
            
        seen = set()
        queue = collections.deque()
        queue.append((1,0))
        while queue:
            label, step = queue.popleft()
            r,c = ltopos(label)
            if board[r][c]!=-1:
                label = board[r][c]
            if label==n*n:
                return step
            for i in range(1,7):
                newlabel = label+i
                if newlabel<=n*n and newlabel not in seen:
                    seen.add((newlabel))
                    queue.append((newlabel,step+1))
        return -1
        