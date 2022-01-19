# Time: O(n*n)
# Space: O(n*n)
class Solution(object):
    def snakesAndLadders(self, board):
        """
        :type board: List[List[int]]
        :rtype: int
        """
        if board[len(board) - 1][0] == 1:
            return -1
        n = len(board)
        arr = [None]*(n*n+1)
        flag = True
        cnt = 1
        for i in range(n-1, -1, -1):
            if flag:
                start = 0
                increment = 1
                end = n
            else:
                start = n - 1
                increment = -1
                end = -1
            for j in range(start, end, increment):
                #print(i,j)
                arr[cnt] = board[i][j]
                cnt += 1
            flag = not flag
        dist = [None]*(n*n+1)
        queue = []
        queue.append(1)
        cnt = 0
        dist[1] = 0
        dic = {}
        dic[1] = True
        while len(queue) != 0:
            cnt += 1
            size = len(queue)
            for i in range(size):
                curr = queue.pop(0)
                for i in range(curr+1, min(curr+7, n*n+1), 1):
                    if arr[i] != -1:
                        if dist[arr[i]] == None:
                            dist[arr[i]] = cnt
                        else:
                            dist[arr[i]] = min(dist[arr[i]], cnt)
                        if arr[i] not in dic:
                            queue.append(arr[i])
                            dic[arr[i]] = True
                        continue
                    if dist[i] == None:
                        dist[i] = cnt
                    else:
                        dist[i] = min(dist[i], cnt)
                    if i not in dic:
                        queue.append(i)
                        dic[i] = True
        #print(dist)
        if dist[n*n] == None:
            return -1
        return dist[n*n]
        
