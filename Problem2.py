"""
TC: O(N²) {We flatten the board into a 1D array and perform BFS starting from square 1.  
           Each square is visited at most once, and for each square, we consider up to 6 
           possible dice rolls. Hence, the overall time complexity is proportional to the 
           number of cells, which is O(N²) for an N x N board.}
SC: O(N²) {We use a 1D array to store the flattened board and a queue for BFS.  
           In the worst case, all squares may be visited and added to the queue, 
           resulting in linear space relative to the number of cells.}

Approach:
We are tasked with finding the minimum number of dice moves to reach the last square 
in a Snakes and Ladders game, given a board with ladders and snakes.

Step 1: Flatten the board
    - Convert the 2D board into a 1D array following the Boustrophedon (zigzag) order.
    - This simplifies navigation and allows direct indexing for moves.

Step 2: BFS traversal
    - Initialize a queue starting at square 0 and mark it visited.
    - For each level of BFS (representing one dice move):
        - Pop a square from the queue.
        - If it is the last square, return the current number of moves.
        - For dice rolls 1 through 6:
            - Compute the next square.
            - If it is not visited:
                - If the square has a ladder or snake (value != -1), move to the target square.
                - Otherwise, move to the next square.
                - Mark the square as visited and enqueue it.
    - Increment the move counter after processing all squares at the current level.

If the last square cannot be reached, return -1. This BFS ensures the shortest path 
in terms of dice moves is found efficiently.

This problem ran successfully on Leetcode.
"""

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # flatten the array
        n = len(board)
        arr = [0]*(n*n)
        row, col = n-1, 0
        flag = True
        idx = 0

        while idx < (n*n) :

            arr[idx] = board[row][col]

            if flag:
                col += 1
                if col == n:
                    row -= 1
                    col -= 1
                    flag = False
            else:
                col -= 1
                if col < 0:
                    row -= 1
                    col += 1
                    flag = True
            idx += 1
        

        q = deque([0])
        arr[0] = -2
        moves = 0

        while q:

            for _ in range(len(q)):

                curr = q.popleft()
                if curr == (n*n)-1:
                    return moves

                for k in range(1, 7):
                    if curr + k > (n*n)-1:
                        break
                    new = curr + k
                    #mark visited and append neighbor
                    if arr[new] != -2:
                        if arr[new] == -1:
                            q.append(new)
                        else:
                            q.append(arr[new]-1)
                        arr[new] = -2
    
            moves += 1
        return -1