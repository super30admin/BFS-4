# TimeCompelexity:O(n*n)
# SpaceComplexity:O(n*n)..
# Approach:
# 1. need to flat list as you need to deal with lot of conditions
# 2.Use bfs over dfs because we need levels





class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        #flatten list 
        l=[] #need to flat because we have lot of conditons to deal with
        
        n=len(board)
        r,c=n-1,0
        flag=True
        cnt=0
        while(cnt<n*n):
            if board[r][c]==-1:
                l.append(board[r][c])
            else:l.append(board[r][c]-1)
            if flag:
                c+=1
                if c==n:
                    c=n-1
                    flag=False
                    r-=1
            else:
                # l.append(board[r][c])
                c-=1
                if c==-1:
                    flag=True
                    r-=1
                    c=0
            cnt+=1
        q=deque([0])
        level=0
        l[0]=-2
        while(len(q)):
            k=len(q)
            
            for _ in range(k):
                node=q.popleft()
                # if node==n*n -1:return level
                # if node>n*n:continue
                for ran in range(1,7): #at every palce we have 6 choices to take we need to whcih one give reachs last first
                    if node+ran>n*n:continue
                    if node+ran==n*n -1 or l[node+ran] == n*n -1:return level+1
                    if l[node+ran]!=-2:
                        if l[node+ran]==-1:
                            q.append(node+ran)
                            l[node+ran]=-2
                        elif l[node+ran]!=-1:
                            q.append(l[node+ran]) #check here
                            l[node+ran]=-2
            level+=1
        return -1
                    
                    

        
