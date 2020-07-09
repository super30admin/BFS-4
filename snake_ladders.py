// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach:


# Time complexity --> o(n*n)
# space complexity --> o(n*n)
from collections import deque
class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n=len(board)
        count1=0
        a=n*n
        out=[-1 for i in range(a)]
        count=0
        i=n-1
        j=0
        dir1=True
        #since the input order given is different we have stored the elements into an array for better traversal.
        while i>=0 and j>=0 and j<n:
            if board[i][j]==-1:
                out[count]=-1
            else:
                out[count]=board[i][j]-1
            count=count+1
            if dir1==True:
                j=j+1
            else:
                j=j-1
            if j>=n:
                j=j-1
                i=i-1
                dir1=not dir1
            if j<0:
                j=j+1
                i=i-1
                dir1=not dir1
        # print(out)
        #using BFS to get the min number of steps required to reach the end.
        queue=deque([0])
        out[0]=-2
        while len(queue)!=0:
            size=len(queue)
            for i in range(size):
            #Maintaining the size as each level is traversed we have to count the number of steps it took to reach the destination.
                index1=queue.popleft()
                #If it reached the first block then it means that it has reached the req destination.
                if index1==a-1:
                    return count1
                #As dice has six sides we have the probability of 6 values.
                for j in range(1,7):
                    index2=index1+j
                    if index2<len(out) and out[index2]!=-2:
                        if out[index2]!=-1:
                            queue.append(out[index2])
                        else:
                            queue.append(index2)
                        out[index2]=-2
            count1=count1+1
        return -1