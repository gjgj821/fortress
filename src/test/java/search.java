/**
 * Created by gaojie on 16-9-27.
 */
public class search {
    public static int binary(int[] a, int s){
        int start = 0, end = a.length - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            if(a[mid] < s){
                start = mid + 1;
            }else if(a[mid] > s){
                end = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static int binaryR(int[] a, int s, int start, int end){
        if(start > end) return -1;
        int mid = start + (end - start) / 2;
        if(a[mid] < s){
            return binaryR(a, s, mid + 1, end);
        }else if(a[mid] > s){
            return binaryR(a, s, start, mid - 1);
        }else{
            return mid;
        }
    }
}
