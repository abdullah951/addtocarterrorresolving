package com.example.hahaha.addtocarterrorresolving;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by hahahaha on 28/01/2018.
*/

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG="DatabaseHelper";

    private static final String DATABASE_NAME="user.db";
    //Table Names
    private static final String USER_TABLE="user_info";
    private static final String ORDER_TABLE="order_";
    private static final String SUB_ORDER_TABLE="sub_order_";
    private static final String ORDER_DETAIL_TABLE="order_detail";
    private static final String CART_TABLE="cart";
    private static final String SUB_CART_TABLE="sub_cart";
    private static final String CART_DETAIL_TABLE="cart_detail";

    //USER_TABLE Columns
    private static final String USER_INFO_ID="user_info_id";
    private static final String USER_NAME="user_name";
    private static final String USER_EMAIL="user_email";
    private static final String USER_PHONE_NO="user_phone_no";
    private static final String USER_LATITUDE="user_latitude";
    private static final String USER_LONGITUDE="user_longitude";
    private static final String USER_LOCATION="user_location";
    private static final String USER_PIC="user_pic";


    private static final String create_user_table = "create table IF NOT EXISTS "+USER_TABLE+
                                                    "("+USER_INFO_ID+" TEXT," +
                                                    ""+USER_NAME+" TEXT,"+USER_EMAIL+" TEXT,"+USER_PHONE_NO+" TEXT," +
                                                    ""+USER_LATITUDE+" TEXT,"+USER_LONGITUDE+" TEXT,"+USER_LOCATION+" TEXT,"+USER_PIC+" BLOB)";

    public boolean insertUserDataWithoutPic(String name, String email, String phone_no,String user_id,String latitude,String longitude,String location){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_NAME,name);
        contentValues.put(USER_EMAIL,email);
        contentValues.put(USER_PHONE_NO,phone_no);
        contentValues.put(USER_INFO_ID,user_id);
        contentValues.put(USER_LATITUDE,latitude);
        contentValues.put(USER_LONGITUDE,longitude);
        contentValues.put(USER_LOCATION,location);

        return db.insert(USER_TABLE,null,contentValues)>0;
    }

    public String[] getUserData(){
        SQLiteDatabase db=this.getWritableDatabase();
        String[] s = new String[6];

        Cursor c = db.query(USER_TABLE,new String[]{USER_NAME,USER_EMAIL,USER_PHONE_NO,USER_LOCATION,USER_PIC,USER_INFO_ID},null,null,null,null, null,"1");
        if(c != null && c.moveToFirst()){
            s[0] = c.getString(c.getColumnIndex(USER_NAME));
            s[1] = c.getString(c.getColumnIndex(USER_EMAIL));
            s[2] = c.getString(c.getColumnIndex(USER_PHONE_NO));
            s[3] = c.getString(c.getColumnIndex(USER_LOCATION));
            s[4] = c.getString(c.getColumnIndex(USER_PIC));
            s[5]=c.getString(c.getColumnIndex(USER_INFO_ID));
            c.close();
        }
        return s;
    }

    public int getCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+USER_TABLE,null).getCount();
    }

    public boolean insertUserPic(byte[] image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_LOCATION,image);
        return db.insert(USER_TABLE,null,contentValues)>0;
    }

    public boolean updateUser(String name,String phone_no,double latitude,double longitude,String location){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_NAME,name);
        contentValues.put(USER_EMAIL,phone_no);
        contentValues.put(USER_PHONE_NO,latitude);
        contentValues.put(USER_LATITUDE,longitude);
        contentValues.put(USER_LONGITUDE,location);

        return db.insert(USER_TABLE,null,contentValues)>0;
    }

    public boolean deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+USER_TABLE);
        return true;
    }



    //CART Columns
    private static final String COL_3_1 = "cart_id";
    private static final String COL_3_2 = "cart_name";
    private static final String COL_3_3 = "total_price";
    private static final String create_cart_table = "create table IF NOT EXISTS "+CART_TABLE
            +"("+COL_3_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_3_2+" TEXT, "+COL_3_3+" TEXT NOT NULL, unique ("+COL_3_2+") on conflict replace)";

    public boolean insertCart(String cart_name, String price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_3_2,cart_name);
        contentValues.put(COL_3_3,price);
        return db.insert(CART_TABLE,null,contentValues)>0;
    }

    public void getCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+CART_TABLE;
        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null && c.moveToFirst()) {
            do {
                Log.e(TAG, "getCart insertedCart id " + c.getInt(c.getColumnIndex(COL_3_1)));
                Log.e(TAG, "getCart insertedCart name " + c.getString(c.getColumnIndex(COL_3_2)));
                Log.e(TAG, "getCart insertedCart price " + c.getString(c.getColumnIndex(COL_3_3)));
            } while (c.moveToNext());
            c.close();
        }
    }

    //CART_DETAIL Columns
    private static final String COL_4_1="cart_detail_id";
    private static final String COL_4_2="name";
    private static final String COL_4_3="image";
    private static final String COL_4_4="AddToCartQuantity";
    private static final String COL_4_4_0="quantity";
    private static final String COL_4_5="description";
    private static final String COL_4_5_1="product_instruction";
    private static final String COL_4_6="price";
    private static final String COL_4_6_1="measuring_unit";
    private static final String COL_4_6_2="sub_cat_name";
    private static final String COL_3_3_1 = "_cart_id";
    private static final String create_cart_detail_table = "create table IF NOT EXISTS "+CART_DETAIL_TABLE
            +"("+COL_4_1+" TEXT ,"
            +COL_4_2+" TEXT,"+COL_4_3+" TEXT,"+COL_4_4+" TEXT,"
            +COL_4_4_0+" TEXT,"+COL_4_5+" TEXT,"+COL_4_5_1+" TEXT,"+COL_4_6+" TEXT,"+COL_4_6_1+" TEXT,"+COL_4_6_2+" TEXT,"+COL_3_3_1+" integer, FOREIGN KEY("+COL_3_3_1+") REFERENCES "+CART_TABLE+"("+COL_3_1+"))";


    public boolean deleteCartDetail(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(CART_DETAIL_TABLE,COL_4_1+" =?", new String[]{id})>0;
    }

    public String getCartDetaliInstruction(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(CART_DETAIL_TABLE, new String[]{COL_4_5_1}, COL_4_1+" =?", new String[]{id},null,null,null);
        if(c != null && c.moveToFirst()) {
            return c.getString(c.getColumnIndex(COL_4_5_1));
        }
        assert c != null;
        c.close();
        return null;
    }

    public boolean insertCartDetailInstructions(String cart_detail_id, String add_instruction){
        SQLiteDatabase db=this.getWritableDatabase();
        String where = COL_4_1+"=?";
        String[] whereArgs = new String[] {cart_detail_id};
        ContentValues contentValues =new ContentValues();
        contentValues.put(COL_4_5_1, add_instruction);
        return db.update(CART_DETAIL_TABLE, contentValues, where, whereArgs)>0;
    }

    public List<object> getCartDetailArrayList(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+CART_DETAIL_TABLE;
        Cursor c = db.rawQuery(selectQuery, null);
        List<object> list1 = new ArrayList<>();
        if(c != null && c.moveToFirst()){
            do{
                Log.e(TAG, "insertedCartDetail id " + c.getInt(c.getColumnIndex(COL_4_1)));
                Log.e(TAG, "insertedCartDetail name " + c.getString(c.getColumnIndex(COL_4_2)));
                Log.e(TAG, "insertedCartDetail image " + c.getString(c.getColumnIndex(COL_4_3)));
                Log.e(TAG, "insertedCartDetail addToCart " + c.getString(c.getColumnIndex(COL_4_4)));
                Log.e(TAG, "insertedCartDetail quantity " + c.getString(c.getColumnIndex(COL_4_4_0)));
                Log.e(TAG, "insertedCartDetail description " + c.getString(c.getColumnIndex(COL_4_5)));
                Log.e(TAG, "insertedCartDetail price " + c.getString(c.getColumnIndex(COL_4_6)));
                Log.e(TAG, "insertedCartDetail measuring_unit " + c.getString(c.getColumnIndex(COL_4_6_1)));
                Log.e(TAG, "insertedCartDetail sub_cat_name " + c.getString(c.getColumnIndex(COL_4_6_2)));
                Log.e(TAG, "insertedCartDetail fid " + c.getInt(c.getColumnIndex(COL_3_3_1)));
                list1.add(new object(String.valueOf(c.getInt(c.getColumnIndex(COL_4_1))), c.getString(c.getColumnIndex(COL_4_2)),c.getString(c.getColumnIndex(COL_4_3)),c.getString(c.getColumnIndex(COL_4_4)), c.getString(c.getColumnIndex(COL_4_4_0)), c.getString(c.getColumnIndex(COL_4_5)),c.getString(c.getColumnIndex(COL_4_6)),c.getString(c.getColumnIndex(COL_4_6_1)), c.getString(c.getColumnIndex(COL_4_6_2))));
            }while(c.moveToNext());
            c.close();
        }
        return list1 ;
    }

    public Cursor getCartDetailItemForUpdatingAddToCartButtonBySub_cat_name(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(CART_DETAIL_TABLE, new String[]{COL_4_1, COL_4_2, COL_4_4}, COL_4_1+" =?", new String[]{id},null,null,null);
        if(c != null && c.moveToFirst()) {
            Log.e(TAG, "getCartDetailItemForUpdatingAddToCartButtonBySub_cat_name(id): found " + c.getString(c.getColumnIndex(COL_4_2)));
        }
        if(c != null)
            return c;
        return null;
    }

    public ArrayList getCartDetailItemForUpdatingAddToCartButtonBySub_cat_nam(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList list = new ArrayList();
        Cursor c = db.query(CART_DETAIL_TABLE, new String[]{COL_4_1, COL_4_2, COL_4_4}, COL_4_1+" =?", new String[]{id},null,null,null);
        if(c != null && c.moveToFirst()) {
            list.add(c.getString(c.getColumnIndex(COL_4_1)));
            list.add(c.getString(c.getColumnIndex(COL_4_2)));
            list.add(c.getString(c.getColumnIndex(COL_4_4)));
            return list;
            //Log.e(TAG, "getCartDetailItemForUpdatingAddToCartButtonBySub_cat_name(id): found " + c.getString(c.getColumnIndex(COL_4_2)));
        }
        assert c != null;
        c.close();
        return null;
    }

    public void getCartDetail(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+CART_DETAIL_TABLE;
        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null && c.moveToFirst()) {
            do {
                Log.e(TAG, "insertedCartDetail id " + c.getInt(c.getColumnIndex(COL_4_1)));
                Log.e(TAG, "insertedCartDetail name " + c.getString(c.getColumnIndex(COL_4_2)));
                Log.e(TAG, "insertedCartDetail image " + c.getString(c.getColumnIndex(COL_4_3)));
                Log.e(TAG, "insertedCartDetail quantity " + c.getString(c.getColumnIndex(COL_4_4)));
                Log.e(TAG, "insertedCartDetail description " + c.getString(c.getColumnIndex(COL_4_5)));
                Log.e(TAG, "insertedCartDetail price " + c.getString(c.getColumnIndex(COL_4_6)));
                Log.e(TAG, "insertedCartDetail unit " + c.getString(c.getColumnIndex(COL_4_6_1)));
                Log.e(TAG, "insertedCartDetail fid " + c.getInt(c.getColumnIndex(COL_3_3_1)));
            } while (c.moveToNext());
            c.close();
        }
    }

    public Cursor getCartItemForUpdateButtons(String sub_cart_name){
        SQLiteDatabase db = this.getWritableDatabase();
        /*Cursor c = db.query(SUB_CART_TABLE, new String[]{COL_3_1_1}, COL_3_2_1+" =?",new String[]{sub_cart_name},null,null,null,"1");
        int n;
        if(c != null && c.moveToFirst()){
            n = c.getInt(0);
            Log.e(TAG,"getCartItemForUpdateButtons "+n);
            c.close();
            Cursor c1 = db.rawQuery("SELECT * FROM "+CART_DETAIL_TABLE,null);
            //Cursor c1 = db.query(CART_DETAIL_TABLE, null, null, null,null,null,null,"1");
            if(c1 != null && c.moveToFirst()){
                Log.e(TAG,c1.getCount()+" TotalcartItems");
//                Log.e(TAG,"name "+ c1.getString(c1.getColumnIndex(COL_4_2))+" Quantity "+c1.getString(c1.getColumnIndex(COL_4_4))+" sub ");
                return c1;
            }
        }*/
        return null;
    }

    public boolean insertCartDetail(String id, String name,String image,String addToCartQuantity, String quantity,String description,String price, String measuring_unit, String sub_cat_name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_4_1, id);
        contentValues.put(COL_4_2, name);
        contentValues.put(COL_4_3, image);
        contentValues.put(COL_4_4, addToCartQuantity);
        contentValues.put(COL_4_4_0, quantity);
        contentValues.put(COL_4_5, description);
        contentValues.put(COL_4_5_1, "");
        contentValues.put(COL_4_6, price);
        contentValues.put(COL_4_6_1, measuring_unit);
        contentValues.put(COL_4_6_2, sub_cat_name);

        String selectQuery = "SELECT * FROM "+CART_TABLE+" ORDER BY "+COL_3_1+" DESC LIMIT 1";
        Cursor c = db.rawQuery(selectQuery, null);
        if(c != null && c.moveToFirst()){
            do{
                Log.e(TAG,"insertSubCart "+c.getInt(c.getColumnIndex(COL_3_1)));
                Log.e(TAG,"insertSubCart "+c.getString(c.getColumnIndex(COL_3_3)));
            }while(c.moveToNext());
            c.moveToLast();
            contentValues.put(COL_3_3_1,c.getInt(c.getColumnIndex(COL_3_1)));
            c.close();
        }
        return db.insert(CART_DETAIL_TABLE,null, contentValues)>0;

    }

    public boolean updateCartDetailQuantity(String id, int quantity){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_4_4, String.valueOf(quantity));
        return db.update(CART_DETAIL_TABLE, contentValues, COL_4_1+" = ?", new String[]{id})>0;
    }



    public int getCartDetailCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+CART_DETAIL_TABLE,null).getCount();
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_cart_table);
        sqLiteDatabase.execSQL(create_cart_detail_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void clearDate() {
        try {
            SQLiteDatabase dbh = this.getReadableDatabase();
            dbh.delete(DATABASE_NAME, null, null);
            dbh.close(); // Closing database connection
        } catch (SQLiteException ex) {
        }
    }

    public void deleteDatabase(Context mContext) {
        mContext.deleteDatabase(DATABASE_NAME);
    }

    public void display(){
        SQLiteDatabase dbh = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + USER_TABLE ;
        Log.e("lOg", selectQuery);
        Cursor c = dbh.rawQuery(selectQuery, null);

        //Log.e("lOg", c.getString(c.getColumnIndex(USER_NAME)));
        Log.e("log",String.valueOf(c.getCount()));
    }

    public String searchPresenceOfNameInCartDetailTable(String id) {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.query(CART_DETAIL_TABLE, new String[]{COL_4_1,COL_4_4}, COL_4_1+" = ?", new String[]{id}, null, null, null);
        String s = null;
        if(c != null && c.moveToFirst()){
            s = c.getString(c.getColumnIndex(COL_4_4));
            c.close();
        }
        return s;
    }

    public boolean searchCartName(String name) {
        boolean b = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(CART_TABLE,new String[]{COL_3_2}, COL_3_2+" =?",new String[]{name},null,null,null);
        if(c != null && c.moveToFirst()) {
            do {
                Log.e(TAG, "searchCartName " + c.getString(c.getColumnIndex(COL_3_2)));
                b = true;
            } while (c.moveToNext());
            c.close();
        }
        return b;
    }

    public boolean deleteCartDetailAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+CART_DETAIL_TABLE);
        return true;
    }

    public String getTotalPrice(){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM "+CART_DETAIL_TABLE;
        Cursor c = db.rawQuery(selectQuery, null);
        int total_price = 0;
        if(c != null && c.moveToFirst()){
            do{
                //  price of individual item * quantity
                total_price = total_price + (Integer.parseInt(c.getString(c.getColumnIndex(COL_4_6)).trim()) * (Integer.parseInt(c.getString(c.getColumnIndex(COL_4_4)).trim())));
            }while(c.moveToNext());
            c.close();
        }
        return String.valueOf(total_price);
    }

    public boolean searchCartDetail(String id) {
        boolean b = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.query(CART_DETAIL_TABLE, new String[]{COL_4_1}, COL_4_1+" =?", new String[]{id},null,null,null);
        if(c != null && c.moveToFirst()) {
            do {
                Log.e(TAG, "searchCartDetail(id): found " + c.getString(c.getColumnIndex(COL_4_1)));
                b = true;
            } while (c.moveToNext());
            c.close();
        }
        return b;
    }

    public void deleteCart(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ CART_DETAIL_TABLE);
        db.execSQL("delete from "+ CART_TABLE);
    }
}

