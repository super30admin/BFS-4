// Time complexity-O(row * cols)
// space complexity O(row * cols);
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : no

//if the click position has a mine return the boord and end game instantly
// add the current click position to the queue  and run a standard bfs 
// pop the element and calculate the number of mines around it
// if there are no mines go in all 8 directions and add to the queue
// if there is a mine just update the current cell with the mine count




#include<algorithm>
#include<iostream>
#include<vector>
#include<stack>
#include<queue>

using namespace std;
class Solution {
public:
    vector<vector<int>>dirs={{0,1},{0,-1},{1,0},{1,1},{1,-1},{-1,1},{-1,0},{-1,-1}};
   
    
    int getMines(vector<vector<char>>& board,int i,int j){
        int count{0};
        int m = board.size(); int n = board[0].size();
        for( auto dir : dirs){
                    int r = i + dir[0];
                    int c = j + dir[1];
                    
                    if(r>=0 && r <m && c >=0 && c<n && board[r][c]=='M'){
                        count++;
                    }
                }
        return count;
    }
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        // edge 
        if(board.size()==0) return board;
        
        int m = board.size(); int n = board[0].size();
        
        // check if the click position is a mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board,click[0],click[1]);
       return board;
    }

    void dfs(vector<vector<char>>& board, int i,int j){
        // check if the click position is a mine
        
         int m = board.size(); int n = board[0].size();
            // base 
            if(i<0 || i==m || j <0 || j==n || board[i][j]!='E'){
                   return; 
            } 
            

            // logic 
             board[i][j] = 'B'; // mark this as visited
            int mines = getMines(board,i,j);
            if(mines ==0){
                for( auto dir : dirs){
                    int r = i + dir[0];
                    int c = j + dir[1];
                    dfs(board,r,c); 
                }
            }else{
                  board[i][j]= mines +'0';  
            }

    }
};






/*****************************************************************bfs*********************************************************/
class Solution {
public:
    vector<vector<int>>dirs={{0,1},{0,-1},{1,0},{1,1},{1,-1},{-1,1},{-1,0},{-1,-1}};
    
    int getMines(vector<vector<char>>& board,int i,int j){
        int count{0};
        int m = board.size(); int n = board[0].size();
        for( auto dir : dirs){
                    int r = i + dir[0];
                    int c = j + dir[1];
                    
                    if(r>=0 && r <m && c >=0 && c<n && board[r][c]=='M'){
                        count++;
                    }
                }
        return count;
    }
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        // edge 
        if(board.size()==0) return board;
        
        int m = board.size(); int n = board[0].size();
        
        // check if the click position is a mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        queue<vector<int>>q;
        //vector<int>temp={click[0],click[1]};
        q.push({click[0],click[1]});
        board[click[0]][click[1]] ='B';
        
        while(!q.empty()){
            vector<int>pos = q.front();q.pop();
            int mines = getMines(board,pos[0],pos[1]);
            
            if (mines ==0){
                for( auto dir : dirs){
                    int r = pos[0] + dir[0];
                    int c = pos[1] + dir[1];
                    
                    if(r>=0 && r<m && c >=0 && c<n && board[r][c]=='E'){
                        q.push({r,c});
                        board[r][c] = 'B';
                    }
                }
            }else{
                board[pos[0]][pos[1]]=mines + '0';
            }
        }
            
      return board;  
    }
};