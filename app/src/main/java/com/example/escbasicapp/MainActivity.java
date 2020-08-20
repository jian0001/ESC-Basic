package com.example.escbasicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageButton addContact;
    private ImageButton contact;
    private TextView phoneNum;
    private TextView[] dials=new TextView[10];
    private TextView star;
    private TextView sharp;
    private ImageButton message;
    private ImageButton call;
    private ImageButton backspace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUI();

    }

    private  void setUpUI(){
        addContact = findViewById(R.id.main_ibtn_add);
        contact = findViewById(R.id.main_ibtn_contact);
        phoneNum = findViewById(R.id.main_tv_phone);

        for (int i =0;i<dials.length;i++){
            dials[i]=findViewById(getResourceID("main_tv_"+i,"id",this));

        }

        star =findViewById(R.id.main_tv_star);
        sharp = findViewById(R.id.main_tv_sharp);
        message = findViewById(R.id.main_ibtn_message);
        call = findViewById(R.id.main_ibtn_call);
        backspace = findViewById(R.id.main_ibtn_backspace);

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : 연락처 추가
                Toast.makeText(MainActivity.this, "test",Toast.LENGTH_SHORT).show();
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO : 연락처
            }
        });

        setOnClickDial(star,"*");
        setOnClickDial(sharp,"#");
        for (int i = 0; i <10 ; i++) {
            setOnClickDial(dials[i],String.valueOf(i));
        }

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : 메세지
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : 전화
            }
        });


        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNum.getText().length() > 0) {
                    phoneNum.setText(changeToDial(phoneNum.getText().subSequence(0, phoneNum.getText().length() - 1).toString()));
                }
                /*
                if (phoneNum.getText().length() > 0) {
                    String formatPhoneNum = PhoneNumberUtils.formatNumber(
                            phoneNum.getText().subSequence(0, phoneNum.getText().length() - 1).toString(),
                            Locale.getDefault().getCountry()
                    );
                    phoneNum.setText(formatPhoneNum);
                }

                */

            }
        });

    }

    private void setOnClickDial(View view, final String input){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNum.setText(changeToDial(phoneNum.getText()+input));

                //String formatPhoneNum = PhoneNumberUtils.formatNumber(phoneNum.getText() + input, Locale.getDefault().getCountry());
                //phoneNum.setText(formatPhoneNum);
            }
        });
    }

    private  int getResourceID(final String resName, final String resType, final Context ctx){
        final int ResourceID=
                ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0){
            throw new IllegalArgumentException("No resource string found with name "+resName);
        }
        else{
            return ResourceID;
        }
    }
    private String changeToDial(String phoneNum) {
        if(phoneNum.contains("*") || phoneNum.contains("#")) {
            while (phoneNum.contains("-")) {
                int num = phoneNum.indexOf("-");
                phoneNum = (String) phoneNum.subSequence(0, num) + (String) phoneNum.subSequence(num + 1, phoneNum.length());
            }
            return phoneNum;
        }

        else{
            String part1;
            String part2;
            String part3;

            if(4==phoneNum.length()){
                part1= (String) phoneNum.subSequence(0, 3);
                part2= (String) phoneNum.subSequence(3,phoneNum.length());
                return part1+"-"+part2;
            }
            else if (9==phoneNum.length()){
                part1=(String) phoneNum.subSequence(0,3);
                part2=(String) phoneNum.subSequence(4,8);
                part3=(String) phoneNum.subSequence(8,phoneNum.length());
                return part1+"-"+part2+"-"+part3;
            }
            else if(14<=phoneNum.length()){
                part1=(String) phoneNum.subSequence(0,3);
                part2=(String) phoneNum.subSequence(4,8);
                part3=(String) phoneNum.subSequence(9,phoneNum.length());
                return part1+part2+part3;
            }
            else{
                return phoneNum;
            }
        }
    }
}