#Time Complexity: O(N*N)
#Space Complexity: O(N*N)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        queue = []
        n = len(board)
        arr = [0] * (n*n)
        index = 0
        total = 0
        r = n -1 
        c = 0
        pointer = 0
        while index < n * n:
            if board[r][c] == -1:   
                arr[index] = board[r][c]
            else:
                arr[index] = board[r][c] - 1
            index +=1
            if pointer % 2 == 0:
                c +=1
                if c == n:
                    r -=1
                    c -=1
                    pointer +=1
            else:
                c -=1
                if c == -1:
                    r -=1
                    c +=1
                    pointer +=1
                    
        #created a 1-d array
        queue = [0]
        arr[0]=-2
        
        while queue:
            size = len(queue)
            for i in range(size):
                curr = queue.pop(0)
                if curr == (n * n) - 1:
                    return total
                for k in range(1,7):
                    child = curr + k
                    if child < n * n:
                        if arr[child] != -2:
                            if arr[child] == -1:
                                queue.append(child)
                            else:
                                queue.append(arr[child])
            
                        arr[child] = -2
                
            total += 1
            
            
        return -1
        