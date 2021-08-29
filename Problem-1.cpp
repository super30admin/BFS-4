class Solution {
public:
	vector<vector<char>> updateBoard(vector<vector<char>>& board, vector<int>& click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}
		dfs(board, click[0], click[1]);
		return board;
	}
	void dfs(vector<vector<char> > &b, int x, int y) {
		if (!judge(x, y, b))return;
		vector<vector<int> > v = { {1,-1},{ 1,0 },{ 1,1 },{ -1,-1 },{ -1,0 },{ -1,1 },{ 0,-1 },{ 0,1 } };
		int count = 0;
		if (b[x][y] == 'E') {
			for (int i = 0; i < 8; i++) {
				if (judge(x + v[i][0], y + v[i][1], b) && b[x + v[i][0]][y + v[i][1]] == 'M')
					count++;
			}
			if (count > 0)
				b[x][y] = '0' + count;
			else {
				b[x][y] = 'B';
				for (int i = 0; i < 8; i++) {
					dfs(b, x + v[i][0], y + v[i][1]);
				}
			}
		}
	}
	bool judge(int x, int y,vector<vector<char> > b) {
		return x >= 0 && x < b.size() && y >= 0 && y < b[0].size();
	}
};
