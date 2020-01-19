import java.util.List;
//time complexity is o(n)
//space complexity is o(n)
//idea is to check id in list and fetch its subordinate and store its importance in global variable.
// recursively call process on its subordinates and add importance in global veriable.
//return total importance
public class EmployeeImportance {
	
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
	
	    int totalImportance =0;
	    public int getImportance(List<Employee> employees, int id) {
	        for(Employee employee : employees){
	            if(employee.id == id){
	                totalImportance+=employee.importance;
	                List<Integer> childSubordinate = employee.subordinates;
	                for(int idx :childSubordinate){
	                    getImportance(employees, idx);
	                }
	            }
	        }
	        return totalImportance;
	    }
	
}
