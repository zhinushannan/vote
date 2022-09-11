package club.kwcoder.vote.util;

import cn.hutool.core.util.StrUtil;

public class StrCustomUtils {

    public static boolean isAnyBlank(String... str) {
        for (String s : str) {
            if (StrUtil.isBlank(s)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isLengthAllowNotContains(String str, int min, int max) {
        return isLengthAllow(str, min, max, false, false);
    }

    public static boolean isLengthAllowContainsAll(String str, int min, int max) {
        return isLengthAllow(str, min, max, true, true);
    }

    public static boolean isLengthAllow(String str, int min, int max, boolean isContainsLeft, boolean isContainsRight) {
        int length = str.length();
        if (isContainsLeft && isContainsRight) {
            return min <= length && length <= max;
        } else if (isContainsLeft) {
            return min <= length && length < max;
        } else if (isContainsRight) {
            return min < length && length <= max;
        }
        return min < length && length < max;
    }

}
