package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Bubble {
    public static int[] sort(int[] a){
        int last = a.length;
        for(int i = 0; i < a.length; i++){
            for(int j = i; j < a.length; j++){
                if(a[j] < a[j - 1]){
                    int t = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = t;
                }
            }
        }
        return a;
    }
}
