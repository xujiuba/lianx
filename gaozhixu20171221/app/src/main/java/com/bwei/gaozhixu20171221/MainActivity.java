package com.bwei.gaozhixu20171221;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    String json_url = "http://120.27.23.105/product/getProductDetail?pid=1&source=android";
    private ViewPager mViewpager;
    private RadioGroup mRadiogroup;
    int i;
    private List<String> imgs = new ArrayList<>();

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mViewpager.setCurrentItem(i);
            i++;
        }
    };
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //判断网络是否连接
        boolean conn=NewWork.isconn(this);
        if (!conn){
            NewWork.showNetwork(this);
        }
        //获取资源ID
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mRadiogroup = (RadioGroup) findViewById(R.id.radiogroup);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivitys.class);
                startActivity(intent);
            }
        });




        new AsyncTask<String,Integer,String>(){

            @Override
            protected String doInBackground(String... params) {
                String getjson = new Util().getjson(json_url);
                return getjson;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                String images = new Gson().fromJson(s, Ba.class).getData().getImages();
                String[] split = images.split("\\|");
                for (int q=0;q<split.length;q++){
                    imgs.add(images);
                }
                mViewpager.setAdapter(new Myviewpager());
            }
        }.execute();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        },0,1000);

        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position% imgs.size()){
                    case 0:
                        mRadiogroup.check(R.id.but1);
                        break;
                    case 1:
                        mRadiogroup.check(R.id.but2);
                        break;
                    case 2:
                        mRadiogroup.check(R.id.but3);
                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    class Myviewpager extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=new ImageView(MainActivity.this);
            ImageLoader.getInstance().displayImage(imgs.get(position%imgs.size()),imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

    }


}
