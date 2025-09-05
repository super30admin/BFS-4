'''
529 Minesweeper
https://leetcode.com/problems/minesweeper/description/

Let's play the minesweeper game (Wikipedia, online game)!

You are given an m x n char matrix board representing the game board where:
'M' represents an unrevealed mine,
'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
'X' represents a revealed mine.
You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').

Return the board after revealing this position according to the following rules:

If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

Constraints:
m == board.length
n == board[i].length
1 <= m, n <= 50
board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
click.length == 2
0 <= click_r < m
0 <= click_c < n
board[click_r][click_c] is either 'M' or 'E'.

Solution:
Note: 'E': unexplored, 'B': explored w/ no mines,
      'M': unexplored w/ mine, 'X': explored w/ mine

1. BFS
Step 0: Start from the clicked cell which becomes the current cell with index (x,y).  Enque the current cell.
Step 1: Pop the q if not empty. If empty, go to Step 5.
Step 2: Count the number of mines in the vicinity (board[x][y] == "M"). If no. of mines > 0, set board[x][y] = #mines and do not explore any further. Go to Step 1 to pop the next element in q.
Step 3: If no. of mines = 0, explore the unvisited neighbors (marked as "E"), mark them as visited ("B") and enque them in the queue. Go to Step 1.
Step 5: Return the board.
https://youtu.be/hgdKslqKhwU?t=3402
Time: O(MN), Space: O(MN)

2. DFS
This is similar to the BFS approach with one key difference. In BFS, we push each unexplored child into a queue. In DFS, we make a recursive call for each unexplored child (child pushed into a stack).
https://youtu.be/hgdKslqKhwU?t=5077
Time: O(MN), Space: O(MN)
'''

from typing import List
from collections import deque
from copy import deepcopy as dcp

def countMines(board, i, j, M, N):
    # U, D, L, R, UL, UR, LL, LR
    dirs = [[-1,0], [1,0], [0,-1], [0,1], [-1,-1], [-1,1], [1,-1], [1,1]]
    count = 0
    for dir in dirs:
        x, y = i + dir[0], j + dir[1]
        if 0<=x<=M-1 and 0<y<=N-1 and board[x][y] == "M":
            count += 1
    return count

def updateBoardBFS(board: List[List[str]], click: List[int]) -> List[List[str]]:
    if not board:
        return board
    # U, D, L, R, UL, UR, LL, LR
    dirs = [[-1,0], [1,0], [0,-1], [0,1], [-1,-1], [-1,1], [1,-1], [1,1]]
    M, N = len(board), len(board[0])

    if board[click[0]][click[1]] == 'M':
        board[click[0]][click[1]] = 'X'
        return board

    q = deque()
    q.append((click[0], click[1]))
    board[click[0]][click[1]] = 'B'
    while q:
        curr = q.popleft()
        mines = countMines(board, curr[0], curr[1], M, N)
        # print(f"pop = ({curr[0]}, {curr[1]}), mines in vicinity = {mines}")
        if mines > 0:
            board[curr[0]][curr[1]] = str(mines)
        else:
            # explore neighbors
            for dir in dirs:
                x, y = curr[0] + dir[0], curr[1] + dir[1]
                if 0<=x<=M-1 and 0<=y<=N-1 and board[x][y] == "E":
                    board[x][y] = 'B'
                    q.append((x,y))
                    # print(f"enque = ({x}, {y})")
    return board

def updateBoardDFS(board: List[List[str]], click: List[int]) -> List[List[str]]:
    def dfs(board, x, y):
        curr = [x, y]

        if board[curr[0]][curr[1]] == 'B':
            return

        mines = countMines(board, curr[0], curr[1], M, N)
        if mines > 0:
            board[curr[0]][curr[1]] = str(mines)
        else:
            board[curr[0]][curr[1]] = 'B'
            # explore neighbors
            for dir in dirs:
                x, y = curr[0] + dir[0], curr[1] + dir[1]
                if 0<=x<=M-1 and 0<=y<=N-1 and board[x][y] == "E":
                    dfs(board, x, y)
        return

    if not board:
        return board

    if board[click[0]][click[1]] == 'M':
        board[click[0]][click[1]] = 'X'
        return board

    # U, D, L, R, UL, UR, LL, LR
    dirs = [[-1,0], [1,0], [0,-1], [0,1], [-1,-1], [-1,1], [1,-1], [1,1]]
    M, N = len(board), len(board[0])
    dfs(board, click[0], click[1])
    return board


def run_updateBoard():
    tests = [ ([["E","E","E","E","E"],
                ["E","E","M","E","E"],
                ["E","E","E","E","E"],
                ["E","E","E","E","E"]],
                [3,0],
                [["B","1","E","1","B"],
                 ["B","1","M","1","B"],
                 ["B","1","1","1","B"],
                 ["B","B","B","B","B"]]),
              ([["B","1","E","1","B"],
                ["B","1","M","1","B"],
                ["B","1","1","1","B"],
                ["B","B","B","B","B"]],
                [1,2],
                [["B","1","E","1","B"],
                 ["B","1","X","1","B"],
                 ["B","1","1","1","B"],
                 ["B","B","B","B","B"]]),
    ]
    for test in tests:
        board, click, ans = test[0], test[1], test[2]
        print(f"\nboard = {board}")
        for method in ['bfs', 'dfs']:
            if method == 'bfs':
                new_board = updateBoardBFS(dcp(board), click)
            elif method == 'dfs':
                new_board = updateBoardDFS(dcp(board), click)
            print(f"Method {method}: board after revealing = {new_board}")
            success = (ans == new_board)
            print(f"Pass: {success}")
            if not success:
                print(f"Failed")
                return

run_updateBoard()
