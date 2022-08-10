//Time Complexity- O(n*m)
//Space Complexity- O(n*m)

class Solution {
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        
        if(board.size()==0 || board[0].size()==0){
            return board;
        }
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        
        int row=board.size();
        int col=board[0].size();
        queue<vector<int>> q;
        q.push({click[0],click[1]});
        board[click[0]][click[1]]='B';
        
        while(!q.empty()){
            auto curr=q.front();
            q.pop();
            int count=countMines(curr[0],curr[1],row,col,board);
            if(count>0){
                board[curr[0]][curr[1]]=(char)(count+'0');
            }
            else{
                for(int i=-1;i<=1;i++){
                    for(int j=-1;j<=1;j++){
                        if(i==0 && j==0){
                            continue;
                        }
                        int nr=curr[0]+i;
                        int nc=curr[1]+j;
                        if(nr>=0 && nc>=0 && nr<row && nc<col && board[nr][nc]=='E'){
                            q.push({nr,nc});
                            board[nr][nc]='B';
                        }
                    }
                }
            }
        }
        return board;
    }
    
    int countMines(int r,int c,int row,int col,vector<vector<char>> &board){
        
        int count=0;
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(i==0 && j==0){
                    continue;
                }
                int nr=r+i;
                int nc=c+j;
                if(nr>=0 && nc>=0 && nr<row && nc<col && board[nr][nc]=='M'){
                    count++;
                }
            }
        }
        return count;
    }
};