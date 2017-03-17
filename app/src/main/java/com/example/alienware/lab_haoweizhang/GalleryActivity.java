package com.example.alienware.lab_haoweizhang;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 101;
    private static String pictureAb;
    private GridView gridView;
    private Button open;
    private List<ImageInfo> items = new ArrayList<>();
    private GalleryAdapter adapter;
    private static final String[] STORE_IMAGES = {
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DESCRIPTION,
            MediaStore.Images.Media._ID
    };
    private FrameLayout largeFl;
    private ImageView largeImg;
    private Button close, share, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        gridView = (GridView) findViewById(R.id.gridView);
        open = (Button) findViewById(R.id.open);
        largeImg = (ImageView) findViewById(R.id.largeImg);
        close = (Button) findViewById(R.id.close);
        share = (Button) findViewById(R.id.share);
        delete = (Button) findViewById(R.id.delete);
        adapter = new GalleryAdapter(this);
        largeFl = (FrameLayout) findViewById(R.id.largeFl);
        gridView.setAdapter(adapter);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File[] files = pictureDirectory.listFiles(new Filter(".jpg"));
        for (int i = 0; files != null && i < files.length; i++) {
            ImageInfo info = new ImageInfo();
            info.fullName = files[i].getAbsolutePath();
            items.add(info);
        }
//        Cursor cursor = getContentResolver()
//                .query(
//                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                        null,
//                        MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
//                        new String[]{"image/jpeg", "image/png"},
//                        MediaStore.Images.Media.DATE_MODIFIED
//                );
//        while (cursor.moveToNext()) {
//            ImageInfo info = new ImageInfo();
//            //获取图片的名称
//            info.name = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
//            //获取图片的生成日期
//            byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            //获取图片的详细信息
//            info.desc = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
//            info.fullName = new String(data, 0, data.length - 1);
//            items.add(info);
//        }
        adapter.setData(items);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (!pictureDirectory.exists()) {
                    pictureDirectory.mkdirs();
                }
                pictureAb = pictureDirectory.getAbsolutePath() + File.separator + getPictureName();
                File imageFile = new File(pictureAb);

                if (!imageFile.exists()) {
                    try {
                        imageFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Uri pictureUri = Uri.fromFile(imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                largeFl.setVisibility(View.VISIBLE);
                ImageInfo info = adapter.getItem(i);
                File file = new File(info.fullName);
                Uri imageUri = Uri.fromFile(file);
                Glide.with(GalleryActivity.this).load(imageUri).into(largeImg);
                delete.setTag(info);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageInfo in = (ImageInfo) view.getTag();
                        File f = new File(in.fullName);
                        f.delete();
                        items.remove(in);
                        adapter.setData(items);
                        largeFl.setVisibility(View.GONE);
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        largeFl.setVisibility(View.GONE);
                    }
                });
                share.setTag(info);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageInfo in = (ImageInfo) view.getTag();
                        String imagePath = in.fullName;
                        Uri imageUri = Uri.fromFile(new File(imagePath));
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                        shareIntent.setType("image/*");
                        startActivity(Intent.createChooser(shareIntent, "Share"));
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                if (pictureAb != null) {
                    ImageInfo info = new ImageInfo();
                    info.fullName = pictureAb;
                    items.add(0, info);
                    adapter.setData(items);
                }
                try {
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inSampleSize = 2;
//                    Bitmap bm = BitmapFactory.decodeFile(pictureAb, options);
//
//                    ImageView imgView = (ImageView) findViewById(R.id.photo_taken);
//                    imgView.setImageBitmap(bm);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private String getPictureName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        return "BestMoments" + timestamp + ".jpg";
    }

    static class Filter implements FilenameFilter {
        private String type;

        public Filter(String type) {
            this.type = type;
        }

        public boolean accept(File dir, String name) {
            return name.endsWith(type);
        }
    }
}
