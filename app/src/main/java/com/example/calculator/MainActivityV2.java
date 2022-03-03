package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivityV2 extends AppCompatActivity {
    private double a=0;
    private double b=0;
    private int i=0;
    private int nbOperand=0;
    private boolean flag=false;
    private boolean falagRev=false;
    private String op;
    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Window window = this.getWindow();
        window.setStatusBarColor(getColor(R.color.black));
        res = findViewById(R.id.res);
    }

    public void ButtonClickValue(View v){
        String Value= ((AppCompatButton) v).getText().toString();
        if(isOperator(Value)){
            falagRev=false;
            if(nbOperand==2){
                Calculate(null);
            }
            flag=true;
            op=Value;
        }else{
            if(flag){
                nbOperand=2;
                if(falagRev){
                    i++;
                    b=b+Double.parseDouble(Value)/Math.pow(10,i);
                    Log.e("b : ","value "+b);
                }
                else
                    b=b*10+Double.parseDouble(Value);
                Display(b);
                //res.setText(String.valueOf(b));
            }else {
                nbOperand=1;
                if(falagRev){
                    i++;
                    a=a+Double.parseDouble(Value)/Math.pow(10,i);
                }
                else
                    a=a*10+Double.parseDouble(Value);
                Display(a);
                //res.setText(String.valueOf(a));
            }
        }
    }

    boolean isOperator(String s){
        return  (s.equals("+")||s.equals("-")||s.equals("x")||s.equals("-")||s.equals("÷")||s.equals("%"));
    }

    private void Display(double value){
        String str="";
        if(value%1==0){
                str= falagRev? String.valueOf((int)value)+",":String.valueOf((int)value);
        }else{
            str = String.valueOf(value).replace('.', ',');
        }
        if(str.length()>6)
            res.setTextSize(50);
        res.setText(str);
    }

    public void Reverse(View v){
        if((a%1==0&&nbOperand==1)||(b%1==0&&nbOperand==2))
            i=0;
        falagRev=true;
        if(nbOperand==1)
            Display(a);
        else
            Display(b);
    }

    public void Calculate(View v){
        switch (op){
            case "+":
                a=a+b;
                break;
            case "-":
                a=a-b;
                break;
            case "÷":
                a=a/b;
                break;
            case "x":
                a=a*b;
                break;
            case "%":
                a=a%b;
                break;
        }
        b=0;
        Display(a);
        nbOperand=1;
        flag=false;
        //res.setText(String.valueOf(a));
    }
    public void Reset(View v){
        nbOperand=0;
        falagRev=false;
        flag=false;
        b=a=0;
        Display(a);
        //res.setText(String.valueOf(a));
    }
    public void changeSigne(View v){
        if(nbOperand==1){
            a=a*-1;
            Display(a);
        }else{
            b=b*-1;
            Display(b);
        }
    }
}
