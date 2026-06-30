# time: O(n^2) 
# 6 * O(n^2) each square is added to the queue (1 to n^2), 6 possible moves
# space: O(n^2) determined by the total number of squares on the board

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        cells = [None] * (n**2 + 1)
        #print(cells)
        # [None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None]
        label = 1
        columns = list(range(0, n))
        #print(columns)
        # [0, 1, 2, 3, 4, 5]
        # build the snake-like ordering of the board in 1D
        for row in range(n - 1, -1, -1):
            for column in columns:
                cells[label] = (row, column)
                label += 1
            columns.reverse()
        #print(cells)
        # 1D snake-like ordering of the board
        # [None, (5, 0), (5, 1), (5, 2), (5, 3), (5, 4), (5, 5), (4, 5), (4, 4), (4, 3), (4, 2), (4, 1), (4, 0), (3, 0), (3, 1), (3, 2), (3, 3), (3, 4), (3, 5), (2, 5), (2, 4), (2, 3), (2, 2), (2, 1), (2, 0), (1, 0), (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (0, 5), (0, 4), (0, 3), (0, 2), (0, 1), (0, 0)]
        
        # dist tracks the move set and acts as a visited set 
        dist = [-1] * (n * n + 1)
        #print(dist)
        # [-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
        q = deque([1]) #15
        dist[1] = 0
        while q:
             if dist[n * n] != -1:
                break
            curr = q.popleft()
            # iterate through the snake board coords (cells)
            # n**2 is the final winning square of the game - in this case highest numbered square is 36
            for next in range(curr + 1, min(curr + 6, n**2) + 1):
                row, column = cells[next]
                # (5, 1), (5, 2), (5, 3), etc
                # board[row][column] is the value on the game board
                destination = (board[row][column] if board[row][column] != -1
                               else next)
                # first destination is 15 bc board[5][1] is 15
                if dist[destination] == -1:
                    print(destination)
                    dist[destination] = dist[curr] + 1
                    # dist[15] = 0
                    # [-1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1]
                    print(dist)
                    q.append(destination)
        return dist[n * n]




--- STARTING BFS SEARCH ---

================ STEP 1 ================
Current Queue: [1]
Processing Square: 1 (Current Moves taken to get here: 0)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 2 -> Transports via LADDER to 15 | VISITED! Shortest moves to 15 is now 1
  • Rolled a 2 -> Landed on 3 (Normal square) | VISITED! Shortest moves to 3 is now 1
  • Rolled a 3 -> Landed on 4 (Normal square) | VISITED! Shortest moves to 4 is now 1
  • Rolled a 4 -> Landed on 5 (Normal square) | VISITED! Shortest moves to 5 is now 1
  • Rolled a 5 -> Landed on 6 (Normal square) | VISITED! Shortest moves to 6 is now 1
  • Rolled a 6 -> Landed on 7 (Normal square) | VISITED! Shortest moves to 7 is now 1

================ STEP 2 ================
Current Queue: [15, 3, 4, 5, 6, 7]
Processing Square: 15 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 16 (Normal square) | VISITED! Shortest moves to 16 is now 2
  • Rolled a 2 -> Landed on 17 -> Transports via SNAKE to 13 | VISITED! Shortest moves to 13 is now 2
  • Rolled a 3 -> Landed on 18 (Normal square) | VISITED! Shortest moves to 18 is now 2
  • Rolled a 4 -> Landed on 19 (Normal square) | VISITED! Shortest moves to 19 is now 2
  • Rolled a 5 -> Landed on 20 (Normal square) | VISITED! Shortest moves to 20 is now 2
  • Rolled a 6 -> Landed on 21 (Normal square) | VISITED! Shortest moves to 21 is now 2

================ STEP 3 ================
Current Queue: [3, 4, 5, 6, 7, 16, 13, 18, 19, 20, 21]
Processing Square: 3 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 4 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 5 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 6 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 4 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 5 -> Landed on 8 (Normal square) | VISITED! Shortest moves to 8 is now 2
  • Rolled a 6 -> Landed on 9 (Normal square) | VISITED! Shortest moves to 9 is now 2

================ STEP 4 ================
Current Queue: [4, 5, 6, 7, 16, 13, 18, 19, 20, 21, 8, 9]
Processing Square: 4 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 5 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 6 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 4 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 10 (Normal square) | VISITED! Shortest moves to 10 is now 2

================ STEP 5 ================
Current Queue: [5, 6, 7, 16, 13, 18, 19, 20, 21, 8, 9, 10]
Processing Square: 5 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 6 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 11 (Normal square) | VISITED! Shortest moves to 11 is now 2

================ STEP 6 ================
Current Queue: [6, 7, 16, 13, 18, 19, 20, 21, 8, 9, 10, 11]
Processing Square: 6 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 12 (Normal square) | VISITED! Shortest moves to 12 is now 2

