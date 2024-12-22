package com.example.proiectdam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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

    private static final String jsonUrl = "https://www.jsonkeeper.com/b/LK4V";
    private ListView lvGames;
    private List<Game> games= new ArrayList<>();
    private ActivityResultLauncher<Intent> launcher;
    private int position;

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
        GameAdapter adapter = new GameAdapter(getApplicationContext(),R.layout.view_game,games,getLayoutInflater());
        lvGames.setAdapter(adapter);

        lvGames.setOnItemClickListener((adapterView, view, position,l)->{
            this.position = position;
            Intent intent = new Intent(getApplicationContext(), AddGameActivity.class);
            intent.putExtra("edit",games.get(position));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
            if(result.getData().hasExtra("gameFromIntent")){
               Intent intent = result.getData();
               Game game = (Game) intent.getSerializableExtra("gameFromIntent");
               if(game != null){
                   games.add(game);
               }

                GameAdapter adapter1 = (GameAdapter) lvGames.getAdapter();
                adapter1.notifyDataSetChanged();

               Toast.makeText(this, game.toString(), Toast.LENGTH_SHORT).show();
            }else if(result.getData().hasExtra("edit")){
                Intent intent = result.getData();
                Game editedGame = (Game) intent.getSerializableExtra("edit");

                if(editedGame != null){
                    Game game = games.get(position);
                    game.setId(editedGame.getId());
                    game.setDescription(editedGame.getDescription());
                    game.setGenre(editedGame.getGenre());
                    game.setTitle(editedGame.getTitle());

                    GameAdapter adapter1 = (GameAdapter) lvGames.getAdapter();
                    adapter1.notifyDataSetChanged();
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("local",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("exampleToken","This is my secret message");
        editor.apply();

    }

    private void getGames(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(jsonUrl);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(()->{
                    getJsonFromHttps(json);
                });
            }
        };
        thread.start();
    }

    private void getJsonFromHttps(String json){
        games.addAll(GameParser.parsareJson(json));
        GameAdapter adapter = (GameAdapter) lvGames.getAdapter();
        adapter.notifyDataSetChanged();
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
        } else if (id == R.id.action_saveToDB) {
            GameDB instance = GameDB.getInstance(getApplicationContext());
            for(Game game : games){
                if(!games.isEmpty()) {
                    instance.getGameDAO().deleteGame(game);
                    instance.getGameDAO().insertGame(game);
                }
            }
            Toast.makeText(this, String.valueOf(instance.getGameDAO().getCountGames()), Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.actiob_loadFromDB) {
            GameDB instance = GameDB.getInstance(getApplicationContext());
            games.clear();
            games.addAll(instance.getGameDAO().getAllGames());
            GameAdapter adapter = (GameAdapter) lvGames.getAdapter();
            adapter.notifyDataSetChanged();
            return true;

        } else if (id == R.id.action_getFromHttps) {
            getGames();
            return true;

        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}