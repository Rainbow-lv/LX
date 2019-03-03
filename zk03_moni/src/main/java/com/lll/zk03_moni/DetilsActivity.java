package com.lll.zk03_moni;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.lll.zk03_moni.bean.DetailsBean;
import com.lll.zk03_moni.bean.Result;
import com.lll.zk03_moni.core.DataCall;
import com.lll.zk03_moni.presenter.DetailsPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetilsActivity extends AppCompatActivity {


    @BindView(R.id.web_view)
    WebView webView;
    private int commodityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detils);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        commodityId = intent.getIntExtra("commodityId", 0);
        //创建对象
        DetailsPresenter detailsPresenter = new DetailsPresenter(new DetailsCall());
        detailsPresenter.reqeust(commodityId);
    }

     class DetailsCall implements DataCall<Result<DetailsBean>> {
        @Override
        public void success(Result<DetailsBean> result) {

            String details = result.getResult().getDetails();
            //网页请求
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            String js = "<script type=\"text/javascript\">" +
                    "var imgs=document.getElementsByTagName('img');" +
                    "for(var i = 0; i<imgs.length; i++){" +
                    "imgs[i].style.width='100%';" +
                    "imgs[i].style.height='auto';" +
                    "}" +
                    "</script>";
            webView.loadDataWithBaseURL(null,details + js + "<html></body>", "text/html", "utf-8", null);
          //  Toast.makeText(DetilsActivity.this, ""+result.getMessage(), Toast.LENGTH_SHORT).show();


        }

        @Override
        public void fail(Result result) {

        }
    }
}
