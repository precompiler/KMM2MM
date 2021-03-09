package com.precompiler.utils;

import lombok.NonNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Richard Li
 */
// not thread safe
public class CategoryUtils {
    public static boolean contains(@NonNull String category) {
        return categories.contains(category.trim());
    }

    public static List<String> parseCategoryString(@NonNull String categoryString) {
        return Arrays.asList(categoryString.split(":"));
    }

    public static void load(String categoryFile) {
        try(BufferedReader br = new BufferedReader(new FileReader(categoryFile))) {
            categories = br.lines().filter(line -> line != null && !line.trim().equals("")).map(line -> {
                String[] buf = line.replace("\"", "").split(",");
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < buf.length - 1; i++) {
                    sb.append(buf[i]).append(":");
                }
                return sb.deleteCharAt(sb.length() - 1).toString();
            }).collect(Collectors.toSet());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private CategoryUtils() {
    }

    private static Set<String> categories;
}
