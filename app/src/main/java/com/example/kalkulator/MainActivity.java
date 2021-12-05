package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView wynik;
    TextView operator;
    TextView edycja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wynik = (TextView) findViewById(R.id.wynik);
        operator = (TextView) findViewById(R.id.operator);
        edycja = (TextView) findViewById(R.id.edycja);
    }

    public void klawisz(View view) {
        Button przycisk = (Button) view;
        String przyciskStr = przycisk.getText().toString();
        //przycisk CE
        if(przyciskStr.equals("CE")){
            wynik.setText("");
            operator.setText("");
            edycja.setText("");
        //przycisk C
        }else if(przyciskStr.equals("C")){
            edycja.setText("");
        //przyciski od 0 - 9
        }else if(przyciskStr.equals("0") || przyciskStr.equals("1") || przyciskStr.equals("2") ||
                przyciskStr.equals("3") || przyciskStr.equals("4") || przyciskStr.equals("5") ||
                przyciskStr.equals("6") || przyciskStr.equals("7") || przyciskStr.equals("8") || przyciskStr.equals("9")) {
            String edycjaStr = edycja.getText().toString();
            //przycisk 0 ale nie ma innych cyfr
            if(edycjaStr.equals("0")){
                edycjaStr = przyciskStr;
             //przycisk 0 ale są inne cyfry
            } else{
                edycjaStr = edycjaStr + przyciskStr;
            }
            edycja.setText(edycjaStr);
        //przcisk separator dziesiętny (.)
        }else if(przyciskStr.equals(".")){
            String edycjaStr = edycja.getText().toString();
            if(edycjaStr.indexOf(".")<0){
                if(edycjaStr.length()==0){
                    edycjaStr = "0.";
                }else{
                    edycjaStr = edycjaStr + ".";
                }
            }
            edycja.setText(edycjaStr);
        //przycisk +/-
        }else if(przyciskStr.equals("+/-")){
            String edycjaStr = edycja.getText().toString();
            //nie ma znaku odjąć
            if(edycjaStr.indexOf("-")<0){
                edycjaStr = "-" + edycjaStr;
            }else{
                edycjaStr = edycjaStr.substring(1);
            }
            edycja.setText(edycjaStr);
        //przycisk <-
        }else if(przyciskStr.equals("<-")){
            String edycjaStr = edycja.getText().toString();
            int dlugoscNapisu = edycjaStr.length();
            if(dlugoscNapisu>0){
                edycjaStr = edycjaStr.substring(0, dlugoscNapisu-1);
                edycja.setText(edycjaStr);
            }
        //obsługa + - * / =
        }else if(przyciskStr.equals("+") || przyciskStr.equals("-") ||
                przyciskStr.equals("*") || przyciskStr.equals("/") || przyciskStr.equals("=")) {
            String edycjaStr = edycja.getText().toString();
            if (przyciskStr.equals("=")) przyciskStr = "";
            //Gdy pole edycji zawiera liczbę można robić obliczenia
            if (edycjaStr.length() > 0 && !edycjaStr.equals("-")) {
                String operatorStr = operator.getText().toString();
                if(operatorStr.length()==0){
                    edycja.setText("");
                    operator.setText(przyciskStr);
                    wynik.setText(edycjaStr);
                } else {
                    String wynikStr = wykonajDzialanie(edycjaStr);
                    if (wynikStr.length() > 0) {
                        wynik.setText(wynikStr);
                    } else {
                        wynik.setText(edycjaStr);
                    }
                    edycja.setText("");
                    operator.setText(przyciskStr);
                }
            } else {
                operator.setText(przyciskStr);
            }
        }
    }

    String wykonajDzialanie(String edycjaStr){
        String wynikStr = wynik.getText().toString();
        String operatorStr = operator.getText().toString();
        if(wynikStr.length()>0 && operatorStr.length()>0) {
            Double edycjaD = Double.parseDouble(edycjaStr);
            Double wynikD = Double.parseDouble(wynikStr);
            if(operatorStr.equals("+")){
                wynikD = wynikD + edycjaD;
            } else if(operatorStr.equals("-")){
                wynikD = wynikD - edycjaD;
            } else if(operatorStr.equals("*")){
                wynikD = wynikD * edycjaD;
            } else if(operatorStr.equals("/")){
                if (edycjaD == 0D){
                    wynikD = 0D;
                } else{
                    wynikD = wynikD / edycjaD;
                }
            }
            wynikStr = wynikD.toString();
        }
        return wynikStr;
    }

}