"""
Rasika Sasturkar
Time Complexity: O(n^2)
Space Complexity: O(n^2)
"""

from collections import deque


def snakesAndLadders(board) -> int:
    # null case
    if board is None:
        return 0

    n = len(board)
    arr = [-1 for _ in range(n * n)]
    idx = 0  # for 1D array
    i, j = n - 1, 0
    even = 0

    while idx < n * n:
        if board[i][j] != -1:
            arr[idx] = board[i][j] - 1
        idx += 1
        if even % 2 == 0:
            j += 1
            if j == n:
                i -= 1
                j -= 1
                even = 1
        else:
            j -= 1
            if j == -1:
                i -= 1
                j += 1
                even = 0

    queue = deque([0])
    arr[0] = -2  # mark as visited
    moves = 0

    while queue:
        size = len(queue)
        for i in range(size):
            curr = queue.popleft()
            if curr == n * n - 1:
                return moves
            for num in range(1, 7):
                child = curr + num
                if child < n * n:
                    if arr[child] != -2:
                        if arr[child] == -1:
                            queue.append(child)
                        else:
                            queue.append(arr[child])
                        arr[child] = -2
        moves += 1
    return -1


def main():
    """
    Main function - examples from LeetCode problem to show the working.
    This code ran successfully on LeetCode and passed all the test cases.
    """
    print(snakesAndLadders(
        board=[[-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1], [-1, 35, -1, -1, 13, -1],
               [-1, -1, -1, -1, -1, -1], [-1, 15, -1, -1, -1, -1]]))  # returns 4
    print(snakesAndLadders(board=[[-1, -1], [-1, 3]]))  # returns 1


if __name__ == "__main__":
    main()
