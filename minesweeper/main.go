// bfs
// func updateBoard(board [][]byte, click []int) [][]byte {
    
//     m := len(board)
//     n := len(board[0])
//     sr := click[0]
//     sc := click[1]
//     if board[sr][sc] == 'M' {
//         board[sr][sc] = 'X'
//         return board
//     }
    
//     board[sr][sc] = 'B' // visited
//     q := [][]int{{sr,sc}}
 
//     for len(q) != 0 {
//         // process current cell
//         dq := q[0]
//         q = q[1:]
//         currRow := dq[0]
//         currCol := dq[1]
        
//         // check for adjacent mines
//         numMines := countMinesAround(currRow, currCol, board)
//         if numMines > 0 {
//             board[currRow][currCol] = byte(numMines + '0')
//         } else {
//             board[currRow][currCol] = 'B'
//             // enqueue univistedCells around them and mark them visited
//             for _, dir := range dirs {
//                 r := currRow + dir[0]
//                 c := currCol + dir[1]
//                 if r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E' {
//                     board[r][c] = 'B'
//                     q = append(q, []int{r,c})
//                 }
//             }
//         }   
//     }
    
//     return board
// }

// dfs
func updateBoard(board [][]byte, click []int) [][]byte {
    var dfs func(r,c int)
    m := len(board)
    n := len(board[0])
    sr := click[0]
    sc := click[1]
    if board[sr][sc] == 'M' {
        board[sr][sc] = 'X'
        return board
    }
    
    dfs = func(r, c int) {
        // base
        if r < 0 || r == m || c < 0 || c == n || board[r][c] != 'E' {
            return
        }
        
        // logic
        num := countMinesAround(r,c, board)
        if num > 0 {
            board[r][c] = byte(num+'0')
        } else {
            board[r][c] = 'B'
            for _, dir := range dirs {
                dfs(r+dir[0], c+dir[1])
            }
        }
    }
    dfs(sr,sc)
    return board
}


var dirs = [][]int{
        {-1,0}, // up
        {1,0}, // down
        {0,-1}, // left
        {0,1}, //right
        {-1,-1}, // diag-up-left
        {-1,1}, // diag-up-right
        {1,-1}, // diag-down-left
        {1,1}, // diag-down-right
    }


func countMinesAround(r,c int, board[][]byte) int {
    m := len(board)
    n := len(board[0])

    numMinesFound := 0
    for _, dir := range dirs {
        newR := r + dir[0]
        newC := c + dir[1]
        if newR >= 0 && newR < m && newC >= 0 && newC < n && board[newR][newC] == 'M' {
            numMinesFound++
        }
    }
    return numMinesFound
}
