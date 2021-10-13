package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean check_img = true;
    int[][] pole = new int[3][3];
    int paste = 0;
    int step = 0;
    int[] all_button = {R.id.IB00,
                        R.id.IB01,
                        R.id.IB02,
                        R.id.IB10,
                        R.id.IB11,
                        R.id.IB12,
                        R.id.IB20,
                        R.id.IB21,
                        R.id.IB22};
    //String string_check= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i =0; i< pole.length; i++){
            for(int j = 0; j<pole[i].length;j++){
                pole[j][i]=0;
            }

        }



    }

    public void restart(View tmp){
        for(int i =0; i< pole.length; i++){
            for(int j = 0; j<pole[i].length;j++){
                pole[j][i]=0;
            }

        }
        check_img = true;
        paste = 0;
        step = 0;
        for(int j=0;j<all_button.length;j++){
            View v = findViewById(all_button[j]);
            ImageButton image = (ImageButton) v;
            image.setEnabled(true);
            image.setImageResource(R.drawable.empty);
        }
    }

    public void check_win(){
        int[] result = new int[8];
        result[0]=pole[0][0]*pole[0][1]*pole[0][2];
        result[1]=pole[1][0]*pole[1][1]*pole[1][2];
        result[2]=pole[2][0]*pole[2][1]*pole[2][2];
        result[3]=pole[0][0]*pole[1][0]*pole[2][0];
        result[4]=pole[0][1]*pole[1][1]*pole[2][1];
        result[5]=pole[0][2]*pole[1][2]*pole[2][2];
        result[6]=pole[0][0]*pole[1][1]*pole[2][2];
        result[7]=pole[0][2]*pole[1][1]*pole[2][0];
        for (int i = 0; i<result.length;i++){
            if (result[i]==1){
                //Log.d("info",String.valueOf(result[i]));
                for(int j=0;j<all_button.length;j++){
                    View v = findViewById(all_button[j]);
                    ImageButton image = (ImageButton) v;
                    image.setEnabled(false);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Win");
                builder.setMessage("Cross Win!!!");
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            } else if(result[i]==8){
                //Log.d("info",String.valueOf(result[i]));
                for(int j=0;j<all_button.length;j++){
                    View v = findViewById(all_button[j]);
                    ImageButton image = (ImageButton) v;
                    image.setEnabled(false);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Win");
                builder.setMessage("Circle Win!!!");
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            } else if(step==9&&i==7){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("GG");
                builder.setMessage("Draw game");
                AlertDialog dialog = builder.create();
                dialog.show();
                break;
            }
        }

    }
    public void onCkick(View v) {
        step ++;
        ImageButton image = (ImageButton) v;
        int id = v.getId();

        if (check_img){
            paste = 1;
            check_img=false;
            image.setImageResource(R.drawable.cross);
        }
        else {
            paste = 2;
            check_img=true;
            image.setImageResource(R.drawable.circle_fix);
        }
        image.setEnabled(false);
        switch (id){
            case R.id.IB00:{
                pole[0][0]=paste;
                break;
            }
            case R.id.IB01:{
                pole[0][1]=paste;
                break;
            }
            case R.id.IB02:{
                pole[0][2]=paste;
                break;
            }
            case R.id.IB10:{
                pole[1][0]=paste;
                break;
            }
            case R.id.IB11:{
                pole[1][1]=paste;
                break;
            }
            case R.id.IB12:{
                pole[1][2]=paste;
                break;
            }
            case R.id.IB20:{
                pole[2][0]=paste;
                break;
            }
            case R.id.IB21:{
                pole[2][1]=paste;
                break;
            }
            case R.id.IB22:{
                pole[2][2]=paste;
                break;
            }

        }
        check_win();


    }
}