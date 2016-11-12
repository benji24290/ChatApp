package ch.juventus.bs.firebaseinstachat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mMessageRecyclerView;

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView userImageView;
        public Button userButton;


        public UserViewHolder(View v) {
            super(v);
            userButton = (Button) itemView.findViewById(R.id.userButton);
            userImageView = (CircleImageView) itemView.findViewById(R.id.userImageView);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Initialize ProgressBar and RecyclerView.
        mMessageRecyclerView = (RecyclerView) findViewById(R.id.userRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setStackFromEnd(true);
        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
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
                                              final User friendlyUser, int position) {
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
                viewHolder.userButton.setText(friendlyUser.getName());
                viewHolder.userButton.setTag(friendlyUser.getUid());
                viewHolder.userButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //Intent i = new Intent(UserOverview.this, privateChatActivity.class);
                        //i.putExtra("user",friendlyUser.getUid()); //Your id
                        Intent i = new Intent(UserOverview.this, privateChatActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("partnerId",friendlyUser.getUid());
                        extras.putString("partnerName",friendlyUser.getName());
                        i.putExtras(extras);
                        startActivity(i);
                        finish();
                        return;
                    }
                });
                /*
                uButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(UserOverview.this, privateChatActivity.class);
                        i.putExtra("uid",friendlyUser.getName()); //Your id
                        startActivity(i);
                        finish();
                        return;
                    }
                });*/


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

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mFirebaseAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                // If the recycler view is initially being loaded or the
                // user is at the bottom of the list, scroll to the bottom
                // of the list to show the newly added message.
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    mMessageRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mMessageRecyclerView.setLayoutManager(mLinearLayoutManager);
        mMessageRecyclerView.setAdapter(mFirebaseAdapter);





    }

}
