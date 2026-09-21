class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        long current = 1;
        result.add(1);

        // Generate each next coefficient from the previous coefficient.
        for (int col = 1; col <= rowIndex; col++) {
            current = current * (rowIndex - col + 1) / col;

            // Final values fit in int under the given constraints.
            result.add((int) current);
        }

        return result;
    }
}