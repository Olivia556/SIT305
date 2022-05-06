package com.example.trucksharing;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.UUID;

public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderAdapter.MyViewHolder> {
    private Context context;
    private OnItemClickListener item_on_click_listeners;
    private List<ItemBean> Item_Bean_list;
    private DatabaseHelper Data_base_Helper_this;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int posIndex = position;
        Log.e("TAG", "onBindViewHolder: "+Item_Bean_list.get(position).vehicleType);
        switch (Item_Bean_list.get(position).vehicleType){
            case "Truck":
                holder.vehicle_type_image_view.setBackgroundResource(R.drawable.truck);
                break;
            case "Van":
                holder.vehicle_type_image_view.setBackgroundResource(R.drawable.van);
                break;
            case "Refrigerated truck":
                holder.vehicle_type_image_view.setBackgroundResource(R.drawable.refrigerated_truck);
                break;
            case "Mini truck":
                holder.vehicle_type_image_view.setBackgroundResource(R.drawable.mini_truck);
                break;
            default:
                holder.vehicle_type_image_view.setBackgroundResource(R.drawable.other_vehecle);
                break;
        }
        holder.text_date_time_this.setText(Item_Bean_list.get(position).pickUpDetailDate);
        holder.text_view_contents.setText(Item_Bean_list.get(position).goodDetail);
        holder.button_share_to_others.setBackgroundResource(R.drawable.btn_share);
        holder.Constraint_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item_on_click_listeners.onItemClick(view,posIndex);
            }
        });

        holder.button_share_to_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = Data_base_Helper_this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("isPublic","true");
                db.update("OrderList",contentValues,"u_id=?",new String[]{Item_Bean_list.get(position).u_id});
                Toast.makeText(context, "share successfully！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Item_Bean_list.size();
    }
    public interface OnItemClickListener {
        void onItemClick(View view,int position);
    }
    public void addItemClickListener(OnItemClickListener listener){
        item_on_click_listeners = listener;
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView vehicle_type_image_view;
        public TextView text_date_time_this;
        public TextView text_view_contents;
        public Button button_share_to_others;
        public ConstraintLayout Constraint_Layout;
        public MyViewHolder(View itemView) {
            super(itemView);
            vehicle_type_image_view = itemView.findViewById(R.id.vehicle_type_image_view);
            text_date_time_this = itemView.findViewById(R.id.text_date_time_this);
            text_view_contents = itemView.findViewById(R.id.text_view_contents);
            button_share_to_others = itemView.findViewById(R.id.button_share_to_others);
            Constraint_Layout = itemView.findViewById(R.id.Constraint_Layout);
        }
    }

    public AllOrderAdapter(List<ItemBean> Item_Bean_list, Context context){
        this.Item_Bean_list = Item_Bean_list;
        this.context = context;
        Data_base_Helper_this = new DatabaseHelper(context,"LocalDatabase.db", null, 1);

    }


}
