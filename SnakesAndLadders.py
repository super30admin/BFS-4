from typing import collections, List
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # convert the array to flat array
        n = len(board)
        i = 0
        arr = [-1]*(n*n)
        # for directions
        flag = True
        r , c= n-1, 0
        while i <n*n:
            if board[r][c] == -1:
                arr[i] = -1
            else :
                arr[i] = board[r][c]-1
            i+=1
            if flag:
                c+=1
                # direction change
                if c == n:
                    r-=1
                    c-=1
                    flag = False
            else:
                c-=1
                # direction change
                if c == -1:
                    c+=1
                    r-=1
                    flag = True
        q = collections.deque([0])
        level = 0
        while q:
            for _ in range(len(q)):
                curr = q.popleft()
                for i in range(1,7):
                    newidx = i+curr
                    if newidx >= n*n:
                        continue
                    if newidx == (n*n)-1 or arr[newidx] == (n*n)-1:
                        return level+1
                    if arr[newidx] != -2:
                        if arr[newidx] == -1:
                            q.append(newidx)
                        else:
                            q.append(arr[newidx])
                        arr[newidx] = -2
            level+=1
        return -1
        