package com.xinsane.media;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.xinsane.util.LogUtil;

import java.io.File;
import java.io.IOException;

public class TakePhotoActivity extends AppCompatActivity {

    private ImageView imageView;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        LogUtil.d("onCreate");
        imageView = findViewById(R.id.image);
    }

    public void onTakePhoto(View view) {
        File dir = new File(getExternalCacheDir(), "provider");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                Toast.makeText(this, "create provider dir fail", Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (dir.isFile()) {
            if (!dir.delete()) {
                Toast.makeText(this, "file provider exists and delete fail", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (!dir.mkdir()) {
                    Toast.makeText(this, "create provider dir fail", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        File outputImage = new File(getExternalCacheDir(), "provider/output_image.jpg");
        LogUtil.d(outputImage.getAbsolutePath(), "onTakePhoto");
        try {
            if (outputImage.exists()) {
                if (!outputImage.delete()) {
                    Toast.makeText(this, "file output_image.jpg exists and delete fail", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (!outputImage.createNewFile()) {
                Toast.makeText(this, "file output_image.jpg create fail", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = FileProvider.getUriForFile(this, "com.xinsane.media.fileprovider", outputImage);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(imageUri));
                LogUtil.d(imageUri.toString(), "onActivityResult");
                if (bitmap == null)
                    LogUtil.d("null bitmap");
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
