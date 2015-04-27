package app.giacomo.lavermicocca.termostato.Business;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import app.giacomo.lavermicocca.termostato.App.AppControllerStore;
import app.giacomo.lavermicocca.termostato.R;
import app.giacomo.lavermicocca.termostato.Utils.DebugLog;
import app.giacomo.lavermicocca.termostato.Utils.JsonObjectRequest;

/**
 * Created by Giacomo on 19/03/2015.
 */
public class DisplayTextBusiness {

    private final Resources res;
    private String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private Activity activity;

    public DisplayTextBusiness(final Activity activity) {
        this.activity = activity;
        this.mContext = activity.getApplicationContext();
        this.res = mContext.getResources();
    }//PartnerBusiness

    //---------------------------------------------------------------------------------------------

    public void setCustomText(final EditText editText) {
        DebugLog.getMethodTrace(Thread.currentThread());

        String url = res.getString(R.string.SERVER_IP) + res.getString(R.string.api_post_set_custom_text);

        Log.i("DEBUG", "URL api : " + url);

        String bodyParam = "text="+editText.getText()+"&delay=1&color=red&reverse=false";

        // Creating volley request obj
        final JsonObjectRequest setCustomText = new JsonObjectRequest(Request.Method.POST, url, bodyParam, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.has("response")) {
                        if (response.get("response").equals("ok")) {
                            editText.setText("");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //StaticMethods.hidePDialog(pDialog);
                Toast.makeText(mContext, "FAIL", Toast.LENGTH_LONG).show();
            }
        });
        AppControllerStore.getInstance().addToRequestQueue(setCustomText);
    }//setCustomText

    private void fillResponse(String jsonString) {

        try {

            JSONObject jsonObject = new JSONObject(jsonString);

        }//for
        catch (JSONException e) {
            e.printStackTrace();
        }
    }//fillSettoreMerciologicoBeanList

}
