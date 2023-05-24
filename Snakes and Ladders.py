class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # Time O(n**2)
        # Space O(n**2)
        n = len(board[0])
        flat = []
        r = n - 1
        c = 0
        dirr = True
        count = 0
        while count < n ** 2:
            # print(r,c)
            if board[r][c] != -1:
                flat.append(board[r][c] - 1)
            else:
                flat.append(board[r][c])
            if dirr:
                c += 1
                if c == n:
                    c -= 1
                    r -= 1
                    dirr = False
            else:
                c -= 1
                if c == -1:
                    c += 1
                    r -= 1
                    dirr = True
            count += 1
        # print(flat)
        # Create a queue and do BFS
        dq = deque()
        steps = 1
        dq.append(0)
        # print(dq)
        flat[0] = -2
        while dq:
            size = len(dq)
            for i in range(size):
                pos = dq.popleft()
                # print(pos)
                for i in range(1, 7):
                    newnum = pos + i
                    if newnum > (n * n): continue

                    if flat[newnum] != -2:
                        if flat[newnum] == -1:
                            dq.append(newnum)
                            if newnum == (n * n - 1): return steps
                        else:
                            dq.append(flat[newnum])
                            if flat[newnum] == (n * n - 1): return steps
                    flat[newnum] = -2

            steps += 1
        return -1