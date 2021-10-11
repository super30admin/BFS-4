class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        arr = [0]
        for i, row in enumerate(board[::-1]):
            if i % 2 == 0:
                arr += row
            else:
                arr += row[::-1]   
        
        NN = len(arr)-1
        queue = [(1, 0)]
        visited = set()
        while queue:
            square, moves = queue.pop(0)
            if square == NN: 
                return moves 
            for i in range(1, 7):
                if square + i <= NN and square + i not in visited :
                    visited.add(square + i)
                    if arr[square + i] != -1:
                        queue.append((arr[square + i], moves+1))
                    else:
                        queue.append((square + i, moves+1))
        return -1 


%TC: O(n)
%SC: O(n)