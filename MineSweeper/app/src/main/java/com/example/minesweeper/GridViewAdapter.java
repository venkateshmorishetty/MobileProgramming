package com.example.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    Context mContext;
//    ArrayList<Integer> bombs;
    int score = 0;
    TextView sc;

    Button[] buttons = new Button[81];
    static boolean flag = true;
    int[] values;

    public GridViewAdapter(Context m, TextView sc, int[] values){
        mContext = m;
        this.sc =sc;
        this.values = values;
    }
    @Override
    public int getCount() {
        return 81;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Button button;
        if(convertView==null){
            button = new Button(mContext);

            buttons[position] = button;

            button.setWidth(30);
            button.setHeight(30);
            button.setText("X");
            button.setId(position);
            button.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    int temp = button.getId();


                    if(values[temp] == -1){

                        flag = false;
                        Toast.makeText(mContext,"BOMB" , Toast.LENGTH_SHORT).show();
                       //to display all the bombs
                        for(int i=0;i<81;i++){
                            if(values[i]==-1){
                                buttons[i].setBackgroundColor(Color.RED);
                            }
                        }
                        //to disable all the buttons
                        for(int i=0;i<buttons.length;i++){
                            buttons[i].setEnabled(false);
                        }
                    }
                    else {

                        button.setBackgroundColor(Color.BLUE);
                        button.setEnabled(false);
                        button.setText(values[temp]+"");
                        button.setTextColor(Color.WHITE);

                        score++;
                        String tem="score is "+score;
                        sc.setText(tem);
                    }
                }
            });
        }else {
            button =(Button)convertView;

        }
        return button;
    }



    public boolean isenabled(int x, int y){
        int pos = x*9+y;

        System.out.println(" is clicked"+buttons[pos].isEnabled());

        if(buttons[pos].isEnabled()){

            return true;
        }
        else {

            return false;
        }
    }




}
