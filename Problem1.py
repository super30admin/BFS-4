#Time complexity is: O(n^2)
#Space complexity is: O(n)
#No issues faced while coding
#Code ran successfully on leetcode
class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        #initializing all the required variables
        n=len(board)
        arr=[0 for i in range(n*n)]
        #taking rows and columns
        r=n-1
        c=0
        dir=True
        idx=0
        #Flattening the array
        while(idx<n*n):
            #if the index value is -1, we will place as it is
            if(board[r][c]==-1):
                arr[idx]=-1
            #if the value is other than -1, we will update it will its repective
            #index value
            else:
                arr[idx]=board[r][c]-1
            #increasing the index value
            idx+=1
            #based on the row and column we will change the dir value
            if(dir):
                c+=1
                if(c==n):
                    dir=False
                    c-=1
                    r-=1
            else:
                c-=1
                if(c==-1):
                    r-=1
                    c+=1
                    dir=True
        #Performing BFS
        #Creating queue and hashset
        q=deque()
        hset=set()
        q.append(0)
        hset.add(0)
        moves=0
        while(q):
            #We will take the current size of queue and we will pop the values
            size=len(q)
            for i in range(size):
                curr=q.popleft()
                #If the current value is equal to end of the matrix, we will return moves
                if(curr==n*n-1):
                    return moves
                #For the current child we will find all the possible
                for k in range(1,7):
                    child=curr+k
                    #If child is less than n*n value and if the child is not in hashset
                    if(child<n*n):
                        if(child not in hset):
                            #If the value is equal to -1, we will add the index
                            if(arr[child]==-1):
                                q.append(child)
                            else:
                            #Else we will add the arr[child] value into the queue
                                q.append(arr[child])
                            #We will add the vlaue into the hashset
                            hset.add(child)
            #Incrementing the number of moves
            moves+=1
        #If it does not reach the end the msatrix, we will return -1
        return -1





