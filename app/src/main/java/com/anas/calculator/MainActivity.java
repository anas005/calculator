package com.anas.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends Activity {
String op;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Window window = MainActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(MainActivity.getResources().getColor(R.color.));*/

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Operators, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
            spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //Toast.makeText(parent.getContext(), parent.getItemAtPosition(pos).toString(), Toast.LENGTH_SHORT).show();
                final Button button = (Button) findViewById(R.id.button);
                switch (pos) {
                    case 0:
                        op = "+";
                        button.setText("Add");
                        //Toast.makeText(parent.getContext(), "Add", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        op = "-";
                        button.setText("Subtract");
                        //Toast.makeText(parent.getContext(), "Subtract", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        op = "x";
                        button.setText("Multiply");
                        //Toast.makeText(parent.getContext(), "Multiply", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        op = "/";
                        button.setText("Divide");
                        //Toast.makeText(parent.getContext(), "Divide", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }

        /*String str = parent.getItemAtPosition(pos).toString()+"...";

        if( str.equals("+"))
            Toast.makeText(parent.getContext(),"Add",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(parent.getContext(),parent.getItemAtPosition(pos).toString()+" . . . .",Toast.LENGTH_SHORT).show();
        */
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Clear) {
            clearAll();
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearAll()
    {
        final TextView Result = (TextView) findViewById(R.id.Result);
        Result.setText("0");
        final EditText Num1 = (EditText) findViewById(R.id.Number1);
        Num1.setText("");
        final EditText Num2 = (EditText) findViewById(R.id.Number2);
        Num2.setText("");
    }

    public static String format(Number n) {
        NumberFormat format = DecimalFormat.getInstance();
        format.setRoundingMode(RoundingMode.FLOOR);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(2);
        return format.format(n);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        EditText Num1 = (EditText) findViewById(R.id.Number1);
        EditText Num2 = (EditText) findViewById(R.id.Number2);

        String message1 = Num1.getText().toString();
        String message2 = Num2.getText().toString();

        double num1,num2,num3=0;

        try {
            num1 = Double.parseDouble(Num1.getText().toString());
            num2 = Double.parseDouble(Num2.getText().toString());
            if(op.equals("+"))
                num3 = (num1+num2);
            else if(op.equals("-"))
                num3 = (num1-num2);
            else if(op.equals("x"))
                num3 = (num1*num2);
            else
                num3 = (num1/num2);

//            Toast.makeText(this, num1+op+num2+" = "+num3 , Toast.LENGTH_SHORT).show();
        } catch(NumberFormatException nfe) {
            Toast.makeText(this, "Invalid Number..." , Toast.LENGTH_SHORT).show();
        }


        //DecimalFormat format = new DecimalFormat("0.#");
        //num3 = format.format(num3);

        //Change Textview Dynamically
            final TextView mTextView = (TextView) findViewById(R.id.Result);
            mTextView.setText(format(num3));
    }
}
