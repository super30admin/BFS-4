// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        int count = board.length*board.length;
        int moves = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board.length;j++) {
                if(i%2==0) {
                    if(board[i][j]==-1){
                        count--;
                        continue;
                    }
                    map.put(count--, board[i][j]);
                }else{
                    if(board[i][board.length-j-1] == -1){
                        count--;
                        continue;
                    }
                    map.put(count--, board[i][board.length-j-1]);
                }
            }
        }
        
        count = board.length*board.length;
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(1);
        visited.add(1);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                int current = queue.poll();
                if(current == count)
                    return moves;
                
                for(int j = current+1;j<current+7;j++) {
                    
                    if(j>count)
                        continue;
                    
                    if(map.containsKey(j)) 
                        j = map.get(j);
                        
                    if(!visited.contains(j)) {
                        queue.add(j);
                        visited.add(j);
                    }
                }
            }
            moves++;
        }
        
        
        return -1;
    }
}
