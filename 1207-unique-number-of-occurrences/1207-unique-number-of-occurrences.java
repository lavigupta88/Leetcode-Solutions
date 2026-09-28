class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Set<Integer> freqSet = new HashSet<>(freqMap.values());

        return freqSet.size() == freqMap.size();
    }
}