package com.example.proiectdam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListViewUserActivity extends AppCompatActivity {


    ListView lvUsers;
    List<User> users= new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fabAddUsers = findViewById(R.id.fabAddUsers);

        fabAddUsers.setOnClickListener(v -> {
            Intent intent =new Intent(getApplicationContext(),AddUserActivity.class);
            launcher.launch(intent);
        });
        lvUsers = findViewById(R.id.lvUsers);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
            if(result.getResultCode() == RESULT_OK){
                Intent intent = result.getData();
                User user = (User) intent.getSerializableExtra("userFromIntent");
                if(user != null){
                    users.add(user);
                }
                UserAdapter adapter = new UserAdapter(getApplicationContext(),R.layout.view_user,users,getLayoutInflater());
                lvUsers.setAdapter(adapter);
                Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}