/**
 * Created by gaojie on 16-9-27.
 */
public class singleton {
    private static volatile singleton _instance;
    public static singleton getInstance(){
        if(_instance == null){
            synchronized(singleton.class){
                if(_instance == null)
                    _instance = new singleton();
            }
        }
        return _instance;
    }
}
