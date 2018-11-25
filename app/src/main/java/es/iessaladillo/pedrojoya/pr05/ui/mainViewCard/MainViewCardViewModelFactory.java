package es.iessaladillo.pedrojoya.pr05.ui.mainViewCard;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaladillo.pedrojoya.pr05.data.local.DataBaseUser;

public class MainViewCardViewModelFactory implements ViewModelProvider.Factory {

    private final DataBaseUser dataBaseUser;

    public MainViewCardViewModelFactory(DataBaseUser dataBaseUser) {
        this.dataBaseUser = dataBaseUser;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewCardViewModel(dataBaseUser);
    }
}
