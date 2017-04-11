package anish.gallerydemo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    //  Cursor used to access the results from querying for images on the SD card.

    private Cursor cursor;

    // Column index for the Thumbnails Image IDs.

    private int columnIndex;


    private Context context;

    public ImageAdapter(Context context, Cursor cursor, int columnIndex) {
        this.cursor = cursor;
        this.columnIndex = columnIndex;
        this.context = context;
    }

    public int getCount() {
        int countSize = 0;
        if (cursor.getCount() != 0) {
            countSize = cursor.getCount();
        }
        return countSize;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView picturesView;
        if (convertView == null) {
            picturesView = new ImageView(context);
            // Move cursor to current position
            cursor.moveToPosition(position);
            // Get the current value for the requested column
            int imageID = cursor.getInt(columnIndex);
            // Set the content of the image based on the provided URI
            picturesView.setImageURI(Uri.withAppendedPath(
                    MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));
            picturesView.setScaleType(ImageView.ScaleType.FIT_XY);
            picturesView.setPadding(10, 10, 10, 10);
            picturesView.setLayoutParams(new GridView.LayoutParams(400, 400));
        } else {
            picturesView = (ImageView) convertView;
        }
        return picturesView;
    }
}

