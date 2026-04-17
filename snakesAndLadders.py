class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        visited = set()
        visited.add(1)
        target = n**2
        q = deque([])
        q.append(1)
        rolls = 0
        while q:
            rolls+=1
            size = len(q)
            for _ in range(size):
                curr = q.popleft()
                if curr+6>=target:
                    return rolls
                
                for i in range(1,7):
                    newC = curr+i
                    row = (newC - 1) // n
                    col = (newC - 1) % n

                    if row % 2 == 1:
                        col = n - 1 - col
                        
                    r = n - 1 - row
                    c = col

                    if board[r][c] != -1:
                        newC = board[r][c]

                    if newC in visited:
                        continue
                    
                    if newC == target:
                        return rolls

                    visited.add(newC)
                    q.append(newC)

        return -1
                

        