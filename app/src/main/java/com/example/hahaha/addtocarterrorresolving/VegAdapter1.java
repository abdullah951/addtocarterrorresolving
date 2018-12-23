package com.example.hahaha.addtocarterrorresolving;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.hahaha.addtocarterrorresolving.Volley.ServerCallback;
import com.example.hahaha.addtocarterrorresolving.Volley.VolleyGetData;
import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VegAdapter1 extends RecyclerView.Adapter {

    private static String TAG = "VegAdapter1";

    private List<object> list;
    private Context context;
    private DatabaseHelper dh;
    private Cursor c;

    public VegAdapter1(List<object> list, Context context) {
        this.list = list;
        this.context = context;
        dh = new DatabaseHelper(context);
        setHasStableIds(true);
    }
    Long id;
    @Override
    public long getItemId(int position) {
        //Long l = Long.parseLong(list.get(position).getItem_id());
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if(viewType == R.layout.activity_listitems_milk){
            view = layoutInflater.inflate(R.layout.activity_listitems_milk, parent, false);
            return new VegAdapter1.ViewHolder(view, context);
        }else{
            view = layoutInflater.inflate(R.layout.show_more, parent, false);
            return new VegAdapter1.FooterViewHolder(view, context);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder = (FooterViewHolder)holder;
//            footerViewHolder.more_items.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(context, DetailScrolling.class);
//                    i.putExtra("query", Constant.uriAllFruit);
//                    i.putExtra("category", Constant.vegetable_category);
//                    context.startActivity(i);
//                }
//            });
        }else if(holder instanceof ViewHolder) {
            final ViewHolder viewHolder = (ViewHolder) holder;
            String id = list.get(viewHolder.getAdapterPosition()).getItem_id();
            String name = list.get(viewHolder.getAdapterPosition()).getName();
            String image = list.get(viewHolder.getAdapterPosition()).getImage();
            String description = list.get(viewHolder.getAdapterPosition()).getDescriptionn();
            String quantity = list.get(viewHolder.getAdapterPosition()).getQuantity();
            String price = list.get(viewHolder.getAdapterPosition()).getPricee();
            String measuring_unit = list.get(viewHolder.getAdapterPosition()).getMeasuring_unit();
            String sub_cat_name = list.get(viewHolder.getAdapterPosition()).getSub_cat_name();
            viewHolder.pricee.setText(context.getString(R.string.priceMain, Integer.parseInt(price)));
            viewHolder.product_name.setText(name);
            String quantityAndMeasuringunit = quantity+" "+measuring_unit;
            viewHolder.measuring_unit.setText(quantityAndMeasuringunit);
            Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.dairy1)
                    .error(R.drawable.dairy2)
                    .into(viewHolder.sriv);
            //Log.e(TAG, sub_cat_name);
              //it checks if MilkAdapter2 items are present in cart table
            boolean b = false;
            if (c != null && c.moveToFirst()) {
                Log.e("ghy", "onBindViewHolder: "+"runninggggggggg" );
                do {
                    Log.e(TAG, "Count "+c.getCount());
                    String product_id = c.getString(c.getColumnIndex("cart_detail_id"));
                    Log.e(TAG, "getCartDetailItemForUpdatingAddToCartButtonBySub_cat_name "+product_id);
                    viewHolder.textView.setVisibility(View.VISIBLE);
                    viewHolder.linearLayout.setVisibility(View.GONE);
                    if (id.equals(product_id)) {  // if present just update the AddToCart status
                        b = true;

                        ((Activity) context).runOnUiThread(() -> Log.e(TAG, "Position---------------------"+position));
                        String product_quantity = c.getString(c.getColumnIndex("AddToCartQuantity"));
                        Log.e(TAG, "getCartDetailItemForUpdatingAddToCartButtonBySub_cat_name "+product_quantity);
                        Log.e(TAG, "ViewHolder " + " " + product_id);

                        //Log.e(TAG, "inside for loop "+name + " " + quantity);
//                        if(product_quantity.equals("0")||product_quantity.equals("-1")){
//                            viewHolder.textView.setVisibility(View.VISIBLE);
//                            viewHolder.linearLayout.setVisibility(View.GONE);
//                        }else{
//                            viewHolder.textView.setVisibility(View.GONE);
//                            viewHolder.linearLayout.setVisibility(View.VISIBLE);
//                            viewHolder.digit.setText(product_quantity);
//                        }
                        viewHolder.textView.setVisibility(View.GONE);
                        viewHolder.linearLayout.setVisibility(View.VISIBLE);
                        viewHolder.digit.setText(product_quantity);
                    }else{
                        viewHolder.textView.setVisibility(View.VISIBLE);
                        viewHolder.linearLayout.setVisibility(View.GONE);
                    }
                } while (c.moveToNext());
                c.close();
            }
            if(b){
                viewHolder.textView.setVisibility(View.VISIBLE);
                viewHolder.linearLayout.setVisibility(View.GONE);

            }

            // click listener for image icon
//            viewHolder.sriv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(context, Item_detail.class);
//                    i.putExtra("id",list.get(holder.getAdapterPosition()).getItem_id());
//                    context.startActivity(i);
//                }
//            });
            // image litener for AddToCart

            //click listener for addtocart +

            //click listener for addtocart -

        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size())?R.layout.show_more:R.layout.activity_listitems_milk;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView) SelectableRoundedImageView sriv;
        @BindView(R.id.cart) public TextView textView;
        @BindView(R.id.plus) public Button plus;
        @BindView(R.id.minus) public Button minus;
        @BindView(R.id.digit) public Button add;
        @BindView(R.id.price) public TextView pricee;
        @BindView(R.id.name) public TextView product_name;
        @BindView(R.id.measuring_unit) public TextView measuring_unit;

        @BindView(R.id.linear) public LinearLayout linearLayout;
        RecyclerView recyclerView;
        private RelativeLayout rel;
        private Context context;
        private Button digit;
        private GridLayout grid;
        private TextView price;
        private Button delete;
        private EditText num;
        private TextView name;
        public ViewHolder(View view, final Context context) {
            super(view);

            ButterKnife.bind(this, itemView);

            textView.setOnClickListener(v -> {
                textView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);

                String id = list.get(getAdapterPosition()).getItem_id();
                String image = list.get(getAdapterPosition()).getImage();
                String price = list.get(getAdapterPosition()).getPricee();
                String description = list.get(getAdapterPosition()).getDescriptionn();
                String quantity = list.get(getAdapterPosition()).getQuantity();
                String name = list.get(getAdapterPosition()).getName();
                String measuring_unit = list.get(getAdapterPosition()).getMeasuring_unit();
                String sub_cat_name = list.get(getAdapterPosition()).getSub_cat_name();
                Log.e(TAG, name);
                Log.e(TAG, sub_cat_name);
                InsertInCart.InsertInCartDatabase(context, Constant.vegetable_category, id, name, image, price, description, quantity, measuring_unit, sub_cat_name);

            });
            plus.setOnClickListener(v -> {
                String id = list.get(getAdapterPosition()).getItem_id();
                int quantit = Integer.parseInt(dh.searchPresenceOfNameInCartDetailTable(id));
                digit.setText(++quantit + "");
                boolean b = dh.updateCartDetailQuantity(id, quantit);
                if (b) {
                    Log.e(TAG, id + " has Updated Quantity " + quantit);
                }
            });
            minus.setOnClickListener(v -> {
                String id = list.get(getAdapterPosition()).getItem_id();
                int quantit = Integer.parseInt(dh.searchPresenceOfNameInCartDetailTable(id));
                if (quantit > 1) {
                    digit.setText(--quantit + "");
                    boolean b = dh.updateCartDetailQuantity(id, quantit);
                    if (b) {
                        Log.e(TAG, id + " has Updated Quantity " + quantit);
                    }
                } else if (quantit == 1) {
                    textView.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.GONE);
                    dh.deleteCartDetail(id);
                    Log.e(TAG, "Deleted from cart");
                }
            });
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        LinearLayout more_items;
        public FooterViewHolder(View view, Context context) {
            super(view);
            more_items = itemView.findViewById(R.id.more_items);
        }
    }

}
