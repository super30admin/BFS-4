'''
909 Snakes and Ladders
https://leetcode.com/problems/snakes-and-ladders/description/

Solution:
1. BFS after creating a new 1D array from the 2D array
Flattens the 2D board into a 1D array in zigzag order.
BFS to explore moves from start to destination using up to 6 steps per turn.
Handles snakes/ladders by jumping to destination cell; visited cells are marked as -2. Normal cells are marked as -1 (by problem definition). All other cells have a +ve integer (indicating a snake/ladder in that cell)
https://youtu.be/hgdKslqKhwU?t=1267
Time: O(N^2), Space: O(N^2) (size of q)

2. BFS after mapping 1D array indices to 2D array indices
There is no need to create a separate 1D array. In this method, we directly map the 1D array index to the 2D index. All other steps are identical to the previous BFS approach (new 1D array). This method is easier to follow.
https://youtu.be/tyA-WSpxytM?t=1023
Time: O(N^2), Space: O(N^2) (size of q)

3. DFS (not worked out in the lecture)
We start from square 1 and use DFS to explore every dice roll up to 6 steps ahead. If we land on a ladder or snake, we jump to the destination and keep going. We only visit a cell again if we find a shorter way to get there.
DFS approach for this problem is not as popular as the BFS approach
Time: O(6^N^2), Space: O(N^2)
'''
from typing import List
from collections import deque
from copy import deepcopy as dcp

# reference solution
def snakesAndLaddersBFSIndexConversion(board):
        n = len(board)
        q = deque([0])
        level = 0

        def getPos(idx):
            # converts 1D index to 2D index
            # Let 1D index = idx, 2D index = (row, col)
            # Let r = idx // N, c = idx % N
            # Then,
            # row = N - 1 - r
            # col = N - 1 - c,  if r is even
            #     = c,          if r is odd
            # Hence, row indices are flipped
            # col indices are flipped for even-numbered rows
            # and not flipped for odd-numbered rows
            r = idx // n
            c = idx % n
            row = n - 1 - r
            if r % 2 == 0:
                col = c
            else:
                col = n - 1 - c
            return row, col

        while q:
            for _ in range(len(q)):
                currIdx = q.popleft()
                for k in range(1, 7):
                    newIdx = currIdx + k
                    r, c = getPos(newIdx)
                    if newIdx == n * n - 1 or board[r][c] == n * n:
                        return level + 1

                    if board[r][c] != -2:
                        if board[r][c] == -1:
                            # no snake/ladder in this cell
                            q.append(newIdx)
                        else:
                            # this cell contains a snake/ladder
                            jumpIdx = board[r][c] - 1
                            q.append(jumpIdx)
                        board[r][c] = -2
            level += 1

        return -1

def snakesAndLaddersBFS1DArray(board: List[List[int]]) -> int:
    if not board:
        return 0

    N = len(board)
    nums = [-1]*(N*N)
    idx = 0 # index of the nums array
    even = 0 # determine if row of 2d board is even or odd
    row = N-1
    col = 0

    # Convert 2D matrix (board) to a 1D array (nums)
    while idx < N*N:
        if board[row][col] == -1:
            # neither snake, nor arrow at row,col
            nums[idx] = -1
        else:
            # ladder or snake at this at row,col
            nums[idx] = board[row][col] - 1
        idx += 1

        if even % 2 == 0:
            col += 1
            if col == N:
                row -= 1
                col = N-1
                even += 1
        else:
            col -= 1
            if col == -1:
                row -= 1
                col = 0
                even += 1

    lvl = 0
    q = deque()
    q.append(0) # enque the start index
    while q:
        # print(f'level = {lvl}')
        # print(f"q = {q}")
        sz = len(q)
        for _ in range(sz):
            curpos = q.popleft()
            if curpos == N*N - 1: # reached end
                return lvl
            for dice in range(1,7):
                newpos = curpos + dice
                if newpos >= N*N:
                    continue
                if nums[newpos] == -1:
                    q.append(newpos)
                    nums[newpos] = -2 # -2 means this posn has been visited
                elif nums[newpos] == -2:
                    continue
                else:
                    # From currpos, we roll the dice to reach currpos + dice.
                    # If after rolling the dice, we end up at newpos which
                    # could be a snake/ladder cell. We jump/fall from newpos to
                    # jumpos, where jumpos = nums[newpos]. Thus, nums[jumpos]
                    # = nums[nums[newpos]] is marked as visited.
                    # We could simultaneously also mark nums[newpos] as visited
                    # Thus, we have two options
                    # a) either mark newpos or jumpos as visited, or
                    # b) mark both visited
                    # Here, we mark both newpos and jumpos as visited. Helps
                    # with enqueing fewer cells into the queue leading to
                    # faster execution.
                    q.append(nums[newpos])
                    nums[nums[newpos]] = -2
                    nums[newpos] = -2
        lvl += 1
    return -1


def snakesAndLaddersDFS(board: List[List[int]]) -> int:
    def getPos(idx):
        r = idx // n
        c = idx % n
        row = n - 1 - r
        if r % 2 == 0:
            col = c
        else:
            col = n - 1 - c
        return row, col

    def dfs(currIdx):
        for k in range(1, 7):
            newIdx = currIdx + k
            if newIdx == n * n:
                return
            r, c = getPos(newIdx)
            nextIdx = newIdx if board[r][c] == -1 else board[r][c] - 1

            if moves[nextIdx] > moves[currIdx] + 1:
                moves[nextIdx] = moves[currIdx] + 1
                dfs(nextIdx)



    n = len(board)
    moves = [float('inf')] * (n * n)
    moves[0] = 0
    dfs(0)
    return -1 if moves[-1] == float('inf') else moves[-1]


def run_snakesAndLadders():
    tests = [( [[-1,-1,-1,-1,-1,-1],
                [-1,-1,-1,-1,-1,-1],
                [-1,-1,-1,-1,-1,-1],
                [-1,35,-1,-1,13,-1],
                [-1,-1,-1,-1,-1,-1],
                [-1,15,-1,-1,-1,-1],
            ], 4),
            ([[-1,-1],[-1,3]], 1),
    ]
    for test in tests:
        board, ans = test[0], test[1]
        print(f"\nboard = {board}")
        for method in ['bfs-new1darray', 'bfs-indexconversion', 'dfs']:
            if method == 'bfs-new1darray':
                rolls = snakesAndLaddersBFS1DArray(dcp(board))
            elif method == 'bfs-indexconversion':
                rolls = snakesAndLaddersBFSIndexConversion(dcp(board))
            elif method == 'dfs':
                rolls = snakesAndLaddersDFS(dcp(board))
            print(f"Method {method}: min number of rolls to reach end = {rolls}")
            success = (ans == rolls)
            print(f"Pass: {success}")
            if not success:
                print(f"Failed")
                return

run_snakesAndLadders()