//time complexity -> O(n)
 
//space complexity -> O(n)

#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

class Solution {
public:
    int getImportance(vector<Employee*> employees, int id) 
    {
        unordered_map<int, Employee*>mp;
        
        
        for(int i = 0; i < employees.size(); i++)
            mp[employees[i]->id] = employees[i];
        
        return dfs(mp, id);
    }
    
    
    int dfs(unordered_map<int, Employee*>&mp, int id)
    {
        int totalImportance = mp[id]->importance;
        
        for(int i = 0; i < mp[id]->subordinates.size(); i++)
        {
            totalImportance += dfs(mp, mp[id]->subordinates[i]);
        }
        
        return totalImportance;
    
    }
};