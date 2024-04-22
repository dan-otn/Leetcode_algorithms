import java.util.HashSet;
import java.util.Set;


class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int answer = 0;

        Set<Character> jewelsSetChars = new HashSet<>();
        for(char c : jewels.toCharArray()) {
            jewelsSetChars.add(c);
        }

        for(char c : stones.toCharArray()) {
            if (jewelsSetChars.contains(c)) {
                answer++;
            }
        }
        return answer;
    }
}