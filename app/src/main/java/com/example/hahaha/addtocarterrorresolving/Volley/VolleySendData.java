package com.example.hahaha.addtocarterrorresolving.Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hahaha.addtocarterrorresolving.Constant;
import com.example.hahaha.addtocarterrorresolving.NumberChecking;

import org.json.JSONException;
import org.json.JSONObject;

public class VolleySendData {
    private static String TAG = "VolleyGetData";
    private static Boolean phoneCheck;

    public VolleySendData() {

    }

    public static void SendUserData(JSONObject js, Context context, String uri, final userDataCallback callback) {
        Log.d("s", "sendData: started ");

        JsonObjectRequest request3 = new JsonObjectRequest(Request.Method.POST, uri, js, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, "onResponse: send Successfully" );

                callback.onSuccess(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                //callback.onError(error.getMessage());
                Log.d("faileder", "onResponsefail: " + error);
                callback.onError("There is connection Error" + error);
            }

        });

        request3.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        requestQueue.add(request3);

    }

    public static Boolean checkNumber(String phoneNo,Context context,String ur, final NumberChecking callback) {
       // s=mPhoneNumberField.getText().toString();

       // callback.onSuccess(true);


        //String uri=ur+"?phoneNo="+js;

        //String uri="http://ec2-13-232-147-28.ap-south-1.compute.amazonaws.com:5000/CheckNumber?phone="+ phoneNo;

        //String uri="http://ec2-13-232-147-28.ap-south-1.compute.amazonaws.com:5000/CheckNumber?phone=0335";


        JsonObjectRequest stringRequest=new JsonObjectRequest(Request.Method.GET, Constant.uriCheckNumber+"?phone="+ phoneNo,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG, "onResponseCheck: "+response );
                try {
                    phoneCheck = response.get("code").equals("1");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onSuccess(phoneCheck);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponseCheck: "+error );


            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return true;

    }
}
