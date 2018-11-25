package es.iessaladillo.pedrojoya.pr05.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;
import es.iessaladillo.pedrojoya.pr05.ui.avatar.AvatarActivity;

import static es.iessaladillo.pedrojoya.pr05.data.local.Database.getInstance;
import static es.iessaladillo.pedrojoya.pr05.utils.IntentsUtils.existAppToOpen;
import static es.iessaladillo.pedrojoya.pr05.utils.IntentsUtils.intentAdress;
import static es.iessaladillo.pedrojoya.pr05.utils.IntentsUtils.intentCallPhone;
import static es.iessaladillo.pedrojoya.pr05.utils.IntentsUtils.intentEmail;
import static es.iessaladillo.pedrojoya.pr05.utils.IntentsUtils.intentWebSearch;
import static es.iessaladillo.pedrojoya.pr05.utils.ValidationUtils.isValidEmail;
import static es.iessaladillo.pedrojoya.pr05.utils.ValidationUtils.isValidPhone;
import static es.iessaladillo.pedrojoya.pr05.utils.ValidationUtils.isValidUrl;

@SuppressWarnings("WeakerAccess")
public class Profile extends AppCompatActivity {

    public final int RC_IMG_AVATAR=1;
    public static final String EXTRA_USER_FROM_CARD="EXTRA_USER_FROM_CARD";
    public static final String EXTRA_USER_TO_CARD="EXTRA_USER_TO_CARD";

    private TextView lblName;
    private EditText txtName;
    private TextView lblPhoneNumber;
    private EditText txtPhoneNumber;
    private TextView lblEmail;
    private EditText txtEmail;
    private TextView lblAddress;
    private EditText txtAddress;
    private TextView lblWeb;
    private EditText txtWeb;
    private ImageView imgEmail;
    private ImageView imgPhoneNumber;
    private ImageView imgAddress;
    private ImageView imgWeb;
    private ImageView imgAvatar;
    private TextView lblAvatar;

    private User userIn;

    private ProfileViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        setupView();
        getIntentData();

