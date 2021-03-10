class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        '''
        Time, Space Complexity: O(n^2)
        '''
        # preprocess
        # visited, modify as -2
        b = []
        k=0
        for i in range(len(board)-1,-1, -1):
            if(k%2==0):
                # move from left to right
                for j in range(0,len(board[0])):
                    b.append(board[i][j])
            else:
                # move from right to left
                for j in range(len(board[0])-1,-1,-1):
                    b.append(board[i][j])
                    
            k+=1
        
        q = deque([0])
        b[0] = -2
        n=0
        while(len(q)>0):
            size = len(q)
            for i in range(0,size):
                k = q.popleft()
                if(k==len(b)-1):
                    return n
                for j in range(k+1,k+7):
                    if(j>=len(b)):
                        continue
                    if(b[j]==-1):
                        q.append(j)
                        b[j] = -2
                    elif(b[j]>0):
                        if(b[j]-1!=-2):
                            q.append(b[j]-1)
                        #Why?
                        #b[b[j]-1] = -2 (Reason, more than one ladder cannot be climbed at one chance)
                        b[j] = -2
            n+=1
        return -1
        
                    
