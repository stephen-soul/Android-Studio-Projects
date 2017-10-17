package com.example.stephen.inquisitiveinquiry;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Include the text view
    TextView Welcome;

    //Include plaintext
    EditText userName;

    //Include Button
    Button startQuizButton;

    //Include the typefaces (Fonts)
    Typeface tf1, tf2, tf3;

    //Create a text watcher - this will be used to check when text is entered into name field
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkNameFieldForEmptyValues();
        }
    };

        //On create do this
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //Find the views by ID
            Welcome = (TextView) findViewById(R.id.Welcome);
            userName = (EditText) findViewById(R.id.userName);
            startQuizButton = (Button) findViewById(R.id.startQuizButton);

            //On create set the button to hidden
            startQuizButton.setEnabled(false);

            //Add the typefaces
            tf1 = Typeface.createFromAsset(getAssets(), "FiraCode-Retina.ttf");
            tf2 = Typeface.createFromAsset(getAssets(), "GoldenPlains.ttf");
            tf3 = Typeface.createFromAsset(getAssets(), "Futura.ttf");

            //Set listeners to the name
            userName.addTextChangedListener(mTextWatcher);

            //Set the userName to have a whitespace at the beginning to avoid errors

            //Set the TextView "Welcome" to typeface3(Futura.ttf)
            Welcome.setTypeface(tf3);

            //Set the edit text for the name to typeface2(GoldenPlaints.ttf)
            userName.setTypeface(tf2);

            //Check the name field for empty values
            checkNameFieldForEmptyValues();

            //Set an on click listener for the start quiz button
            startQuizButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, QuizSection.class);
                    String input = userName.getText().toString();
                    intent.putExtra("input", input);
                    startActivity(intent);
                }
            });

        }//End of onCreate

    //Function to check to see if the name field is empty
    void checkNameFieldForEmptyValues(){
        if(!userName.getText().toString().equals(""))
            startQuizButton.setEnabled(true);
        else
            startQuizButton.setEnabled(false);
    }//End of the obnoxiously named checkNameFieldForEmptyValues

}//End of MainActivity
