package com.example.minesweeper;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class player{

    public player(){

    }
    String level;
    String name;
    String score;
    public player(String n, String l, String s){
        level = l;
        name = n;
        score = s;
    }
}


public class HighscoresActivity extends AppCompatActivity {

    public TextView difficult;
    public TextView mediumpl;
    public TextView easy;
    ArrayList<player> easyplayers=new ArrayList<>();
    ArrayList<player> mediumplayers=new ArrayList<>();
    ArrayList<player> hardplayers=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        difficult = (TextView) findViewById(R.id.difficult);
        mediumpl = (TextView) findViewById(R.id.temp);
        easy = (TextView) findViewById(R.id.easy);

        gettingdataFromDb();

    }

    public void gettingdataFromDb(){

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child("Players").child("easy").exists()) {
                    for (DataSnapshot data : dataSnapshot.child("Players").child("easy").getChildren()) {
                        String pname = data.child("name").getValue().toString();
                        String level = data.child("level").getValue().toString();
                        String score = data.child("score").getValue().toString();
                        player p = new player(pname, level, score);
                        easyplayers.add(p);

                    }


                }


                if(dataSnapshot.child("Players").child("medium").exists()) {
                    for (DataSnapshot data : dataSnapshot.child("Players").child("medium").getChildren()) {
                        String pname = data.child("name").getValue().toString();
                        String level = data.child("level").getValue().toString();
                        String score = data.child("score").getValue().toString();
                        player p = new player(pname, level, score);
                        mediumplayers.add(p);

                    }

                }

                System.out.println("medium players"+mediumplayers.size());
                if(dataSnapshot.child("Players").child("hard").exists()) {
                    for (DataSnapshot data : dataSnapshot.child("Players").child("hard").getChildren()) {
                        String pname = data.child("name").getValue().toString();
                        String level = data.child("level").getValue().toString();
                        String score = data.child("score").getValue().toString();
                        player p = new player(pname, level, score);
                        hardplayers.add(p);

                    }

                }
                calling();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public void calling(){

        String toPrinteasy ="Level : Easy"+"\n\n";
        for(player p:easyplayers){
            toPrinteasy+=p.name+"\t\t"+p.level+"\t\t"+p.score+"\n";
        }
        easy.setText(toPrinteasy);

        String toPrintmedium ="Level : Medium"+"\n\n";
        System.out.println("medium====="+mediumplayers.size());

        for(player p:mediumplayers){

            toPrintmedium+=p.name;
            toPrintmedium+= p.level;
            toPrintmedium+= p.score;
            System.out.println(toPrintmedium);
            mediumpl.setText(toPrintmedium);
        }

//        mediumpl.setText(toPrintmedium);


        String toPrinthard ="Level : Hard"+"\n\n";
        for(player p:hardplayers){
            toPrinthard+=p.name+"\t\t"+p.level+"\t\t"+p.score+"\n";
        }
        difficult.setText(toPrinthard);
    }


}
