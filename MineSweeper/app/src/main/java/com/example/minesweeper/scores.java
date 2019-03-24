package com.example.minesweeper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import static com.example.minesweeper.R.id.playagain;

public class scores extends AppCompatActivity {
    String playername, level1,score,levelforDb, scoreforDb, nameforDb;
    Button playagain,exit, home;
    TextView sc, level, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);



        sc = findViewById(R.id.s);
        level = findViewById(R.id.level);
        name = findViewById(R.id.name);
        playagain = findViewById(R.id.playagain);
        exit = findViewById(R.id.exit);
        home = findViewById(R.id.home);


        Intent i= getIntent();



        if(i.hasExtra("name")){
            nameforDb = i.getStringExtra("name");
            playername = "Player name is  : ";
            playername += i.getStringExtra("name");
            System.out.println("gettinname"+playername);
//            playername += "Player name is : " + playername;
            name.setText(playername);
        }
        if(i.hasExtra("score")){
            scoreforDb = i.getStringExtra("score");

            score = i.getStringExtra("score");

            sc.setText(score);
        }
        if(i.hasExtra("level")) {


            levelforDb = i.getStringExtra("level");
            level1 = "level is : ";
            level1 += i.getStringExtra("level");
            level.setText(level1);
        }


        playagain.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(scores.this, PlayGame.class);
                i.putExtra("name", playername);
                startActivity(i);

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                insertingIntoDb();
                Intent i = new Intent(scores.this, MainActivity.class);
                i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("Exit",true);
                startActivity(i);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(scores.this, MainActivity.class);
                startActivity(i);

            }
        });



    }



}
