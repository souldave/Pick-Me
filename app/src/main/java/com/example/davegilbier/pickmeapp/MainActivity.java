package com.example.davegilbier.pickmeapp;


        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_PICK_PHOTO = 101;

    private View mSelectedPhotoLayout;
    private ImageView mSelectedPhoto;

    private Uri mSelectedPhotoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        mSelectedPhotoLayout = findViewById(R.id.selectedPhotoLayout);
        mSelectedPhoto = (ImageView) findViewById(R.id.selectedPhoto);
    }

    public void pickPhoto(View view) {
        Intent pickIntent = new Intent(Intent.ACTION_GET_CONTENT);
        pickIntent.setType("image/*");

        Intent chooser = Intent.createChooser(pickIntent, getString(R.string.text_pick_photo));
        startActivityForResult(chooser, REQ_PICK_PHOTO);
    }

    public void share(View view) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/*");
        shareIntent.putExtra(Intent.EXTRA_STREAM, mSelectedPhotoUri);

        Intent chooser = Intent.createChooser(shareIntent, getString(R.string.text_share));
        startActivity(chooser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_PICK_PHOTO && resultCode == RESULT_OK) {
            mSelectedPhotoUri = data.getData();

            mSelectedPhotoLayout.setVisibility(View.VISIBLE);
            mSelectedPhoto.setImageURI(mSelectedPhotoUri);
        }
    }
}

