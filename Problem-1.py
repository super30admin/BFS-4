'''
    Time Complexity: O(m*n)
    Space Complexity: O(m*n)
'''
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        flatBoard = [0]
        dirRight = True
        lastCell = pow(n, 2)

        for i in range(n-1, -1, -1):
            if dirRight:
                for j in range(0, n):
                    flatBoard.append(board[i][j])
            else:
                for j in range(n-1, -1, -1):
                    flatBoard.append(board[i][j])

            dirRight = not dirRight

        queue = deque()
        visited = set()
        queue.append(1)
        size = len(queue)
        roll = 1
        visited.add(1)

        while len(queue):
            for i in range(size):
                cell = queue.popleft()

                for i in range(cell+1, min(cell + 7, lastCell+1)):
                    dest = flatBoard[i] if flatBoard[i] != -1 else i
                    if i == lastCell:
                        return roll
                    if dest not in visited:
                        if dest == lastCell:
                            return roll
                                
                        queue.append(dest)
                        visited.add(dest)

            roll += 1
            size = len(queue)

        return -1