# tc O(mn), sc O(mn).
if len(board) == 0:
    return board
ROWS, COLUMNS = len(board), len(board[0])

def get_neighbors(row, column):
    neighbors = []
    
    for neighbor_row in range(row-1, row+2):
        for neighbor_column in range(column-1, column+2):
            if neighbor_row == row and neighbor_column == column:
                continue
            if neighbor_row in range(ROWS) and neighbor_column in range(COLUMNS):
                neighbors.append([neighbor_row, neighbor_column])

    return neighbors

def get_mines_near_me(row, column):
    mines = 0
    neighbors = get_neighbors(row, column)
    for neighbor_row, neighbor_column in neighbors:
        if board[neighbor_row][neighbor_column] == "M":
            mines += 1
    return mines, neighbors

def reveal_squares_with_dfs(row, column):
    if board[row][column] == "M":
        board[row][column] = "X"
        return True
    if board[row][column] == "E":
        mines_near_me, neighbors = get_mines_near_me(row, column)
        if mines_near_me != 0:
            board[row][column] = str(mines_near_me)
        else:
            board[row][column] = "B"
            for neighbor_row, neighbor_column in neighbors:
                if board[neighbor_row][neighbor_column] in {"M", "E"}:
                    if reveal_squares_with_dfs(neighbor_row, neighbor_column):
                        return True
    return False         

reveal_squares_with_dfs(click[0], click[1])
return board