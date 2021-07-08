//TC : O(N)
//SC : O(N)

//IDEA : We are given with empid and their imp and subordinates  in a list.
//we need to have the all the empid , so that we can recursively find each //employee importance and add it final rest;

/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    HashMap<Integer,Employee> map;
    public int getImportance(List<Employee> employees, int id) {
            map = new  HashMap<>();
        for(Employee e : employees){
            if(!map.containsKey(e.id)){
                map.put(e.id,e);
            }
        }
        
        return dfs(id);
        
    }
    private int dfs(int empID){
        Employee e  = map.get(empID);
        int ans = e.importance;
        for(Integer subEmpID : e.subordinates){
            ans += dfs(subEmpID);
        }
        return ans;
    }
}
