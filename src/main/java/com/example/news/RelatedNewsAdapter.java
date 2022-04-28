package com.example.news;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.MyViewHolder>{

    public List<Integer> reportNewList;
    public String[] newsTitleList;
    public String[] newsDetailList;

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int currentIndex;
    public OnRelatedNewsItemClickListener onRelatedNewsItemClickListener;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_related_news,parent,false);
        return new RelatedNewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int pos = position;
        holder.myNewTiltleString.setText(newsTitleList[reportNewList.get(position)]);
        if(newsDetailList[reportNewList.get(position)].length()>35){
            String str = "";
            str = newsDetailList[reportNewList.get(position)].substring(0,35);
            str = str + "...";
            holder.myNewDetailString.setText(str);
        }else{
            Log.e("TAG", "onBindViewHolder: 22222" );
            holder.myNewDetailString.setText(newsDetailList[reportNewList.get(position)]);
        }
        switch (reportNewList.get(position)){
            case 0:
                holder.imageView.setBackgroundResource(R.drawable.news1);
                break;
            case 1:
                holder.imageView.setBackgroundResource(R.drawable.news2);
                break;
            case 2:
                holder.imageView.setBackgroundResource(R.drawable.news3);
                break;
            case 3:
                holder.imageView.setBackgroundResource(R.drawable.news4);
                break;
            case 4:
                holder.imageView.setBackgroundResource(R.drawable.news5);
                break;
            case 5:
                holder.imageView.setBackgroundResource(R.drawable.news6);
                break;
            case 6:
                holder.imageView.setBackgroundResource(R.drawable.news7);
                break;
            default:
                break;
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRelatedNewsItemClickListener.onRelatedNewsItemClick(view,pos);
            }
        });

    }

    public interface OnRelatedNewsItemClickListener {
        void onRelatedNewsItemClick(View view,int position);
    }

    public void addOnRelatedNewsItemClickListener(OnRelatedNewsItemClickListener listener){
        Log.e("TAG", "addOnTopNewsItemClickListener: "+"成功设置监听器" );
        onRelatedNewsItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return reportNewList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView myNewTiltleString;
        public TextView myNewDetailString;
        public ConstraintLayout constraintLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPicture);
            myNewTiltleString = itemView.findViewById(R.id.newsTitleString);
            myNewDetailString =  itemView.findViewById(R.id.newsDetailString);
            constraintLayout = itemView.findViewById(R.id.layout_cl);

        }
    }

    public RelatedNewsAdapter(int index, Context context,List<Integer> reportNewList){
        this.reportNewList = reportNewList;
        currentIndex = index;
        Resources res =context.getResources();
        newsTitleList =res.getStringArray(R.array.newsTitle);
        newsDetailList =res.getStringArray(R.array.newsDetail);
    }
}
