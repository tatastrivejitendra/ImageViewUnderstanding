   package com.example.imageviewunderstanding;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

   public class MainActivity extends AppCompatActivity {
       ImageView imageView;
       ImageButton imageButton;
       final static int clickcode=100;
       Bitmap bitmap;
       Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView);
        imageButton=findViewById(R.id.imageButton);
        button=findViewById(R.id.button);
        InputStream inputStream = getResources().openRawResource(R.drawable.a2);
        bitmap= BitmapFactory.decodeStream(inputStream);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,clickcode);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getApplicationContext().setWallpaper(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();

                }
                Toast.makeText(MainActivity.this, "Set Wallpaper", Toast.LENGTH_SHORT).show();

            }
        });



    }

       @Override
       protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
           super.onActivityResult(requestCode, resultCode, data);
           if (resultCode == RESULT_OK)
           {
              Bundle bundle_jitu=data.getExtras();
              bitmap = (Bitmap) bundle_jitu.get("data");
              imageView.setImageBitmap(bitmap);
           }
       }
   }
