class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        ## T.C = O(n.n)
        ## S.C = O(n.n)
        
        n = len(board)

        r = n - 1
        c = 0
        even = 0

        flat_board = [0]*(n*n)

        for i in range(n*n):
            if board[r][c] == -1:
                flat_board[i] = -1
            else:
                flat_board[i] = board[r][c] - 1

            if even == 0:
                c += 1
                if c == n:
                    c -= 1
                    r -= 1
                    even = 1
            else:
                c -= 1
                if c == -1:
                    c += 1
                    r -= 1
                    even = 0
        
        print(flat_board)


        q = [0]
        moves = 0

        while q:
            size = len(q)
            for i in range(size):
                el = q.pop(0)
                
                if el == ((n*n) -1) :
                    return moves
                
                for j in range(1, 7):
                    idx = el + j
                    if idx >= n*n:
                        continue
                    elif flat_board[idx] == -1:
                        q.append(idx)
                        flat_board[idx] = -2
                        
                    elif flat_board[idx] > 0:
                        q.append(flat_board[idx])
                        flat_board[idx] = -2  
            moves += 1
            
            
        
        return -1



