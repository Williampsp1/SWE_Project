package android.sweproject.fyndhr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewaccount_screen);

        Intent mIntent = getIntent();
        mIntent.getExtras();
        String User = mIntent.getStringExtra("Username");
/**
 * if key is "Username" then set the top TextView to the username.
 */
        if (mIntent.hasExtra("Username"))

        {

            TextView us = findViewById(R.id.Username);
            us.setText(User);
        }
    }
}
