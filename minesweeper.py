# Time Complexity :
# O(N*M)  - Size of the Matrix

# Space Complexity :
# O(N*M)

# Did this code successfully run on Leetcode :
#Yes

#We run a BFS starting from the given position. We add the neighbours in the queue if they have no mines in their neighbouring blocks. If there are mines in the neighbour's neighbour locations, we udpate the neighbour's value to the count of neighbouring mines

class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:

        n = len(board)
        m = len(board[0])
        self.dirs = [(-1,0), (1,0), (0,1),(0,-1), (1,1),(1,-1),(-1,1),(-1,-1)]

        if board[click[0]][click[1]] == 'M' :
            board[click[0]][click[1]] = 'X'
            return board

        queue = collections.deque()
        queue.append((click,board[click[0]][click[1]]))
        board[click[0]][click[1]] = 'B'

        while len(queue) != 0 :
            curr_elem = queue.popleft()
            curr_pos = curr_elem[0]
            curr_val = curr_elem[1]
            count = 0

            for direction in self.dirs :
                new_i = curr_pos[0] + direction[0]
                new_j = curr_pos[1] + direction[1]
                if self.is_valid(new_i,new_j,n,m):
                    if board[new_i][new_j] == 'M':
                        count += 1

            if count > 0 :
                board[curr_pos[0]][curr_pos[1]] = str(count)
                continue

            for direction in self.dirs :
                new_i = curr_pos[0] + direction[0]
                new_j = curr_pos[1] + direction[1]
                if self.is_valid(new_i,new_j,n,m) and board[new_i][new_j] == 'E' :
                    queue.append(((new_i,new_j),board[new_i][new_j]))
                    board[new_i][new_j]= 'B'
        return board

    def is_valid(self,i,j,n,m):
        if i >=0 and i < n and j >= 0 and j < m :
            return True 

        return False
