import java.util.ArrayList;

public class BinarySearch {
    public static int binarySearchL(ArrayList<Word> arr, String s) {
        int l = 0;
        int r = arr.size() - 1;

        while (l < r) {
            int m = (l + r + 1) / 2;
            if (arr.get(m).getWord_target().compareTo(s) < 0) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static int binarySearchR(ArrayList<Word> arr, String s) {
        int l = 0;
        int r = arr.size() - 1;

        while (l < r) {
            int m = (l + r + 1) / 2;
            if (arr.get(m).getWord_target().compareTo(s) >= 0) {
                if ( arr.get(m).getWord_target().contains(s) ) {
                    l = m;
                } else {
                    r = m - 1;
                }
            } else {
                l = m + 1;
            }
        }
        return r;
    }
}
