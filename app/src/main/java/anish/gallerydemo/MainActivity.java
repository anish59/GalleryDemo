package anish.gallerydemo;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;

import java.util.ArrayList;

import anish.gallerydemo.adapter.ImageAdapter;
import anish.gallerydemo.helper.HelperFuunctions;

public class MainActivity extends AppCompatActivity {
    //  Cursor used to access the results from querying for images on the SD card.
    Context context = MainActivity.this;
    private Cursor cursor;

    // Column index for the Thumbnails Image IDs.

    private int columnIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        HelperFuunctions.setPermission(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                fetchImages();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void fetchImages() {
        // Set up an array of the Thumbnail Image ID column we want
        String[] projection = {MediaStore.Images.Thumbnails._ID};
        // Create the cursor pointing to the SDCard
        cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                projection, // Which columns to return
                null,       // Return all rows
                null,
                MediaStore.Images.Thumbnails.IMAGE_ID);
        // Get the column index of the Thumbnails Image ID
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

        GridView sdcardImages = (GridView) findViewById(R.id.gridView1);
        sdcardImages.setAdapter(new ImageAdapter(this, cursor, columnIndex));
    }
}
