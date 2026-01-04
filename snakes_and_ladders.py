class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        #flatten the array first
        flattened = [-2]
        direction = True
        n = len(board)
        for i in range(n-1, -1, -1):
            if direction:
                for j in range(n):
                    flattened.append(board[i][j])
                direction = False
            else:
                for j in range(n-1, -1, -1):
                    flattened.append(board[i][j])
                direction = True
        #print(flattened)
        q = deque() #(pos, curstep) #bc for -1s step+=1 immedietly right?
        q.append(1)
        visited = set()
        steps = 0

        while q:
            l = len(q)
            for _ in range(l):
                cur= q.popleft()
                if cur == n*n: return steps
                if cur in visited:
                    continue
                
                visited.add(cur)
                for i in range(1, 7):
                    newi = cur + i
                    if newi > n*n:
                        break
                    
                    if flattened[newi] == -1:
                        q.append(newi)
                    else:
                        q.append(flattened[newi])
            steps += 1
        return -1
        




            

