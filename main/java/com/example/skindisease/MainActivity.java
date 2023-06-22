package com.example.skindisease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String Ownername;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Button takeImage,browseImage,Proceed,DataBase;
    Bitmap bitmap;
    String Result;
    String imageString="";
    BitmapDrawable drawable;
    int SELECT_PHOTO=1;
    Uri uri;
    ArrayList<String> AllFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        AllFeatures=new ArrayList<>();
        imageView=findViewById(R.id.authimage);
        takeImage=findViewById(R.id.authbutton);
        browseImage=findViewById(R.id.authbutton2);
        Proceed=findViewById(R.id.Proceed);
        DataBase=findViewById(R.id.DataBase);

        DataBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AllDetails.class);
                startActivity(intent);
            }
        });
        Proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        predict();
                        Handler threadHandler = new Handler(Looper.getMainLooper());
                        threadHandler.post(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(MainActivity.this, Result, Toast.LENGTH_SHORT).show();
                                AllFeatures=extractStrings(Result);
                                CallIntent();

                            }

                        });
                    }
                }).start();
            }
        });
        takeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                            {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                            }
                            else
                            {
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAMERA_REQUEST);

                            }

                        }

                    }
                }).start();
            }

        });

        browseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,SELECT_PHOTO);
            }
        });
    }
    public void predict(){
        drawable=(BitmapDrawable) imageView.getDrawable();
        bitmap=drawable.getBitmap();
        imageString=getStringImage(bitmap);
        System.out.println(imageString);
        Python py=Python.getInstance();
        PyObject pyObject=py.getModule("app");
        String filedata = "data:image/png;base64," + imageString;
        PyObject obj=pyObject.callAttr("prediction",filedata);
        Result=obj.toString();
        System.out.println("Result is:"+Result);
    }
    private String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] imageBytes=baos.toByteArray();

        String encodedImage=android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(MainActivity.this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PHOTO&& resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            uri=data.getData();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    predict();
//                    Handler threadHandler = new Handler(Looper.getMainLooper());
//                    threadHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            Toast.makeText(MainActivity.this, Result, Toast.LENGTH_SHORT).show();
//                            AllFeatures=extractStrings(Result);
//                            CallIntent();
//
//                        }
//
//                    });
//                }
//            }).start();



        }

    }

    public ArrayList<String> extractStrings(String input) {
        ArrayList<String> result = new ArrayList<>();

        // Regular expression pattern to match the dollar sign ($)
        Pattern pattern = Pattern.compile("\\$");
        Matcher matcher = pattern.matcher(input);

        int lastIndex = 0;
        while (matcher.find()) {
            int startIndex = matcher.start();
            int endIndex = matcher.end();

            // Extract substring before the dollar sign
            if (startIndex > lastIndex) {
                result.add(input.substring(lastIndex, startIndex));
            }

            lastIndex = endIndex;
        }

        // Add the remaining substring after the last dollar sign
        if (lastIndex < input.length()) {
            result.add(input.substring(lastIndex));
        }

        return result;
        }
        public void CallIntent(){
        Intent intent=new Intent(MainActivity.this,Detail.class);
        intent.putExtra("Name",AllFeatures.get(0));
        intent.putExtra("Description",AllFeatures.get(1));
        intent.putExtra("Symptoms",AllFeatures.get(2));
        intent.putExtra("Causes",AllFeatures.get(3));
        intent.putExtra("Treatement",AllFeatures.get(4));

        startActivity(intent);

        }
}
