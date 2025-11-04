# Space Complexity: O(m * n)
# Time Complexity: O(m * n) 

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        n = len(board)
        l = []
        r = n-1
        c = 0
        flag = True # left to right
        idx = 0
        while idx < n * n: 
            if board[r][c] == -1: 
                l.append(-1)
            else: 
                l.append(board[r][c]-1)

            idx+=1
            if flag:
                c+=1
                if c == n: 
                    r-=1
                    c-=1
                    flag = False
            else:
                c-=1
                if c == -1:
                    r-=1
                    c+=1
                    flag = True

        q = collections.deque()
        q.append(0)
        l[0] = -2
        moves = 0

        while q: 
            size = len(q)
            for i in range(size): 
                curr_indx = q.popleft()
                if curr_indx == (n*n)-1: 
                    return moves
                for k in range(1, 7): 
                    new_index = curr_indx + k
                    if new_index > (n*n) -1:
                        break
                    if l[new_index] !=-2:
                        if l[new_index] == -1:
                            q.append(new_index)
                        else:
                            q.append(l[new_index])

                    l[new_index] = -2
            moves+=1
        return -1

