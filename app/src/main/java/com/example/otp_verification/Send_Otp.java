package com.example.otp_verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Send_Otp extends AppCompatActivity {

EditText recipientEditText;
Button sendOtpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        recipientEditText=findViewById(R.id.recipientEditText);
        sendOtpButton=findViewById(R.id.sendOtpButton);

        sendOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtpButton.setVisibility(View.GONE);
                String phoneNumber="+91"+recipientEditText.getText().toString();

               PhoneAuthProvider.getInstance().verifyPhoneNumber(
                       phoneNumber,
                       10,
                       TimeUnit.SECONDS,
                       Send_Otp.this,
                       new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                           @Override
                           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                               Toast.makeText(Send_Otp.this, "Verification Done", Toast.LENGTH_SHORT).show();
                           }

                           @Override
                           public void onVerificationFailed(@NonNull FirebaseException e) {
                               Toast.makeText(Send_Otp.this,e.getMessage(), Toast.LENGTH_LONG).show();
                           }

                           @Override
                           public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               Intent intent=new Intent(Send_Otp.this,Receive_Otp.class);
                               intent.putExtra("Verification Code",s);
                               intent.putExtra("Phone number",phoneNumber);
                               startActivity(intent);
                               finish();
                           }
                       }
               );

            }
        });

    }
}