package com.example.proiectdam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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

public class ListViewGameActivity extends AppCompatActivity {

    ListView lvGames;
    List<Game> games= new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

       fabAdd.setOnClickListener(v -> {
           Intent intent =new Intent(getApplicationContext(),AddGameActivity.class);
           launcher.launch(intent);
       });
        lvGames = findViewById(R.id.lvGames);

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
            if(result.getResultCode() == RESULT_OK){
               Intent intent = result.getData();
               Game game = (Game) intent.getSerializableExtra("gameFromIntent");
               if(game != null){
                   games.add(game);
               }
               ArrayAdapter<Game> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,games);
               lvGames.setAdapter(adapter);
               Toast.makeText(this, game.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}