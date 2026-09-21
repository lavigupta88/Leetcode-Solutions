class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 1; i <= numRows; i++){
            List<Integer> row = new ArrayList<>();

            long val = 1;
            row.add(1);

            for(int j = 1; j< i; j++){
                val = val * (i - j)/ j;
                row.add((int) val);
            }
            result.add(row);
        }
        return result;
    }
}