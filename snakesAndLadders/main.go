/*
    time: o(n^2)
    space: o(n^2)
*/
func snakesAndLadders(board [][]int) int {
    n := len(board)
    moves := make([]int, n*n)
    r := n-1
    c := 0
    idx := 0
    even := 0
    for idx < n*n {
        if board[r][c] != -1 {
            moves[idx] = board[r][c]-1
        } else {
            moves[idx] = board[r][c]
        }
        idx++
        if even % 2 == 0 {
            c++
            if c == n {
                c--
                r--
                even++
            }
        } else {
            c--
            if c == -1 {
                c++
                r--
                even++
            }
        }
    }
    
    level := 0
    q := []int{0}
    moves[0] = -2 //mark this node visited
    for len(q) != 0 {
        qSize := len(q)
        for qSize != 0 {
            dq := q[0]
            q = q[1:]
            if dq == n*n-1 {
                return level
            }
            for i := 1; i <= 6; i++ {
                child := dq + i
                if child < n*n { // out of bound is not an option
                    if moves[child] != -2 { // if not visited ( i.e not already in the queue )
                        if moves[child] == -1 { // if this cell does not have a ladder
                            q = append(q, child) // then simply add that cell
                        } else { // other wise if the cell has a ladder, take the ladder and add that resulting cell of ladder in queue
                            q = append(q, moves[child])
                        }
                        moves[child] = -2 // finally mark this child visited
                    }
                }
            }
            qSize--
        }
        level++
    } 
    return -1
}
