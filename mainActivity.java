package com.example.generictestapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IllegalFormatCodePointException;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button ansBtn1;
    private Button ansBtn2;
    private Button ansBtn3;
    private Button ansBtn4;
    private Button ansBtn5;
    private Button ansBtn6;
    private Button ansBtn7;
    private Button ansBtn8;
    private Button ansBtn9;
    private Button ansBtn10;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 40;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        ansBtn1 = findViewById(R.id.ansBtn1);
        ansBtn2 = findViewById(R.id.ansBtn2);
        ansBtn3 = findViewById(R.id.ansBtn3);
        ansBtn4 = findViewById(R.id.ansBtn4);
        ansBtn5 = findViewById(R.id.ansBtn5);
        ansBtn6 = findViewById(R.id.ansBtn6);
        ansBtn7 = findViewById(R.id.ansBtn7);
        ansBtn8 = findViewById(R.id.ansBtn8);
        ansBtn9 = findViewById(R.id.ansBtn9);
        ansBtn10 = findViewById(R.id.ansBtn10);

        // Receive quizCategory from startActivity
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        String[][] quizData = {readTextFile(quizCategory)};

        Log.v("CATEGORY_TAG", quizCategory + "");

        // Create quizArray from quizData
        for (int i = 0; i < quizData.length; i++) {

            // Prepare array
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // quizCategory
            tmpArray.add(quizData[i][1]); // Question
            tmpArray.add(quizData[i][2]); // Correct Answer
            tmpArray.add(quizData[i][3]); // Choice 2
            tmpArray.add(quizData[i][4]); // Choice 3
            tmpArray.add(quizData[i][5]); // Choice 4
            tmpArray.add(quizData[i][6]); // Choice 5
            tmpArray.add(quizData[i][7]); // Choice 6
            tmpArray.add(quizData[i][8]); // Choice 7
            tmpArray.add(quizData[i][9]); // Choice 8
            tmpArray.add(quizData[i][10]); // Choice 9
            tmpArray.add(quizData[i][11]); // Choice 10

            // Add tmpArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        // Update quizCountLabel
        countLabel.setText("Question" + quizCount);

        // Generate random number between 0 and quiz size
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer
        // Array format {"quizCategory", "Question"; "right answer", "Choice 1", "Choice 2", "Choice 3"}
        int arrayCategory = Integer.parseInt(quiz.get(0));
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);


        if (arrayCategory == quizCategory) {
            questionLabel.setText(quiz.get(1));
            rightAnswer = quiz.get(2);

            // Remove "arrayCategory" and 'question' from array and shuffle
            quiz.remove(1);
            quiz.remove(0);
            Collections.shuffle(quiz);

            // Set Choices
            ansBtn1.setText(quiz.get(0));
            ansBtn2.setText(quiz.get(1));
            ansBtn3.setText(quiz.get(2));
            ansBtn4.setText(quiz.get(3));
            ansBtn5.setText(quiz.get(4));
            ansBtn6.setText(quiz.get(5));
            ansBtn7.setText(quiz.get(6));
            ansBtn8.setText(quiz.get(7));
            ansBtn9.setText(quiz.get(8));
            ansBtn10.setText(quiz.get(9));

            // Remove this quiz from quizArray
            quizArray.remove(randomNum);
        } else {
            quizArray.remove(randomNum);
            showNextQuiz();
        }
    }

    public void checkAnswer(View view) {

        // Get pushed button
        Button ansBtn = findViewById(view.getId());
        String btnText = ansBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct!
            alertTitle = "Correct!";
            rightAnswerCount++;
        } else {
            // Wrong...
            alertTitle = "Wrong...";
        }

        // Create dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer: " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // Show result
                    Intent intent = new Intent(getApplicationContext(), resultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }

            }
        });
        builder.setCancelable(false);
        builder.show();

    }

    public String[] readTextFile(int quizCategory) {
        String fileName;

        switch (quizCategory) {
            case 1:
                fileName = "1_ITILv3Foundation.txt";
                break;
            case 2:
                fileName = "2_AZ-900.txt";
                break;
            case 3:
                fileName = "3_MS-100.txt";
                break;
            case 4:
                fileName = "4_MCSA 70-740.txt";
                break;
            case 5:
                fileName = "5_MCSA 70-483.txt";
                break;
            case 6:
                fileName = "6_MCSA 70-486.txt";
                break;
            case 7:
                fileName = "7_MCSA 70-480.txt";
                break;
            case 8:
                fileName = "8_MCSA 70-741.txt";
                break;
            case 9:
                fileName = "9_MCSA 70-742.txt";
                break;
            case 10:
                fileName = "10_MD-100.txt";
                break;
            case 11:
                fileName = "11_ITILv4Foundation.txt";
                break;
            case 12:
                fileName = "12_PRINCE2FOUNDATION.txt";
                break;
            case 13:
                fileName = "13_MCSA 70-778.txt";
                break;
            case 14:
                fileName = "4_MCSA 70-779.txt";
                break;
            default:
                fileName = "null";
                break;

        }

        String text = "";

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, 1);
        getContentResolver().openFileDescriptor(intent.getData().);

        return lines;
    }
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
