package com.prayag.citywiseindia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.core.splashscreen.SplashScreen;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private boolean keepSplashScreenOn = true;
    Button loginButton, signupButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);

        splashScreen.setKeepOnScreenCondition(() -> {
            Log.d("SplashScreen", "keepSplashScreenOn: " + keepSplashScreenOn);
            return keepSplashScreenOn;
        });


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        //if the user is already logged in
        if (currentUser != null) {
            //user is signed in, go directly to select city
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
            startActivity(intent);
            finish(); //finish main activity so there is no way to go back to it
            return;
        }

        //if user is new, proceed to show main activity
        Log.d("MainActivityAuth", "User is NOT logged in. Setting up MainActivity UI.");


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        keepSplashScreenOn = false;
        Log.d("SplashScreen", "keepSplashScreenOn set to false. Splash should hide.");


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initialize buttons
        loginButton = findViewById(R.id.button);
        signupButton = findViewById(R.id.button2);

        //click listeners
        loginButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });

        signupButton.setOnClickListener(v -> {
            Intent signupIntent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(signupIntent);
        });
    }
}