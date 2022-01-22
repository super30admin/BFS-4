# Time: O(m*n)
# Space: O(m*n)
class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        dirs = [[-1,0], [1,0], [0, -1], [0, 1], [-1, -1], [-1, 1], [1, -1], [1,1]]
        queue = []
        queue.append(click)
        visited = [[False for i in range(len(board[0]))] for j in range(len(board))]
        while len(queue) != 0:
            #print(queue)
            size = len(queue)
            for i in range(size):
                curr = queue.pop(0)
                visited[curr[0]][curr[1]] = True
                #print('*'*50)
                #print(curr)
                if board[curr[0]][curr[1]] == 'M':
                    board[curr[0]][curr[1]] = 'X'
                    return board
                elif board[curr[0]][curr[1]] == 'E':
                    cnt = 0
                    for dr in dirs:
                        row = curr[0] + dr[0]
                        col = curr[1] + dr[1]
                        if row >=0 and row < len(board) and col >= 0 and col < len(board[0]) and board[row][col] == 'M':
                            cnt += 1
                    #print(curr, cnt)
                    if cnt != 0:
                        board[curr[0]][curr[1]] = str(cnt)
                    else:
                        board[curr[0]][curr[1]] = 'B'
                        for dr in dirs:
                            row = curr[0] + dr[0]
                            col = curr[1] + dr[1]
                            if row >=0 and row < len(board) and col >= 0 and col < len(board[0]) and board[row][col] == 'E' and visited[row][col] == False:
                                #print(row, col)
                                visited[row][col] = True
                                queue.append([row,col])
                elif board[curr[0]][curr[1]] == 'B':
                    for dr in dirs:
                            row = curr[0] + dr[0]
                            col = curr[1] + dr[1]
                            if row >=0 and row < len(board) and col >= 0 and col < len(board[0]) and board[row][col] == 'E' and visited[row][col] == False:
                                visited[row][col] = True
                                queue.append([row,col])
        return board
                    
                            
                        
                        
                    
                    
        
