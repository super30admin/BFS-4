# Time:- O(n^2)
# Space:- O(n^2)
# Approach:- Start doing a bfs from the 1 and check if we can reach len(board)^2
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        # we start from 1 appending dummy 0 to take care of that 
        snakes_and_ladders_arr=[0]
        reverse=False
        # flatten the 2d board into 1d array for convenience
        for i in reversed(board):
            if reverse:
                for j in reversed(i):
                    snakes_and_ladders_arr.append(j)
                reverse=False
                continue
            for j in i:
                snakes_and_ladders_arr.append(j)
            reverse=True
        q=collections.deque([1])
        seen=set()
        moves=0
        while(q):
            size=len(q)
            for i in range(size):
                ele=q.popleft()
                for i in range(1,7):
                    # we can have -1 or the position of another square in the board which means a snake or ladder 
                    if ele+i==len(board)*len(board) or snakes_and_ladders_arr[ele+i]==len(board)*len(board):
                        return moves+1
                    if ele+i<len(board)*len(board) and snakes_and_ladders_arr[ele+i]==-1 and ele+i not in seen:
                        q.append(ele+i)
                        seen.add(ele+i)
                    elif ele+i<len(board)*len(board) and snakes_and_ladders_arr[ele+i] not in seen:
                        q.append(snakes_and_ladders_arr[ele+i])
                        seen.add(snakes_and_ladders_arr[ele+i])
            moves+=1
        return -1
                        