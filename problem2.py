# Time Complexity : O(n^2)
# Space Complexity : O(n^2)
# Did this code successfully run on Leetcode : YES

# Any problem you faced while coding this : NO

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        arr = []
        N = len(board)
        flag = True # means left-right

        # Flatten the board
        for i in range(N-1, -1, -1):
            if flag:
                for j in range(N):
                    if board[i][j] != -1:
                        arr.append(board[i][j] - 1)
                    else:
                        arr.append(board[i][j])
                flag = False
            else:
                for j in range(N-1, -1, -1):
                    if board[i][j] != -1:
                        arr.append(board[i][j] - 1)
                    else:
                        arr.append(board[i][j])
                flag = True

        print(arr)
        
        queue = collections.deque()
        queue.append(0)
        arr[0] = -2
        moves = 0
        while queue:
            size = len(queue)
            for i in range(size):
                cur = queue.popleft()
                if cur == N**2 - 1:
                    return moves
                for j in range(1,7):
                    new_ix = cur + j
                    if new_ix == N**2:
                        break
                    if arr[new_ix] == -2:
                        continue
                    if arr[new_ix] == -1:
                        queue.append(new_ix)
                    else:
                        queue.append(arr[new_ix])
                    arr[new_ix] = -2
            moves += 1

        return -1