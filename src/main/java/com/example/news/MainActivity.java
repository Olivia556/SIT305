package com.example.news;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.content.Context;


public class MainActivity extends AppCompatActivity {

    public Context context;
    public FrameLayout frameLayout;
    public FragmentManager fragmentManager;
    public String[] newsTitle;
    public String[] newsDetail;
    public FragmentTransaction fragmentTransaction;

    public TopNewsFragment topNewsFragment;
    public DetailNewsFragment detailNewsFragment;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));//设置状态栏颜色
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//实现状态栏图标和文字颜色为暗色
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        Resources res =getResources();
        newsTitle =res.getStringArray(R.array.newsTitle);
        newsDetail =res.getStringArray(R.array.newsDetail);

        context = MainActivity.this;
        fragmentManager = getSupportFragmentManager();

        topNewsFragment = new TopNewsFragment(new TopNewsAdapter.OnTopNewsItemClickListener() {
            @Override
            public void onTopNewsItemClick(View view, int position) {
                Log.e("TAG", "onTopNewsItemClick: "+newsTitle[position]);
//                DetailNewsFragment detailNewsFragment = new DetailNewsFragment(newsTitle[position],newsDetail[position],position);
//                fragmentTransaction.replace(R.id.fl_content,detailNewsFragment);
//                fragmentTransaction.commit();
                detailNewsFragment = new DetailNewsFragment();
                detailNewsFragment.setNewsTitle(newsTitle[position]);
                detailNewsFragment.setNewsDetail(newsDetail[position]);
                detailNewsFragment.setIndex(position);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fl_content,detailNewsFragment);
                fragmentTransaction.hide(topNewsFragment);
                fragmentTransaction.show(detailNewsFragment);
                fragmentTransaction.commit();
            }
        }, new AllNewsAdapter.OnAllNewsItemClickListener() {
            @Override
            public void onAllNewsItemClick(View view, int position) {
                Log.e("TAG", "onTopNewsItemClick: "+newsDetail[position]);
            }
        });
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_content,topNewsFragment);
        fragmentTransaction.commit();
    }


}