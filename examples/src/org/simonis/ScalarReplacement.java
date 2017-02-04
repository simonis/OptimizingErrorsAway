package org.simonis;

public class ScalarReplacement {

    private static int dot(int[] a, int[] b) {
        return a[0]*b[0] + a[1]*b[1] + a[2]*b[2];
    }

    public static int scalarReplace(int x) {
        int[] a = new int[] { x, x, x };
        return dot(a, a);
    }

    public static void arrayCopy1(int[] src, int[] dst) {
        System.arraycopy(src, 0, dst, 0, 8);
    }

    public static void arrayCopy2(int[] src, int[] dst, int len) {
        System.arraycopy(src, 0, dst, 0, len);
    }

    public static void arrayCopy3(Object src, Object dst, int len) {
        System.arraycopy(src, 0, dst, 0, len);
    }

    public static boolean arrayCopy4(int[] src, int cpy_len) {
        try {
            System.arraycopy(src, 3, new int[8], 5, cpy_len);
            return false;
        } catch (IndexOutOfBoundsException e) {
            return true;
        }
    }

    public static boolean arrayCopy5(Object src, int cpy_len) {
        try {
            System.arraycopy(src, 3, new Object[8], 5, cpy_len);
            return false;
        } catch (ArrayStoreException e) {
            return true;
        }
    }

    public static void main(String args[]) {
        if (args.length == 0 || "scalarReplace".equals(args[0])) {
            for (int i = 0; i < 20_000; i++) {
                scalarReplace(42);
            }
        }
        else if ("arrayCopy1".equals(args[0])) {
            int[] src = new int[128];
            int[] dst = new int[128];
            for (int i = 0; i < 20_000; i++) {
                arrayCopy1(src, dst);
            }
        }
        else if ("arrayCopy2".equals(args[0])) {
            int[] src = new int[128];
            int[] dst = new int[128];
            for (int i = 0; i < 20_000; i++) {
                arrayCopy2(src, dst, 8);
            }
        }
        else if ("arrayCopy3".equals(args[0])) {
            int[] src = new int[128];
            int[] dst = new int[128];
            for (int i = 0; i < 20_000; i++) {
                arrayCopy3(src, dst, 8);
            }
        }
        else if ("arrayCopy4".equals(args[0])) {
            int[] src = new int[128];
            for (int i = 0; i < 20_000; i++) {
                if (!arrayCopy4(src, -1)) {
                    System.out.println("----> Error in iteration " + i);
                    System.out.println("----> Expected IndexOutOfBoundsException");
                    System.exit(-1);
                }
            }
        }
        else if ("arrayCopy5".equals(args[0])) {
            int[] src = new int[128];
            for (int i = 0; i < 20_000; i++) {
                if (!arrayCopy5(src, 0)) {
                    System.out.println("----> Error in iteration " + i);
                    System.out.println("----> Expected ArrayStoreException");
                    System.exit(-1);
                }
            }
        }
    }
}
