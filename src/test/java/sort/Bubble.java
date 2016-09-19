package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Bubble {
    public static int[] sort(int[] a){
        int last = a.length;
        for(int i = 0; i < a.length; i++){
            int j = i;
            for(; j < a.length && j < last; j++){
                if(a[i] > a[j]){
                    last = j;
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
            last = j;
        }
        return a;
    }
}
