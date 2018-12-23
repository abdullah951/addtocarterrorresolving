package com.example.hahaha.addtocarterrorresolving;

public class object {

    private String slider_category;
    private int imagePath;
    private String pricee;
    private String descriptionn;
    private String quantity;
    private String name;
    private String measuring_unit;
    private String sub_cat_name;
    private String image;
    private String item_id;
    private String addToCartQuantity;
    private String total_Price;
    private String order_id;

    public String getDelivery_before() {
        return delivery_before;
    }

    public void setDelivery_before(String delivery_before) {
        this.delivery_before = delivery_before;
    }

    private String delivery_before;

    public String getTotal_Price() {
        return total_Price;
    }

    public void setTotal_Price(String total_Price) {
        this.total_Price = total_Price;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_Items() {
        return order_Items;
    }

    public void setOrder_Items(String order_Items) {
        this.order_Items = order_Items;
    }

    private String order_date;
    private String order_Items;
    public object(String total_Price, String order_id, String order_date, String order_Items, String delivery_before){
        this.total_Price=total_Price;
        this.order_id=order_id;
        this.order_date=order_date;
        this.order_Items=order_Items;
        this.delivery_before=delivery_before;
    }


    public object(String slider_category, String image){

        this.slider_category = slider_category;
        this.image = image;
    }

    public object(String name, int imagePath, String quantity, String description, String price, String measuring_unit){
        this.imagePath = imagePath;
        this.pricee = price;
        this.name = name;
        this.descriptionn = description;
        this.quantity = quantity;
        this.measuring_unit = measuring_unit;
    }

    public object(String item_id, String name, String image, String quantity, String description, String price, String measuring_unit, String sub_cat_name) {
        this.item_id = item_id;
        this.image = image;
        this.pricee = price;
        this.name = name;
        this.descriptionn = description;
        this.quantity = quantity;
        this.measuring_unit = measuring_unit;
        this.sub_cat_name = sub_cat_name;
    }


    public object(String item_id, String name, String image, String AddToCartQuantity, String quantity, String description, String price, String measuring_unit, String sub_cat_name){

        this.item_id = item_id;
        this.name = name;
        this.image = image;
        this.addToCartQuantity = AddToCartQuantity;
        this.quantity = quantity;
        this.descriptionn = description;
        this.pricee = price;
        this.measuring_unit = measuring_unit;
        this.sub_cat_name = sub_cat_name;
    }


    public String getAddToCartQuantity() {
        return addToCartQuantity;
    }

    public void setAddToCartQuantity(String addToCartQuantity) {
        this.addToCartQuantity = addToCartQuantity;
    }

    public String getSub_cat_name() {
        return sub_cat_name;
    }

    public void setSub_cat_name(String sub_cat_name) {
        this.sub_cat_name = sub_cat_name;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getPricee() {
        return pricee;
    }

    public void setPricee(String price) {
        this.pricee = price;
    }

    public String getDescriptionn() {
        return descriptionn;
    }

    public void setDescriptionn(String description) {
        this.descriptionn = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasuring_unit() {
        return measuring_unit;
    }

    public void setMeasuring_unit(String measuring_unit) {
        this.measuring_unit = measuring_unit;
    }

}
