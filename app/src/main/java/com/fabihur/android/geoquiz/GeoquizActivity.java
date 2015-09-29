package com.fabihur.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class GeoquizActivity extends AppCompatActivity {

    private static final String TAG = GeoquizActivity.class.getSimpleName();
    private static final String KEY_INDEX = "index";

    private Button trueButton;
    private Button falseButton;
    private ImageButton prevButton;
    private ImageButton nextButton;
    private Button cheatButton;
    private TextView questionTextView;

    private Question[] questions = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_geoquiz);

        questionTextView = (TextView)findViewById(R.id.question_text_view);
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        trueButton = (Button)findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton = (Button)findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        prevButton = (ImageButton)findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevQuestion();
            }
        });

        nextButton = (ImageButton)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        cheatButton = (Button)findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cheatIntent = new Intent(GeoquizActivity.this, CheatActivity.class);
                cheatIntent.putExtra(Intent.EXTRA_TEXT, questions[currentIndex].getQuestionAnswer());
                startActivity(cheatIntent);
            }
        });

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState() called");
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prevQuestion(){
        currentIndex = currentIndex - 1;
        if(currentIndex < 0){
            currentIndex = questions.length - 1;
        }
        updateQuestion();
    }

    private void nextQuestion(){
        currentIndex = (currentIndex + 1) % questions.length;
        updateQuestion();
    }

    private void updateQuestion(){
        questionTextView.setText(questions[currentIndex].getQuestionText());
    }

    private void checkAnswer(Boolean userAnswer){
        Question q = questions[currentIndex];
        int messageResId = (userAnswer == q.getQuestionAnswer())?
                                    R.string.correct_toast : R.string.incorrect_toast;

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    public Button getTrueButton() {
        return trueButton;
    }

    public void setTrueButton(Button trueButton) {
        this.trueButton = trueButton;
    }

    public Button getFalseButton() {
        return falseButton;
    }

    public void setFalseButton(Button falseButton) {
        this.falseButton = falseButton;
    }

    public ImageButton getNextButton() {
        return nextButton;
    }

    public void setNextButton(ImageButton nextButton) {
        this.nextButton = nextButton;
    }

    public TextView getQuestionTextView() {
        return questionTextView;
    }

    public void setQuestionTextView(TextView questionTextView) {
        this.questionTextView = questionTextView;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
