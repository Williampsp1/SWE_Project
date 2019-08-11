package android.sweproject.fyndhr;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Homepage extends AppCompatActivity {
    DatabaseHelper myDb;
    private Button mCreateAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        myDb = new DatabaseHelper(this);
        mCreateAccount = findViewById(R.id.CreateAButton);

        Intent intent = getIntent();
        intent.getExtras();
        /**
         * If the account was successfully created, you are logged in and the Create Account button
         * has been disabled.
         */
        if (intent.hasExtra("Disable")) {
            mCreateAccount.setEnabled(false);
        }
    }

    /**
     * Shows your current account. This methods parameters (View view) will have to be changed to actually show the
     * proper name on the ViewAccount screen.
     * @param view
     */
    public void viewAccount(View view){
        Cursor res = myDb.getAllData();
        Intent rev = new Intent(this, ViewAccount.class);


        StringBuffer name = new StringBuffer();
        StringBuffer age = new StringBuffer();
        StringBuffer sex = new StringBuffer();
        while(res.moveToNext()){
           /* users.append(" "+ res.getString(0)+"\n");
            buffer.append("Password :"+ res.getString(1)+"\n");
           */
            name.append(" "+ res.getString(2));
            name.append(" "+ res.getString(3));

            sex.append(" "+ res.getString(4)+"\n");
            age.append(" "+ res.getString(5));

        }
        rev.putExtra("Name",name.toString());
        rev.putExtra("Age",age.toString());
        rev.putExtra("Sex",sex.toString());

        startActivity(rev);
    }

    /**
     * When you click on an account in the top 4 list it will take you to the ViewUser screen.
     * It uses Intents to transfer the name of the account clicked.
     * @param view
     */
    public void ClickAccount(View view) {
        TextView a = (TextView) view;
        String user = a.getText().toString();
        Intent rev = new Intent(this, ViewUser.class);
        rev.putExtra("Username",user);
        startActivity(rev);
    }

    /**
     * If you want to create a new Account, it will send you to to CreateAccount class.
     * @param view
     */
    public void CreateAccount(View view){

        Intent cre = new Intent(this, CreateAccount.class);
        startActivity(cre);
    }
}
