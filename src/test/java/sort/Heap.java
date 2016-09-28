package sort;

/**
 * Created by gaojie on 16-9-26.
 */
public class Heap {
    public static void sort(int[] a){
        for(int i = a.length / 2 - 1; i >= 0; i--)
            sort(a, i, a.length);
    }

    public static void sort(int[] a, int l, int n){
        int j = l;
    }


}
