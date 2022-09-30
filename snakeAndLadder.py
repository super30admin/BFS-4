from collections import deque

#Time Complexity : O(N^2)
#Space Complexity : O(N^2)

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return -1

        result = 0
        n = len(board)
        arrBoard = [-1] * (n * n)
        idx = 0
        i = n - 1
        j = 0
        even = False
        while idx < n * n:
            if board[i][j] >= 1:
                arrBoard[idx] = board[i][j] - 1

            idx += 1
            # new r and c
            if not even:
                j += 1
                if j == n:
                    i -= 1
                    j -= 1
                    even = True
            else:
                j -= 1
                if j < 0:
                    i -= 1
                    j += 1
                    even = False
        # print(arrBoard)

        que = deque([0])
        arrBoard[0] = -2

        while que:
            size = len(que)
            for i in range(size):
                curr = que.popleft()
                for i in range(1, 7):
                    child = curr + i
                    # if child > (n*n):
                    #     break
                    if arrBoard[child] != -2:
                        if arrBoard[child] == -1:
                            if child == n * n - 1:
                                return result + 1
                            que.append(child)

                        else:
                            if arrBoard[child] == n * n - 1:
                                return result + 1
                            que.append(arrBoard[child])
                        arrBoard[child] = -2
            result += 1
        return -1