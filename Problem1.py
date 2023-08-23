
'''
Problem: Snakes and Ladders
Time Complexity: O(m * n), where n is given elements
Space Complexity: O(m * n)
Did this code successfully run on Leetcode: Yes
Any problem you faced while coding this: No
Your code here along with comments explaining your approach:
        Flattened the 2D array into 1 D
        Applied BFS to process the dice rolls 1 to 6
        BFS will ensure minimum number of moves
'''

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [0] * (n*n)
        idx = 0
        r = n-1
        c = 0
        dir = True
        while idx< n*n:
            if board[r][c] == -1:
                arr[idx]=-1
                idx+=1
            else:
                arr[idx] = board[r][c]-1
                idx+=1
            
            if dir:
                c+=1
                if c==n:
                    c-=1
                    r-=1
                    dir = False
            else:
                c-=1
                if c == -1:
                    r-=1
                    c+=1
                    dir = True
        
        queue = deque()
        queue.append(0)
        seen = set()
        seen.add(0)
        moves = 0

        while queue:
            size = len(queue)
            for i in range(size):
                ele = queue.popleft()
                for j in range(1, 7):
                    child = ele + j
                    if child < n*n:
                        if child == n*n-1 or arr[child] == n*n -1:
                            return moves+1
                        if arr[child]==-1:
                            if child not in seen:
                                queue.append(child)
                                seen.add(child)
                        else:
                            if arr[child] not in seen:
                                queue.append(arr[child])
                                seen.add(arr[child])
            moves+=1

        return -1
                



