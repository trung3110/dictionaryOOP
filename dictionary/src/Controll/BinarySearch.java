package Controll;

import java.util.ArrayList;

public class BinarySearch {
    public static int binarySearchL(String s) {
        int l = 0;
        int r = Dictionary.WordList.size() - 1;

        while (l < r) {
            int m = (l + r + 1) / 2;
            if (Dictionary.WordList.get(m).getWord_target().compareTo(s) < 0) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static int binarySearchR(String s) {
        int l = 0;
        int r = Dictionary.WordList.size() - 1;

        while (l < r) {
            int m = (l + r + 1) / 2;
            if (Dictionary.WordList.get(m).getWord_target().compareTo(s) >= 0) {
                if ( Dictionary.WordList.get(m).getWord_target().contains(s) ) {
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
