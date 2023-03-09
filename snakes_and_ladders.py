# Time Complexity :
# O(N*M)  - Size of the Matrix

# Space Complexity :
# O(N*M)

# Did this code successfully run on Leetcode :
#Yes

#We do a BFS from the start position with the neighours being the set of positions that can be reached from the current position. 
#At each potential next position, we add it to the queue along with the number of steps it took to reach there and mark is as visited. If the position has a snake or a ladder, we make the new position as the end point of the position instead of the current position
#While processing the queue, we check if at any point we reach the final state, we return the number of steps required to reach there

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:

        n = len(board)
        m = len(board[0])
        if n == 1 and m == 1 :
            return 0
        queue = collections.deque()
        board_new = self.flatten(board,n,m)
        queue.append((0,0))
        board_new[0] = -2

        while len(queue) != 0:
            curr_elem = queue.popleft()
            pos = curr_elem[0]
            curr_steps = curr_elem[1]

            for i in range(pos+1,min(pos+7,n*m)) :
                if board_new[i] == -2:
                    continue

                if board_new[i] == -1:
                    new_pos = i
                else :
                    new_pos = board_new[i]
                if new_pos == n * m -1:
                    return curr_steps + 1
                queue.append((new_pos,curr_steps+1))
                board_new[i] = -2
        return -1


    def flatten(self,board,n,m):
        flattened = []
        flag = True
        for i in reversed(range(0,n)):
            if flag == True :
                range_elem = range(0,m)
            else :
                range_elem = reversed(range(0,m))
            for j in range_elem :
                if board[i][j] == -1 :
                    flattened.append(board[i][j])
                else :
                    flattened.append(board[i][j]-1)
            flag = not flag

        return flattened
