package com.revature.planetarium.utility;

import java.util.ArrayList;
import java.util.List;

public class FileType {
    public static String getFileType(byte[] file) {
        if (file == null) return "ZE";

        List<Character> fileType = new ArrayList<>();
        for (byte b : file) {
            if (b == 0) break;
            if (Character.isUpperCase((char) b)) fileType.add((char) b);
        }
        StringBuilder ftype = new StringBuilder();
        for (char c : fileType) {
            ftype.append(c);
        }

        return ftype.toString();
    }
}
