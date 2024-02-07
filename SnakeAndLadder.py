#TC: O(m*n)
#SC: O(m*n)

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        m = len(board)
        n = len(board[0])
        count = 0
        straight = True
        arr = []
        que = collections.deque()
        
        # Flattening the board matrix
        for i in range(m-1, -1, -1):
            if straight:
                for j in range(0, n):
                    if board[i][j]==-1:
                        arr.append(-1)
                    else:
                        arr.append(board[i][j]-1)
                straight = False
            else:
                for j in range(n-1, -1, -1):
                    if board[i][j]==-1:
                        arr.append(-1)
                    else:
                        arr.append(board[i][j]-1)
                straight = True
        #print(arr)
        que.append(0)
        arr[0] = -2
        
        while que:
            size=len(que)
            count+=1
            for s in range(size):
                index = que.popleft()
                for i in range(1, 7):
                    nd = i + index
                    if nd == len(arr)-1:
                        return count
                    if arr[nd] != -2:
                        if arr[nd]==-1:
                            que.append(nd)
                            arr[nd]=-2    
                        else:
                            if arr[nd] == len(arr)-1:
                                return count
                            if arr[arr[nd]]!=-2: 
                                que.append(arr[nd])
                                arr[nd]=-2
        return -1
