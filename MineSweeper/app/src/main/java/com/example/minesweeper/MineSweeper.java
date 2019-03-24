package com.example.minesweeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class MineSweeper extends AppCompatActivity {
    int numberofBombs;
    String level, player, scores;
    public TextView in, sc;
    Button exit;
    static boolean flag1 = true;
    GridView simpleGrid;
//    ArrayList<Integer> bombs = new ArrayList<>();

    LayoutInflater inflater;
    int[][] values = new int[9][9];
    int[][] bomb = new int[9][9];

    int[] value = new int[81];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper);
        in = findViewById(R.id.level);
        sc = findViewById(R.id.score);
        exit = findViewById(R.id.exit);


//        player.setText(n);

        Intent intent = getIntent();

            if(intent.hasExtra("name")){
                player = intent.getStringExtra("name");
            }

        if(intent.hasExtra("level")){
            level = intent.getStringExtra("level");
            System.out.println("level is "+level);
            if(level.equals("easy")){
                numberofBombs = 10;
            } else if(level.equals("medium")){
                numberofBombs = 15;
            }else {
                numberofBombs=20;
            }
            String temp1 = "Level ";
            String temp2 ="score is";
            in.setText(temp1+level+"");
            sc.setText(temp2+" 0"+"");
            Random r = new Random();
            for(int i= 0;i< numberofBombs;i++){
                System.out.println("number of bombs"+numberofBombs);
                int x = r.nextInt(9);
                int y = r.nextInt(9);
                if(bomb[x][y]==-1){
                    x = r.nextInt(9);
                    y = r.nextInt(9);
                }
                bomb[x][y] =-1;
                values[x][y] = -1;

            }

            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    if(bomb[i][j]!=-1){
                        values[i][j] = valueOf(i, j);
                    }
                }
            }

            //converting into 1 - D array

            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++) {
                    int pos = i*9+j;
                    value[pos] = values[i][j];
                }
            }


        }

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertingIntoDb();
                flag1 = GridViewAdapter.flag;

                if(GridViewAdapter.flag){
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(MineSweeper.this);
                    builder.setTitle("Exit!");

                    //Setting message manually and performing action on button click  
                    builder.setMessage("Do you really want to quit the game?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {




                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(MineSweeper.this, MainActivity.class);
                                    i.setFlags(i.FLAG_ACTIVITY_CLEAR_TOP);
                                    i.putExtra("Exit",true);

                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"MineSweeper!!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box  
                    AlertDialog alert = builder.create();

                    alert.show();

                }
                else{
                    GridViewAdapter.flag = true;
//                    flag1 = false;
                    Intent i = new Intent(MineSweeper.this, scores.class);

                    scores = sc.getText().toString();
                    System.out.println("sending"+scores);
                    i.putExtra("score",scores);
                    i.putExtra("name", player);
                    i.putExtra("level", level);
                    startActivity(i);
                }


            }
        });


        simpleGrid = (GridView) findViewById(R.id.simpleGridView);
        GridViewAdapter adapter = new GridViewAdapter(this,sc, value);
        simpleGrid.setAdapter(adapter);


    }
    public int valueOf(int row, int col){
        int value = 0;
        if(check(row-1,col-1)) value++;
        if(check(row-1,col)) value++;
        if(check(row-1,col+1)) value++;
        if(check(row,col-1)) value++;
        if(check(row,col+1)) value++;
        if(check(row+1,col-1)) value++;
        if(check(row+1,col)) value++;
        if(check(row+1,col+1)) value++;

        return value;

    }

    public boolean check(int row, int col){
        if(row>=0 && col>=0 && row<9 && col<9) {
            if(bomb[row][col]==-1){
                return true;
            }
        }
        return false;
    }


    public void insertingIntoDb() {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("name",player);
                data.put("score", scores);
                data.put("level", level);
                System.out.println("iam not here");
                RootRef.child("Players").child(level).child(player).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("inserted");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
