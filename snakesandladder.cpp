 // Time complexity-O(n*n)
// space complexity O(n);
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : no

// flatten the 2d board into 1d matrix
// add the index of the first position and do a standard bfs until you are able to reach the last index
// each level of the bfs counts as one move




#include<algorithm>
#include<iostream>
#include<vector>
#include<stack>
#include<queue>

using namespace std;

 class Solution {
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        // edge 
        if(board.size()== 0){
            return 0;
        }
        int r= board.size();int c= board[0].size();
        int moves[r*r];
        int i =r-1;int j=0;int even=0;
        int idx=0;
        
        while(i>=0 && i <r && j>=0 && j<c){
                if(board[i][j] == -1){
                   moves[idx]= board[i][j];
                }else{
                    moves[idx]= board[i][j]-1;
                }
                
                idx++;
                
                    if(even % 2==0){
                        j++;
                        if(j==c){
                            even++;
                            j= c-1;
                            i--;
                        }
                    }
                    else{
                        j--;
                        if(j<0){
                            even++;
                            j= 0;
                            i--;
                        }
                    }
            }
        
        queue<int>q;
        q.push(0); moves[0]= -2;
        int minimum_moves{0};
        while(!q.empty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int curr = q.front();q.pop();
                    // check if this is the last index
                if(curr== r*r-1){return minimum_moves;}
                for(int k=1;k<=6;k++){
                    int child = curr + k;
                    if(child > r*r-1) break;
                    // check if the child is visited
                    if(moves[child]!=-2){
                        if(moves[child]==-1){
                            q.push(child);
                        }else{
                            q.push(moves[child]);
                        }
                        moves[child]=-2;
                    }
                }
            }
            minimum_moves++;          
        }
        return -1;
    }
};