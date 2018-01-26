package com.bwei.gaozhixu20171221;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;

/**
 * Created by admin on 2017/12/21.
 */

public class NewWork {

    public static boolean isconn(Context context){
        boolean bis=false;
        ConnectivityManager connManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = connManager.getActiveNetworkInfo();
        if (network==null){
            bis=connManager.getActiveNetworkInfo().isAvailable();
        }
        return bis;
    }

    public static void showNetwork(final Context context){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle(R.string.app_name)
                .setMessage("当前无网络").setPositiveButton("设置", new DialogInterface.OnClickListener() {



            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent =null;
                    if (Build.VERSION.SDK_INT>10){
                        intent = new Intent(Settings.ACTION_WEBVIEW_SETTINGS);
                    }else {
                        intent = new Intent();
                        intent.setClassName("com.android.settings","com.android.settings.WirelessSettings");
                    }
                context.startActivity(intent);
            }
        }).setNegativeButton("知道了",null).show();


    }
}
