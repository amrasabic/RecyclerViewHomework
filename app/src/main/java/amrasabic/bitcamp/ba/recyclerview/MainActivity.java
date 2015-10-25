package amrasabic.bitcamp.ba.recyclerview;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static UserList mUserList = new UserList();

    private EditText mNameText;
    private EditText mSurnameText;
    private Button mAddButton;

    private RadioButton mSortByNameButton;
    private RadioButton mSortBySurnameButton;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameText = (EditText) findViewById(R.id.name);
        mSurnameText = (EditText) findViewById(R.id.surname);

        mAddButton = (Button) findViewById(R.id.add_person);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        userAdapter = new UserAdapter(mUserList);
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameText.getText().toString();
                String surname = mSurnameText.getText().toString();

                if(name.equals("") || surname.equals("")) {
                    Toast.makeText(MainActivity.this, R.string.insert_values, Toast.LENGTH_SHORT).show();
                } else {
                    mUserList.addUser(name, surname);
                    userAdapter.notifyDataSetChanged();
                }

            }
        });


        mSortByNameButton = (RadioButton) findViewById(R.id.sort_by_name);
        mSortBySurnameButton = (RadioButton) findViewById(R.id.sort_by_surname);

        mSortByNameButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mSortBySurnameButton.setChecked(false);
                    Collections.sort(UserList.getUsers(), new Comparator<User>() {
                        @Override
                        public int compare(User first, User second) {
                            return first.getName().compareTo(second.getName());
                        }
                    });
                    userAdapter.notifyDataSetChanged();
                    updateUI();
                }
            }
        });


        mSortBySurnameButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mSortByNameButton.setChecked(false);
                    Collections.sort(UserList.getUsers(), new Comparator<User>() {
                        @Override
                        public int compare(User first, User second) {
                            return first.getSurname().compareTo(second.getSurname());
                        }
                    });
                    userAdapter.notifyDataSetChanged();
                    updateUI();
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        List<User> users = UserList.getUsers();
        userAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(userAdapter);
    }

    private class UserView extends RecyclerView.ViewHolder {

        private TextView nameText;
        private TextView surnameText;
        private TextView dateText;
        private Button deleteButton;
        private Button editButton;

        public UserView(View itemView) {
            super(itemView);

            nameText = (TextView) itemView.findViewById(R.id.user_name);
            surnameText = (TextView) itemView.findViewById(R.id.user_surname);
            dateText = (TextView) itemView.findViewById(R.id.user_date);
            deleteButton = (Button) itemView.findViewById(R.id.delete_button);
            editButton = (Button) itemView.findViewById(R.id.edit_button);
        }

    }

    private class UserAdapter extends RecyclerView.Adapter<UserView> {

        private UserList mUserList;

        public UserAdapter(UserList userList) {
            this.mUserList = userList;
        }

        @Override
        public UserView onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

            View view = layoutInflater.inflate(R.layout.user_layout, parent, false);
            return new UserView(view);
        }

        @Override
        public void onBindViewHolder(final UserView holder, int position) {
            final User user = mUserList.getUser(position);

            holder.nameText.setText(user.getName().toString());
            holder.surnameText.setText(user.getSurname().toString());
            holder.dateText.setText(user.getDate().toString());

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserList.removeUser(user.getUUID());
                    updateUI();
                }
            });

            holder.editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, EditUserActivity.class);
                    i.putExtra("nesto drugo", user.getUUID());
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mUserList.getSize();
        }
    }

}
