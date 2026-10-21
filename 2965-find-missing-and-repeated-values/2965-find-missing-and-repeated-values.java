class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        ArrayList<Integer> ans = new ArrayList <>();
        HashSet<Integer> set = new HashSet <>();

        int actualSum = 0;
        int a = 0, b = 0;
        int n = grid.length;

        for(int i = 0; i<n; i++){
            for(int j = 0; j < n; j++){
                actualSum += grid[i][j];
                if(set.contains(grid[i][j])){
                    a = grid[i][j];
                    ans.add(a);
                }
                set.add(grid[i][j]);
            }
        }

        int total = n * n;
        int expectedSum = total * (total + 1)/2;

        b = expectedSum + a - actualSum;
        ans.add(b);

        return new int[]{a, b};
    }
}