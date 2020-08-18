-------------------------Snakes and Ladders------------------------------------------
# Time Complexity : O(N**2) as N is the length of the board
# Space Complexity : O(N**2)  No extra space
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# 
# first we will flatten the board with exact path we will be moving in the board. And when we encounter a number except -1 then we
# are keeping its index into new matrix. Then we will process each and every step using BFS as we have 6 steps at each element on board.
# we will iterate over queue and check if the next step is -1 then we will just add that index into queue, else we will take the exact index of the ladder or snake.


class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return 0
        
        n = len(board)
        total = n*n
        i = n-1
        j = 0
        matrix =[0 for i in range(total)]
        idx = 0
        even = 0
        while i>=0 and j>=0:
            if board[i][j] == -1:
                matrix[idx] = -1
            else:
                matrix[idx] = board[i][j]-1
            
            idx +=1
            if even%2 == 0:
                j +=1
            else:
                j -=1
            
            if j == n:
                even+=1
                j -=1
                i -=1
            if j<0:
                even+=1
                j +=1
                i -=1

        queue = collections.deque()
        queue.append(0)
        matrix[0] = -2
        count = 0
        
        while queue:
            size = len(queue)
            for j in range(size):
                curr = queue.popleft()
                if curr == total-1:
                    return count
                
                for i in range(1, 7):
                    child = curr+i
                    if child<total and matrix[child]!=-2:
                        if matrix[child] == -1: 
                            queue.append(child)
                        else:
                            queue.append(matrix[child])
                        matrix[child] = -2
            count +=1
        return -1