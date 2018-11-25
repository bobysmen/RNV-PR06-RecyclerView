package es.iessaladillo.pedrojoya.pr05.ui.avatar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.Database;
import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr05.utils.ResourcesUtils;

public class AvatarActivity extends AppCompatActivity {

    public static final String EXTRA_AVATAR_FROM_MAIN ="EXTRA_AVATAR_FROM_MAIN";
    public static final String EXTRA_AVATAR_TO_MAIN="EXTRA_AVATAR_TO_MAIN";
    public final long CAT1_ID=1;
    public final long CAT2_ID=2;
    public final long CAT3_ID=3;
    public final long CAT4_ID=4;
    public final long CAT5_ID=5;
    public final long CAT6_ID=6;

    private Avatar avatarIn;
    private ImageView imgAvatar1;
    private ImageView imgAvatar2;
    private ImageView imgAvatar3;
    private ImageView imgAvatar4;
    private ImageView imgAvatar5;
    private ImageView imgAvatar6;

    private List<Avatar> avatares;
    private TextView lblAvatar1;
    private TextView lblAvatar2;
    private TextView lblAvatar3;
    private TextView lblAvatar4;
    private TextView lblAvatar5;
    private TextView lblAvatar6;

    private AvatarActivityViewModel viewModel;

    @VisibleForTesting
    public static final String EXTRA_AVATAR = "EXTRA_AVATAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        viewModel = ViewModelProviders.of(this).get(AvatarActivityViewModel.class);
        setupView();
        getIntentData();

        imgAvatar1.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar1.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        imgAvatar2.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar2.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        imgAvatar3.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar3.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        imgAvatar4.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar4.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        imgAvatar5.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar5.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        imgAvatar6.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar6.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        lblAvatar1.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar1.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        lblAvatar2.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar2.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        lblAvatar3.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar3.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        lblAvatar4.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar4.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        lblAvatar5.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar5.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });

        lblAvatar6.setOnClickListener(v -> {
            allImageColorDefault();
            imgAvatar6.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        });
    }

    private void intentAvatarToMain(long i) {
        Intent intentOut = new Intent();
        intentOut.putExtra(EXTRA_AVATAR_TO_MAIN, avatares.get((int) i-1));
        setResult(RESULT_OK, intentOut);
        finish();
    }

    private void allImageColorDefault(){
        imgAvatar1.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_not_selected_image_alpha));
        imgAvatar2.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_not_selected_image_alpha));
        imgAvatar3.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_not_selected_image_alpha));
        imgAvatar4.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_not_selected_image_alpha));
        imgAvatar5.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_not_selected_image_alpha));
        imgAvatar6.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_not_selected_image_alpha));
    }

    private void setupView() {
        imgAvatar1=ActivityCompat.requireViewById(this, R.id.imgAvatar1);
        imgAvatar2=ActivityCompat.requireViewById(this, R.id.imgAvatar2);
        imgAvatar3=ActivityCompat.requireViewById(this, R.id.imgAvatar3);
        imgAvatar4=ActivityCompat.requireViewById(this, R.id.imgAvatar4);
        imgAvatar5=ActivityCompat.requireViewById(this, R.id.imgAvatar5);
        imgAvatar6=ActivityCompat.requireViewById(this, R.id.imgAvatar6);

        lblAvatar1=ActivityCompat.requireViewById(this, R.id.lblAvatar1);
        lblAvatar2=ActivityCompat.requireViewById(this, R.id.lblAvatar2);
        lblAvatar3=ActivityCompat.requireViewById(this, R.id.lblAvatar3);
        lblAvatar4=ActivityCompat.requireViewById(this, R.id.lblAvatar4);
        lblAvatar5=ActivityCompat.requireViewById(this, R.id.lblAvatar5);
        lblAvatar6=ActivityCompat.requireViewById(this, R.id.lblAvatar6);


        avatares=Database.getInstance().queryAvatars();
        imgAvatar1.setImageResource(avatares.get(0).getImageResId());
        lblAvatar1.setText(avatares.get(0).getName());
        imgAvatar2.setImageResource(avatares.get(1).getImageResId());
        lblAvatar2.setText(avatares.get(1).getName());
        imgAvatar3.setImageResource(avatares.get(2).getImageResId());
        lblAvatar3.setText(avatares.get(2).getName());
        imgAvatar4.setImageResource(avatares.get(3).getImageResId());
        lblAvatar4.setText(avatares.get(3).getName());
        imgAvatar5.setImageResource(avatares.get(4).getImageResId());
        lblAvatar5.setText(avatares.get(4).getName());
        imgAvatar6.setImageResource(avatares.get(5).getImageResId());
        lblAvatar6.setText(avatares.get(5).getName());
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(EXTRA_AVATAR_FROM_MAIN)){
            avatarIn = intent.getParcelableExtra(EXTRA_AVATAR_FROM_MAIN);
            viewModel.setAvatar(avatarIn);
            setColorSelectAvatar(viewModel.getAvatar().getId());
        }
    }

    private void setColorSelectAvatar(long id) {
        if(id==CAT1_ID){
            imgAvatar1.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        }else if(id==CAT2_ID){
            imgAvatar2.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        }else if(id==CAT3_ID){
            imgAvatar3.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        }else if(id==CAT4_ID){
            imgAvatar4.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        }else if(id==CAT5_ID){
            imgAvatar5.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        }else if(id==CAT6_ID){
            imgAvatar6.setAlpha(ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha));
        }
    }


    public static void startForResult(Activity activity, int requestCode,
                                      Avatar avatar) {
        Intent intent = new Intent(activity, AvatarActivity.class);
        intent.putExtra(EXTRA_AVATAR_FROM_MAIN, avatar);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_avatar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSelect) {
            if(imgAvatar1.getAlpha()==ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha)){
                intentAvatarToMain(CAT1_ID);
            }else if(imgAvatar2.getAlpha()==ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha)){
                intentAvatarToMain(CAT2_ID);
            }else if(imgAvatar3.getAlpha()==ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha)){
                intentAvatarToMain(CAT3_ID);
            }else if(imgAvatar4.getAlpha()==ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha)){
                intentAvatarToMain(CAT4_ID);
            }else if(imgAvatar5.getAlpha()==ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha)){
                intentAvatarToMain(CAT5_ID);
            }else if(imgAvatar6.getAlpha()==ResourcesUtils.getFloat(this, R.dimen.avatar_selected_image_alpha)){
                intentAvatarToMain(CAT6_ID);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
