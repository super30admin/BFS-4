class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # using 1d array
        # time complexity is O(n^2)
        # space complexity is O(n^2)
        n = len(board)
        arr = [0] * (n * n + 1)
        flag = True
        r = n - 1
        c = 0
        idx = 1

        while idx < n * n + 1:
            arr[idx] = board[r][c]
            idx += 1
            # moving left to right
            if flag:
                c += 1
                # move to the next row if we have reached the last cell in current row
                if c == n:
                    r -= 1
                    c -= 1
                    flag = False

            else:  # moving right to left
                c -= 1
                if c < 0:
                    r -= 1
                    c += 1
                    flag = True

        q = deque([1])
        arr[1] == -2  # -2 indicating that it is visited
        moves = 0
        while q:
            size = len(q)
            for k in range(size):
                curr_idx = q.popleft()
                for i in range(1, 7):
                    new_idx = curr_idx + i
                    if new_idx > n * n:
                        continue
                    if new_idx == n * n or arr[new_idx] == n * n:
                        return moves + 1
                    if arr[new_idx] != -2:
                        if arr[new_idx] == -1:
                            q.append(new_idx)
                        else:
                            q.append(arr[new_idx])
                        arr[new_idx] = -2
            moves += 1
        return -1
