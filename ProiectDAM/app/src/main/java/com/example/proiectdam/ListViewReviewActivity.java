package com.example.proiectdam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListViewReviewActivity extends AppCompatActivity {
    ListView lvReviews;
    List<Review> reviews= new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fabAdd = findViewById(R.id.fabAddReview);

        fabAdd.setOnClickListener(v -> {
            Intent intent =new Intent(getApplicationContext(),AddReviewActivity.class);
            launcher.launch(intent);
        });
        lvReviews = findViewById(R.id.lvReviews);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                Review review = (Review) intent.getSerializableExtra("reviewFromIntent");
                if(review != null){
                    reviews.add(review);
                }
                ReviewAdapter adapter = new ReviewAdapter(getApplicationContext(),R.layout.view_review,reviews,getLayoutInflater());
                lvReviews.setAdapter(adapter);
                Toast.makeText(this, review.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}