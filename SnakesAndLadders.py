#909. Snakes and Ladders
"""
Time Complexity : O(n ^ 2) #actualy it is 6 * n^2
Space Complexity : O(n)  #queue
"""
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        #2D matrix into 1D matrix
        n = len(board)
        n1 = n * n #lenght of 1D array
        arr = [0] * (n1)
        
        idx = 0 # index for 1D array
        back = False # because we have to consider alternate row in reverse order.
     
        for i in board[::-1]: #we will start baord from last because that will become first
            #print(arr)
            if back == False:
                for x in i:
                    arr[idx] = x
                    idx = idx + 1
                    
                back = True
                idx -= 1
            
            else: #row will be considered in reversed order
                newIdx = idx
                newIdx += len(i)
                idx = newIdx
                for x in i:
                    arr[newIdx] = x
                    newIdx -= 1
                    
                back = False
                idx += 1
        
        #solving 1D array as game of snakes and ladders       
        q = deque()
        q.append(0) 
        arr[0] = -2 # -2 means that element is visited
        moves = 0
        
        
        while len(q) != 0:
            #print(q)
            size = len(q) 
            """
            size is taken into consideration because we are rolling a dice after 
            previously all the moves(which is equal to size of queue) 
            have been covered
            """
            #print(size)
            
            moves += 1 #each time we roll a dice
            for i in range(0, size):
                curr = q.popleft()
                #print(curr)

                
                for dice in range(1, 7): #j = 1,2,3,4,5,6  i.e. dice
                    child = curr + dice

                    if arr[child] != -2:

                        if arr[child] == -1:
                            nextPos = child
                            q.append(nextPos)
                            arr[child] = -2

                        else:
                            nextPos = arr[child] - 1
                            q.append(nextPos)
                            arr[child] = -2


                    if nextPos == n1-1: #as soon as we reach final location, we return. 
                        #no use of going further.
                        return moves
            
            
                        
        return -1 #if there is no valid path, then -1 will be returned at the end.
        
