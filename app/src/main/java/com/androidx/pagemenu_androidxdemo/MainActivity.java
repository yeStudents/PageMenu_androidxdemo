package com.androidx.pagemenu_androidxdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidx.pagemenulayoutandroidx.IndicatorView;
import com.androidx.pagemenulayoutandroidx.PageMenuLayout;
import com.androidx.pagemenulayoutandroidx.ScreenUtil;
import com.androidx.pagemenulayoutandroidx.holder.AbstractHolder;
import com.androidx.pagemenulayoutandroidx.holder.PageMenuViewHolderCreator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ModelHomeEntrance> homeEntrances;
    IndicatorView entranceIndicatorView;
    PageMenuLayout<ModelHomeEntrance> mPageMenuLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //屏幕适配绑定，一定要写，否则加载不出来
        ScreenUtil.init(MainActivity.this);
        initView();
        //初始化数据
        initData();
        init();
    }
    private void initView() {
        entranceIndicatorView = findViewById(R.id.main_home_entrance_indicator);
        mPageMenuLayout = findViewById(R.id.pagemenu);
    }
    //初始化数据
    private void initData() {
        homeEntrances = new ArrayList<>();
        homeEntrances.add(new ModelHomeEntrance("美食", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("电影", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("酒店住宿", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("生活服务", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("KTV", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("旅游", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("学习培训", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("汽车服务", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("摄影写真", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("休闲娱乐", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("丽人", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("运动健身", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("大保健", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("团购", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("景点", R.mipmap.shouye1));
        homeEntrances.add(new ModelHomeEntrance("全部分类", R.mipmap.shouye1));
    }
    private void init() {
        mPageMenuLayout.setPageDatas(homeEntrances, new PageMenuViewHolderCreator() {
            @Override
            public AbstractHolder createHolder(View itemView) {
                return new AbstractHolder<ModelHomeEntrance>(itemView) {
                    private TextView entranceNameTextView;
                    private ImageView entranceIconImageView;

                    @Override
                    protected void initView(View itemView) {
                        entranceIconImageView = itemView.findViewById(R.id.entrance_image);
                        entranceNameTextView = itemView.findViewById(R.id.entrance_name);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) ((float) ScreenUtil.getScreenWidth() / 4.0f));
                        itemView.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void bindView(RecyclerView.ViewHolder holder, final ModelHomeEntrance data, int pos) {
                        entranceNameTextView.setText(data.getName());
                        entranceIconImageView.setImageResource(data.getImage());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                };
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_home_entrance;
            }
        });
        entranceIndicatorView.setIndicatorCount(mPageMenuLayout.getPageCount());
        mPageMenuLayout.setOnPageListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                entranceIndicatorView.setCurrentIndicator(position);
            }
        });
    }
}