================ STEP 7 ================
Current Queue: [7, 16, 13, 18, 19, 20, 21, 8, 9, 10, 11, 12]
Processing Square: 7 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 8 ================
Current Queue: [16, 13, 18, 19, 20, 21, 8, 9, 10, 11, 12]
Processing Square: 16 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 18 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 19 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 20 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 22 (Normal square) | VISITED! Shortest moves to 22 is now 3

================ STEP 9 ================
Current Queue: [13, 18, 19, 20, 21, 8, 9, 10, 11, 12, 22]
Processing Square: 13 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 14 -> Transports via LADDER to 35 | VISITED! Shortest moves to 35 is now 3
  • Rolled a 2 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 18 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 19 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 10 ================
Current Queue: [18, 19, 20, 21, 8, 9, 10, 11, 12, 22, 35]
Processing Square: 18 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 19 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 20 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 23 (Normal square) | VISITED! Shortest moves to 23 is now 3
  • Rolled a 6 -> Landed on 24 (Normal square) | VISITED! Shortest moves to 24 is now 3

================ STEP 11 ================
Current Queue: [19, 20, 21, 8, 9, 10, 11, 12, 22, 35, 23, 24]
Processing Square: 19 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 20 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 25 (Normal square) | VISITED! Shortest moves to 25 is now 3

================ STEP 12 ================
Current Queue: [20, 21, 8, 9, 10, 11, 12, 22, 35, 23, 24, 25]
Processing Square: 20 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 25 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 26 (Normal square) | VISITED! Shortest moves to 26 is now 3

================ STEP 13 ================
Current Queue: [21, 8, 9, 10, 11, 12, 22, 35, 23, 24, 25, 26]
Processing Square: 21 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 2 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 25 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 26 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 27 (Normal square) | VISITED! Shortest moves to 27 is now 3

================ STEP 14 ================
Current Queue: [8, 9, 10, 11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 8 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)

================ STEP 15 ================
Current Queue: [9, 10, 11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 9 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)

