#Time Complexity : O(N*N)
#Space Complexity : O(N*N)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        length = len(board)
        board.reverse()
        def inttopos(square):
            r = (square-1) // length
            c = (square-1) % length
            if r%2!=0:
                c = length-1-c
            return [r,c]
        q = deque()
        q.append([1,0])
        visited = set()
        while(len(q)>0):
            square, moves = q.popleft()
            for i in range(1,7):
                nextsquare = square+i
                r,c = inttopos(nextsquare)
                if board[r][c] != -1:
                    nextsquare = board[r][c]
                if nextsquare == length*length:
                    return moves+1
                if nextsquare not in visited:
                    visited.add(nextsquare)
                    q.append([nextsquare,moves+1])
        return -1

        