/*
// Time Complexity : O(N^2)
// Space Complexity : O(N^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
intially flatten the baord into array
You add intital position of the board into the queue
From their go to places from pos+1 to pos+6 if they dont exceed n*n else until n*n-1
Then as you go through each level of the BFS increase the number of moves. And put the index of the position
if it is not -1 replace it with the index of the array and put it into  hashset to avoid repetittion of those places
Go through the graph until you find the shortest route . If cant find return -1;
*/

//bfs

#include<iostream>
#include<vector>
#include<unordered_set>
#include<queue>

using namespace std;

class Solution {
    void display(const vector<int>& vec){
        for(const int i:vec){
            cout<<i<<" ";
        }
        cout<<endl;
    }
public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        int flag{true};
        int r{n-1},c{0};
        vector<int> board_arr(n*n,-1);
        for(int i{};i<n*n;i++){
            if(board.at(r).at(c)!=-1)   board_arr.at(i) = board.at(r).at(c)-1;//preprocessing to store the index of the array.
            if(flag){
                c++;
                if(c==n){
                    r--;
                    c--;
                    flag = false;
                }
            }
            else{
                c--;
                if(c<0){
                    r--;
                    c++;
                    flag = true;
                }
            }
        }
        //display(board_arr);
        int cnt{};
        queue<int> q{};
        unordered_set<int> uset{};
        q.push(0);uset.insert(0);
        while(!q.empty()){
            int sz = q.size();
            cnt++;
            for(int i{};i<sz;++i){
                int idx = q.front();
                //cout<<idx<<" ";
                q.pop();
                for(int j{1};j<7;++j){
                    int nidx = idx+j;
                    if(nidx < n*n){
                        if(nidx == n*n-1 || board_arr.at(nidx) == n*n-1) return cnt;
                        if(board_arr.at(nidx)!=-1){
                            nidx = board_arr.at(nidx);
                        }
                        if(uset.find(nidx) == uset.end()){
                            q.push(nidx);
                            uset.insert(nidx);
                        }
                    }
                } 
            }
        }
        return -1;
    }
};

//using DFS Solution, having trouble with one or another test case.
//For DFS save the minimum number of moves from each position and add those moves from the position those next indices are called.

class Solution {
    void display(const vector<int>& vec){
        for(const int i:vec){
            cout<<i<<" ";
        }
        cout<<endl;
    }

    vector<int> get_flatten(vector<vector<int>>& board){
        int n = board.size();
        int r{n-1},c{0};
        int flag{true};
        vector<int> board_arr(n*n,-1);
        for(int i{};i<n*n;++i){
            if(board.at(r).at(c)!=-1) board_arr.at(i) = board.at(r).at(c)-1;
            //above subtract -1 for preprocessing
            if(flag){
                c++;
                if(c==n){
                    r--;
                    c--;
                    flag = false;
                }
            }
            else{
                c--;
                if(c<0){
                    r--;
                    c++;
                    flag = true;
                }
            }
        }
        return board_arr;
    }

    unordered_map<int,int> umap{};
    int dfs(vector<int> board_arr,int idx){
        //base
        int n = board_arr.size();
        //cout<<idx<<" ";
        if(idx == n-1) return 0;
        if(umap.find(idx)!=umap.end()) return umap[idx];
        umap[idx] = INT_MAX;
        int min_moves = umap[idx];
        //logic
        for(int j{1};j<7;++j){
            int nidx = idx+j;
            if(nidx<n){
                if(board_arr.at(nidx)!=-1) nidx = board_arr.at(nidx);
                int moves = dfs(board_arr,nidx);
                if(moves!=INT_MAX){
                    min_moves = min(min_moves,moves+1);
                }
            } 
        }
        umap[idx] = min_moves;
        return umap[idx];
    }

public:
    int snakesAndLadders(vector<vector<int>>& board) {
        int n = board.size();
        vector<int> board_arr = get_flatten(board);
        int result = dfs(board_arr,0);
        return result == INT_MAX?-1:result;
        //display(board_arr);
    }
};