================ STEP 16 ================
Current Queue: [10, 11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 10 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 6 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 17 ================
Current Queue: [11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 11 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 5 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)

================ STEP 18 ================
Current Queue: [12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 12 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 4 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 18 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 19 ================
Current Queue: [22, 35, 23, 24, 25, 26, 27]
Processing Square: 22 (Current Moves taken to get here: 3)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 2 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 25 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 26 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 27 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 28 (Normal square) | VISITED! Shortest moves to 28 is now 4

================ STEP 20 ================
Current Queue: [35, 23, 24, 25, 26, 27, 28]
Processing Square: 35 (Current Moves taken to get here: 3)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 36 (Normal square) | VISITED! Shortest moves to 36 is now 4

🎉 Reached the final square 36! Shortest path found.

--- SEARCH COMPLETE ---
--- STARTING BFS SEARCH ---

================ STEP 1 ================
Current Queue: [1]
Processing Square: 1 (Current Moves taken to get here: 0)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 2 -> Transports via LADDER to 15 | VISITED! Shortest moves to 15 is now 1
  • Rolled a 2 -> Landed on 3 (Normal square) | VISITED! Shortest moves to 3 is now 1
  • Rolled a 3 -> Landed on 4 (Normal square) | VISITED! Shortest moves to 4 is now 1
  • Rolled a 4 -> Landed on 5 (Normal square) | VISITED! Shortest moves to 5 is now 1
  • Rolled a 5 -> Landed on 6 (Normal square) | VISITED! Shortest moves to 6 is now 1
  • Rolled a 6 -> Landed on 7 (Normal square) | VISITED! Shortest moves to 7 is now 1

================ STEP 2 ================
Current Queue: [15, 3, 4, 5, 6, 7]
Processing Square: 15 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 16 (Normal square) | VISITED! Shortest moves to 16 is now 2
  • Rolled a 2 -> Landed on 17 -> Transports via SNAKE to 13 | VISITED! Shortest moves to 13 is now 2
  • Rolled a 3 -> Landed on 18 (Normal square) | VISITED! Shortest moves to 18 is now 2
  • Rolled a 4 -> Landed on 19 (Normal square) | VISITED! Shortest moves to 19 is now 2
  • Rolled a 5 -> Landed on 20 (Normal square) | VISITED! Shortest moves to 20 is now 2
  • Rolled a 6 -> Landed on 21 (Normal square) | VISITED! Shortest moves to 21 is now 2

================ STEP 3 ================
Current Queue: [3, 4, 5, 6, 7, 16, 13, 18, 19, 20, 21]
Processing Square: 3 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 4 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 5 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 6 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 4 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 5 -> Landed on 8 (Normal square) | VISITED! Shortest moves to 8 is now 2
  • Rolled a 6 -> Landed on 9 (Normal square) | VISITED! Shortest moves to 9 is now 2

================ STEP 4 ================
Current Queue: [4, 5, 6, 7, 16, 13, 18, 19, 20, 21, 8, 9]
Processing Square: 4 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 5 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 6 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 4 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 10 (Normal square) | VISITED! Shortest moves to 10 is now 2

================ STEP 5 ================
Current Queue: [5, 6, 7, 16, 13, 18, 19, 20, 21, 8, 9, 10]
Processing Square: 5 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 6 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 11 (Normal square) | VISITED! Shortest moves to 11 is now 2

================ STEP 6 ================
Current Queue: [6, 7, 16, 13, 18, 19, 20, 21, 8, 9, 10, 11]
Processing Square: 6 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 7 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 2 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 12 (Normal square) | VISITED! Shortest moves to 12 is now 2

================ STEP 7 ================
Current Queue: [7, 16, 13, 18, 19, 20, 21, 8, 9, 10, 11, 12]
Processing Square: 7 (Current Moves taken to get here: 1)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 8 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 8 ================
Current Queue: [16, 13, 18, 19, 20, 21, 8, 9, 10, 11, 12]
Processing Square: 16 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 18 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 19 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 20 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 22 (Normal square) | VISITED! Shortest moves to 22 is now 3

================ STEP 9 ================
Current Queue: [13, 18, 19, 20, 21, 8, 9, 10, 11, 12, 22]
Processing Square: 13 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 14 -> Transports via LADDER to 35 | VISITED! Shortest moves to 35 is now 3
  • Rolled a 2 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 3 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 18 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 19 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 10 ================
Current Queue: [18, 19, 20, 21, 8, 9, 10, 11, 12, 22, 35]
Processing Square: 18 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 19 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 20 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 23 (Normal square) | VISITED! Shortest moves to 23 is now 3
  • Rolled a 6 -> Landed on 24 (Normal square) | VISITED! Shortest moves to 24 is now 3

================ STEP 11 ================
Current Queue: [19, 20, 21, 8, 9, 10, 11, 12, 22, 35, 23, 24]
Processing Square: 19 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 20 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 25 (Normal square) | VISITED! Shortest moves to 25 is now 3

================ STEP 12 ================
Current Queue: [20, 21, 8, 9, 10, 11, 12, 22, 35, 23, 24, 25]
Processing Square: 20 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 21 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 25 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 26 (Normal square) | VISITED! Shortest moves to 26 is now 3

================ STEP 13 ================
Current Queue: [21, 8, 9, 10, 11, 12, 22, 35, 23, 24, 25, 26]
Processing Square: 21 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 22 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 2 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 25 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 26 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 27 (Normal square) | VISITED! Shortest moves to 27 is now 3

================ STEP 14 ================
Current Queue: [8, 9, 10, 11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 8 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 9 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)

================ STEP 15 ================
Current Queue: [9, 10, 11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 9 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 10 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)

================ STEP 16 ================
Current Queue: [10, 11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 10 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 11 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 4 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 6 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 17 ================
Current Queue: [11, 12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 11 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 12 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 3 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 5 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)

================ STEP 18 ================
Current Queue: [12, 22, 35, 23, 24, 25, 26, 27]
Processing Square: 12 (Current Moves taken to get here: 2)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 13 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 2 -> Landed on 14 -> Transports via LADDER to 35 | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 15 (Normal square) | SKIPPED (Already visited in 1 moves)
  • Rolled a 4 -> Landed on 16 (Normal square) | SKIPPED (Already visited in 2 moves)
  • Rolled a 5 -> Landed on 17 -> Transports via SNAKE to 13 | SKIPPED (Already visited in 2 moves)
  • Rolled a 6 -> Landed on 18 (Normal square) | SKIPPED (Already visited in 2 moves)

================ STEP 19 ================
Current Queue: [22, 35, 23, 24, 25, 26, 27]
Processing Square: 22 (Current Moves taken to get here: 3)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 23 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 2 -> Landed on 24 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 3 -> Landed on 25 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 4 -> Landed on 26 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 5 -> Landed on 27 (Normal square) | SKIPPED (Already visited in 3 moves)
  • Rolled a 6 -> Landed on 28 (Normal square) | VISITED! Shortest moves to 28 is now 4

================ STEP 20 ================
Current Queue: [35, 23, 24, 25, 26, 27, 28]
Processing Square: 35 (Current Moves taken to get here: 3)
Checking potential die rolls (1 to 6):
  • Rolled a 1 -> Landed on 36 (Normal square) | VISITED! Shortest moves to 36 is now 4

🎉 Reached the final square 36! Shortest path found.

--- SEARCH COMPLETE ---