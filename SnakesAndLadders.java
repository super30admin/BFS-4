//BFS
class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n= board.length;
        int [] arr = new int[n*n];
    boolean dir = true;
    int r=n-1;
    int c=0;
    int idx= 0;
    while(idx<n*n)
    {
        if(board[r][c]==-1)
        {
            arr[idx]= board[r][c];
        }
        else{
            arr[idx]= board[r][c]-1;
        }
        idx++;
       
       if(dir)
       {
        //left to right
        c++;
        if(c==n)
        {dir = false;
        r--;
        c--;
        }
       }
       else{
        c--;
        if(c==-1)
        {
        dir = true;
        r--;
        c++; 
        }
       }

    }
        
    Queue<Integer> q = new LinkedList<>();
    q.add(0);
    arr[0]=-2;
    int moves=0;
    while(!q.isEmpty())
    {
        int size = q.size();
        for(int i=0;i<size;i++)
        {
            int cur= q.poll();
            if(cur == n*n-1)
            {
                //reached destination
                return moves;
            }
            for(int j=1;j<=6;j++)
            {
                int newIdx = cur+j;
                if(newIdx > n*n-1)
                break;
                if(arr[newIdx]==-1)
                {
                     q.add(newIdx);
                }
                else{
                    if(arr[newIdx]!=-2)
                    q.add(arr[newIdx]);
                }
                 //making the visited as -2
                arr[newIdx]= -2;

            }

        }
        moves++;
    }
    return -1;
    }
}


/*Time Complexity: O(n^2), where n is the size of the board (number of rows or columns).

Space Complexity: O(n^2), for storing the board in a 1D array and the BFS queue. */