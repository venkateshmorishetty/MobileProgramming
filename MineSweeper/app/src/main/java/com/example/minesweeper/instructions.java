package com.example.minesweeper;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class instructions extends AppCompatActivity {

    TextView  inp, title;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        title = findViewById(R.id.title);
        title.setText("\uD83C\uDF89 Happy Gaming!! \uD83C\uDF89");
        inp = findViewById(R.id.instructions);
        inp.setText("\u2022 If we click on the grid then the values of the grid is given it would be blue if the grid is safe. \n\n" +
                "\u2022 If the value clicked is bomb then the game would end and show the result in the next screen.\n \n" +
                "\u2022 You can again replay the game by clicking on the try again button. \n\n" +
                "\u2022 If the clicked grid is 1 then the adjacent blocks would definitely have a bomb. \n \n" +
                "\u2022 If the clicked grid is 0 then there are no bombs adjacent to that. \n \n" +
                "\u2022 The value shown on the grid is the indication of number of bombs that are present in the adjacent grids.\n \n" +
                "\u2022 The score is calculated for the number of safe moves the user selects. \n \n" +
                "\u2022 In the Easy mode there would be 10 bombs. \n \n" +
                "\u2022 In the Medium mode there would be 15 bombs. \n \n" +
                "\u2022 In the Hard mode there would be 20 bombs. \n \n"
                );


    }

}
