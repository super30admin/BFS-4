#SC:O(n)
#TC:O(n)
from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        n = len(board[0])
        
        count=0
        
        list_val = [0]
        for i in range(n-1,-1,-1):
            if count%2 == 0:
                list_val+=board[i]
            else:
                list_val+=board[i][::-1]
            count+=1
        
        queue = deque()
        queue.append((1,0))
        visited = set()
        visited.add(1)
        while(queue):
            index,steps =  queue.popleft()
            if index == n*n:
                return steps
            for i in range(1,7):
                if index+i<len(list_val) and index+i not in visited:
                    if list_val[index+i]==-1:
                        queue.append((index+i,steps+1))
                    else:
                        queue.append((list_val[index+i],steps+1))
                    visited.add(index+i)
        return -1