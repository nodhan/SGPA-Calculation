package com.nodhan.sgpa;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText sub1, sub2, sub3;
    TextView result;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sub1 = (EditText) findViewById(R.id.sub1);
        sub2 = (EditText) findViewById(R.id.sub2);
        sub3 = (EditText) findViewById(R.id.sub3);
        result = (TextView) findViewById(R.id.result);

        calculate = (Button) findViewById(R.id.button);
        /***
         * Invoked when calculate button is clicked
         */
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float one = 0, two = 0, three = 0, sum = 0;
                boolean flag = true;
                result.setVisibility(View.INVISIBLE); //Setting the TextView to invisible
                try {
                    one = Float.parseFloat(sub1.getText().toString());
                } catch (NumberFormatException nfe) {
                    sub1.setError("This field is required.");
                    flag = false;
                }
                try {
                    two = Float.parseFloat(sub2.getText().toString());
                } catch (NumberFormatException nfe) {
                    sub2.setError("This field is required.");
                    flag = false;
                }
                try {
                    three = Float.parseFloat(sub3.getText().toString());
                } catch (NumberFormatException nfe) {
                    sub3.setError("This field is required.");
                    flag = false;
                }

                /***
                 * if flag is true then SGPA calculation occurs
                 */
                if (flag) {
                    Log.d("SGPAAPP", "ONE " + one + " TWO " + two + " THREE " + three);
                    sum = one + two + three;
                    int sgpa = Math.round(sum / 3);

                    Log.d("SGPAAPP", "SUM " + sum + " SGPA " + sgpa);
                    int color = (sgpa > 60) ? Color.GREEN : Color.RED;
                    result.setTextColor(color); //Setting the TextView's color
                    result.setText("SGPA : " + sgpa); //Setting the TextView's text
                    result.setVisibility(View.VISIBLE); //Setting the TextView to visible

                    LayoutInflater inflater = getLayoutInflater(); //Getting the LayoutInflater
                    View layout = inflater.inflate(R.layout.custom_toast, //Reference to the layout file
                            (ViewGroup) findViewById(R.id.toast_layout_root) //Finding the root element in the referenced layout
                    );

                    //Creation and customisation of Toast
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                }
            }
        });
    }
}
