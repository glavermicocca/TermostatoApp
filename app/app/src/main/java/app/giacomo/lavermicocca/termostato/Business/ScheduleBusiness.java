package app.giacomo.lavermicocca.termostato.Business;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import app.giacomo.lavermicocca.termostato.App.AppControllerStore;
import app.giacomo.lavermicocca.termostato.Bean.ScheduleBean;
import app.giacomo.lavermicocca.termostato.Bean.ScheduleItemBean;
import app.giacomo.lavermicocca.termostato.Fragment.Calendar;
import app.giacomo.lavermicocca.termostato.R;
import app.giacomo.lavermicocca.termostato.Utils.DebugLog;
import app.giacomo.lavermicocca.termostato.Utils.JsonObjectRequest;

/**
 * Created by Giacomo on 19/03/2015.
 */
public class ScheduleBusiness {

    private final Resources res;
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private Activity activity;

    private ScheduleBean scheduleBean;
    public ScheduleBean getScheduleBean() {
        return scheduleBean;
    }

    public ScheduleBusiness(final Activity activity) {
        this.activity = activity;
        this.mContext = activity.getApplicationContext();
        this.res = mContext.getResources();
        this.scheduleBean = new ScheduleBean();
    }//PartnerBusiness

    //---------------------------------------------------------------------------------------------

    public void getSchedule(final Calendar calendar) {
        DebugLog.getMethodTrace(Thread.currentThread());

        String url = res.getString(R.string.SERVER_IP) + res.getString(R.string.api_post_get_schedule);

        Log.i("DEBUG", "URL api : " + url);

        // Creating volley request obj
        final JsonObjectRequest getSchedule = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    Log.e("",response.toString());
                    parseResponseSchedule(response);
                    calendar.listAdapter.notifyDataSetChanged();
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //StaticMethods.hidePDialog(pDialog);
                Toast.makeText(mContext, "FAIL", Toast.LENGTH_LONG).show();
            }
        });
        AppControllerStore.getInstance().addToRequestQueue(getSchedule);
    }//getSchedule

    private void parseResponseSchedule(JSONObject jsonObject) {

        if(jsonObject.has("monday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("monday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                            ""+jo.get("end"),
                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
        if(jsonObject.has("tuesday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("tuesday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                            ""+jo.get("end"),
                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
        if(jsonObject.has("wednesday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("monday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                            ""+jo.get("end"),
                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
        if(jsonObject.has("thursday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("thursday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                            ""+jo.get("end"),
                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
        if(jsonObject.has("friday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("friday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                            ""+jo.get("end"),
                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
        if(jsonObject.has("saturday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("saturday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                                                                            ""+jo.get("end"),
                                                                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
        if(jsonObject.has("sunday"))
        {
            try {
                JSONArray ja = (JSONArray)jsonObject.get("sunday");
                for (int i = 0; i < ja.length(); i++) {
                    JSONObject jo = (JSONObject)ja.get(i);
                    ScheduleItemBean scheduleItemBean = new ScheduleItemBean(""+jo.get("start"),
                            ""+jo.get("end"),
                            ""+jo.get("temperature"));
                    this.scheduleBean.getSaturday().add(scheduleItemBean);
                }
            } catch (JSONException e) {
                Log.e("JSON PARSE", e.getLocalizedMessage());
            }
        }
    }//fillSettoreMerciologicoBeanList

}
