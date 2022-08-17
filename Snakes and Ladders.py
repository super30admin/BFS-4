# time complexity is o(n^2), where n^2 is the size of the board
# space complexity is o(n^2), where n^2 is the size of the board
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [0 for i in range(n**2)]
        even = 0
        r = n - 1
        c = 0
        ind = 0
        while(ind < n**2):
            # print(c)
            if(even % 2 == 0):
                if(c < n):
                    if(board[r][c] >= 1):
                        arr[ind] = board[r][c] - 1
                    else:
                        arr[ind] = board[r][c]
                    ind += 1
                    c += 1
                else:
                    c -= 1
                    r -= 1
                    even += 1
            else:
                if(c >= 0):
                    if(board[r][c] >= 1):
                        arr[ind] = board[r][c] - 1
                    else:
                        arr[ind] = board[r][c]
                    ind += 1
                    c -= 1
                else:
                    c += 1
                    r -= 1
                    even += 1
            # ind += 1
           
        # print(arr)
        
        from collections import deque
        q = deque()
        q.append(0)
        arr[0] = -2
        count = 0
        while(len(q) > 0):
            l = len(q)
            for j in range(l):
                curr = q.popleft()
                for i in range(1,7):
                    child = curr + i
        
                    if(arr[child] != -2):
                        if(arr[child] == -1):
                            if(child == n*n -1):
                                return count + 1
                            q.append(child)
                            # arr[child] = -2
                        else:
                            if(arr[child] == n**2 - 1):
                                return count + 1
                            q.append(arr[child])
                        arr[child] = -2
            count += 1
        return -1
                    
                        
                    
                    
        
                
                    