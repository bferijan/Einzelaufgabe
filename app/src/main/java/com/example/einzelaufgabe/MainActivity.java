package com.example.einzelaufgabe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_absenden);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NetworkThread thread = new NetworkThread();

                EditText matrikelnr =  findViewById(R.id.mtr_input); //EditText ist Textfeld wo Matr. drinnen steht
                thread.mtr = matrikelnr.getText().toString();

                String serverResponse = null;
                try {
                    serverResponse = (String) thread.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                TextView response = findViewById(R.id.s_response);
                response.setText(serverResponse);

            }
        });

        Button button_berechnen = findViewById(R.id.button_berechnen);
        button_berechnen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText matrikelnr =  findViewById(R.id.mtr_input);
                String mtr = matrikelnr.getText().toString();

                char digit[] = new char[mtr.length()];
                mtr.getChars(0, mtr.length(), digit, 0);
                Arrays.sort(digit);

                boolean prime;
                int i = 0;
                int j = 2;
                String out =    "";
                for (i = 0; i<digit.length; i++){
                    int value = Character.getNumericValue(digit[i]);
                    prime = true;//Wenn immer ein Rest ist, ist es eine Primzahl. In allen anderen Fällen nicht.
                    for(j = 2; j < value; j++){
                        if (value % j == 0){
                            prime = false;
                        }
                    }
                 if (prime == false)   {
                     out = out+ " " + value;
                 }
                }
                TextView output = findViewById(R.id.s_response);
                output.setText(out);


            }
        });



    }


}
