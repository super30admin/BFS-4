Time: O(M*N)
Space: O(M*N)

/**
 * @param {number[][]} board
 * @return {number}
 */
var snakesAndLadders = function(board) {
    if (!board || !board.length) return 0;
    
    const N = board.length**2,
          flattenBoard = new Array(N);
    let leftToRight = true,
        i = board.length - 1,
        j = 0,
        index = 0;
    
    while (i >= 0 && j >= 0) {
        if (board[i][j] == -1) flattenBoard[index++] = -1;
        else flattenBoard[index++] = board[i][j] - 1;
        
        if (leftToRight) j++;
        else j--;
        if (j >= board.length) {
            j--;
            i--;
            leftToRight = false;
        } else if (j < 0) {
            j++;
            i--;
            leftToRight = true;
        }
    }

    const q = [];
    let steps = 0;
    q.push(0);
    flattenBoard[0] = -2;

    while (q.length > 0) {
        let level = q.length;
        
        for (i = 0; i < level; i++) {
            let current = q.shift();
            console.log(current);
            if (current == N - 1) return steps;
            
            for (j = 1; j < 7; j++) {
                let child = current + j;

                if (child < N && flattenBoard[child] != -2) {
                    if (flattenBoard[child] >= 0)
                        q.push(flattenBoard[child]);
                    else
                        q.push(child);

                    flattenBoard[child] = -2;
                }

            }
        }
        steps++;
    }
    
    return -1;
};
