# Otp-Verification

Tools > Firebase > Authentication > using custom authentication 


Connect > Add SDK (Update those dependency else error will come )
type "/gradlew signingReport" in terminal Copy SHA-1 Certificate Paste it in Firebase Fingerprint
Now You can use this Project 


==> Send Otp Activity
Edit text for number to send OTP using PhoneAuthProvider
if code is send then Intent is Used

==>Receive Otp Activity
Linear Layout having 6 edit Text , add TextListner used for autopassing through Text
Code is Compared with user input using  PhoneAuthCredential
FirebaseAuth used for verification 
if code is Verified then Intent is Used

 MainActivity
