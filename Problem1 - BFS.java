//TC : O(N)
//SC : O(N)

//IDEA - used the BFS Approach to get the all the importance and store result //and return the result.


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
    HashMap<Integer,Employee> map ;
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        int result=0;
        for(Employee e : employees) map.put(e.id,e);
        
        Queue<Employee> q = new LinkedList<>();
        q.offer(map.get(id));
        while(!q.isEmpty()){
            Employee tmp = q.poll();
            result += tmp.importance;
            for(Integer subEmp : tmp.subordinates)
            {
                q.offer(map.get(subEmp));
            }  
        }
        
        return result;
    }
}
