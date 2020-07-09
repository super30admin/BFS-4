/*
The time complexity is O(N^2) and the space complexity is also O(N^2) where N is the length of the board.

Here the intuition is to start from 1 and add this to the queue. We have a map which consists of numbers and the
number of steps we took to reach there. For for every number in the queue check where it can reach when we roll the dice from 1-6.
Add it to the queue and store its step length in the map. When we reach last number return the steps from the map.

Yes, the solution passed all the test cases in leetcode.
 */

class Solution {
    public int snakesAndLadders(int[][] board) {
        int length = board.length; int size = length*length;
        Map<Integer,Integer> dist = new HashMap<>();

        //Store 1's distance as 0;
        dist.put(1,0);
        Queue<Integer> q = new LinkedList<>(); q.offer(1);

        while(q.size()>0){
            int curr = q.poll();
            int currDist = dist.get(curr);

            //If the curr is equal to last number return the distance.
            if(curr==size){
                return dist.get(curr);
            }
            else{

                //Go to its 6 children
                for(int i=curr+1;i<=Math.min(size,curr+6);i++){
                    int num = get(i,length);
                    int r = num/length;
                    int c = num%length;
                    int next = board[r][c]==-1?i:board[r][c];

                    //Store each childrens dist in map and add it to the queue.
                    if(!dist.containsKey(next)){
                        dist.put(next,currDist+1);
                        q.offer(next);
                    }
                }
            }
        }

        return -1;
    }

    public int get(int num, int N){
        int q = (num-1)/N;
        int row = (N-1)-q;
        int rem = (num-1)%N;
        int col = row%2 != N%2?rem:(N-1)-rem;
        return row*N+col;
    }
}