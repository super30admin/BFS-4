/* 
    Time Complexity                              :  O(N)
    Space Complexity                             :  O(1)
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  

class Solution {
private: 
    vector<vector<int>> dirs{{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        int n = board.size(), m = board[0].size();
        queue<vector<int>> q;
        q.push(click);
        while(!q.empty()) {
            vector<int> f = q.front();q.pop();
            int cx = f[0], cy = f[1];
            if(board[cx][cy] == 'B') {
                continue;
            } else if(board[cx][cy] == 'M') {
                board[cx][cy] = 'X';
                return board;
            }
         
            
            int mineCount = 0;
            for(auto dir : dirs) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];
                
                if(nx >= 0 and nx < n and ny >= 0 and ny < m and board[nx][ny] == 'M') {
                    mineCount++;
                }
            }
            
            if(board[cx][cy] == 'E' and mineCount == 0) {
                board[cx][cy] = 'B';
                for(auto dir : dirs) {
                    int nx = cx + dir[0];
                    int ny = cy + dir[1];
                
                    if(nx >= 0 and nx < n and ny >= 0 and ny < m) {
                        q.push({nx,ny});
                    }
                }
            } else {
                board[cx][cy] = '0' + mineCount;
            }
        }
        
        return board;
    }
};