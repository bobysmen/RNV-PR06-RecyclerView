package es.iessaladillo.pedrojoya.pr05.ui.profile;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.pr05.data.local.model.Avatar;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;

public class ProfileViewModel extends ViewModel {
    public Avatar avatar;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
