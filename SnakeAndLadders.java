import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
/*
Time Complexity: O(N*M), N is the number of rows and M is the number of cols
Space Complexity: O(N*M), using Queue and Hashset
Run on Leetcode: Yes
Any Difficulties: No

Approach:
1. Using Breadth first search to solve the problem
2. Iterating on the coordinates where ever -1 is found
3. getting coordinates using current position
 */
public class SnakeAndLadders {
    public static int snakeAndLadders(int[][] board){
        HashSet<Integer> set = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        int length = board.length;
        set.add(1);
        queue.add(new int[]{1,1});

        while(!queue.isEmpty()){
            int[] curr = queue.remove();

            for(int i = 1; i<=6; i++){
                int nextPos = curr[0]+i;

                int[] coordinates = getCoordinates(nextPos, length);
                int x = coordinates[0];
                int y = coordinates[1];

                if(board[x][y]!= -1){
                    nextPos = board[x][y];
                }

                if(nextPos == length*length){
                    return curr[1];
                }

                if(!set.contains(nextPos) && nextPos<= length*length){
                    set.add(nextPos);
                    queue.add(new int[]{nextPos, curr[1]+1});
                }
            }
        }
        return -1;
    }

    public static int[] getCoordinates(int current, int length){
        int row = (current-1)/length;
        int col = (current-1)%length;

        int x = length-1-row;
        int y = row%2 == 0 ? col : length-1-col;

        return new int[]{x, y};
    }

    public static void main(String[] args){
        int[][] board = {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };

        System.out.println("Moves required to reach at the final state: "+ snakeAndLadders(board));
    }
}
