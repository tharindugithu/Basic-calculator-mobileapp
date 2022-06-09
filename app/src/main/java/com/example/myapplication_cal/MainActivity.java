package com.example.myapplication_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText quizeditText;
    EditText anseditText;
    boolean operatorInUse=true;
    double result,devide = 0.0;
    double mul = 1.0;
    int i=0,j=0,count;
    List<Double> values = new ArrayList<>();
    List<String> ops = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quizeditText = findViewById(R.id.editTextTextPersonName);
        anseditText = findViewById(R.id.editTextTextPersonName2);
    }

    public void numberTapped(View view) {
        Button numberButton = (Button) view;
        quizeditText.append(numberButton.getText());
        operatorInUse = false;
    }

    public void clearTapped(View view) {
        quizeditText.setText("");
        anseditText.setText("");
        values.clear();
        ops.clear();
        operatorInUse = false;
    }

    public void operatorTapped(View view) {
       if(!operatorInUse){
           Button operatorButton = (Button) view;
           String currentOperator = operatorButton.getText().toString();

           String eq = "=";
           if(currentOperator.compareTo(eq) != 0){
               quizeditText.append(currentOperator);
           }else {
               values.add(getLastValue());
               calculate(currentOperator);
               return;
           }
           values.add(getLastValue());
           calculate(currentOperator);
           operatorInUse = true;
       }
    }

    private void calculate(String op){
      String[] operators = new String[]{"+","-","/","*","="};
      if(op.compareTo(operators[4]) != 0) {
          ops.add(op);
      }else {
            j= 0;count =1;
            for (i = 0; i < values.size(); i++) {
                    if(count == 3)
                       j++;
                    else if(count >= 4)
                       j++;
                if (ops.get(j).compareTo(operators[0]) == 0)
                    result = result + values.get(i);
                else if(ops.get(j).compareTo(operators[1]) == 0)
                    result = result - values.get(i);
                else if(ops.get(j).compareTo(operators[3]) == 0)
                    if(result == 0)
                        result = 1 * values.get(i);
                    else
                        result = result *values.get(i);
                else
                    if(result == 0 && values.get(i) != 0)
                        result = values.get(i)/1;
                    else if(result != 0)
                        result = result/values.get(i);
                    else {
                        anseditText.append("0.0");
                        return;
                        }
                count =count+1;
            }finalCal();
      }

    }

    private double getLastValue(){
        double lastValue = 0.0;
        String quiz = quizeditText.getText().toString();
        String[] valueString = quiz.split("[/*+-]");
        if(valueString.length >0){
            String lastValueString = valueString[valueString.length-1];
            lastValue = Double.parseDouble(lastValueString);
        }
         return lastValue;
    }

    private void finalCal(){
        anseditText.append(String.valueOf(result));
        result=0.0;
        count=0;
    }

}






















