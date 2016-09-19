package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Quick {
    public static int[] sort(int[] a){
        sort(a, 0, a.length - 1);
        return a;
    }

    public static void sort(int[] a, int left, int right){
        if(left >= right) return ;
        int j = partition(a, left, right);
        sort(a, left, j - 1);
        sort(a, j + 1, right);
    }

    public static int partition(int[] a, int left, int right){
        int v = a[left];
        int l = left, r = right;
        while(l <= r){
            while(l <= r && a[r] > v) r--;
            while(l <= r && a[l] < v) l++;
            if(a[l] > a[r]){
                int tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;
            }
        }
        a[left] = a[l];
        a[l] = v;
        return l;
    }
}
