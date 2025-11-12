#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
DFS is used to explore and reveal all safe 'E' cells and convert them to 'B'.
If a cell has adjacent mines, it's updated with the count.
8-directional search is used to explore all neighboring cells.
*/
class Solution{
public:
    vector<vector<int>> dirs = {{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    int m, n;
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click){
        m = board.size();
        n = board[0].size();

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
        }else{
            dfs(board, click[0], click[1]);
        }
        return board;
    }

    void dfs(vector<vector<char>>& board, int i, int j){
        if(i < 0 || j < 0 || i >= m || j >= n || board[i][j]) return;
        
        int count = countMines(board, i, j);
        if(count == 0){
            board[i][j] = 'B';
            for(auto& dir : dirs){
                dfs(board, i+dir[0], j+dir[1]);
            }
        }else{
            board[i][j] = '0' + count;
        }
    }

    int countMines(vector<vector<char>>& board, int i, int j){
        int count = 0;
        for(auto& dir : dirs){
            int r = i + dir[0], c = j + dir[1];
            if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M')
                count++;
        }
        return count;
    }
};
