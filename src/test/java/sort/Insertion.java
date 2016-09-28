package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Insertion {
    public static int[] sort(int[] a){
        for(int i = 0; i < a.length; i++){
            for(int j = i; j>=0; j--){
                if(a[j] < a[i]){
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
        return a;
    }
}
