package com.example.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AllNewsAdapter extends RecyclerView.Adapter<AllNewsAdapter.MyViewHolder>{

    public List<String> newsTitleList;
    public List<String> newsDetailList;
    private OnAllNewsItemClickListener AllNewsAdapterListener;

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allnews,parent,false);
        return new AllNewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.myNewTiltleString.setText(newsTitleList.get(position));
        holder.myNewDetailString.setText(newsDetailList.get(position));
        if(pos == 0){
            holder.imageView.setBackgroundResource(R.drawable.news1);
        }else if(pos == 1){
            holder.imageView.setBackgroundResource(R.drawable.news2);

        }else if(pos == 2){
            holder.imageView.setBackgroundResource(R.drawable.news3);

        }else if(pos == 3){
            holder.imageView.setBackgroundResource(R.drawable.news4);
            
        }else if(pos == 4){
            holder.imageView.setBackgroundResource(R.drawable.news5);
            
        }else if(pos == 5){
            holder.imageView.setBackgroundResource(R.drawable.news6);
            
        }else if(pos == 6){
            holder.imageView.setBackgroundResource(R.drawable.news7);
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllNewsAdapterListener.onAllNewsItemClick(view,pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsTitleList.size();
    }

    public interface OnAllNewsItemClickListener {
        void onAllNewsItemClick(View view,int position);
    }

    public void addOnAllNewsItemClickListener(OnAllNewsItemClickListener listener){
        AllNewsAdapterListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView myNewDetailString;
        public ImageView imageView;
        public TextView myNewTiltleString;
        public ConstraintLayout constraintLayout;

        public MyViewHolder(View viewP) {
            super(viewP);
            imageView = viewP.findViewById(R.id.myNewImageViewCache);
            myNewTiltleString = viewP.findViewById(R.id.myNewTiltleString);
            myNewDetailString =  viewP.findViewById(R.id.myNewDetailString);
            constraintLayout = viewP.findViewById(R.id.layout_cl);
        }
    }
    public AllNewsAdapter(List<String> list1,List<String> list2){
        this.newsTitleList = list1;
        this.newsDetailList = list2;
    }
}
