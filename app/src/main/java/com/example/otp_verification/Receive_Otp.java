package com.example.otp_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Receive_Otp extends AppCompatActivity {
EditText otpEditText1,otpEditText2,otpEditText3,otpEditText4,otpEditText5,otpEditText6;
Button VerifyOtpButton;
TextView Resend;

    String phoneNumber;
    String Code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_otp);
        otpEditText1=findViewById(R.id.otpEditText1);
        otpEditText2=findViewById(R.id.otpEditText2);
        otpEditText3=findViewById(R.id.otpEditText3);
        otpEditText4=findViewById(R.id.otpEditText4);
        otpEditText5=findViewById(R.id.otpEditText5);
        otpEditText6=findViewById(R.id.otpEditText6);
        VerifyOtpButton=findViewById(R.id.VerifyOtpButton);
        Resend=findViewById(R.id.Resend);

        sendOtpInput();

        VerifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Code=getIntent().getStringExtra("Verification Code");
                phoneNumber=getIntent().getStringExtra("Phone number");

                String Enter_Code=otpEditText1.getText().toString()+
                    otpEditText2.getText().toString()+
                    otpEditText3.getText().toString()+
                    otpEditText4.getText().toString()+
                    otpEditText5.getText().toString()+
                    otpEditText6.getText().toString();

            if (Code != null){
                VerifyOtpButton.setVisibility(View.GONE);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(Code, Enter_Code);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(Receive_Otp.this, "Invalid Code ", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
            else {
                Toast.makeText(Receive_Otp.this, "Otp Not sended", Toast.LENGTH_SHORT).show();
            }

            }
        });


        Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,
                        60,
                        TimeUnit.SECONDS,
                        Receive_Otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(Receive_Otp.this, "Verification Done", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Receive_Otp.this,e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String new_VerificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                Code=new_VerificationID;
                                Toast.makeText(Receive_Otp.this, "Otp Sended", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

    }



    private void sendOtpInput() {
        otpEditText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otpEditText2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otpEditText3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otpEditText4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otpEditText5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otpEditText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otpEditText6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}