package com.example.trucksharing;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class SignUpActivity extends AppCompatActivity {

    public EditText edit_text_full_name_login;
    public EditText user_name_login_edit_text;
    public EditText edit_text_pass_word_login;
    public EditText edit_text_confirm_password_login;
    public EditText edit_text_phone_login;
    public Button btn_create_account_login;

    public ImageView iv_headIcon;
    public String imageBase64;

    private DatabaseHelper Data_base_Helper_this;
    public ActivityResultLauncher<Intent> activityResultLauncher;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onActivityResult(ActivityResult result) {
                Intent data = result.getData();
                handleImageOnApi19(data);
            }
        });


        edit_text_full_name_login = findViewById(R.id.edit_text_full_name_login);
        user_name_login_edit_text = findViewById(R.id.user_name_login_edit_text);
        edit_text_pass_word_login = findViewById(R.id.edit_text_pass_word_login);
        edit_text_confirm_password_login = findViewById(R.id.edit_text_confirm_password_login);
        edit_text_phone_login = findViewById(R.id.edit_text_phone_login);
        btn_create_account_login = findViewById(R.id.btn_create_account_login);

        iv_headIcon = findViewById(R.id.iv_headIcon);

        Data_base_Helper_this = new DatabaseHelper(this,"LocalDatabase.db",null,2);

        btn_create_account_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edit_text_pass_word_login.getText().toString().equals(edit_text_confirm_password_login.getText().toString())){
                    Toast.makeText(SignUpActivity.this,"The two passwords do not match",Toast.LENGTH_SHORT).show();
                    return;
                }
                SQLiteDatabase db = Data_base_Helper_this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("u_id", UUID.randomUUID().toString());
                contentValues.put("fullName",edit_text_full_name_login.getText().toString());
                contentValues.put("username",user_name_login_edit_text.getText().toString());
                contentValues.put("password",edit_text_pass_word_login.getText().toString());
                contentValues.put("head",imageBase64);
                contentValues.put("phone",edit_text_phone_login.getText().toString());
                db.insert("Person",null,contentValues);
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        iv_headIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhoto();
            }
        });
    }


    public void choosePhoto(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openAlbum();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openAlbum();
            } else {
                Toast.makeText(this, "no permision to photos~", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        //java写法
        activityResultLauncher.launch(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnApi19(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String documentId = DocumentsContract.getDocumentId(uri);

            if (TextUtils.equals(uri.getAuthority(), "com.android.providers.media.documents")) {
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            } else if (TextUtils.equals(uri.getAuthority(), "com.android.providers.downloads.documents")) {
                if (documentId != null && documentId.startsWith("msf:")) {
                    resolveMSFContent(uri, documentId);
                    return;
                }
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri, null);
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        Log.e("TAG", "handleImageOnApi19: ----------"+ imagePath);
        displayImage(imagePath);
    }

    @SuppressLint("Range")
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }


    private void resolveMSFContent(Uri uri, String documentId) {

        File file = new File(getCacheDir(), "temp_file" + getContentResolver().getType(uri).split("/")[1]);

        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);

            OutputStream outputStream = new FileOutputStream(file);

            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            iv_headIcon.setImageBitmap(bitmap);
            imageBase64 = ImageUtil.imageToBase64(bitmap);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            iv_headIcon.setImageBitmap(bitmap);

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            Bitmap bitmap2 = BitmapFactory.decodeFile(imagePath, options);
            String imageToBase64 = ImageUtil.imageToBase64(bitmap2);
            imageBase64 = imageToBase64;
        }
    }


}