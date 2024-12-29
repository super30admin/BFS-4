class Solution:
    def __init__(self):
        self.m = 0
        self.n = 0
        self.dirs = [
            (-1, -1),
            (1, 0),
            (-1, 0),
            (0, -1),
            (0, 1),
            (1, 1),
            (-1, 1),
            (1, -1),
        ]

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        self.m = len(board)
        self.n = len(board[0])
        if board[click[0]][click[1]] == "M":
            board[click[0]][click[1]] = "X"
            return board

        def getMines(board, i, j):
            count = 0
            for dir in self.dirs:
                r, c = i + dir[0], j + dir[1]
                if (
                    0 <= r < len(board)
                    and 0 <= c < len(board[0])
                    and board[r][c] == "M"
                ):
                    count += 1
            return count

        q = deque()
        q.append(click)
        board[click[0]][click[1]] = "B"

        while q:
            curr = q.popleft()
            count = getMines(board, curr[0], curr[1])
            if count != 0:
                board[curr[0]][curr[1]] = str(count)
            else:
                for dir in self.dirs:
                    r, c = curr[0] + dir[0], curr[1] + dir[1]
                    if (
                        0 <= r < len(board)
                        and 0 <= c < len(board[0])
                        and board[r][c] == "E"
                    ):
                        q.append((r, c))
                        board[r][c] = "B"
        return board

    # time complexity is O(n^2)
    # space complexity is O(n^2)
