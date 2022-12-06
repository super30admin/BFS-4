// Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)

// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Flattening 2d array


// Your code here along with comments explaining your approach

/**
 * @param {number[][]} board
 * @return {number}
 */
var snakesAndLadders = function (board) {
    if (board === null || board.length === 0)
        return 0;
    let n = board.length;
    let nums = new Array(n * n);
    // Flatten the 2d array
    let row = n - 1, col = 0, even = 0;
    for (let i = 0; i < n * n; i++) {
        if (board[row][col] === -1) {
            nums[i] = -1;
        } else {
            nums[i] = board[row][col] - 1;
        }
        if (even % 2 === 0) {
            col++;
            if (col === n) {
                col--;
                row--;
                even++;
            }
        } else {
            col--;
            if (col === -1) {
                col++;
                even++;
                row--;
            }
        }
    }

    let queue = [];
    queue.push(0);
    nums[0] = -2;
    let level = 0;
    while (queue.length > 0) {
        let size = queue.length;
        for (let j = 0; j < size; j++) {
            curr = queue.shift();
            if (curr === ((n * n) - 1))
                return level;
            for (let i = 1; i <= 6; i++) {
                let newcurr = curr + i;
                if (newcurr >= n * n)
                    continue;
                if (nums[newcurr] === -1) {
                    queue.push(newcurr);
                    nums[newcurr] = -2;
                } else {
                    if (nums[newcurr] != -2) {
                        queue.push(nums[newcurr]);
                        nums[newcurr] = -2;
                    }
                }
            }
        }
        level++;
    }
    return -1;
};