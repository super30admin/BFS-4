//Time O(n) where n is the total number of cells in the matrix

class Solution {
    public int snakesAndLadders(int[][] board) {
        class GameMove{   // Class to keep track of each game move. Each move has 2 aspects, the destination on the board that it leads you to and the number of moves it took you to get there.
            int destination;
            int moves;
            public GameMove(int dest, int mov)
            {
                this.destination= dest;
                this.moves= mov;
            }
        }
        
       int n = board.length;
       int r = n-1 , c = 0, even =0, idx =0;  // since the board array is given in a jumbled up order(as mentioned in the question) we need to process it and put it into a regular array for ease of computation.
       int [] arr =  new int [(n*n)];
       while(idx<(n*n))
       {
           arr[idx] = board[r][c]==-1?idx+1:board[r][c]; // if board[r][c]==-1 it means that the position represents the actual number of that index like 1, 2, etc, so just put idx+1(since its 0 indexed) there and otherwise put the number inside board[r][c] which is the destination that the snake or the ladder at that position leads to.
            idx++; 
           
           if(even%2==0)  // for even rows, move from left to right(c++)
           {
               c++;
               if(c==n)  // if you reach the end of the row, increase even so that next row is odd and also reduce c to n-1 since for the odd rows you move from right to left within the row
               {
                   r--;
                   c--;
                   even++;
               }
           }
           else  // working of an odd row, similar to an even row just the logic reversed.
           {
               c--;
               if(c==-1)
               {
                   c++;
                   r--;
                   even++;
               }
           }
            
       }
        
  Queue<GameMove> q = new LinkedList<>();
  q.add(new GameMove(1,0));
  System.out.println(Arrays.toString(arr));  
  boolean[] visited= new boolean[arr.length+1];
  while(!q.isEmpty())
  {
      GameMove curr= q.poll();
      int pos = curr.destination;
      int moves= curr.moves;
      for(int i = 1; i<=6;i++)
      {      
          if(pos+i>=n*n || arr[pos+i-1]>= (n*n))
              return moves+1;
          if(!visited[i+pos])
          { visited[i+pos]= true;   
          GameMove m = new GameMove(arr[pos+i-1], moves+1);
          q.add(m);
          }
      } 
      
  }
        return -1;
    
      
        
    }
}