package stephen.androidmagiccounter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CounterScreen extends AppCompatActivity {

    //Call the buttons
    TextView playerLife;
    TextView enemyLife;
    ImageButton magic;
    Button playerPlus1;
    Button playerPlus5;
    Button playerPlus10;
    Button playerMinus1;
    Button playerMinus5;
    Button playerMinus10;
    Button enemyPlus1;
    Button enemyPlus5;
    Button enemyPlus10;
    Button enemyMinus1;
    Button enemyMinus5;
    Button enemyMinus10;
    int playerValue;
    int enemyValue;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_screen);

        //Call the extras which brings the users choice of the life total to this activity
        Intent intent = getIntent();
        playerValue = intent.getIntExtra("lifeTotal", 0);
        enemyValue = intent.getIntExtra("lifeTotal", 0);

        //Add value to the variables
        playerLife = (TextView) findViewById(R.id.playerCounter);
        enemyLife = (TextView) findViewById(R.id.enemyCounter);
        magic = (ImageButton) findViewById(R.id.magicButton);
        playerPlus1 = (Button) findViewById(R.id.btnPlus1);
        playerPlus5 = (Button) findViewById(R.id.btnPlus5);
        playerPlus10 = (Button) findViewById(R.id.btnPlus10);
        playerMinus1 = (Button) findViewById(R.id.btnMinus1);
        playerMinus5 = (Button) findViewById(R.id.btnMinus5);
        playerMinus10 = (Button) findViewById(R.id.btnMinus10);
        enemyPlus1 = (Button) findViewById(R.id.btnEnemyPlus1);
        enemyPlus5 = (Button) findViewById(R.id.btnEnemyPlus5);
        enemyPlus10 = (Button) findViewById(R.id.btnEnemyPlus10);
        enemyMinus1 = (Button) findViewById(R.id.btnEnemyMinus1);
        enemyMinus5 = (Button) findViewById(R.id.btnEnemyMinus5);
        enemyMinus10 = (Button) findViewById(R.id.btnEnemyMinus10);

        //Set the life totals to what the user enters
        playerLife.setText(String.valueOf(playerValue));
        enemyLife.setText(String.valueOf(enemyValue));


        magic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RotateAnimation rotate = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);
                rotate.setInterpolator(new LinearInterpolator());
                magic.startAnimation(rotate);
                AlertDialog diaBox = AskOption();
                diaBox.show();
            }
        });

        playerPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerValue+=1;
                playerLife.setText(Integer.toString(playerValue));
            }
        });

        playerPlus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerValue+=5;
                playerLife.setText(Integer.toString(playerValue));
            }
        });

        playerPlus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerValue+=10;
                playerLife.setText(Integer.toString(playerValue));
            }
        });

        playerMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerValue-=1;
                playerLife.setText(Integer.toString(playerValue));
            }
        });

        playerMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerValue-=5;
                playerLife.setText(Integer.toString(playerValue));
            }
        });

        playerMinus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerValue-=10;
                playerLife.setText(Integer.toString(playerValue));
            }
        });

        enemyPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyValue+=1;
                enemyLife.setText(Integer.toString(enemyValue));
            }
        });

        enemyPlus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyValue+=5;
                enemyLife.setText(Integer.toString(enemyValue));
            }
        });

        enemyPlus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyValue+=10;
                enemyLife.setText(Integer.toString(enemyValue));
            }
        });

        enemyMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyValue-=1;
                enemyLife.setText(Integer.toString(enemyValue));
            }
        });

        enemyMinus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyValue-=5;
                enemyLife.setText(Integer.toString(enemyValue));
            }
        });

        enemyMinus10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enemyValue-=10;
                enemyLife.setText(Integer.toString(enemyValue));
            }
        });

    }//End of onCreate

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Return to main")
                .setMessage("Are you sure you wish to return to main?")
                .setIcon(R.drawable.magic)

                .setPositiveButton("Back to main", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Intent switchActivity = new Intent(CounterScreen.this, MainActivity.class);
                        startActivity(switchActivity);
                        finish();
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

}
