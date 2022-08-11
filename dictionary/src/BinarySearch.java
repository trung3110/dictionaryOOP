import java.util.ArrayList;

public class BinarySearch {
    public static int binarySearch(ArrayList<Word> arr, String s) {
        int l = 0;
        int r = arr.size() - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (arr.get(m).getWord_target().compareTo(s) == 0) {
                return m;
            }

            if (arr.get(m).getWord_target().compareTo(s) > 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }
}
