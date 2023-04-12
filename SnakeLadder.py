#All TC passed on leetcode

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:

        #Here we store values of 2D matrix into 1D array. 
        #Then perform BFS on children formed by dice values 1-6 from cur positions. 
        #Time complexity - O(n^2) where n is length of matrix
        #Space complexity - O(n^2)
        values = []
        n = len(board)
        i=n-1
        j=0
        idx=0
        isEven = True

        while idx<n*n:
            if isEven:
                #move L to R
                if board[i][j]==-1:
                    values.append(board[i][j])
                else:
                    values.append(board[i][j]-1)

                if j==n-1:
                    i-=1
                    j=n-1
                    isEven=False
                else:
                    j+=1
            else:
                #move R to L
                if board[i][j]==-1:
                    values.append(board[i][j])
                else:
                    values.append(board[i][j]-1)
                
                if j==0:
                    i-=1
                    j=0
                    isEven=True
                else:
                    j-=1
            idx+=1
        print(values)
        q = collections.deque()
        moves=0
        q.append((0))

        while q:
            for k in range(len(q)):
                cur = q.popleft()
                if cur==(n*n)-1:
                        return moves
                for l in range(1,7):
                    newVal = cur+l
                    if newVal<(n*n) and values[newVal]!=-2:
                        if values[newVal]==-1:
                            q.append(newVal)
                        else:
                            q.append(values[newVal])
                            val = values[newVal]
                            #values[val] = -2
                        values[newVal] = -2
            moves+=1

        return -1



        