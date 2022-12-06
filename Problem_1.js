// Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

// Time Complexity : O(V+E)
// Space Complexity : O(V)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

let dirs, m, n;
var getMines = (board, r, c) => {
    let count = 0;
    for (let i = 0; i < dirs.length; i++) {
        let nr = r + dirs[i][0];
        let nc = c + dirs[i][1];
        if (nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] === 'M')
            count++;
    }
    return count;
}

var dfs = (board, r, c) => {
    // Base case
    if (r < 0 || c < 0 || r >= m || c >= n || board[r][c] !== 'E')
        return;
    // Logic
    let mineCount = getMines(board, r, c);
    if (mineCount > 0) {
        board[r][c] = mineCount.toString();
    } else {
        board[r][c] = "B";
        for (let i = 0; i < dirs.length; i++) {
            let nr = r + dirs[i][0];
            let nc = c + dirs[i][1];
            dfs(board, nr, nc);
        }
    }

}
/**
 * @param {character[][]} board
 * @param {number[]} click
 * @return {character[][]}
 */
var updateBoard = function (board, click) {
    if (board === null || board.length === 0 || click === null || click.length === 0)
        return board;

    m = board.length;
    n = board[0].length;
    dirs = [[-1, 0], [-1, 1], [0, 1], [1, 1], [1, 0], [1, -1], [0, -1], [-1, -1]]; // U, UR, R, DR, D, DL, L, LU

    if (board[click[0]][click[1]] === 'M') {
        board[click[0]][click[1]] = 'X';
        return board;
    }

    dfs(board, click[0], click[1]);

    // let queue = [];
    // queue.push([click[0] , click[1]]);
    // board[click[0]][click[1]] = 'B';
    // while(queue.length > 0){
    //     let curr = queue.shift();
    //     let mineCount = getMines(board, curr[0], curr[1]);

    //     if(mineCount > 0){
    //         board[curr[0]][curr[1]] = mineCount.toString();;
    //     } else {
    //         for(let i=0; i<dirs.length; i++){
    //             let nr = curr[0] + dirs[i][0];
    //             let nc = curr[1] + dirs[i][1]; 
    //             if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] === 'E'){
    //                 board[nr][nc] = "B";
    //                 queue.push([nr,nc]);
    //             }
    //         }
    //     }
    // }

    return board;
};