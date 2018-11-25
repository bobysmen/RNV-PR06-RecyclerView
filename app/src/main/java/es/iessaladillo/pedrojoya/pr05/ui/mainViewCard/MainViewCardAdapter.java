package es.iessaladillo.pedrojoya.pr05.ui.mainViewCard;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.pedrojoya.pr05.R;
import es.iessaladillo.pedrojoya.pr05.data.local.model.User;

public class MainViewCardAdapter extends ListAdapter<User, MainViewCardAdapter.ViewHolder> {

    private final OnUserClickListener onUserClickListener;

    public MainViewCardAdapter(OnUserClickListener onUserClickListener) {
        super(new DiffUtil.ItemCallback<User>(){

            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                        TextUtils.equals(oldItem.getEmail(), newItem.getEmail()) &&
                        TextUtils.equals(oldItem.getAddress(), newItem.getAddress()) &&
                        oldItem.getAvatar().getImageResId() == newItem.getAvatar().getImageResId();
            }
        });
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.activity_card_item, parent, false), onUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public User getItem(int position) {
        return super.getItem(position);
    }



    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblName;
        private final TextView lblEmail;
        private final TextView lblPhoneNumber;
        private final ImageView imgAvatar;
        private final Button btnEdit;
        private final Button btnDelete;

        public ViewHolder(@NonNull View itemView, OnUserClickListener onUserClickListener) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.lblEmail);
            lblPhoneNumber = ViewCompat.requireViewById(itemView, R.id.lblPhonenumber);
            imgAvatar = ViewCompat.requireViewById(itemView, R.id.imgAvatar);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.itemButtonEdit);
            btnDelete = ViewCompat.requireViewById(itemView, R.id.itemButtonDelete);
            btnEdit.setOnClickListener(v -> onUserClickListener.onItemClick(getAdapterPosition()));
            btnDelete.setOnClickListener(v -> onUserClickListener.onItemClick(getAdapterPosition()));
        }

        void bind(User user){
            lblName.setText(user.getName());
            lblEmail.setText(user.getEmail());
            lblPhoneNumber.setText(user.getPhoneNumber());
            imgAvatar.setImageResource(user.getAvatar().getImageResId());
        }
    }
}
