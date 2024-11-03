package com.example.proiectdam;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
              GameAdapter adapter = new GameAdapter(getApplicationContext(),R.layout.view_game,games,getLayoutInflater());
               lvGames.setAdapter(adapter);
               Toast.makeText(this, game.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_add_review) {
            Intent intent = new Intent(ListViewGameActivity.this, ListViewReviewActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Review", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_users) {
            Intent intent = new Intent(ListViewGameActivity.this, ListViewUserActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Users", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_profile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}