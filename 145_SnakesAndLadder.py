'''
Accepted on leetcode(909)
time - O(M*N)
space - O(M*N)
Approach:
1. Flatten the matrix
2. Perform BFS as per the 6 items on dice.
3. return minimum dist if final node is reached else return -1.
'''


class Solution:
    class Entry:
        def __init__(self):
            self.v = 0
            self.dist = 0

    def snakesAndLadders(self, board) -> int:
        N = len(board) * len(board[0])

        moves = [0 for i in range(N)]  # flattened array

        i = len(board) - 1
        j = 0

        counter = 0
        even = 0

        while i >= 0 and j >= 0:
            # Processing part
            if board[i][j] != -1:
                moves[counter] = board[i][j] - 1
            elif board[i][j] == -1:
                moves[counter] = -1
            counter += 1

            # increment
            # even
            if even % 2 == 0:
                j += 1
                # odd
            else:
                j -= 1

            # shifting rows
            if j >= len(board):
                i -= 1
                even += 1
                j -= 1
            elif j < 0:
                i -= 1
                even += 1
                j += 1

        # BFS
        visited = [0 for i in range(N)]
        queue = []

        ans = Solution.Entry()  # start node

        ans.v = 0
        ans.dist = 0

        visited[0] = 1
        queue.append(ans)

        while queue:
            ans = queue.pop(0)
            v = ans.v

            if v == N - 1:  # reached end node
                return ans.dist
            for x in range(v + 1, v + 7):  # iterate till the end node else return -1.
                if x < N:
                    if visited[x] == 0:
                        visited[x] = 1
                        temp = Solution.Entry()

                        temp.dist = ans.dist + 1

                        if moves[x] != -1:
                            temp.v = moves[x]  # if we encounter ladder or snake
                        else:
                            temp.v = x  # if we just adding current index
                        queue.append(temp)
        return -1
