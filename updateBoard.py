# Time Complexity : O(v+e) where v is the vertices of the graph and e is the edges
# Space Complexity : O(v+e)
# Did this code successfully run on Leetcode : Yes
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:

        if not board or not board[0]:
            return None

        x = click[0]
        y = click[1]

        if board[x][y] == 'M':
            board[x][y] = 'X'
            return board

        q = collections.deque([(x, y)])
        OFFSETS = [(-1, -1), (-1, 0), (-1, 1), (0,1), (1,1), (1,0), (1, -1), (0, -1)]

        while q:
            r, c = q.popleft()

            if board[r][c] == 'E':
                count = 0
                for delta_r, delta_c in OFFSETS:
                    next_r, next_c = r + delta_r, c + delta_c
                    if 0 <= next_r < len(board) and 0 <= next_c < len(board[0]):
                        if board[next_r][next_c] == 'M':
                            count += 1
                if count != 0:
                    board[r][c] = str(count)
                else:
                    board[r][c] = 'B'
                    for delta_r, delta_c in OFFSETS:
                        next_r, next_c = r + delta_r, c + delta_c
                        if 0 <= next_r < len(board) and 0 <= next_c < len(board[0]) and board[next_r][next_c] == 'E':
                            q.append((next_r, next_c))

        return board
