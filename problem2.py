class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board[0])
        
        arr = [-1 for i in range(n*n)]
        r = n-1
        c = 0
        di = 0
        idx=0
        arr[idx] = board[r][c]
        while(r>=0 and c>=0):
            if(board[r][c]==-1):
                arr[idx]= board[r][c]
            else:
                arr[idx] = board[r][c]-1
            if(di==0):
                c+=1
                if(c==n):
                    c=n-1
                    r-=1
                    di=1
            else:
                c-=1
                if(c<0):
                    c=0
                    r-=1
                    di=0
    
            idx+=1
        q = [0]
        arr[0] = -2
        count = 0
        while(len(q)!=0):
            for j in range(len(q)):
                curr = q.pop(0)
                if curr==(n*n-1):
                    return count
                for i in range(1,7):
                    if(curr+i>=n*n):
                        break
                    if(arr[curr+i]!=-2):
                        if(arr[curr+i]==-1):
                            q.append(curr+i)
                        else:
                            q.append(arr[curr+i])
                        arr[curr+i]=-2
                    
            count+=1
        return -1