package sort;

/**
 * Created by gaojie on 16-9-19.
 */
public class Shell {
    public static int[] sort(int[] a){
        int h = 1;
        while(h < a.length) h += 3;
        while(h >= 1){
            for(int i = 0; i < a.length; i++){
                for(int j = i; j >= 0; j-=h){
                    if(a[j] > a[i]){
                        int t = a[j];
                        a[j] = a[i];
                        a[i] = t;
                    }
                }
            }
            h -= 3;
        }
        return a;
    }
}
