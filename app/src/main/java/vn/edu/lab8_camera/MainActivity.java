package vn.edu.lab8_camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    static final int REQUEST_IMAGE_PHOTO = 1;
//    private static final String JPEG_FILE_PREFIX = "IMG_";
//    private static final String JPEG_FILE_SUFFIX = ".jpg";
//    private AlbumStorageDirFactory albumStorageDirFactory = null;
    Button btnPhoto;
    ImageView imgPhoto;
    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPhoto = findViewById(R.id.btnPhoto);
        imgPhoto = findViewById(R.id.imgPhoto);

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},888);

                }else {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,999);
                }
            }
        });
//        btnPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                File f = null;
//
//                try {
//                    f = setUpPhotoFile();
//                    mCurrentPhotoPath = f.getAbsolutePath();
//                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    f = null;
//                    mCurrentPhotoPath = null;
//                }
//                startActivityForResult(takePictureIntent, 1);
//            }
//        });
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
//            albumStorageDirFactory = new FroyoAlbumDirFactory();
//        } else {
//            albumStorageDirFactory = new BaseAlbumDirFactory();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==999 && resultCode==RESULT_OK){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imgPhoto.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"Chụp ảnh không thành công",Toast.LENGTH_SHORT).show();
        }
    }

//    private String getAlbumName() {
//        return "CameraSample";
//    }
//
//    private File getAlbumDir() {
//        File storageDir = null;
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            storageDir = albumStorageDirFactory.getAlbumStorageDir(getAlbumName());
//            if (storageDir != null) {
//                if (!storageDir.mkdirs()) {
//                    if (!storageDir.exists()) {
//                        return null;
//                    }
//                }
//            }
//        } else {
//
//        }
//        return storageDir;
//    }
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
//        File albumF = getAlbumDir();
//        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
//        return imageF;
//    }
//
//    private File setUpPhotoFile() throws IOException {
//        File f = createImageFile();
//        mCurrentPhotoPath = f.getAbsolutePath();
//        return f;
//    }
//
//    private void setPic() {
//        int targetW = imgPhoto.getWidth();
//        int targetH = imgPhoto.getHeight();
//
//        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//        bmOptions.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//        int photoW = bmOptions.outWidth;
//        int photoH = bmOptions.outHeight;
//        int scaleFactor = 1;
//        if ((targetW > 0) || (targetH > 0)) {
//            scaleFactor = Math.min(photoW / targetW, photoH / targetH);
//        }
//
//        bmOptions.inJustDecodeBounds = false;
//        bmOptions.inSampleSize = scaleFactor;
//        bmOptions.inPurgeable = true;
//
//        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
//
//        imgPhoto.setImageBitmap(bitmap);
//        imgPhoto.setVisibility(View.VISIBLE);
//    }
//
//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
//        File f = new File(mCurrentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }
//
//    private void handBigCameraPhoto() {
//        if (mCurrentPhotoPath != null) {
//            setPic();
//            galleryAddPic();
//            mCurrentPhotoPath = null;
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_OK) {
//            handBigCameraPhoto();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
