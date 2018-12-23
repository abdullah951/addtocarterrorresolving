package com.example.hahaha.addtocarterrorresolving.Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hahaha.addtocarterrorresolving.OrderStatusCallBack;
import com.example.hahaha.addtocarterrorresolving.object;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyGetData  {

    private static String TAG = "VolleyGetData";

    public static void getData(Context context, String uri, final ServerCallback callback) {
        Log.d("s", "getData: started ");

        //String uris = "http://ancient-journey-87940.herokuapp.com/items";

        JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, "onResponsed: " + response);
                List<object> list = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject objects;
                        objects = response.getJSONObject(i);

                        String name = objects.getString("name");
                        Log.d(TAG, "name: " + name);
                        String image = objects.getString("image");

                       String id = objects.getString("item_id");
                        String quantity = objects.getString("quantity");
                        String description = objects.getString("description");
                        String price = objects.getString("updated_price");
                        String measuring_unit = objects.getString("measuring_unit");
                        String sub_cat_name = objects.getString("sub_category_name");
                        //object o= new object(name,imagePath , quantity, description, price, measuring_unit);

                        list.add(new object(id, name, image, quantity, description, price, measuring_unit, sub_cat_name));
                        Log.d(TAG, "onResponseList: list " + list.size());
                        callback.onSuccess(list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.onError(error.getMessage());
                Log.d("faileder", "onResponsefail: " + error);
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
public static void getDelivery(Context context,String uri,final getDelivery callback){
    JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

            Log.d(TAG, "onResponsed: " + response);
            try {
                String date=response.getJSONObject(0).getString("earliest_delivery");
                callback.onSuccess(date);
            } catch (JSONException e) {
                callback.onError("Error connecting to database");
                e.printStackTrace();
            }


        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
            callback.onError(error.getMessage());
            Log.d("faileder", "onResponsefail: " + error);
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

    RequestQueue requestQueue = Volley.newRequestQueue(context);

    requestQueue.add(request3);

}
    public static void getSliderData(Context context, String uri, final ServerCallback callback){
        JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET, uri, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, "onResponsed: " + response);
                List<object> list = new ArrayList<>();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject objects;
                        objects = response.getJSONObject(i);

                        String slider_category = objects.getString("slider_category");
                        Log.d(TAG, "name: " + slider_category);
                        String image = objects.getString("image");



                        list.add(new object(slider_category, image));
                        Log.d(TAG, "onResponseList: list " + list.size());
                        callback.onSuccess(list);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.onError(error.getMessage());
                Log.d("faileder", "onResponsefail: " + error);
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

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(request3);
    }

    public static void getOrderDataHistory(String url, final OrderStatusCallBack callback, String user_id, String orderStatus, Context context){
        String uri=url+"?user_id="+user_id+"&orderStatus="+orderStatus;

        JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET, uri, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, "onResponsed: " + response);
                //List<object> list = new ArrayList<>();
                List<object> list2=new ArrayList<>();

                    if(response.length()>0) {


                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject objects;
                                objects = response.getJSONObject(i);

                                // String slider_category = objects.getString("slider_category");
                                // Log.d(TAG, "name: " + slider_category);
                                //String image = objects.getString("image");
                                if (response.getJSONObject(i).getString("orderStatus").equals("3")) {
                                    String total_Price = objects.getString("total_price");
                                    String order_id = objects.getString("order_id");
                                    String order_date = objects.getString("dated");
                                    String order_Items = objects.getString("total_items");
                                    //String delivery_before=objects.getString("delivery_before");
                                    String delivery_before = "55";

                                    //list.add(new object(slider_category, image));
                                    Log.d(TAG, "onResponseList2: "+list2.size());
                                    list2.add(new object(total_Price, order_id, order_date, order_Items, delivery_before));
                                }
                                callback.OnSuccess(list2);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                else{
                        callback.OnError("You haven't Ordered anything");
                    }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.OnError("There is network Error");
                Log.d("faileder", "onResponsefail: " + error);
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

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(request3);

    }

    public static void getOrderDataOnGoing(String url, final OrderStatusCallBack callback, String user_id,String orderStatus, Context context){
        String uri=url+"?user_id="+user_id+"&orderStatus="+orderStatus;

        JsonArrayRequest request3 = new JsonArrayRequest(Request.Method.GET, uri, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d(TAG, "onResponsed: " + response);
                List<object> list = new ArrayList<>();
                List<object> list2=new ArrayList<>();

                if(response.length()>0) {


                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject objects;
                            objects = response.getJSONObject(i);

                            // String slider_category = objects.getString("slider_category");
                            // Log.d(TAG, "name: " + slider_category);
                            //String image = objects.getString("image");
                            if (response.getJSONObject(i).getString("orderStatus").equals("3")) {
                                String total_Price = objects.getString("total_price");
                                String order_id = objects.getString("order_id");
                                String order_date = objects.getString("dated");
                                String order_Items = objects.getString("total_items");
                                //String delivery_before=objects.getString("delivery_before");
                                String delivery_before = "55";


                                //list.add(new object(slider_category, image));
                                Log.d(TAG, "onResponseList2: "+list2.size());
                                list2.add(new object(total_Price, order_id, order_date, order_Items, delivery_before));
                            } else if (response.getJSONObject(i).getString("orderStatus").equals("1")) {
                                String total_Price = objects.getString("total_price");
                                String order_id = objects.getString("order_id");
                                String order_date = objects.getString("dated");
                                String order_Items = objects.getString("total_items");
                                String delivery_before = "50";
                                list.add(new object(total_Price, order_id, order_date, order_Items, delivery_before));
                            }
                            Log.d(TAG, "onResponseList: list " + list.size());
                            callback.OnSuccess(list);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                else{
                    callback.OnError("You haven't Ordered anything");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                callback.OnError("There is network Error");
                Log.d("faileder", "onResponsefail: " + error);
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

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        requestQueue.add(request3);

    }

}
