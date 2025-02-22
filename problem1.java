import java.util.LinkedList;
import java.util.Queue;

// Time complexity: 0(m*n)
// Space complexity: 0(m*n)

public class problem1 {

    public int snakesAndLadders(int[][] board) {
        int n= board.length;
        int[] arr= new int[n*n];

        boolean flag=true;

        int r= n-1, c=0;
        for (int i=0;i<n*n;i++)
        {
            if (board[r][c]==-1)
            {
                arr[i]= board[r][c];
            }
            else {
                arr[i]=board[r][c]-1;
            }
            if (flag)
            {
                c++;
                if (c==n)
                {
                    flag=false;
                    c=n-1;
                    r--;
                }
            }
            else {
                c--;
                if (c==-1)
                {
                    flag=true;
                    r--;
                    c=0;

                }
            }
        }
        Queue<Integer> q= new LinkedList<>();
        q.add(0);
        int level=0;
        while (!q.isEmpty())
        {
            int size=q.size();
            for (int i=0;i<size;i++){
                int currIdx= q.poll();
                for (int k=1;k<=6;k++)
                {
                    int newIdx= currIdx+k;
                    if (newIdx==n*n-1 || arr[newIdx]==n*n-1)
                    {
                        return level+1;
                    }
                    if (arr[newIdx]!=-2)
                    {
                        if (arr[newIdx]==-1)
                        {
                            q.add(newIdx);
                        }
                        else {
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx]=-2;
                    }
                }

            }
            level++;

        }
        return -1;
    }
}
