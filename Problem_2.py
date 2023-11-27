"""
Problem : 2

Time Complexity : 
Approach 1 - O(n^2)
Approach 2 - O(n^n)

Space Complexity :
Approach 1 - O(n^2)
Approach 2 - O(n^n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No


"""

# Snakes and ladders

# Approach - 1
# BFS

class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n=len(board)
        newArr=[0 for _ in range(n*n)]
        r=n-1
        c=0
        direction=True
        idx=0
        while idx<n*n:
            if board[r][c]==-1:
                newArr[idx]=-1
            else:
                newArr[idx]=board[r][c]-1
            idx+=1
            if direction:
                c+=1
                if c==n:
                    direction=False
                    r-=1
                    c-=1
            else:
                c-=1
                if c==-1:
                    r-=1
                    c+=1
                    direction=True

        q=collections.deque()
        hset=set()
        q.append(0)
        hset.add(0)
        moves=0
        while q:
            size=len(q)
            for i in range(size):
                curr=q.popleft()
                if curr==n*n-1:
                    return moves
                for j in range(1,7):
                    child=curr+j
                    if child<n*n:
                        if child not in hset:
                            if newArr[child]==-1:
                                q.append(child)
                                
                            else:
                                q.append(newArr[child])
                            hset.add(child)
            moves+=1
        return -1
    

#  Approach - 2
# DFS


class Solution(object):
    def __init__(self):
        self.minn=float('inf')
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        n=len(board)
        newArr=[0 for _ in range(n*n)]
        r=n-1
        c=0
        direction=True
        idx=0
        while idx<n*n:
            if board[r][c]==-1:
                newArr[idx]=-1
            else:
                newArr[idx]=board[r][c]-1
            idx+=1
            if direction:
                c+=1
                if c==n:
                    direction=False
                    r-=1
                    c-=1
            else:
                c-=1
                if c==-1:
                    r-=1
                    c+=1
                    direction=True

        self.dfs(newArr,0,0)
        return self.minn if self.minn!=float('inf') else -1

    def dfs(self,arr,idx,currMoves):
        # base
        if currMoves>self.minn or currMoves>len(arr):
            return
        if idx==len(arr)-1:
            self.minn=currMoves
            return
        # logic
        for i in range(1,7):
            child=idx+i
            if child<len(arr):
                if arr[child]==-1:
                    self.dfs(arr,child,currMoves+1)
                else:
                    self.dfs(arr,arr[child],currMoves+1)
