package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Selection {
    public static int[] sort(int[] a){
        int min = 0;
        for(int i = 0; i < a.length; i++){
            min = a[i];
            for(int j = i; j < a.length; j++){
                if(a[j] < min){
                    min = a[j];
                }
            }
            a[i] = min;
        }
        return a;
    }
}
