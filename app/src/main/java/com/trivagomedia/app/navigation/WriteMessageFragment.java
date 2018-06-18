package com.trivagomedia.app.navigation;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trivagomedia.app.R;
import com.trivagomedia.app.home.HomeActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class WriteMessageFragment extends Fragment implements View.OnClickListener {

    private Uri imageUri;
    private View rootView;
    private ImageView ivMainView;
    private static final int SELECT_VIDEO = 7;
    private static final int SELECT_IMAGE = 77;
    private static final int SELECT_CAMERA = 777;
    private final String TAG = WriteMessageFragment.class.getSimpleName();

    public WriteMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_write_message, container, false);
        ((HomeActivity) getActivity()).setPosition(2);
        initView(rootView);
        return rootView;
    }

    private void initView(View v) {
        ivMainView = (ImageView) v.findViewById(R.id.write_ivMainView);
        v.findViewById(R.id.write_ivVideo).setOnClickListener(this);
        v.findViewById(R.id.write_ivImage).setOnClickListener(this);
        v.findViewById(R.id.write_ivCamera).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.write_ivVideo:
                Intent videoPickerIntent = new Intent();
                videoPickerIntent.setType("video/*");
                videoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(videoPickerIntent, "Select a Video "), SELECT_VIDEO);
                break;

            case R.id.write_ivImage:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                //startActivityForResult(photoPickerIntent, SELECT_IMAGE);
                startActivityForResult(Intent.createChooser(photoPickerIntent, "Select a Video "), SELECT_IMAGE);
                break;

            case R.id.write_ivCamera:
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "Camera_Example.jpg");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                imageUri = getActivity().getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(cameraIntent, SELECT_CAMERA);
                break;

            default:
                break;

        }
    }

    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
        cursor.close();

        return path;
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.MediaColumns.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            if (requestCode == SELECT_VIDEO) {
                System.out.println("SELECT_VIDEO");
                Uri selectedImageUri = data.getData();
                String selectedPath = getPath(selectedImageUri);

                //uploadVideo(selectedPath);
                Log.e(TAG, "SELECT_VIDEO Path : " + selectedPath);
                ivMainView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(selectedPath,
                        MediaStore.Video.Thumbnails.FULL_SCREEN_KIND));
            } else if (requestCode == SELECT_IMAGE) {
                System.out.println("SELECT_VIDEO");
                Uri selectedImageUri = data.getData();
                String selectedPath = getRealPathFromURI(selectedImageUri);
                Picasso.with(getActivity()).load(selectedImageUri).into(ivMainView);

                //uploadVideo(selectedPath);
                Log.e(TAG, "SELECT_IMAGE Path : " + selectedPath);
            } else if (requestCode == SELECT_CAMERA) {
                String selectedPath = getRealPathFromURI(imageUri);
                Log.e(TAG, "SELECT_CAMERA Path : " + selectedPath);
                Picasso.with(getActivity()).load(imageUri).into(ivMainView);

            }
        }
    }


}
