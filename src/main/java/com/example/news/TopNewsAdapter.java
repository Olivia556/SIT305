package com.example.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.LinkedList;
import android.widget.ImageView;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class TopNewsAdapter extends RecyclerView.Adapter<TopNewsAdapter.MyViewHolder> {

    private OnTopNewsItemClickListener mOnTopNewsItemClickListener;
    private List<Boolean> onClickJudgeBool;
    private List<Integer> mData;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topnews,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        int pos = position;
        switch (position){
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

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnTopNewsItemClickListener.onTopNewsItemClick(view,pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public interface OnTopNewsItemClickListener {
        void onTopNewsItemClick(View view,int position);
    }

    public void addOnTopNewsItemClickListener(OnTopNewsItemClickListener listener){
        mOnTopNewsItemClickListener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.myNewImageViewCache);
        }
    }

    public TopNewsAdapter(List<Integer> mData){
        this.mData = mData;
        onClickJudgeBool = new LinkedList<Boolean>();
        for(int i=0 ;i <= mData.size()-1 ; i = i + 1){
            onClickJudgeBool.add(false);
        }


    }


}
