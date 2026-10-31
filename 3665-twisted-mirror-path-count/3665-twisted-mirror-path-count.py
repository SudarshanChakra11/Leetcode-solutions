class Solution:
    def uniquePaths(self, grid: list[list[int]]) -> int:
        MOD = 10**9 + 7

        m = len(grid)
        n = len(grid[0])

        right = [[0] * n for _ in range(m)]
        down = [[0] * n for _ in range(m)]

        for i in range(m):
            for j in range(n):

                # Starting position
                if i == 0 and j == 0:
                    ways = 1

                # Mirror cell
                elif grid[i][j] == 1:

                    # Entered from left: reflect downward
                    if i + 1 < m:
                        down[i + 1][j] = (
                            down[i + 1][j] + right[i][j]
                        ) % MOD

                    # Entered from above: reflect rightward
                    if j + 1 < n:
                        right[i][j + 1] = (
                            right[i][j + 1] + down[i][j]
                        ) % MOD

                    continue

                # Empty cell
                else:
                    ways = (right[i][j] + down[i][j]) % MOD

                # Destination reached
                if i == m - 1 and j == n - 1:
                    return ways

                # Move right
                if j + 1 < n:
                    right[i][j + 1] = (
                        right[i][j + 1] + ways
                    ) % MOD

                # Move down
                if i + 1 < m:
                    down[i + 1][j] = (
                        down[i + 1][j] + ways
                    ) % MOD

        return 0