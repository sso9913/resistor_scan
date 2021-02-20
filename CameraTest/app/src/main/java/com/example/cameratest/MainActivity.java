package com.example.cameratest;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cameratest.CameraSurfaceView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void capture() {
        CameraSurfaceView surfaceView = new CameraSurfaceView(this);
        surfaceView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(bitmap);
                // 사진을 찍게 되면 미리보기가 중지된다. 다시 미리보기를 시작하려면...
                camera.startPreview();
            }
        });
    }
}