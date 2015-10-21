package ximalayafm.beiing.com.ximalayafm.utils;


import android.os.Build;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * HttpUtils 请求工具类
 */

public final class HttpTools {

    // 手机型号等信息
    public static final String USER_AGNET = "ting_4.1.7(" + Build.MODEL + "," + Build.VERSION.SDK_INT + ")";

    private HttpTools(){

    }
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int READ_TIMEOUT = 30000;

    /**
     *
     * @param url 请求网址
     * @return 返回结果集
     */
    public  static byte[] doGet(String url){
        byte[] ret = null;
        if (url != null) {
            HttpURLConnection conn = null;
            try {
                URL u = new URL(url);
                 conn = (HttpURLConnection)u.openConnection();

                // 设置连接的配置
                conn.setConnectTimeout(CONNECT_TIMEOUT);
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setRequestMethod("GET");

                // 完善Http请求的内容
                // 1 设置通用的Http头信息
                // 设置Accept头信息，告诉服务器，客户端能够接收的数据类型
                conn.setRequestProperty("Accept","application/*,text/*,image/*,*/*");
                // 设置接收的内容压缩编码算法
                conn.setRequestProperty("Accept-Encoding","gzip");

                // 设置User-Agent
                conn.setRequestProperty("User-Agent", USER_AGNET);
                conn.setDoOutput(true);
                conn.connect();

                int code = conn.getResponseCode();
                if (code==200){
                    // TODO 给ret赋值

                        InputStream fis = null;
                        try {
                             fis = conn.getInputStream();
                            //  TODO 进行网络输入流的GZIP解压缩
                            String encoding = conn.getHeaderField("Content-Encoding");
//                            String encoding = conn.getContentEncoding();

                            if ("gzip".equals(encoding)){
                                fis = new GZIPInputStream(fis);
                            }
                            ret = StreamUtils.readStream(fis);

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                           StreamUtils.close(fis);
                        }
                    }


            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                StreamUtils.close(conn);
            }
        }
        return ret;
    }
}
