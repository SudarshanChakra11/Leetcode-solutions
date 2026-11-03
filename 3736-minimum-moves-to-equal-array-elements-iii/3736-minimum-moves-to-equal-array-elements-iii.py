class Solution:
    def minMoves(self, nums: List[int]) -> int:

        maximum = max(nums)

        total = sum(nums)

        n = len(nums)

        return (maximum * n) - total
