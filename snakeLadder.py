    #Time : O(m * n)
    #Space: O(m * n)
    
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        m, n = len(board), len(board[0])
        
        def convert_coor(num):
            row, col = divmod(num-1, n)
            if row % 2 == 1: col = n-col-1
            return n-row-1, col
        
        des_row, des_col = convert_coor(m*n)
        
        queue = [(1, 0)]
        visited = set()
        visited.add(1)
        
        while queue:
            size = len(queue)
            for i in range(size):
                num, step = queue.pop(0)
                if num == m * n: 
                    return step
                for i in range(1, 7):
                    temp_num = num + i
                    temp_row, temp_col = convert_coor(temp_num)
                    if not 0 <= temp_row < m or not 0 <= temp_col < n: 
                        continue
                    if board[temp_row][temp_col] != -1:
                        temp_num = board[temp_row][temp_col]
                    if temp_num not in visited:
                        queue.append((temp_num, step+1))
                        visited.add(temp_num)
        return -1