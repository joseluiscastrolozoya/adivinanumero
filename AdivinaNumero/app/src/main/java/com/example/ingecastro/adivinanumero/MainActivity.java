package com.example.ingecastro.adivinanumero;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtx;
    TextView txnum,txindic,txacie;
    Button btn;
    int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtx=(EditText)findViewById(R.id.editText);
        txnum=(TextView)findViewById(R.id.textView);
        txindic=(TextView)findViewById(R.id.textView4);
        txacie=(TextView)findViewById(R.id.textView6);
        btn=(Button)findViewById(R.id.button);

        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        txacie.setText(prefe.getString("aciertos",""));

        final int al = (int)(Math.random()*50);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int canti=Integer.parseInt(edtx.getText().toString());
                if (al==canti){
                    contador++;
                    txnum.setText(""+al);
                    txacie.setText(""+contador);
                    txindic.setText("FELICIDADES, ADIVINASTE EL NUMERO");
                }else if (canti<al){
                    txindic.setText("INCORRECTO, EL NUMERO ES AUN MAYOR");
                }else if (canti>al){
                    txindic.setText("INCORRECTO, EL NUMERO ES AUN MENOR");
                }
                SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putString("aciertos", txacie.getText().toString());
                editor.commit();

            }
        });
    }
}
