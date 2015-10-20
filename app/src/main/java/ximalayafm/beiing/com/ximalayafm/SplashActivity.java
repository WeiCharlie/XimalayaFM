package ximalayafm.beiing.com.ximalayafm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 启动扉页
 * 扉页中包含了广告图片，跳过，还有软件名称
 */
public class SplashActivity extends FragmentActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        try{
            Thread.sleep(2000);
        } catch (Exception e){
            e.printStackTrace();
        }
        Intent intent = new Intent();

        //TODO 如果没有显示欢迎页，那么启动欢迎页，否则直接启动首页

        SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);

        //利用 SP 字段，判断是否显示欢迎页，保存的数值一定是程序版本号
        //利用当前程序版本号，和sp中的版本号进行比较，从而判断，这样更精确，兼容性更好
        int wsv = sp.getInt(Constants.SP_KEY_WELCOME_SHOW_VER, -1);
        if(BuildConfig.VERSION_CODE != wsv){
            //TODO 显示欢迎页
            intent.setClass(this, WelcomeActivity.class);
        } else {
            //TODO 显示主界面
            intent.setClass(this, MainActivity.class);
        }

        startActivity(intent);

        finish();
    }



}



