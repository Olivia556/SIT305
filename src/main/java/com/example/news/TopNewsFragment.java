package com.example.news;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class TopNewsFragment extends Fragment implements TopNewsAdapter.OnTopNewsItemClickListener, AllNewsAdapter.OnAllNewsItemClickListener{

    public RecyclerView rvTopNews;
    public RecyclerView rvAllNews;
    public ImageView imv_lastNew;
    public ImageView imv_nextNew;
    public TopNewsAdapter topNewsAdapter;
    public AllNewsAdapter allNewsAdapter;
    public LinearLayoutManager linearLayoutManager;
    public GridLayoutManager gridLayoutManager;

    public List<Integer> mData;
    public List<String> newsTitleList;
    public List<String> newsDetailList;

    public int currentID = 0;

    public int assist_position = 0;
    public TopNewsAdapter.OnTopNewsItemClickListener onTopNewsItemClickListener;
    public AllNewsAdapter.OnAllNewsItemClickListener AllNewsAdapterListener;

    public TopNewsFragment(TopNewsAdapter.OnTopNewsItemClickListener onTopNewsItemClickListener,AllNewsAdapter.OnAllNewsItemClickListener AllNewsAdapterListener){
        this.onTopNewsItemClickListener = onTopNewsItemClickListener;
        this.AllNewsAdapterListener = AllNewsAdapterListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_news, container, false);
        rvTopNews = view.findViewById(R.id.rv_topNews);
        rvAllNews = view.findViewById(R.id.rv_allNews);
        imv_lastNew = view.findViewById(R.id.imv_lastNews);
        imv_nextNew = view.findViewById(R.id.imv_nextNews);

        //start rvTopNews
        mData = new ArrayList<>();
        for(int i=0;i<7;i++){
            mData.add(i);
        }
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvTopNews.setLayoutManager(linearLayoutManager);
        topNewsAdapter = new TopNewsAdapter(mData);
        topNewsAdapter.addOnTopNewsItemClickListener(onTopNewsItemClickListener);
        rvTopNews.setAdapter(topNewsAdapter);

        rvTopNews.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                currentID = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            }
        });
        //end rvTopNews

        //start rvAllNews
        newsTitleList = new ArrayList<>();
        newsDetailList = new ArrayList<>();
        Resources res =getResources();
        String[] newsTitle =res.getStringArray(R.array.newsTitle);
        String[] newsDetail =res.getStringArray(R.array.newsDetail);
        for(int i=0;i<7;i++){
            String tempStr = "";
            if(newsTitle[i].length()>30){
                tempStr = newsTitle[i].substring(0,29);
                tempStr = tempStr + "...";
                newsTitleList.add(tempStr);
            }else{
                newsTitleList.add(newsTitle[i]);
            }

            if(newsDetail[i].length()>30){
                tempStr = newsDetail[i].substring(0,29);
                tempStr = tempStr + "...";
                newsDetailList.add(tempStr);
            }else{
                newsDetailList.add(newsDetail[i]);
            }

        }
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        rvAllNews.setLayoutManager(gridLayoutManager);
        allNewsAdapter = new AllNewsAdapter(newsTitleList,newsDetailList);
        allNewsAdapter.addOnAllNewsItemClickListener(AllNewsAdapterListener);
        rvAllNews.setAdapter(allNewsAdapter);
        //end rvAllNews


        // Set the previous button for the top horizontal news list
        //start imv_lastNew
        imv_lastNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentID>0){
                    ((LinearLayoutManager)rvTopNews.getLayoutManager()).scrollToPositionWithOffset(currentID-1,0);
                }
            }
        });
        //end imv_lastNew

        // Set the next button in the top horizontal news list
        //start imv_nextNew
        imv_nextNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentID<5){
                    ((LinearLayoutManager)rvTopNews.getLayoutManager()).scrollToPositionWithOffset(currentID+1,0);
                }
            }
        });
        //end imv_nextNew

        return view;

    }


    @Override
    public void onAllNewsItemClick(View view, int position) {
    }

    @Override
    public void onTopNewsItemClick(View view, int position) {
    }
}