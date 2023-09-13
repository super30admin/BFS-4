

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:

        #method 1 - BFS
        # Time Complexity: O(m * n)
        # Space Complexity: O(m * n)
        n = len(board)
        arr = [0] * (n*n)
        idx = 0
        r = n-1
        c = 0
        dir = True
        while idx< n*n: #flatten the 2D array to 1D.
            if board[r][c] == -1: #if normal element, just add 
                arr[idx]=-1                
            else:
                arr[idx] = board[r][c]-1 #our 1D array has index from 0, so let's store ladder/snake val-1
            idx+=1
            if dir: #go left to right
                c+=1
                if c==n: #out of bounds, bring it back to n-1 and go to above row
                    c-=1
                    r-=1
                    dir = False
            else: #go right to left
                c-=1
                if c == -1: #out of bounds, bring it back to 0 and go to above row
                    r-=1
                    c+=1
                    dir = True
        
        queue = deque()
        seen = set() 
        queue.append(0) #add the first index in our 1D array to q and seen.
        seen.add(0) 
        moves = 0

        while queue:
            size = len(queue)
            for i in range(size):
                curr = queue.popleft() 
                for j in range(1, 7): #rolling dice so 1....6 possible values.
                    child = curr + j
                    if child < n*n: #see if new index is within bounds
                        if child == n*n-1 or arr[child] == n*n -1: #if it is, check newindex is last one or arr[newindex] is last one, if it is return.
                            return moves+1
                        if arr[child]==-1: #if normal element
                            if child not in seen: #and not visited yet, add to q and to seen.
                                queue.append(child)
                                seen.add(child)
                        else: #if not normal, 
                            if arr[child] not in seen: #and arr[child] not visited yet, add to q and to seen and add child to seen.
                                queue.append(arr[child])
                                seen.add(arr[child])
                                seen.add(child)
            moves+=1

        return -1
                



