package com.polsri.paio.menu;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.polsri.paio.R;

public class CameraView extends AppCompatActivity {
    ImageView Camera;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    SwipeRefreshLayout swipeRefreshLayout;
    com.polsri.paio.dataClass.Snap Snap;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_view);
        String urlStorage = "gs://autofish-d31e9.appspot.com/";
        String filename = "Kolam.jpeg";

        String url = "https://autofish-d31e9-default-rtdb.asia-southeast1.firebasedatabase.app/";
        database = FirebaseDatabase.getInstance(url).getReference("/");

        Camera = (ImageView) findViewById(R.id.CameraIV);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayoutNode1);
        ShowImage(urlStorage, filename);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {@Override
        public void onRefresh() {
            database.child("snap").child("trig").setValue("1");
            ShowImage(urlStorage, filename);
            swipeRefreshLayout.setRefreshing(false);
        }
        });


    }

    private void ShowImage(String url, String file) {
        final long ONE_MEGABYTE = 1024 * 1024;
        FirebaseStorage storage = FirebaseStorage.getInstance(url);
        StorageReference gsReference = storage.getReference(file);
        gsReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                Camera.setImageBitmap(bmp);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });

        //setting Refreshing to false

    }
}