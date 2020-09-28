# APPROACH 1: BFS
# Time Complexity : O(n * n) , n * n: size of the given board
# Space Complexity : O(n * n), space of the flattened 1-d array
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None
#
#
# Your code here along with comments explaining your approach
# 1. Firstly convert the given board to 1-d array as it's difficult to process BFS on it. (when we land a cell via ladder or snake, difficult to gauge in which direction we need 
#    to move next as we have to traverse the board in zig zag manner). 
#       - So have a 1-d array the same size as the size of the board
#       - if the cell in original board is -1, put -1 in the corresponding index as well (original call number - 1 as it's index)
#       - if the call in the original board has a number (destination of snake / ladder), place the number - 1 in the corresponding index. 
#       - keep track of the level currently processing. if even, move from left -> right, if odd right -> left
#       - also take care if the column is out of the bounds, then need to traverse to next row (here start from last row and go up to the first row)
# 2. Then execute BFS on 1-d array using queue. Use -2 to mark it as visited.
# 3. If the cell number dequeued is the last cell, return the number of moves. 
# 4. If not process all it's childrens, that we get from rolling the dice 1- 6 (it's like the brute force exponential search tree). If not visited, enqueue it. 
#       - If the cell in 1-d array is -1, then enqueue the index
#       - If the cell in 1-d array is a number, enqueue the cell value and not the ind. 
# 5. Increement the number of moves after processing a level (and not just a single node). In this way, we will get the minimum number of move as we are searhing in BFS.

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board is None or len(board) == 0:
            return 0
        
        board_size = len(board) * len(board)
        flatten_list = [None for _ in range(board_size)]
        r, c = len(board) - 1, 0
        flatten_ind, row_num = 0, 0
        
        while r >= 0 and c >= 0:
            if board[r][c] == -1:
                flatten_list[flatten_ind] = -1
            else:
                flatten_list[flatten_ind] = board[r][c] - 1
            flatten_ind += 1
                
            if row_num % 2 == 0:
                c += 1
            else:
                c -= 1
                
            if c == len(board):
                r -= 1
                row_num += 1
                c -= 1
            elif c < 0:
                r -= 1
                row_num += 1
                c += 1
                
        move, queue = 0, deque()
        queue.append(0)
        flatten_list[0] = -2
        
        while queue:
            size = len(queue)
            while size > 0:
                curr = queue.popleft()
                
                if curr == board_size - 1:
                    return move
                
                for ind in range(1, 7):
                    child = curr + ind
                    if child < board_size and flatten_list[child] != -2:
                        if flatten_list[child] == -1:
                            queue.append(child)
                        else:
                            queue.append(flatten_list[child])
                        flatten_list[child] = -2
                        
                size -= 1
            move += 1
                
        return -1
        
