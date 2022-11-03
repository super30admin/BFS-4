/*
O(mn)
O(n)
*/
class Solution {
    int[][] board; 
    int n;
    
    public int snakesAndLadders(int[][] board) {
        this.board = board; 
        this.n = board.length;
        
        if (n == 1) return 0;
        
        // find the map for snakes and ladder
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i <= n * n; i++) {
            int[] pos = getPos(i);
            int r = pos[0], c = pos[1];
            if (board[r][c] != -1) {
                map.put(i, board[r][c]);
            }
        }
        
        Queue<Integer> queue = new LinkedList<>(); // bfs
        Set<Integer> set = new HashSet<>(); // cycle detection
        int steps = 0;
        
        queue.add(1);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {
                int v = queue.poll();
                if (set.contains(v)) continue;
                set.add(v);
            
                // found solution                
                if (v >= n * n) return steps; 
                
                // next step
                for(int i = 1; i <= 6; i++) {
                    int next = v + i;
                    
                    // if there's a ladder or snake
                    if (map.containsKey(next)) {
                        queue.add(map.get(next));
                    } else {
                        // regular step
                        queue.add(next);
                    }
                }
            }
            
            steps++;
        }
        
        return -1;
    }
    
    private int[] getPos(int v) {
        v -= 1; // adjust back to 0 index
        int row = n - 1 - (v / n);
        boolean isEven = (v/n) % 2 == 0;
        int col = v % n;
        
        if (isEven) {
            return new int[] {row, col};
        } else {
            return new int[] {row, n-1-col};
        }
    }
}