"""
If A knows B, then A cannot be the celebrity - eliminate A
If A does not know B, then B cannot be the celebrity - eliminate B
After one pass, only left with one candidate — verify it by checking:
They know no one
Everyone else knows them
"""
"""
Time Complexity: O(n)	One pass to find + one to verify
Space Complexity: O(1)	Only a few variables
"""


class findCeleb:
    def findCelebrity(self, n: int) -> int:
        candidate = 0
        for i in range(1, n):
            if knows(candidate, i):
                candidate = i  

  
        for i in range(n):
            if i == candidate:
                continue
            if knows(candidate, i) or not knows(i, candidate):
                return -1

        return candidate

def knows(a, b):
    matrix = [
        [False, True,  True],
        [False, False, True],
        [False, False, False]
    ]
    return matrix[a][b]

if __name__ == "__main__":
    obj = findCeleb()
    print(obj.findCelebrity(3)) 