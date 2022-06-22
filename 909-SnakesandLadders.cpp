/* 
    Time Complexity                              :  O(2*N*N) ~= O(N*N) 
    Space Complexity                             :  O(N*N) to store the values in the moves array
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std;  

class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n=board.size();
        vector<int> moves(n*n,-1);
        bool dir=true;
        int r=n-1,c=0,idx=0;
        while(r>=0 and c>=0) {
            if(board[r][c] == -1) {
                moves[idx] = -1;
            } else {
                moves[idx] = board[r][c] - 1;
            }
            idx++;
            
            if(dir) {
                c++;
                if(c == n) {
                    c--;
                    r--;
                    dir = false;
                }
            } else {
                c--;
                if(c == -1) {
                    c++;
                    r--;
                    dir = true;
                }
            }
            
        }
        
        // implement bfs
        queue<int> q;
        q.push(0);
        moves[0] = -2;
        int count=0;
        while(!q.empty()) {
            int sz = q.size();
            for(int i=0;i<sz;i++) {
                int id=q.front();q.pop();
                for(int j=1;j<=6;j++) {
                    int nid = id + j;
                    if(nid == n*n - 1 or moves[nid] == n*n - 1) 
                        return count+1;
                    if(nid >= n*n)
                        continue;
                    
                    if(moves[nid] != -2) {
                        if(moves[nid] == -1) {
                            q.push(nid);
                        } else {
                            q.push(moves[nid]);
                        }
                        moves[nid] = -2;
                    }
                }
            }
            count++;
        }
        
        return -1;
    }
};