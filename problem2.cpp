#include<iostream>
#include<vector>
#include<queue>

using namespace std;

class Solution {
    
    int adjacent_mines(vector<vector<char>>& board, vector<int>& pos,vector<vector<int>>& dirs){
        int m = board.size();
        int n = board.at(0).size();
        int cnt{};
        for(vector<int> dir:dirs){
            int r = pos.at(0) + dir.at(0);
            int c = pos.at(1) + dir.at(1);
            if(r>=0 && r<m && c>=0 && c<n && board.at(r).at(c) == 'M'){
                cnt++;
            }
        }
        return cnt;
    }
public:
    vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
        if(board.at(click.at(0)).at(click.at(1)) == 'M'){
            board.at(click.at(0)).at(click.at(1)) = 'X';
        }
        else{
            vector<vector<int>> dirs{
                {0,1},
                {0,-1},
                {1,0},
                {-1,0},
                {1,-1},
                {-1,1},
                {1,1},
                {-1,-1}
            };
            int m = board.size();
            int n = board.at(0).size();
            queue<vector<int>> q{};
            q.push(click);
            while(!q.empty()){
                int sz = q.size();
                for(int i{};i<sz;++i){
                    vector<int> pos = q.front();
                    q.pop();
                    int mine_cnt = adjacent_mines(board,pos,dirs);
                    if(mine_cnt == 0){
                        for(vector<int> dir:dirs){
                            int r = pos.at(0) + dir.at(0);
                            int c = pos.at(1) + dir.at(1);
                            if(r>=0 && r<m && c>=0 && c<n && board.at(r).at(c)=='E'){
                                vector<int> gg{r,c};
                                q.push(gg);
                                board.at(r).at(c) = 'G';
                            }
                        }
                        board.at(pos.at(0)).at(pos.at(1)) = 'B';
                    }
                    else{
                        board.at(pos.at(0)).at(pos.at(1)) = static_cast<char>(48+mine_cnt);
                    }
                }

            }
        }
        return board;
    }
};