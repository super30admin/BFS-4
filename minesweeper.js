see: https://brandonjgoodman.com/minesweeper
- Hold ALT and click to mark a mine
- Click to reveal a square (can click on numbers too if the appropriate number of mines are marked)

Time:
  - BFS: O(M*N)
  - DFS: O(M*N)

Space:
  - BFS: O(M+N) maximum elements in the BFS would be the perimeter of our board
  - DFS: O(M*N)

/**
 * @param {character[][]} board
 * @param {number[]} click
 * @return {character[][]}
 */

// BFS
let dx;
let dy;
var updateBoard = function(board, click) {
    if (!board || !board.length) return board;
    
    const m = board.length;
    const n = board[0].length;
    const row = click[0];
    const col = click[1];
    
    if (board[click[0]][click[1]] == 'M') {
        board[click[0]][click[1]] = 'X';
        return board;
    }
    
    dx = [0, 0, 1, -1, 1, 1, -1, -1];
    dy = [1, -1, 0, 0, 1, -1, 1, -1];
    
    const q = [];
    q.push([row, col]);
    board[row][col] = 'B';
    
    while (q.length > 0) {
        const curr = q.shift(),
              row = curr[0],
              col = curr[1];
        const count = countMines(board, row, col);
        if (count == 0) {
            for(let i = 0; i < 8; i++) {
                let x = row + dx[i];
                let y = col + dy[i];

                if (isValid(board, x, y) && board[x][y] == 'E') {
                    board[x][y] = 'B';
                    q.push([x, y]);
                }
            }
        } else {
            board[row][col] = `${count}`;
        }
        
    }

    return board;
}


// DFS
// let dx;
// let dy;
// var updateBoard = function(board, click) {
//     if (!board || !board.length) return board;
    
//     const m = board.length;
//     const n = board[0].length;
//     const row = click[0];
//     const col = click[1];

//     if (board[click[0]][click[1]] == 'M') {
//         board[click[0]][click[1]] = 'X';
//         return board;
//     }
    
//     dx = [0, 0, 1, -1, 1, 1, -1, -1];
//     dy = [1, -1, 0, 0, 1, -1, 1, -1];
    
//     dfsVisit(board, row, col);

//     return board;
// };

// const dfsVisit = function(board, row, col) {
//     const count = countMines(board, row, col);
//     if (count == 0) {
//         board[row][col] = 'B';
//         for(let i = 0; i < 8; i++) {
//             let x = row + dx[i];
//             let y = col + dy[i];
                
//             if (isValid(board, x, y) && board[x][y] == 'E') {
//                 dfsVisit(board, x, y);
//             }
//         }
//     } else {
//         board[row][col] = `${count}`;
//     }
// }

const countMines = function(board, row, col) {
    let count = 0;
    for (let i = 0; i < 8; i++) {
        let x = row + dx[i];
        let y = col + dy[i];
        if (isValid(board, x, y) && board[x][y] == 'M') count++;
    }
    
    return count;
}

const isValid = function(board, x, y) {
    return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
}
