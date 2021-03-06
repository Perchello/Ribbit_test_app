package perchello.ribbit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends ActionBarActivity {

	protected EditText mUsername;
	protected EditText mPassword;
	protected Button mLoginButton;
    protected TextView mResetPasswordButton;
	protected TextView mSignUpTextView;
    protected ProgressBar mProgressBar;


    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		
		mSignUpTextView = (TextView)findViewById(R.id.signUpText);
        mProgressBar = (ProgressBar) findViewById(R.id.loginProgress);

        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
				startActivity(intent);
			}
		});
		
		mUsername = (EditText)findViewById(R.id.usernameField);
		mPassword = (EditText)findViewById(R.id.passwordField);
        mResetPasswordButton = (TextView) findViewById(R.id.resetTextView);
        mResetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });

		mLoginButton = (Button)findViewById(R.id.loginButton);
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				
				username = username.trim();
				password = password.trim();
				
				if (username.isEmpty() || password.isEmpty()) {
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
					builder.setMessage(R.string.login_error_message)
						.setTitle(R.string.login_error_title)
						.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else {
					// Login
                    mProgressBar.setVisibility(View.VISIBLE);
                    mLoginButton.setVisibility(View.INVISIBLE);
                    ParseUser.logInInBackground(username, password, new LogInCallback() {
						@Override
						public void done(ParseUser user, ParseException e) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            mLoginButton.setVisibility(View.VISIBLE);


                            if (e == null) {
								// Success!
								Intent intent = new Intent(LoginActivity.this, MainActivity.class);
								intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							}
							else {
								AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
								builder.setMessage(e.getMessage())
									.setTitle(R.string.login_error_title)
									.setPositiveButton(android.R.string.ok, null);
								AlertDialog dialog = builder.create();
								dialog.show();
							}
						}
					});
				}
			}
		});
	}



}
