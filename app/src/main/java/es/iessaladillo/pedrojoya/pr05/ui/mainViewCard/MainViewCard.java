package es.iessaladillo.pedrojoya.pr05.ui.mainViewCard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.DataBaseUser;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;
import es.iessaladillo.pedrojoya.pr05.databinding.ActivityCardBinding;
import es.iessaladillo.pedrojoya.pr05.ui.profile.Profile;

public class MainViewCard extends AppCompatActivity {

    public static final int RC_EDIT = 1;

    private ActivityCardBinding b;
    private MainViewCardViewModel viewModel;
    private MainViewCardAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_card);
        viewModel = ViewModelProviders.of(this, new MainViewCardViewModelFactory(new DataBaseUser()))
                .get(MainViewCardViewModel.class);
        setupView();
        observeUsers();
    }

    private void setupView() {
        listAdapter = new MainViewCardAdapter(position -> {
            deleteUser(listAdapter.getItem(position));
            editIntentUser(listAdapter.getItem(position));
        });
        b.lstUsers.setHasFixedSize(true);
        b.lstUsers.setLayoutManager(new GridLayoutManager(this,
                getResources().getInteger(R.integer.main_lstUsers_columns)));
        b.lstUsers.setItemAnimator(new DefaultItemAnimator());
        b.lstUsers.setAdapter(listAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==RESULT_OK && requestCode==RC_EDIT){
            if(data!=null && data.hasExtra(Profile.EXTRA_USER_TO_CARD)){
                editUser(data.getParcelableExtra(Profile.EXTRA_USER_TO_CARD));
            }
        }
    }

    private void editIntentUser(User user) {
        Profile.startForResult(MainViewCard.this, RC_EDIT, user);
        deleteUser(user);
    }


    private void observeUsers() {
        viewModel.getUsers().observe(this, users -> {
            listAdapter.submitList(users);
            b.lblEmptyView.setVisibility(users.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    private void deleteUser(User user) {
        viewModel.deleteUser(user);
    }

    private void editUser (User user){
        viewModel.editUser(user);
    }



}
