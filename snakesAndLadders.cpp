/*Intuition: We can do BFS to get minMoves. All we need to worry about is the board indices.
We convert the 2d board to 1d
Time: O(N^2)
Space: O(N^2)
*/
class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        vector<int>moves;
        int n = board.size();
        int i = n -1;
        int j = 0;
        int even = 0;
        while ( i >= 0 and j >= 0){
        
            if ( board[i][j] == -1){
                moves.push_back(-1);
            }
            else{
                moves.push_back(board[i][j] -1);
            }
            
            
            if ( even % 2 == 0){
            
                j++;
                if ( j == n){
                    j = n-1;
                    i--;
                    even++;
                }
                
            }
            else{
                j--;
                if ( j < 0){
                    j = 0;
                    i--;
                    even++;
                }
                
            
            }

        }
        
        queue<int> queue;
        queue.push(0);
        moves[0] = -2;
        int minMoves = 0;
        while ( queue.size() != 0){
            
            int size = queue.size();
            for ( int i = 0; i < size; i++){
            
                auto index = queue.front();
                queue.pop();
                
                
                if ( index == n*n -1) return minMoves;
                
                for ( int k = 1; k <= 6; k++){
                    
                    int newIndex = k + index;
                    if ( newIndex >= n*n)break;
                    if ( moves[newIndex] != -2){
                        
                        if ( moves[newIndex] == -1){
                            queue.push(newIndex);
                        }
                        else{
                            queue.push(moves[newIndex]);
                        }
                        moves[newIndex] = -2;
                    }  
                }

            }
            minMoves++;
            
            
            
            
        }
        return -1;
            
    }
};