package com.nilhcem.hexawatch.common.utils;

/**
 * Preconditions inspired by
 * https://github.com/google/guava/blob/master/guava/src/com/google/common/base/Preconditions.java
 */
public class Preconditions {

    private Preconditions() {
        throw new UnsupportedOperationException();
    }

    public static void checkArgument(boolean expression) {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <T> void checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
    }

    public static <T> void checkNotNull(T reference, String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
    }
}
