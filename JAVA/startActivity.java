package com.example.generictestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class startActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void startQuiz(View view) {

        int quizCategory = 0; // All

        switch (view.getId()) {
            case R.id.ITILv3:
                quizCategory = 1;
                break;
            case R.id.AZ900:
                quizCategory = 2;
                break;
            case R.id.MS100:
                quizCategory = 3;
                break;
            case R.id.MCSA70740:
                quizCategory = 4;
                break;
        }

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("QUIZ_CATEGORY", quizCategory);
        startActivity(intent);
    }
}


