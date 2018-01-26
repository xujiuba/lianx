package com.bwei.gaozhixu20171221;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by admin on 2017/12/21.
 */

public class Util {

    private URL url;
    private HttpURLConnection coo;
    String str="";

    public String getjson(String json_url){

        try {
            url = new URL(json_url);
            coo = (HttpURLConnection) url.openConnection();
            coo.setConnectTimeout(5000);
            coo.setReadTimeout(5000);

            int responseCode = coo.getResponseCode();
            if (responseCode==200){
                InputStream in = coo.getInputStream();
                byte[] b=new byte[1024];
                int len=0;
                while ((len=in.read(b))!=-1){
                    str+=new String(b,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }
}
