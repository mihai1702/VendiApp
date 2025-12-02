package com.example.vendiappfrontend;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddCarActivity extends AppCompatActivity {

    private static final String URL = "http://10.0.2.2:8000/addcar";
    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText titleEditText, descriptionEditText, priceEditText, phoneEditText, sellerEditText, locationEditText;
    private Button uploadButton, submitButton;
    public Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_car);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, 1);
        }
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        priceEditText = findViewById(R.id.priceEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        sellerEditText = findViewById(R.id.sellerEditText);
        locationEditText = findViewById(R.id.locationEditText);
        uploadButton = findViewById(R.id.uploadButton);
        submitButton = findViewById(R.id.submitButton);

        uploadButton.setOnClickListener(v -> openImagePicker());

        submitButton.setOnClickListener(v -> {
            try {
                submitForm();
                finish();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
        }
    }

    private void submitForm() throws IOException {
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        double price = Double.parseDouble(priceEditText.getText().toString());
        String phone = phoneEditText.getText().toString();
        String seller = sellerEditText.getText().toString();
        String location = locationEditText.getText().toString();

        File imageFile = new File(getRealPathFromURI(imageUri));

        OkHttpClient client = new OkHttpClient();

        MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/jpeg");

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", title)
                .addFormDataPart("description", description)
                .addFormDataPart("price", String.valueOf(price))
                .addFormDataPart("phone", phone)
                .addFormDataPart("seller", seller)
                .addFormDataPart("location", location)
                .addFormDataPart("image", imageFile.getName(), RequestBody.create(MEDIA_TYPE_IMAGE, imageFile));

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(URL)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Error", "Error: " + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        Toast.makeText(AddCarActivity.this, "Ad was added successfully", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(AddCarActivity.this, "Error while submitting", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
    private String getRealPathFromURI(Uri uri) {
        String result = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.moveToFirst()) {
                result = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return result;
    }

}