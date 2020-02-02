package com.vishwas.tts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.text.TextRecognizer;

public class CamScannerActivity extends AppCompatActivity {

    private String TAG = "MPC-Porject";
    private CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cam_scanner);
    }

    private void createCameraSource(boolean autoFocus, boolean useFlash) {
//        Context context = getApplicationContext();
//
//        // TODO: Create the TextRecognizer
//        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();
//
//        // TODO: Set the TextRecognizer's Processor.
//        textRecognizer.setProcessor(new OcrDetectorProcessor(graphicOverlay));
//
//
//        // TODO: Check if the TextRecognizer is operational.
//        if (!textRecognizer.isOperational()) {
//            Log.w(TAG, "Detector dependencies are not yet available.");
//
//            // Check for low storage.  If there is low storage, the native library will not be
//            // downloaded, so detection will not become operational.
//            IntentFilter lowstorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
//            boolean hasLowStorage = registerReceiver(null, lowstorageFilter) != null;
//
//            if (hasLowStorage) {
//                Toast.makeText(this, "low_storage_error", Toast.LENGTH_LONG).show();
//                Log.w(TAG, "low_storage_error");
//            }
//        }
//
//        // TODO: Create the mCameraSource using the TextRecognizer.
//
//        cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
//                .setFacing(CameraSource.CAMERA_FACING_BACK)
//                .setRequestedPreviewSize(1280, 1024)
//                .setRequestedFps(15.0f)
////                        .setFlashMode(useFlash ? Camera.Parameters.FLASH_MODE_TORCH : null)
////                        .setFocusMode(autoFocus ? Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO : null)
//                .build();
    }

    @Override
    protected void onDestroy() {
        cameraSource.release();
        super.onDestroy();
    }
}
