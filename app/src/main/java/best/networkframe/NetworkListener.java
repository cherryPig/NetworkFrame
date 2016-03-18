package best.networkframe;

/**
 * Created by BG207369 on 2015/11/16.
 */
public class NetworkListener {
    public interface NetworkResponseListener<T> {
        void onSuccess(T obj);

        void onFail(String errorMsg);
    }

}
