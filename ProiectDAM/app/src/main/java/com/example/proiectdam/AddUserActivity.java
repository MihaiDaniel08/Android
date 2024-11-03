package com.example.proiectdam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextUserId = findViewById(R.id.editTextUserId);
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        Button submitButton = findViewById(R.id.btnSubmitUsers);

        submitButton.setOnClickListener(v -> {
            String id = editTextUserId.getText().toString();
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            User user = new User(id, name, email, password);

            Intent intent = getIntent();
            intent.putExtra("userFromIntent", user);
            setResult(RESULT_OK,intent);
            finish();


        });
    }
}