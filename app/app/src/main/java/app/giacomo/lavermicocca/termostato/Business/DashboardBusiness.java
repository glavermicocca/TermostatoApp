package app.giacomo.lavermicocca.termostato.Business;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Date;

import app.giacomo.lavermicocca.termostato.App.AppControllerStore;
import app.giacomo.lavermicocca.termostato.Bean.ScheduleBean;
import app.giacomo.lavermicocca.termostato.R;
import app.giacomo.lavermicocca.termostato.Utils.DebugLog;
import app.giacomo.lavermicocca.termostato.Utils.JsonObjectRequest;
import app.giacomo.lavermicocca.termostato.Utils.JsonStringRequest;

/**
 * Created by Giacomo on 19/03/2015.
 */
public class DashboardBusiness {

    private final Resources res;
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private Activity activity;

    private ScheduleBean scheduleBean;
    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public DashboardBusiness(final Activity activity) {
        this.activity = activity;
        this.mContext = activity.getApplicationContext();
        this.res = mContext.getResources();
        this.scheduleBean = new ScheduleBean();
    }//PartnerBusiness

    //---------------------------------------------------------------------------------------------

    public void setSwitchOnOff() {
        DebugLog.getMethodTrace(Thread.currentThread());

        String url = res.getString(R.string.SERVER_IP) + res.getString(R.string.api_get_set_heating_system_on_off);

        Log.i("DEBUG", "URL api : " + url);

        // Creating volley request obj
        final JsonObjectRequest setSwitchOnOff = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("",response.toString());

            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //StaticMethods.hidePDialog(pDialog);
                Toast.makeText(mContext, "FAIL", Toast.LENGTH_LONG).show();
            }
        });
        AppControllerStore.getInstance().addToRequestQueue(setSwitchOnOff);
    }//setSwitchOnOff

    public void setSeason(String season) {
        DebugLog.getMethodTrace(Thread.currentThread());

        String url = res.getString(R.string.SERVER_IP) + res.getString(R.string.api_get_set_season);

        Log.i("DEBUG", "URL api : " + url);

        String bodyParam = "season=%s";

        bodyParam = String.format(bodyParam, season);

        // Creating volley request obj
        final JsonObjectRequest setSeason = new JsonObjectRequest(Request.Method.POST, url, bodyParam, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("",response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //StaticMethods.hidePDialog(pDialog);
                        Toast.makeText(mContext, "FAIL", Toast.LENGTH_LONG).show();
                    }
                });
        AppControllerStore.getInstance().addToRequestQueue(setSeason);
    }//setSwitchOnOff

    public void setDisplayLight(String display) {
        DebugLog.getMethodTrace(Thread.currentThread());

        String url = res.getString(R.string.SERVER_IP) + res.getString(R.string.api_get_set_display_light);

        Log.i("DEBUG", "URL api : " + url);

        String bodyParam = display;

        // Creating volley request obj
        final JsonObjectRequest setDisplayLight = new JsonObjectRequest(Request.Method.POST, url, bodyParam, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("",response.toString());
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //StaticMethods.hidePDialog(pDialog);
                        Toast.makeText(mContext, "FAIL", Toast.LENGTH_LONG).show();
                    }
                });
        AppControllerStore.getInstance().addToRequestQueue(setDisplayLight);
    }//setSwitchOnOff

    public void setSystemDate(Date date) {
        DebugLog.getMethodTrace(Thread.currentThread());

        String url = res.getString(R.string.SERVER_IP) + res.getString(R.string.api_post_set_system_date_millis);

        Log.i("DEBUG", "URL api : " + url);

        String bodyParam = "date=%s";

        bodyParam = String.format(bodyParam, date.getTime() );

        Log.e("", bodyParam);

        // Creating volley request obj
        final JsonStringRequest setSystemDate = new JsonStringRequest(Request.Method.POST, url, bodyParam, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("",response.toString());
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //StaticMethods.hidePDialog(pDialog);
                Toast.makeText(mContext, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
        AppControllerStore.getInstance().addToRequestQueue(setSystemDate);
    }//setSwitchOnOff

}
