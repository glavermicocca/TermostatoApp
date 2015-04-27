package app.giacomo.lavermicocca.termostato.Business;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import app.giacomo.lavermicocca.termostato.Bean.ParamContainer;
import app.giacomo.lavermicocca.termostato.R;
import app.giacomo.lavermicocca.termostato.Utils.DebugLog;
import app.giacomo.lavermicocca.termostato.Utils.GenericCallback;

/**
 * Created by Giacomo on 19/03/2015.
 */
public class SensorsClockBusiness {

    private final Resources res;
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private Activity activity;
    private Socket socket;
    private GenericCallback mListener;

    public SensorsClockBusiness(final Activity activity, final Fragment fragment) {
        this.activity = activity;
        this.mContext = activity.getApplicationContext();
        this.res = mContext.getResources();
        mListener = (GenericCallback) fragment;
    }//PartnerBusiness

    //---------------------------------------------------------------------------------------------

    public void getMessages() {
        DebugLog.getMethodTrace(Thread.currentThread());

        try {
            IO.Options opts = new IO.Options();
            opts.forceNew = true;
            opts.reconnection = true;

            String url = res.getString(R.string.SERVER_IP);

            this.socket = IO.socket(url, opts);
            this.socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    Log.e("connect", "connect");
                }

            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    Log.e("disconnect", "disconnect");
                }

            }).on(Socket.EVENT_MESSAGE, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    ParamContainer paramContainer = new ParamContainer();

                    paramContainer.setValue(args[0].toString());
                    paramContainer.setParams(args[1].toString());
                    //socket.send("IL MIO SEND");
                    mListener.updateView(paramContainer);
                }
            });
            socket.connect();

        } catch (URISyntaxException e) {
            Log.e("URI", e.getLocalizedMessage());
        }
    }//setCustomText

    public void closeSocket()
    {
        if(this.socket != null) {
            this.socket.close();
        }//if
    }//closeSocket

}
