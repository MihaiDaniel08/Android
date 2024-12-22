package com.example.proiectdam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddGameActivity extends AppCompatActivity {

    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextID = findViewById(R.id.editTextId);
        EditText editTextTitle = findViewById(R.id.editTextTitle);
        EditText editTextDescription = findViewById(R.id.editTextDescription);
        EditText editTextGenre = findViewById(R.id.editTextGenre);

        Button submitButton = findViewById(R.id.btnSubmit);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")){
            isEditing = true;
            Game game = (Game) editIntent.getSerializableExtra("edit");

            editTextID.setText(game.getId());
            editTextTitle.setText(game.getTitle());
            editTextGenre.setText(game.getGenre());
            editTextDescription.setText(game.getDescription());
        }



        submitButton.setOnClickListener(v -> {
            String id = editTextID.getText().toString();
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();
            String genre = editTextGenre.getText().toString();

            Game game = new Game(id, title, description, genre);
            Intent intent = getIntent();

            if(isEditing){
                intent.putExtra("edit",game);
                isEditing=false;
            }
            else {
                intent.putExtra("gameFromIntent", game);
            }
            setResult(RESULT_OK,intent);
            finish();
        });


        SharedPreferences sharedPreferences = getSharedPreferences("local",MODE_PRIVATE);
        String token = sharedPreferences.getString("exampleToken","deff val");
        Toast.makeText(this,token,Toast.LENGTH_SHORT).show();
    }


}