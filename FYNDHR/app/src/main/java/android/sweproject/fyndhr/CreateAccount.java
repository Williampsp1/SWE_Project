package android.sweproject.fyndhr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    private EditText Username, password, birth, sex, lastname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_screen);

        Username = findViewById(R.id.editUsername);
        password = findViewById(R.id.editPassword);
        birth = findViewById(R.id.editBirthdate);
        sex = findViewById(R.id.editSex);
        lastname = findViewById(R.id.editLast);
    }


    /**
     * When you select create, it will check if the boolean is true, if it is then it will
     * use an intent with a key that will send you to the main screen (while disabling the Create Account button).
     * else it will display a Toast.
     * @param view
     */
    public void Create(View view){
        boolean validateOk = validate(new EditText[] {Username, password, birth, sex, lastname});

        if (validateOk) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Disable", 1);
            startActivity(intent);
        }
        else
            Toast.makeText(this,"Must not leave a field blank!", Toast.LENGTH_LONG).show();
    }

    /**
     * Validating that all the text fields have information in them
     * @param fields
     * @return boolean
     */
    private boolean validate(EditText[] fields){
        for(int i = 0; i < fields.length; i++){
            EditText currentField = fields[i];
            if(currentField.getText().toString().length() <= 0){
                return false;
            }
        }
        return true;
    }


}
