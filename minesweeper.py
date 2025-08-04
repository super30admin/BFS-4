# Time complexity: O(m * n)

# Space complexity: O(m * n)

from collections import deque

def updateBoard(board, click):
    m, n = len(board), len(board[0])
    directions = [(0, 1), (0, -1), (1, 0), (-1, 0), (1, 1), (-1, -1), (1, -1), (-1, 1)]

    def count_mines(i, j):
        count = 0
        for d in directions:
            ni, nj = i + d[0], j + d[1]
            if 0 <= ni < m and 0 <= nj < n and board[ni][nj] == 'M':
                count += 1
        return count

    if board[click[0]][click[1]] == 'M':
        board[click[0]][click[1]] = 'X'
        return board

    queue = deque([click])
    visited = set([tuple(click)])

    while queue:
        i, j = queue.popleft()
        count = count_mines(i, j)
        if count > 0:
            board[i][j] = str(count)
        else:
            board[i][j] = 'B'
            for d in directions:
                ni, nj = i + d[0], j + d[1]
                if 0 <= ni < m and 0 <= nj < n and board[ni][nj] == 'E' and (ni, nj) not in visited:
                    queue.append((ni, nj))
                    visited.add((ni, nj))

    return board


print(updateBoard(board, click))
