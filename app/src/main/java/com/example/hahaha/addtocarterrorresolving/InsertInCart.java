package com.example.hahaha.addtocarterrorresolving;

import android.content.Context;
import android.util.Log;


import java.util.concurrent.ExecutionException;

public class InsertInCart {

    private static String TAG = "InsertInCart";

    public static void InsertInCartDatabase(Context context,String categoryName, String id, String name, String image, String price, String description, String quantity, String measuring_unit, String sub_cat_name){

        DatabaseHelper dh = new DatabaseHelper(context);

        InsertInSqliteDatabase insertInCart = new InsertInSqliteDatabase(context);
        InsertInSqliteDatabase insertCartDetail = new InsertInSqliteDatabase(context);
        boolean insertedInCart = false; // used to check if cart data inserted correctly
        boolean insertedInCartDetail = false; // used to check if cart_detail data inserted correctly
        if (!dh.searchCartName(categoryName)) { // if cart not exist then insert. If exists theres no need to insert or replace bcz its unique
            try {
                insertedInCart = insertInCart.execute("insertCart", categoryName, price).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else
            insertedInCart = true;  // if exists just skip inserting

        if (!dh.searchCartDetail(id)) { // if cartDetail not exist then insert. If exists theres no need to insert or replace bcz its unique
            if (insertedInCart) {
                try {
                    insertedInCartDetail = insertCartDetail.execute("insertCartDetail",id, name, image, quantity, description, price, measuring_unit, sub_cat_name).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } else
            insertedInCartDetail = true;  // if exists just skip inserting
        if (insertedInCartDetail) {
            dh.getCart();
            dh.getCartDetail();
        } else
            Log.e(TAG, "Not Inserted In Cart");
    }
}
