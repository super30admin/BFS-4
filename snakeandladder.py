'''
time complexity: O(n)
space complexity: O(1)
'''
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [-1 for _ in range(n*n)]
        idx = 0
        evenflag = 0
        j = 0
        i = n-1
        while(idx<n*n):
            if(board[i][j]==-1):
                arr[idx] = board[i][j]
            else:
                arr[idx] = board[i][j]-1
            idx+=1
            if(evenflag%2==0):
                j+=1
                if(j>=n):
                    j-=1
                    i-=1
                    evenflag+=1
            else:
                j-=1
                if(j<0):
                    j+=1
                    i-=1
                    evenflag+=1
        print(arr)
        q = deque([])
        q.append(0)
        arr[0]=-2
        level=0
        while(len(q)!=0):
            sz = len(q)
            for _ in range(sz):
                curr = q.popleft()
                if(curr== n*n -1): return level
                for i in range(1,7):
                    newIdx = curr + i
                    
                    if(newIdx< n*n and arr[newIdx]!=-2):
                        if(arr[newIdx]>-1):
                            q.append(arr[newIdx])
                        else:
                            q.append(newIdx)
        
                        arr[newIdx] = -2
            level+=1
        return -1