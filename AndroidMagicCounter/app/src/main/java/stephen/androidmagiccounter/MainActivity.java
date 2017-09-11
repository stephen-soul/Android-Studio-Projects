package stephen.androidmagiccounter;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Call the buttons
    Button btnPlayer2;
    Button life20;
    Button life30;
    Button life40;
    Button begin;

    //Make a variable
    int lifeTotal;
    int numOfPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign the buttons
        btnPlayer2 = (Button) findViewById(R.id.ButtonPlayers2);
        life20 = (Button) findViewById(R.id.startingLifeIs20);
        life30 = (Button) findViewById(R.id.startingLifeIs30);
        life40 = (Button) findViewById(R.id.startingLifeIs40);
        begin = (Button) findViewById(R.id.begin);

        //Assign the variable to avoid nulls
        lifeTotal = 0;
        numOfPlayers = 0;

        //Set button color defaults
        btnPlayer2.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
        life20.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
        life30.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
        life40.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));

        //When button "numOfPlayers2" is clicked, highlight it.
        btnPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPlayer2.getBackground().setColorFilter(new LightingColorFilter(0x333333, 0xFFAA0000));
                numOfPlayers = 2;
            }//End of btnPlayer2 onClick
        });//End of numPlayers2 being clicked

        //When button for 20 life is clicked, highlight it, and unhighlight the other buttons in case
        life20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life30.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
                life40.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
                life20.getBackground().setColorFilter(new LightingColorFilter(0x333333, 0xFFAA0000));
                lifeTotal = 20;
            }//End of onClick
        });//End of button life20 being clicked

        life30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life30.getBackground().setColorFilter(new LightingColorFilter(0x333333, 0xFFAA0000));
                life40.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
                life20.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
                lifeTotal = 30;
            }//End of onClick
        });//End of button life30 being clicked

        life40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                life30.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
                life40.getBackground().setColorFilter(new LightingColorFilter(0x333333, 0xFFAA0000));
                life20.getBackground().setColorFilter(new LightingColorFilter(0x222222, 0x222222));
                lifeTotal = 40;
            }//End of onClick
        });//End of button life40 being clicked

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lifeTotal != 0){
                    if(numOfPlayers != 0){
                        begin.getBackground().setColorFilter(new LightingColorFilter(0x333333, 0xFFAA0000));
                        Intent intent = new Intent(MainActivity.this, CounterScreen.class);
                        intent.putExtra("lifeTotal", lifeTotal);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }//End of onCreate
}//End of MainActivity
