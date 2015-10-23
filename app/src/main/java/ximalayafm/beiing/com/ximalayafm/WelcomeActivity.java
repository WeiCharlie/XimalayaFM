package ximalayafm.beiing.com.ximalayafm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * 欢迎页面，当第一次安装或者更新之后需要显示
 */
public class WelcomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.SP_KEY_WELCOME_SHOW_VER, BuildConfig.VERSION_CODE);
        editor.apply();  // 更推荐用apply

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class) ;

        startActivity(intent);

        super.onBackPressed();
    }

    public void btnJumpToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class) ;

        startActivity(intent);
        finish();

    }
}
