// O(n^2) time and space

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board.length == 1)
            return 0;
        int steps = 0;
        int n = board.length;
        Map<Integer,Node> nodes = createBoard(board);
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[n-1][0] = true;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {    
                int curr = queue.poll();
                if(curr == n*n) {
                    return steps;
                }
                for(int k=1;k<=6;k++) {
                    int next = curr+k;
                    if(next > n*n)
                        break;
                    Node node = nodes.get(next);
                    if(visited[node.x][node.y])
                        continue;
                    visited[node.x][node.y]= true;
                    if(board[node.x][node.y] == -1)
                        queue.add(next);
                    else
                        queue.add(board[node.x][node.y]);
                }
            }
            steps++;
        }
       return -1;
    }
    
    public Map<Integer,Node> createBoard(int[][] board) {
        Map<Integer,Node> nodes = new HashMap<>();
        int x = board.length-1;
        int y = 0;
        int num = 0;
        boolean leftToRight = true;
        
        while(x >= 0 && y >= 0) {
            num++;
            nodes.put(num, new Node(x,y, board[x][y]));
            if(leftToRight && y == board.length-1) {
                x--;
                leftToRight = false;
            } else if(!leftToRight && y == 0) {
                x--;
                leftToRight = true;
            } else {
                if(leftToRight) {
                    y++;
                } else {
                    y--;
                }
            }
        }
        return nodes;
    }
    
    class Node {
        int x;
        int y;
        int val;
        
        Node(int x,int y,int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
}