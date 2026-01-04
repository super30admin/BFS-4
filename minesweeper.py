class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        """
        #E? -> check mines -> no mines? -> change it to B -> queue all neighbors
                           mines? -> count mines and change it to that count
        """ 
        q = deque()
        q.append(click)
        dirs = [(0,-1), (0,1), (-1,0), (1,0), (-1,-1), (1,1), (-1,1), (1,-1)]
        visited = set()
        ROWS, COLS = len(board), len(board[0])

        while q:
            curi, curj = q.popleft()
            if (curi, curj) in visited:
                continue
            
            visited.add((curi, curj))
            if board[curi][curj] == "M":
                board[curi][curj] = "X"
                return board
            elif board[curi][curj] == "E":
                count = 0
                neighbors = []
                for i, j in dirs:
                    if 0 <= curi+i < ROWS and 0 <= curj+j < COLS:
                        if board[curi+i][curj+j] == "M":
                            count+=1
                        else:
                            neighbors.append((curi+i,curj+j))
                if count:
                    board[curi][curj] = str(count)
                else:
                    board[curi][curj] = "B"
                    for i, j in neighbors:
                        if board[i][j] == "E":
                            q.append((i, j))
        return board

