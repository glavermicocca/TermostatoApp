package app.giacomo.lavermicocca.termostato.App;


import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import app.giacomo.lavermicocca.termostato.Utils.LruBitmapCache;
/**
 * Created by Giacomo on 27/11/2014.
 */
public class AppControllerStore extends Application {
    static final Object mLock = new Object();

    //LISTVIEW + VOLLEY JSON REQUEST
    public static final String TAG = AppControllerStore.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static AppControllerStore mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        synchronized (mLock) {}
    }



    //LISTVIEW + VOLLEY JSON REQUEST
    public static synchronized AppControllerStore getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
