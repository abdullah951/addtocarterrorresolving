package com.example.hahaha.addtocarterrorresolving;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by hehehehe on 2/4/2018.
 */

public class InsertInSqliteDatabase extends AsyncTask<String, Void, Boolean> {

    public Context ctx;
    private DatabaseHelper dh;

    public InsertInSqliteDatabase(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        dh = new DatabaseHelper(ctx);
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... params) {

        String method = params[0];
        switch (method) {

            case "insertUserData": {

                String name = params[1];
                String email = params[2];
                String phone_no = params[3];
                String user_id=params[4];
                String latitude = params[5];
                String longitude = params[6];
                String location = params[7];
                //value of x is from DatabaseHelper class line 54
                return dh.insertUserDataWithoutPic(name, email,phone_no,user_id,latitude,longitude,location);
            }

            case "insertUserPic":

                break;

            case "insertCart": {
                String item_name = params[1];
                String price = params[2];
                return dh.insertCart(item_name, price);
            }
            case "insertCartDetail":{
                String id = params[1];
                String name = params[2];
                String image = params[3];
                String quantity = params[4];
                String description = params[5];
                String price = params[6];
                String measuring_unit = params[7];
                String sub_cat_name = params[8];
                return dh.insertCartDetail(id, name,image,"1",quantity,description,price,measuring_unit, sub_cat_name);
            }
            case "insertOrder":{

            }
            case "insertSubOrder":{

            }
            case "insertOrderDetail":{

            }

        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
