#Time Complexity : O(n*m) where n and m are the number of rows and columns
#Space Complexity : O(n*m) where n and m are the number of rows and columns
#Did this code successfully run on Leetcode : Yes

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        flatBoard = [0 for _ in range(n*n)]
        #direction -> Even row or odd row
        #index -> curr position on flattened board
        index, i, j, direction = 0, n-1, 0, 0

        #flatten the 2d board into a 1d array
        while i>=0 and j>=0:
            if board[i][j] == -1:
                flatBoard[index] = -1
            else:
                flatBoard[index] = board[i][j] - 1

            index += 1

            if direction % 2 == 0: #even numbered row
                j += 1
            else: #odd numbered row
                j -= 1

            if j == n: #reached past last column
                i -= 1
                direction += 1
                j -= 1
            if j < 0: #reached past first column
                i -= 1
                direction += 1
                j += 1

        q = deque([0])
        level = 0
        flatBoard[0] = -2
        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                if cur == (len(flatBoard)-1): #if we've reached the end of our board
                    return level
                #check all 6 sides of the die
                for i in range(1, 7):
                    new = cur + i
                    if new <= (len(flatBoard)-1) and flatBoard[new] != -2:
                        if flatBoard[new] == -1:
                            q.append(new)
                        else:
                            q.append(flatBoard[new])
                        flatBoard[new] = -2
            level += 1
        return -1
