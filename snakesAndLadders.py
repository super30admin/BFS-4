#Time complexity: O(mn)
#Space complexity: O(mn)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return 0
        self.min = 0
        m = len(board)
        n = len(board[0])
        arr = list()
        var = 0
        for i in range(m-1,-1,-1):
            if var % 2 == 0:
                for j in range(n):
                    if board[i][j] != -1:
                        arr.append(board[i][j]-1)
                    else:
                        arr.append(board[i][j])
            else:
                for j in range(n-1,-1,-1):
                    if board[i][j] != -1:
                        arr.append(board[i][j]-1)
                    else:
                        arr.append(board[i][j])
            var += 1
        q=deque()
        q.append(0)
        arr[0]=-2
        level=0
        while q:
            size=len(q)
            for i in range(size):
                curr=q.popleft()
                #print(curr)
                if curr==len(arr)-1:
                    return level
                for j in range(1,7):
                    newIdx=curr+j
                    if newIdx<len(arr) and arr[newIdx]!=-2:
                        if arr[newIdx]<0:
                            q.append(newIdx)
                        else:
                            q.append(arr[newIdx])
                        arr[newIdx]=-2
            level+=1
        return -1
                