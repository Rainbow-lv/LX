package com.lll.zk03_moni;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.lll.zk03_moni.bean.GoodsBanner;
import com.lll.zk03_moni.bean.Result;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.fragment.Frag_01;
import com.lll.zk03_moni.fragment.Frag_02;
import com.lll.zk03_moni.fragment.Frag_03;
import com.lll.zk03_moni.presenter.BannerPresenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.radio_news)
    RadioButton radioNews;
    @BindView(R.id.radio_fin)
    RadioButton radioFin;
    @BindView(R.id.radio_lif)
    RadioButton radioLif;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Frag_01 frag_01;
    private Frag_02 frag_02;
    private Frag_03 frag_03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //获取管理者
        manager = getSupportFragmentManager();
        //开启事务
        transaction = manager.beginTransaction();
        //添加Fragment
        frag_01 = new Frag_01();
        frag_02 = new Frag_02();
        frag_03 = new Frag_03();
        transaction.add(R.id.fragment, frag_01).show(frag_01);
        transaction.add(R.id.fragment, frag_02).hide(frag_02);
        transaction.add(R.id.fragment, frag_03).hide(frag_03);
        //提交事务
        transaction.commit();
        //默认选中第一个
        radioGroup.check(radioGroup.getChildAt(0).getId());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.radio_news:
                        transaction1.show(frag_01).hide(frag_02).hide(frag_03);
                       break;
                    case R.id.radio_fin:
                        transaction1.show(frag_02).hide(frag_01).hide(frag_03);
                        break;
                    case R.id.radio_lif:
                        transaction1.show(frag_03).hide(frag_02).hide(frag_01);
                        break;
                }
                transaction1.commit();
            }
        });
        //创建Banner对象
        BannerPresenter bannerPresenter = new BannerPresenter(new BannCall());
        bannerPresenter.reqeust();
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.btn_home:
                        //首页

                        break;
                    case R.id.btn_zixun:
                        //资讯
                        Intent intent = new Intent(MainActivity.this,WebActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menui, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private class BannCall implements DataCall<Result<List<GoodsBanner>>> {
        @Override
        public void success(Result<List<GoodsBanner>> result) {
            List<String> list = new ArrayList<>();
            List<GoodsBanner> result1 = result.getResult();
            for (int i = 0; i < result1.size(); i++) {
                list.add(result1.get(i).getImageUrl());
            }
            banner.setImages(list);
            banner.setImageLoader(new MyBanner());
            banner.start();
        }

        @Override
        public void fail(Result result) {

        }
    }

    private class MyBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Uri parse = Uri.parse((String) path);
            imageView.setImageURI(parse);
        }

        @Override
        public ImageView createImageView(Context context) {
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) View.inflate(MainActivity.this, R.layout.banner_item, null);
            return simpleDraweeView;
        }
    }
}
