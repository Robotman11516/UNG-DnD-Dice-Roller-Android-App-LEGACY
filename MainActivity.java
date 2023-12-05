package com.ung.dndDiceRollerApp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity {

    public static final int MAX_DICE = 6;
    private int mVisibleDice;
    private Dice[] mDice;
    private ImageView[] mDiceImageViews;
    private TextView[] mDiceTextViews;

    private int mLightOnColorId;
    private CountDownTimer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLightOnColorId = R.color.red_900;

        // Create an array of Dice
        mDice = new Dice[MAX_DICE];
        mDice[0] = new Dice(R.drawable.d4);
        mDice[1] = new Dice(R.drawable.d6);
        mDice[2] = new Dice(R.drawable.d8);
        mDice[3] = new Dice(R.drawable.d10);
        mDice[4] = new Dice(R.drawable.d12);
        mDice[5] = new Dice(R.drawable.d20);

        // Create an array of ImageViews
        mDiceImageViews = new ImageView[MAX_DICE];
        mDiceImageViews[0] = findViewById(R.id.d4Image);
        mDiceImageViews[1] = findViewById(R.id.d6Image);
        mDiceImageViews[2] = findViewById(R.id.d8Image);
        mDiceImageViews[3] = findViewById(R.id.d10Image);
        mDiceImageViews[4] = findViewById(R.id.d12Image);
        mDiceImageViews[5] = findViewById(R.id.d20Image);

        //Array of TextViews
        mDiceTextViews = new TextView[MAX_DICE];
        mDiceTextViews[0] = findViewById(R.id.d4Text);
        mDiceTextViews[1] = findViewById(R.id.d6Text);
        mDiceTextViews[2] = findViewById(R.id.d8Text);
        mDiceTextViews[3] = findViewById(R.id.d10Text);
        mDiceTextViews[4] = findViewById(R.id.d12Text);
        mDiceTextViews[5] = findViewById(R.id.d20Text);



        // All dice are initially visible
        mVisibleDice = MAX_DICE;

        showDice();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void showDice() {
        // Display only the number of dice visible
        for (int i = 0; i < 6; i++) {
            if (mDice[i].visabiliy) {
                Drawable diceDrawable = ContextCompat.getDrawable(this, mDice[i].getImageId());
                mDiceImageViews[i].setImageDrawable(diceDrawable);
                //mDiceImageViews[i].setContentDescription(Integer.toString(mDice[i].getNumber()));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        // Determine which menu option was chosen
        if (item.getItemId() == R.id.action_d4) {
            changeDiceVisibility(0);
            showDice();
            return true;
        }
        else if (item.getItemId() == R.id.action_d6) {
            changeDiceVisibility(1);
            showDice();
            return true;
        }
        else if (item.getItemId() == R.id.action_d8) {
            changeDiceVisibility(2);
            showDice();
            return true;
        }
        else if (item.getItemId() == R.id.action_d10) {
            changeDiceVisibility(3);
            showDice();
            return true;
        }
        else if (item.getItemId() == R.id.action_d12) {
            changeDiceVisibility(4);
            showDice();
            return true;
        }
        else if (item.getItemId() == R.id.action_d20) {
            changeDiceVisibility(5);
            showDice();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void changeDiceVisibility(int die) {
        mVisibleDice = die;

        // Make dice visible
        if (mDice[die].visabiliy) {
            mDiceImageViews[die].setVisibility(View.GONE);
            mDiceTextViews[die].setVisibility(View.GONE);
            mDice[die].visabiliy = false;
        }
        else{
            mDiceImageViews[die].setVisibility(View.VISIBLE);
            mDiceTextViews[die].setVisibility(View.VISIBLE);
            mDice[die].visabiliy = true;
        }
    }

    public void onChangeColorClick(View view) {
        Intent intent = new Intent(this, ColorActivity.class);
        intent.putExtra(ColorActivity.EXTRA_COLOR, mLightOnColorId);
        mColorResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> mColorResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            mLightOnColorId = data.getIntExtra(ColorActivity.EXTRA_COLOR, R.color.red_900);
                            int mLightOnColor = ContextCompat.getColor(MainActivity.this, mLightOnColorId);
                            //setButtonColors();
                        }
                    }
                }

                /*private void setButtonColors() {
                }*/
            });



}

