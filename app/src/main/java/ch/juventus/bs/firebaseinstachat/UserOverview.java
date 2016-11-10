package ch.juventus.bs.firebaseinstachat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserOverview extends AppCompatActivity {


    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<User, UserOverview.UserViewHolder> mFirebaseAdapter;
    private ProgressBar mProgressBar;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public TextView userTextView;
        public CircleImageView userImageView;

        public UserViewHolder(View v) {
            super(v);
            userTextView = (TextView) itemView.findViewById(R.id.userTextView);
            userImageView = (CircleImageView) itemView.findViewById(R.id.userImageView);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Init Progressbar
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        // New child entries
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<User,
                UserOverview.UserViewHolder>(
                User.class,
                R.layout.item_user,
                UserOverview.UserViewHolder.class,
                mFirebaseDatabaseReference.child("user")) {

            @Override
            protected void populateViewHolder(UserOverview.UserViewHolder viewHolder,
                                              User friendlyUser, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.userTextView.setText(friendlyUser.getEmail());
                if (friendlyUser.getPhotoUrl() == null) {
                    viewHolder.userImageView
                            .setImageDrawable(ContextCompat
                                    .getDrawable(UserOverview.this,
                                            R.drawable.ic_account_circle_black_36dp));
                } else {
                    Glide.with(UserOverview.this)
                            .load(friendlyUser.getPhotoUrl())
                            .into(viewHolder.userImageView);
                }
            }
        };

    }

}