        //OnFocusListener
        txtName.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblName, hasFocus));
        txtPhoneNumber.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblPhoneNumber, hasFocus));
        txtEmail.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblEmail, hasFocus));
        txtAddress.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblAddress, hasFocus));
        txtWeb.setOnFocusChangeListener((v, hasFocus) -> setLblBold(lblWeb, hasFocus));
        //OnClickListener
        imgEmail.setOnClickListener(v -> {
            //Check out if exist app
            if (existAppToOpen(this, intentEmail(String.valueOf(txtEmail.getText())))) {
                startActivity(intentEmail(String.valueOf(txtEmail.getText())));
            }
        });

        imgPhoneNumber.setOnClickListener(v -> {
            if(existAppToOpen(this,intentCallPhone(String.valueOf(txtPhoneNumber.getText())))){
                startActivity(intentCallPhone(String.valueOf(txtPhoneNumber.getText())));
            }
        });

        imgAddress.setOnClickListener(v -> {
            if(existAppToOpen(this, intentAdress(String.valueOf(txtAddress.getText())))){
                startActivity(intentAdress(String.valueOf(txtAddress.getText())));
            }
        });

        imgWeb.setOnClickListener(v -> {
            if(existAppToOpen(this, intentWebSearch(String.valueOf(txtWeb.getText())))){
                startActivity(intentWebSearch(String.valueOf(txtWeb.getText())));
            }
        });

        imgAvatar.setOnClickListener(v -> {
            AvatarActivity.startForResult(Profile.this, RC_IMG_AVATAR, viewModel.getAvatar());
        });
        lblAvatar.setOnClickListener(v -> {
            AvatarActivity.startForResult(Profile.this, RC_IMG_AVATAR, viewModel.getAvatar());
        });
        //OnEditorActionListener IME
        txtWeb.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId==EditorInfo.IME_ACTION_DONE){
                save();
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        //TextChangedListener
        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkName();
            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkEmail();
            }
        });

        txtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPhoneNumber();
            }
        });

        txtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkAddress();
            }
        });

        txtWeb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkWeb();
            }
        });
    }

    //Reception user of intent of the mainViewCard
    private void getIntentData() {
        Intent intent = getIntent();
        if(intent!=null && intent.hasExtra(EXTRA_USER_FROM_CARD)){
            userIn = intent.getParcelableExtra(EXTRA_USER_FROM_CARD);
            fillFieldsProfile(userIn);
        }
    }

    private void fillFieldsProfile(User userIn) {
        lblName.setText(userIn.getName());
        lblEmail.setText(userIn.getEmail());
        lblPhoneNumber.setText(userIn.getPhoneNumber());
        lblAddress.setText(userIn.getAddress());
        lblWeb.setText(userIn.getWeb());
        imgAvatar.setImageResource(userIn.getAvatar().getImageResId());
    }

    private void intentUserToCard(){
        Intent intent = new Intent();
        User userOut = new User(
                userIn.getId(), txtName.getText().toString(), txtEmail.getText().toString(), txtPhoneNumber.getText().toString(), txtAddress.getText().toString(), txtWeb.getText().toString(), viewModel.getAvatar());
        intent.putExtra(EXTRA_USER_TO_CARD, userOut);
        setResult(RESULT_OK, intent);
        finish();
    }

    //startForResult of the MainCardView
    public static void startForResult(Activity activity, int requestCode, User user){
        Intent intent = new Intent(activity, Profile.class);
        intent.putExtra(EXTRA_USER_FROM_CARD, user);
        activity.startActivityForResult(intent, requestCode);
    }

    //Recepcion datos

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==RC_IMG_AVATAR){
            if(data!=null && data.hasExtra(AvatarActivity.EXTRA_AVATAR_TO_MAIN)){
                viewModel.setAvatar(data.getParcelableExtra(AvatarActivity.EXTRA_AVATAR_TO_MAIN));
                imgAvatar.setImageResource(viewModel.getAvatar().getImageResId());
                lblAvatar.setText(viewModel.getAvatar().getName());
            }
        }
    }

    //Function for Validate
    private boolean checkName() {
        if(TextUtils.isEmpty(txtName.getText())){
            txtName.setError(getString(R.string.main_invalid_data));
            lblName.setEnabled(false);
            return false;
        }else{
            lblName.setEnabled(true);
            return true;
        }
    }

    private boolean checkEmail() {
        if(!isValidEmail(String.valueOf(txtEmail.getText()))){
            setDisableIcon(txtEmail, lblEmail, imgEmail);
            return false;
        }else{
            setEnableIcon(lblEmail, imgEmail);
            return true;
        }
    }

    private boolean checkPhoneNumber() {
        if(!isValidPhone(String.valueOf(txtPhoneNumber.getText()))){
            setDisableIcon(txtPhoneNumber, lblPhoneNumber, imgPhoneNumber);
            return false;
        }else{
            setEnableIcon(lblPhoneNumber, imgPhoneNumber);
            return true;
        }
    }

    private boolean checkAddress() {
        if(TextUtils.isEmpty(txtAddress.getText())){
            setDisableIcon(txtAddress, lblAddress, imgAddress);
            return false;
        }else{
            setEnableIcon(lblAddress, imgAddress);
            return true;
        }
    }

    private boolean checkWeb() {
        if(!isValidUrl(String.valueOf(txtWeb.getText()))){
            setDisableIcon(txtWeb, lblWeb, imgWeb);
            return false;
        }else{
            setEnableIcon(lblWeb, imgWeb);
            return true;
        }
    }

    private void setEnableIcon(TextView lbl, ImageView img) {
        lbl.setEnabled(true);
        img.setEnabled(true);
    }

    private void setDisableIcon(EditText txt, TextView lbl, ImageView img) {
        txt.setError(getString(R.string.main_invalid_data));
        lbl.setEnabled(false);
        img.setEnabled(false);
    }


    //Function Label Bold
    private void setLblBold(TextView lbl, boolean hasFocus){
        if(hasFocus){
            lbl.setTypeface(Typeface.DEFAULT,Typeface.BOLD);
        }else{
            lbl.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        }
    }

    private void setupView() {
        imgAvatar=ActivityCompat.requireViewById(this, R.id.imgAvatar);
        lblAvatar=ActivityCompat.requireViewById(this, R.id.lblAvatar);
        lblName=ActivityCompat.requireViewById(this,R.id.lblName);
        txtName=ActivityCompat.requireViewById(this, R.id.txtName);
        lblPhoneNumber=ActivityCompat.requireViewById(this, R.id.lblPhonenumber);
        txtPhoneNumber=ActivityCompat.requireViewById(this, R.id.txtPhonenumber);
        imgPhoneNumber=ActivityCompat.requireViewById(this, R.id.imgPhonenumber);
        lblEmail=ActivityCompat.requireViewById(this, R.id.lblEmail);
        txtEmail=ActivityCompat.requireViewById(this, R.id.txtEmail);
        imgEmail=ActivityCompat.requireViewById(this, R.id.imgEmail);
        lblAddress=ActivityCompat.requireViewById(this, R.id.lblAddress);
        txtAddress=ActivityCompat.requireViewById(this, R.id.txtAddress);
        imgAddress=ActivityCompat.requireViewById(this, R.id.imgAddress);
        lblWeb=ActivityCompat.requireViewById(this, R.id.lblWeb);
        txtWeb=ActivityCompat.requireViewById(this, R.id.txtWeb);
        imgWeb=ActivityCompat.requireViewById(this, R.id.imgWeb);
        //Set default Avatar
        if (viewModel.getAvatar()==null) {
            viewModel.setAvatar(getInstance().getDefaultAvatar());
        }
        imgAvatar.setImageResource(viewModel.getAvatar().getImageResId());
        imgAvatar.setTag(viewModel.getAvatar().getImageResId());
        lblAvatar.setText(viewModel.getAvatar().getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuSave) {
            save();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        boolean isCheckName, isCheckEmail, isCheckPhoneNumber, isCheckAddress, isCheckWeb;
        isCheckName=checkName();
        isCheckEmail=checkEmail();
        isCheckPhoneNumber=checkPhoneNumber();
        isCheckAddress=checkAddress();
        isCheckWeb=checkWeb();
        if(isCheckName && isCheckEmail && isCheckPhoneNumber && isCheckAddress && isCheckWeb){
            Snackbar.make(lblName,R.string.main_saved_succesfully, Snackbar.LENGTH_LONG).show();
            intentUserToCard();
        }else{
            Snackbar.make(lblName, R.string.main_error_saving, Snackbar.LENGTH_LONG).show();
        }
    }

}
