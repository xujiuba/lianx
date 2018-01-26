package com.bwei.gaozhixu20171221;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.bawei.wzq.wzlibrary.view.XListView;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by admin on 2017/12/21.
 */

public class MainActivitys extends AppCompatActivity {

    private XListView mXlv;
    private List<Bean.DataBean> list;
    private MyBsaeAdaper adaper;

    private String json_url = "http://120.27.23.105/product/getProductCatagory?cid=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitys_main);
        //
        initView();

    }

    private void initView() {
        mXlv = (XListView) findViewById(R.id.xlv);
        new AsyncTask<String, Integer, String>() {


            @Override
            protected String doInBackground(String... params) {
                Util u = new Util();
                String json = u.getjson(json_url);
                return json;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson = new Gson();
                list = gson.fromJson(s, Bean.class).getData();
                adaper = new MyBsaeAdaper(MainActivitys.this, list);
                mXlv.setAdapter(adaper);

            }
        }.execute();
        /**
         * 条目长按效果
         */
        mXlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adaper.notifyDataSetChanged();
            }
        });
        /**
         * 刷新
         */



    }
}
