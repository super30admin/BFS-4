class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        board.reverse()
        mainArray = [0]
        
        for index, row in enumerate(board):
            if index%2==0:
                mainArray += row
            else:
                mainArray += row[::-1]
        finalIndex = len(board)**2
        
        queue = deque([1])
        moves = 0
        visited = set()
        while queue:
            for _ in range(len(queue)):
                currIndex = queue.popleft()
                if currIndex == finalIndex:
                    return moves
                for i in range(1,7):
                    newIndex = currIndex+i
                    if newIndex<= finalIndex and newIndex not in visited:
                        visited.add(newIndex)
                        if mainArray[newIndex]!=-1:
                            queue.append(mainArray[newIndex])
                        else:
                            queue.append(newIndex)
            moves+=1
        return -1
        
        Time: O(n^2)
        Space: O(n^2)
            
                    
