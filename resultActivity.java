package com.example.generictestapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class resultActivity extends AppCompatActivity {

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = findViewById(R.id.resultLabel);
        TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);

        SharedPreferences settings = getSharedPreferences("Generic Test Application", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);
        totalScore += score;

        resultLabel.setText(score + " / 40");
        totalScoreLabel.setText("Total score: " + totalScore);

        // Update total score
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore", totalScore);
        editor.commit();
    }

    public void returnTop(View view) {

        Intent intent = new Intent(getApplicationContext(), startActivity.class);
        startActivity(intent);
    }

}
