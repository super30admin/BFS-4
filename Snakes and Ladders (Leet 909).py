'''
Using BFS
Time: O(n^2), where n is rows * cols
Space: O(n), where n is rows * cols
'''

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        if board is None or len(board) == 0:
            return 0
        
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

        q = collections.deque()
        q.append(0)
        lvl = 0
        arr[0] = -2
        while len(q) != 0:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == len(arr) - 1:
                    return lvl
                for co in range(1,7):
                    if curr+co < len(arr) and arr[curr + co] != -2:
                        if arr[curr+co] == -1:
                            q.append(curr+co)
                        else:
                            q.append(arr[curr+co])
                        arr[curr+co] = -2
            lvl += 1
        return -1
            
        