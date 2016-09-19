package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Merge {
    public static int[] sort(int[] a){
        sort(a, 0, a.length - 1);
        return a;
    }

    public static void sort(int[] a, int left, int right){
        if(left >= right) return;
        int mid = left + (right - left) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);
        merge(a, left, right, mid);
    }

    public static void merge(int[] a, int left, int right, int mid){

    }
}
