package ximalayafm.beiing.com.ximalayafm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class WelcomeActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        SharedPreferences sp = getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(Constants.SP_KEY_WELCOME_SHOW_VER, BuildConfig.VERSION_CODE);
        editor.apply();

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
