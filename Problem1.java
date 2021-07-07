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
//Time : O(n)
//Space : O(n)
//LeetCode : Yes

//Approach:
//1.Put all employees into a map with employee i as key and employee as value.
//2.Put the first employee onto queue.
//3.Poll employee id from queue.Add his importance value.And then add his subordinates onto queue.
//4.Repeat this till queue is empty i.e till all employees importance has been added.Return final sum
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        HashMap<Integer,Employee> map = new HashMap<>();
        for(Employee e:employees){
            map.put(e.id,e);
        }
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(id);
        HashSet<Integer> set = new HashSet<>();
        while(!queue.isEmpty()){
            int current = queue.poll();
            if(!set.contains(current)){
                set.add(current);
                result+=map.get(current).importance;
                for(int i:map.get(current).subordinates)
                    queue.add(i);
            }
        }
        return result;
    }
}
