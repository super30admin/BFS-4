#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
Flattens the 2D into a 1D array in zigzag order.
Uses BFS to explore moves from start to destination using up to 6 steps per turn
Handles snakes/ladders by jumping to destination cell; visited cells are marked
*/
class Solution{
public:
    int n;
    int snakesAndLadders(vector<vector<int>>& board){
        n = board.size();
        vector<int> arr(n*n);

        int r = n-1, c = 0;
        bool dir = true;

        for(int i = 0; i < n*n; i++){
            if(board[r][c] == -1) arr[i] = board[r][c];
            else arr[i] = board[r][c] - 1;

            if(dir){
                c++;
                if(c == n){
                    r--; c--;
                    dir = false;
                }
            }else{
                c--;
                if(c == -1){
                    r--; c++;
                    dir = true;
                }
            }
        }

        queue<int> q;
        q.push(0);
        vector<bool> visited(n*n, false);
        visited[0] = true;
        int level = 0;

        while(!q.empty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int currIdx = q.front(); q.pop();
                for(int k = 1; k <= 6; k++){
                    int newIdx = currIdx + k;
                    if(newIdx == n*n - 1 || arr[newIdx] == n*n -1) return level +1;
                    if(!visited[newIdx]){
                        if(arr[newIdx] == -1){
                            q.push(newIdx);
                            visited[newIdx] = true;
                        }else{
                            q.push(arr[newIdx]);
                        }
                        visited[newIdx] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
};
