class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        Arrays.sort(arr);

        List<List<Integer>> result = new ArrayList<>();

        int minDiff = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {

            int diff = Math.abs(arr[i] - arr[i - 1]);

            if (diff < minDiff) {
                minDiff = diff;
                result.clear();
            }

            if (diff == minDiff) {
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }

        return result;
    }
}
