#include <iostream>
#include <vector>
#include <unordered_map>
#include <queue>

using namespace std;

class Solution {
    public:
        int getImportance(vector<Employee*> employees, int id) {
            int size = employees.size();
            
            unordered_map<int, Employee*>mp;
            
            for(int i = 0; i < size; i++)
                mp[employees[i]->id] = employees[i];
            
            queue<int> myQueue;
            myQueue.push(id);
            
            int totalImportance = 0;
            
            while(!myQueue.empty())
            {
                int tempID = myQueue.front();
                myQueue.pop();
                
                totalImportance += mp[tempID]->importance; 
                
                for(int i = 0; i < mp[tempID]->subordinates.size(); i++)
                    myQueue.push(mp[tempID]->subordinates[i]);    
            }
            
            return totalImportance;
        }
};