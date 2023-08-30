package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView results,solutions;
    MaterialButton buttonC,buttonOpen,buttonClose,buttonMultiply,buttonDivide,buttonAdd,buttonSub,button7,button8,button9,button4,button5,button6,button1,button2,button3,buttonAC,buttonDot,buttonEquals,button0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        results=findViewById(R.id.idTVResult);
        solutions=findViewById(R.id.idTVSolution);
        assignId(buttonC,R.id.BtnC);
        assignId(button0,R.id.Btn0);
        assignId(button7,R.id.Btn7);
        assignId(button1,R.id.Btn1);
        assignId(button2,R.id.Btn2);
        assignId(button3,R.id.Btn3);
        assignId(button4,R.id.Btn4);
        assignId(button5,R.id.Btn5);
        assignId(button6,R.id.Btn6);
        assignId(buttonDot,R.id.BtnDot);
        assignId(button8,R.id.Btn8);
        assignId(button9,R.id.Btn9);
        assignId(buttonAC,R.id.BtnAC);
        assignId(buttonOpen,R.id.BtnOpenBrac);
        assignId(buttonClose,R.id.BtnCloseBrac);
        assignId(buttonAdd,R.id.BtnAdd);
        assignId(buttonDivide,R.id.BtnDiv);
        assignId(buttonMultiply,R.id.BtnMul);
        assignId(buttonSub,R.id.BtnSub);
        assignId(buttonEquals,R.id.Btnequals);





    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutions.getText().toString();

        if(buttonText.equals("AC")){
            solutions.setText("");
            results.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutions.setText(results.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate+=buttonText;
        }

        solutions.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            results.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context= Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult= context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }


}