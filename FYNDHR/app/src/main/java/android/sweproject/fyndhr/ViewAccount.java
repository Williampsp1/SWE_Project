package android.sweproject.fyndhr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewaccount_screen);

        Intent mIntent = getIntent();
        mIntent.getExtras();
        String User = mIntent.getStringExtra("Name");
        String Age = mIntent.getStringExtra("Age");
        String Sex = mIntent.getStringExtra("Sex");
/**
 * if key is "Username" then set the top TextView to the username.
 */
        if (mIntent.hasExtra("Name"))

        {

            TextView Name = findViewById(R.id.user_name);
            Name.setText(User);

            TextView birth = findViewById(R.id.user_age);
            birth.setText(Age);

            TextView Gender = findViewById(R.id.user_relationship);
            Gender.setText(Sex);

            TextView us = findViewById(R.id.Username);
            us.setText(User);
        }
    }
}
