# Time Complexity : O(n*n)
# Space Complexity : O(n*n) 
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Intuition:
# Find all valid paths(backtracking) and return minimum(dp): On the board from 1, we have 5 branches: hop to 2(go up ladder:15)/3/4/5/6. At each of these points, we'll again have 6 options and as soon as we reach 36, we'd have our path.
# DFS: we can explore all paths till the bottom and return soln when we reach 36. If we don't get the path, we can re-use the explored paths by using a hashmap. Time Complexity: O(n^2)
# Can we do better? 
# BFS: Before exploring till the bottom of all branches, we may on average get our solution before that. If we explore level by level, we may find solution sooner(avg case). Worst case is same for dfs/bfs.

# Approach:
# Keep track of even/odd rows for direction of moving forward/backward
# Make an array(move) of nxn = here, 36; no ladder: put -1; ladder(to 14): put 14 and so on. If visited(index is in the queue for processing), change the number at that index to anything except 1-36 using nextMove(i+1,...i+6).

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board == None or len(board)==0 or len(board[0])==0:
            return 0
        
        n,m = len(board), len(board[0])
        move = [0]*n*m
        # print(move)
        i,j = n-1, 0
        even, index = 0,0
        
        while i>=0 and j>=0:
            #no ladder
            if board[i][j] == -1:
                move[index] = -1
            #when a ladder
            else:
                move[index] = board[i][j]-1
            
            #use even to move forward
            if even%2==0:
                j+=1
            else:
                j-=1
                
            #check bounds
            if j>=m:
                even += 1
                i -=1
                j -=1
            elif j<0:
                even +=1
                i -= 1
                j += 1
            
            index +=1
                
        #BFS
        queue = []
        minLevel = 0
        queue.append(0)
        move[0] = -2
        
        while queue:
            size = len(queue)
            for x in range(size):
                curr = queue.pop(0)
                if curr == n*m -1:
                    return minLevel
                #explore possibilities
                for y in range(1,7):
                    nextMove = curr +y
                    if nextMove < n*m and move[nextMove]!= -2:
                        if move[nextMove] == -1:
                            queue.append(nextMove)
                        else:
                            queue.append(move[nextMove])
                        
                        move[nextMove] = -2

            minLevel +=1
        
        return -1
                
        
        