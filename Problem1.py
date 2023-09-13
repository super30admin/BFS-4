#Time Complexity :O(n^n) 
#Space Complexity :O(n^n)
#Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No

from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n=len(board)
        arr=[0]*(n*n)
        q=deque()
        i=0
        r=n-1
        c=0
        flag=True
        while i<n*n:
            if board[r][c]==-1:
                arr[i]=-1
            else:
                arr[i]=board[r][c]-1
            i+=1
            if flag:
                c+=1
                if c==n:
                    flag=False
                    r-=1
                    c=n-1
            else:
                c-=1
                if c==-1:
                    flag=True
                    r-=1
                    c=0
        q.append(0)
        arr[0]=-2
        moves=0
        while q:
            qLen=len(q)
            for j in range(qLen):
                currIdx=q.popleft()
                for k in range(1,7):
                    print(k)
                    nextIdx=currIdx+k
                    if nextIdx==len(arr)-1:
                        return moves+1
                    if arr[nextIdx]!=-2:
                        if arr[nextIdx]==-1:
                            q.append(nextIdx)
                        else:
                            if arr[nextIdx]==len(arr)-1:
                                return moves+1
                            q.append(arr[nextIdx])
                        arr[nextIdx]=-2
            moves+=1
        return -1


        