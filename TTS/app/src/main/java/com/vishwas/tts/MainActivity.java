package com.vishwas.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static TextToSpeech ttobj;
    private static int counter = 0;

    private EditText inputText;
    private Button speakButton, ocrActivity;


    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.text_input);
        speakButton = findViewById(R.id.speak_button);
        ocrActivity = findViewById(R.id.ocr_activity_button);

        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_to_speak = inputText.getText().toString();
                speakOutLoud(getApplicationContext(), text_to_speak);
            }
        });

        ocrActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hii");
                captureText(v);
            }
        });
    }

    public static void speakOutLoud(Context context, String text_to_speak) {

        if (ttobj == null) {
            ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status != TextToSpeech.ERROR) {
                        ttobj.setLanguage(Locale.US);
                    }
                }
            });
        }


        if (!ttobj.isSpeaking()) {
            ttobj.speak(text_to_speak, 0, null, String.valueOf(counter++));
        } else {
            Toast.makeText(context, "Please let me finish!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        ttobj.stop();
        ttobj.shutdown();
        super.onDestroy();
    }


    public void captureText(View v) {
        // launch Ocr capture activity.
        Intent intent = new Intent(this, OcrCaptureActivity.class);
//            intent.putExtra(OcrCaptureActivity.AutoFocus, autoFocus.isChecked());
//            intent.putExtra(OcrCaptureActivity.UseFlash, useFlash.isChecked());

        startActivityForResult(intent, RC_OCR_CAPTURE);
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
//                    statusMessage.setText(R.string.ocr_success);
                    inputText.setText(text);

                    MainActivity.speakOutLoud(getApplicationContext(), text);

                    Log.d(TAG, "Text read: " + text);
//                    acceptButton.setVisibility(View.VISIBLE);
                } else {
//                    statusMessage.setText(R.string.ocr_failure);
                    Log.d(TAG, "No Text captured, intent data is null");
                }
            } else {
//                statusMessage.setText(String.format(getString(R.string.ocr_error),
//                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
