#time complexity: O(m*n)
#spce complexity: O(m*n)
#ran on leetcode: yes
#convert the board to a 1-D list. start from 1 and do BFS for all combination from 1 till 6.
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        L=[]
        i=len(board)-1
        j=0
        right=True
        while(i>=0):
            if(right):
                if(board[i][j]!=-1):
                    L.append(board[i][j]-1)
                else:
                    L.append(board[i][j])
                j+=1
                if(j==len(board[0])):
                    right=False
                    i-=1
                    j-=1
            else:
                if(board[i][j]!=-1):
                    L.append(board[i][j]-1)
                else:
                    L.append(board[i][j])
                j-=1
                if(j==-1):
                    j+=1
                    i-=1
                    right=True
        
        Q=[]
        Q.append(0)
        level=0
        print(len(L))
        while(Q):
            size=len(Q)
            #print(Q)
            
            while(size>0):
                #print(size)
                curr=Q[0]
                #print(curr)
                if(curr==len(L)-1):
                    return level
                if(L[curr]==-2):
                    size-=1
                    #Q.popleft()
                    del(Q[0])
                    continue
                L[curr]=-2
                for dice in range(1,7):
                    new_index=curr+dice
            
                    if(new_index>=len(L)):
                        continue
                    if(L[new_index]==-1):
                        Q.append(new_index)
                        #L[new_index]=-2
                    else:
                        if(L[new_index]>0):
                            Q.append(L[new_index])
                            #L[new_index]=-2
                        else:
                            Q.append(new_index)

                size-=1
                #Q.popleft()
                del(Q[0])
            level+=1
        return -1



                    
        

