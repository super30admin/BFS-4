# Time complexity : O(n^2)
# Space complexity : O(n^2)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board or not board[0]:
            return 0
        ans = 0
        visited = set()
        n = len(board)
        flattened = []
        flag = False
        
        idx = 0
        for i in range(n-1,-1,-1):
            if not flag:
                for j in range(n):
                    if board[i][j] >-1:
                        flattened.append(board[i][j]-1)
                    else:
                        flattened.append(idx)
                    idx += 1
            else:
                for j in range(n-1,-1,-1):
                    if board[i][j] >-1:
                        flattened.append(board[i][j]-1)
                    else:
                        flattened.append(idx)
                    idx += 1
            if flag:
                flag = False
            else:
                flag = True
        
        q = collections.deque()
        
        q.append(flattened[0])
        visited.add(flattened[0])
        
        moves = 0
        
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == (n*n -1):
                    return moves
                limit = min(curr+6,n*n-1)
                for j in range(curr+1,limit+1):
                    if flattened[j] not in visited:
                        if flattened[j] == (n*n -1):
                            return moves+1
                        q.append(flattened[j])
                        visited.add(flattened[j])
            moves += 1
        
        return -1
                    
                    
                    
                