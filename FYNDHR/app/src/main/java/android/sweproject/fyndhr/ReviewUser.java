package android.sweproject.fyndhr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_user);


    }

    /**
     * Submits the review to the database.
     * Uses Intents to get the username from ViewUser and send it back when done reviewing (So it can show
     * on the top textView again) and to send a message that will go to a Toast in ViewUser.
     * @param view
     */
    public void Submit(View view){

        Intent mIntent = getIntent();
        mIntent.getExtras();

        String User = mIntent.getStringExtra("Username");


    Intent sub = new Intent(this, ViewUser.class);
        String thanks = "Thank you for the Review";
        sub.putExtra("Review_Complete", thanks);
        sub.putExtra("Username", User);

        startActivity(sub);


    }

}
