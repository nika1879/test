package com.example.homa.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button Ok;
    EditText input;
    TextView output;
    String s;
    String temp = "";
    char[] ch ;
    String t = "";
    int length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ok = (Button) findViewById(R.id.buttonOk);
        input = (EditText) findViewById(R.id.editText);
        output = (TextView) findViewById(R.id.textView);


        Ok.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                s = input.getText().toString();

                if(s != null) {
                    s = s.trim();
                    ch = s.toCharArray();
                    length = ch.length;

                    //убираем лишние пробелы
                    for (int i = 0; i < length; i++) {
                        if(ch[i] == ' ' && ch[i+1] == ' '){}else temp += ch[i];
                    }
                    replay();

                    //апостроф
                    for (int i = 0; i < length; i++) {
                        if(ch[i] == ' ' && ch[i+1] == '\'' && ch[i+2] == ' ')
                        {temp += ch[i+1];i+=2;}else
                            if(ch[i] == ' ' && ch[i+1] == '\''){temp += ch[i+1];i++;}else
                        if(ch[i] == '\'' && ch[i+1] == ' '){temp += ch[i];i++;}
                        else temp += ch[i];
                   }
                    replay();

                    // после ,  пробел
                    for (int i = 0; i < length; i++) {
                        temp += ch[i];
                        if(ch[i] == ',' && ch[i+1] != ' ')
                            temp +=" ";
                    }

                    replay();
                    // после точки пробел если не последний символ
                    for (int i = 0; i < length; i++) {

                        if(i !=(length-1)) {
                            if(ch[i]== '.' && ch[i+1] != ' '  && ch[i+1] !='.')
                            {
                                temp +=ch[i];
                                temp += ' ';
                            } else temp +=ch[i];
                        }else temp +=ch[i];
                    }
                    replay();

                    //убираем пробел перед троеточием
                    for (int i = 0; i < length; i++) {
                        if((i+3)<length){
                            if (ch[i + 1] == '.' && ch[i + 2] == '.' && ch[i + 3] == '.' && ch[i] == ' '){
                                temp +=ch[i+1];temp +=ch[i + 2];temp +=ch[i + 3];
                                i+=3;
                            }else temp += ch[i];
                        }else temp += ch[i];
                    }

//все в нижний регистр а в верхний только первая буква предложения
                    s = temp;
                    temp = "";
                    ch = s.toLowerCase().toCharArray();// все в нижний регистр
                    length = ch.length;
                    for (int i = 0; i < length; i++){
                        if(i == 0){
                            t += ch[0];
                            temp +=t.toUpperCase(); t ="";
                        }
                        if( i > 2 && ch[i-1] == ' ' && ch[i-2] =='.'){
                          t ="";
                        t += ch[i];
                        temp +=t.toUpperCase();t="";
                        }else if(i > 0) temp +=ch[i];
                    }
                        output.append("\n" + temp);
                }else output.setText("Empty");
                temp="";
            }
        });
    }
    //перезапись
    void replay(){
        s = temp;
        temp = "";
        ch = s.toCharArray();
        length = ch.length;
    };
}
