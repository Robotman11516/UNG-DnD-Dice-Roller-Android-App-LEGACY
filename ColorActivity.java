package com.ung.dndDiceRollerApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;

public class ColorActivity extends AppCompatActivity {

    public static final String EXTRA_COLOR = "com.ung.dndDiceRollerApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        // Get the color ID from MainActivity
        Intent intent = getIntent();
        int colorId = intent.getIntExtra(EXTRA_COLOR, R.color.red_900);
    }

    public void onColorSelected(View view) {
        int colorId = R.color.red_900;
        if (view.getId() == R.id.user_green) {
            colorId = R.color.user_green;
        }
        else if (view.getId() == R.id.user_blue) {
            colorId = R.color.user_blue;
        }
        else if (view.getId() == R.id.user_purple) {
            colorId = R.color.user_purple;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_COLOR, colorId);
        setResult(RESULT_OK, intent);
        finish();
    }
}