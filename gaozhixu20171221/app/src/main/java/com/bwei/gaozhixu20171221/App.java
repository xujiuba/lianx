package com.bwei.gaozhixu20171221;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by admin on 2017/12/21.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(5*1024*1024)//内存缓冲大小
                .diskCacheSize(20*1024*1024)//sd卡缓冲大小
                .defaultDisplayImageOptions(options())
                .build();

        ImageLoader.getInstance().init(configuration);
    }

    private DisplayImageOptions options() {
        DisplayImageOptions option = new  DisplayImageOptions.Builder()
                .cacheInMemory(true)//缓冲到内存
                .cacheOnDisk(true)//缓冲到sd卡
                .build();
        return option;
    }

}
