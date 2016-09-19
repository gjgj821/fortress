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
        int l1 = left, l2 = mid + 1, size = right - left;
        int[] b = {};
        for(int k = 0; k <= size; k++){
            if(a[l1] > a[l2]){
                b[k] = a[l2++];
            }else{
                b[k] = a[l1++];
            }
        }
        for(int k = 0; k <= size; k++){
            a[left + k] = b[k];
        }
    }
}
