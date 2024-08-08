# Time Complexity :
# O(n^2) 

# Space Complexity :  
# O(n^2) 


# Approach:
# BFS Approach



class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        # =================> TC = O(n^2), SC = O(n^2) , 1d array and queue ================== #
        if not board or len(board) == 0:
            return 0
        n = len(board)
        nums = [-1 for i in range(n*n)]   # flattened array
        queue = []     #queue
        moves = 0
        index = 0
        row = n-1
        col = 0
        even = 0
        while(index < n*n):
            print("Index: ", index)
            if (board[row][col] != -1):     # snake or ladder
                nums[index] = board[row][col] - 1
            else:
                nums[index] = -1
            index += 1
            if (even % 2 == 0):
                col += 1
                if col == n:
                    row -= 1
                    even += 1
                    col = n-1
            else:
                col -= 1
                if col == -1:
                    row -= 1
                    even += 1
                    col = 0

        queue.append(0)      # add first index

        while (queue):
            currSize = len(queue)
            for i in range(currSize):
                curr = queue.pop(0)
                if curr == n*n-1:
                    return moves
                for j in range(1, 7):
                    child = curr + j
                    if child >= n*n:
                        continue
                    if nums[child] == -1:   # Normal cell
                        queue.append(child)
                        nums[child] = -2
                    else:                    # Snake or ladder
                        if nums[child] != -2:
                            queue.append(nums[child])
                            nums[child] = -2

            moves += 1
        

        return -1