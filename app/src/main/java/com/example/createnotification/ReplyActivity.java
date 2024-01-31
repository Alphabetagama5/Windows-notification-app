package com.example.createnotification;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.RemoteInput;


public class ReplyActivity extends Activity {

    private static final String KEY_TEXT_REPLY = "key_text_reply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        // Get the reply text from the intent
        CharSequence replyText = getMessageText(getIntent());

        // Display the reply text in a TextView or handle it as needed
        TextView replyTextView = findViewById(R.id.replyTextView);
        replyTextView.setText(replyText);
    }

    // Helper method to extract reply text from the intent
    private CharSequence getMessageText(Intent intent) {
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            return remoteInput.getCharSequence(KEY_TEXT_REPLY);
        }
        return null;
    }
}

