package com.example.proiectdam;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnToListViewActivity = findViewById(R.id.btnToLvGames);

        btnToListViewActivity.setOnClickListener(v -> {
            Intent intent = new Intent(AddReviewActivity.this,ListViewGameActivity.class);
            startActivity(intent);
        });

        EditText editTextUserId = findViewById(R.id.editTextUserId);
        EditText editTextGameId = findViewById(R.id.editTextGameId);
        EditText editTextContent = findViewById(R.id.editTextContent);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
//        EditText editTextRating = findViewById(R.id.editTextRating);

        Button submitButton = findViewById(R.id.btnSubmitReview);

        submitButton.setOnClickListener(v -> {
            String userId = editTextUserId.getText().toString();
            String gameId = editTextGameId.getText().toString();
            String content = editTextContent.getText().toString();
            float rating = ratingBar.getRating();

            Review review = new Review(userId,gameId,content,rating);

            Intent intent = getIntent();
            intent.putExtra("reviewFromIntent", review);
            setResult(RESULT_OK,intent);
            finish();



        });
    }
}