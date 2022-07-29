package com.example.calculator_app;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //we need to grab the values which we put in edit text
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.input);
        //to stop the default keyboard from popping up
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.displays).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });


    }
    //to take in/edit input in the display
    private void updateText(String strToAdd){
        String oldStr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);
        if(getString(R.string.displays).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);

        }else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }


    }

    // view is a small rectangular box that responds to user inputs. Eg: EditText, Button, CheckBox, etc.
    public void zeroBtn(View view){
        updateText("0");
    }
    public void oneBtn(View view){
        updateText("1");
    }
    public void twoBtn(View view){
        updateText("2");
    }
    public void threeBtn(View view){
        updateText("3");
    }
    public void fourBtn(View view){
        updateText("4");
    }
    public void fiveBtn(View view){
        updateText("5");
    }
    public void sixBtn(View view){
        updateText("6");
    }
    public void sevenBtn(View view){
        updateText("7");
    }
    public void eightBtn(View view){
        updateText("8");
    }
    public void nineBtn(View view){
        updateText("9");
    }
    public void addBtn(View view){
        updateText("+");
    }
    public void subBtn(View view){
        updateText("-");
    }
    public void mulBtn(View view){
        updateText("×");
    }
    public void divBtn(View view){
        updateText("÷");

    }
    public void clearBtn(View view){
       display.setText("");
    }
    public void expBtn(View view){
        updateText("^");
    }
    public void plusMinusBtn(View view){
        updateText("-");

    }
    public void pointBtn(View view){
        updateText(".");
    }
    public void backspaceBtn(View view){
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursorPos!=0 && textLen!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);

        }
    }
    public void equalsBtn(View view){
        String userExp=display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");
        Expression exp=new Expression(userExp);
        String result=String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());


    }
    public void parBtn(View view){
        int cursorPos=display.getSelectionStart();
        int openPar=0;
        int closedPar=0;
        int textLen=display.getText().length();

        for(int i=0;i<cursorPos;i++){
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar+=1;

            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closedPar+=1;

            }
        }
        if (openPar == closedPar||display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");

        }
        else if (closedPar<openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");

        }
        display.setSelection(cursorPos+1);
    }


}