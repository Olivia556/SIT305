package com.example.news;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.fragment.app.Fragment;


public class DetailNewsFragment extends Fragment implements RelatedNewsAdapter.OnRelatedNewsItemClickListener{
    public ImageView imageViewPicture;
    public TextView newsTitleString;
    public TextView newsDetailString;
    public RecyclerView recyclerViewButton;
    public String newsTitle;
    public String newsDetail;
    public int index = -5;
    public List<Integer> reportNewList;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String[] newsTitleStringList;
    public String[] newsDetailStringList;
    public RelatedNewsAdapter relatedNewsAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_news, container, false);
        newsTitleString = view.findViewById(R.id.newsTitleString);
        newsDetailString = view.findViewById(R.id.newsDetailString);
        imageViewPicture = view.findViewById(R.id.imageViewPicture);
        recyclerViewButton = view.findViewById(R.id.recyclerViewButton);
        newsTitleString.setText(newsTitle);
        newsDetailString.setText(newsDetail);
        setBackgroundResourcePicture();
        Resources getResourceList =getResources();
        newsTitleStringList =getResourceList.getStringArray(R.array.newsTitle);
        newsDetailStringList =getResourceList.getStringArray(R.array.newsDetail);
        reportNewList = new ArrayList<Integer>();
        for(int tempIndex = 0 ; tempIndex < 7; tempIndex += 1){
            if(tempIndex != index){
                reportNewList.add(tempIndex);
            }
        }
        relatedNewsAdapter = new RelatedNewsAdapter(index,getContext(),reportNewList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewButton.setLayoutManager(linearLayoutManager);
        relatedNewsAdapter.addOnRelatedNewsItemClickListener(this);
        recyclerViewButton.setAdapter(relatedNewsAdapter);
        return view;
    }
    @Override
    public void onRelatedNewsItemClick(View view, int position) {
        this.index = reportNewList.get(position);
        newsTitleString.setText(newsTitleStringList[index]);
        newsDetailString.setText(newsDetailStringList[index]);
        setBackgroundResourcePicture();
        reportNewList.clear();
        for(int i=0;i<7;i++){
            if(i > index || i < index){
                reportNewList.add(i);
            }
        }
        relatedNewsAdapter.setCurrentIndex(index);
        relatedNewsAdapter.notifyDataSetChanged();
    }

    public void setBackgroundResourcePicture(){
        switch (this.index){
            case 0:
                imageViewPicture.setBackgroundResource(R.drawable.news1);
                return;
            case 1:
                imageViewPicture.setBackgroundResource(R.drawable.news2);
                return;
            case 2:
                imageViewPicture.setBackgroundResource(R.drawable.news3);
                return;
            case 3:
                imageViewPicture.setBackgroundResource(R.drawable.news4);
                return;
            case 4:
                imageViewPicture.setBackgroundResource(R.drawable.news5);
                return;
            case 5:
                imageViewPicture.setBackgroundResource(R.drawable.news6);
                return;
            case 6:
                imageViewPicture.setBackgroundResource(R.drawable.news7);
                return;
            default:
                return;
        }
    }

}