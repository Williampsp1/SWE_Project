package android.sweproject.fyndhr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewuser_screen);
/**
 * Getting username and review(toast) strings.
 */
        Intent mIntent = getIntent();
        mIntent.getExtras();
        String User = mIntent.getStringExtra("Username");
        String thanks = mIntent.getStringExtra("Review_Complete");
/**
 * if the key is "Username" then it sets the top TextView to the name.
 * if the key is "Review_Complete then it sets the message to the toast.
 */
        if (mIntent.hasExtra("Username"))

        {

            TextView us = findViewById(R.id.Username);
            us.setText(User);
         }
        if(mIntent.hasExtra("Review_Complete"))
        {

            Toast.makeText(this, thanks, Toast.LENGTH_LONG).show();

        }
    }

    /**
     * Once pressed, it will use Intents to get the username and send it to the ReviewUser class and start
     * new Activity.
     * @param view
     */
    public void ReviewUser (View view) {

        Intent user = getIntent();
        user.getExtras();
        String User = user.getStringExtra("Username");


        Intent intent = new Intent(this, ReviewUser.class);
        intent.putExtra("Username", User);
        startActivity(intent);
    }



}
