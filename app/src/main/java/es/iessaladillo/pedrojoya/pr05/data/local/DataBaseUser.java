package es.iessaladillo.pedrojoya.pr05.data.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;
import es.iessaladillo.pedrojoya.pr05.ui.mainViewCard.MainViewCard;
import es.iessaladillo.pedrojoya.pr05.ui.profile.Profile;

public class DataBaseUser {

    private Database avatarDataBase = Database.getInstance();

    private final ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User(0,"Ruben", "ruben@ruben.com","666111222","Calle Guerrita", "www.google.es", avatarDataBase.queryAvatar(1)),
            new User(1,"Ismael", "ismael@ismael.com","666000789","Calle Falsa", "www.google.es", avatarDataBase.queryAvatar(2)),
            new User(2,"Raquel", "raquel@raquel.com","666147258","Calle Incierta", "www.google.es", avatarDataBase.queryAvatar(3))
    ));

    private final MutableLiveData <List<User>> usersLiveData = new MutableLiveData<>();

    public DataBaseUser() {
        updateUsersLiveData();
    }

    private void updateUsersLiveData() {
        usersLiveData.setValue(new ArrayList<>(users));
    }

    public LiveData<List<User>> getUsersLiveData() {
        return usersLiveData;
    }

    public void deleteUser(User user){
        users.remove(user);
        updateUsersLiveData();
    }

    public void addUser(User user) {
        //when the user is new and i dont know its id
        if (user.getId()==-1) {
            user.setId(users.size());
            users.add(user);
            updateUsersLiveData();
        }else{
            users.add(user);
            updateUsersLiveData();
        }

    }
}
