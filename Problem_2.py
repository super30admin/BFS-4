# Time Complexity: O(n^2)
# Space Complexity: O(n^2)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n = len(board)
        moves = 0
        q = collections.deque([1])
        visited = [[False for _ in range(n)] for _ in range(n)]
        visited[n - 1][0] = True
        while q:
            size = len(q)
            for i in range(size):
                currBoardVal = q.popleft()
                if currBoardVal == (n * n):
                    return moves
                for diceVal in range(1, 7):
                    if (currBoardVal + diceVal) > (n * n):
                        break
                    pos = self.findCoordinates((currBoardVal + diceVal), n)
                    row, col = pos
                    if not visited[row][col]:
                        visited[row][col] = True
                        if board[row][col] == -1:
                            q.append(currBoardVal + diceVal)
                        else:
                            q.append(board[row][col])
            moves += 1
        return -1
    
    def findCoordinates(self, curr, n):
        row = (((n - (curr - 1)) // n) - 1)
        col = ((curr - 1) % n)
        if (row % 2) == (n % 2):
            return (row, (n - 1 - col))
        else:
            return (row, col)