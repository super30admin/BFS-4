class Solution:
    
    """
    Description: Find minimum steps to win snake and ladder game
    
    Time Complexity: O(N^2)
    Space Complexity: O(N^2)
    where N is size of the edge of the given board
    
    Approach: BFS 
    - convert the board to an array, and add first element's index to the board or the value if the element != -1
    - start a queue and convert each visited item to a value beyond the allowable limits (say -5)
    - in each step roll the dice and check all answers from dice roll 1 to 6, mark visited as above
    - after appending the queue, add one step
    - as soon as the current value matches with number of fields in the board - 1, return the number of steps, otherwise return -1
    """
    
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        from collections import deque
        
        n = len(board)
        arr = []
        diff = 1
        
        # convert board to array
        while diff <= n:
            if diff % 2 == 0:
                # move left
                arr.extend([board[n - diff][n - k - 1] for k in range(n)])
            else:
                # move right
                arr.extend([board[n - diff][k] for k in range(n)])                
            diff += 1
        
        queue = deque()
        queue.append(arr[0] - 1 if arr[0] != -1 else 0)
        arr[0] = -5 # mark as visited
        min_steps = 0
        
        while queue:
            for _ in range(len(queue)):
                curr = queue.popleft()
                if curr == len(arr) - 1: return min_steps
                for k in range(1, 7):
                    step = curr + k
                    if step < len(arr) and arr[step] != -5:
                        if arr[step] == -1: queue.append(step)
                        else: queue.append(arr[step] - 1)
                        arr[step] = -5
            min_steps += 1
            
        return -1
