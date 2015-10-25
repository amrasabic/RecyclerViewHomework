package amrasabic.bitcamp.ba.recyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class EditUserActivity extends Activity {

    private User mUser;
    private UUID mUUID;

    private EditText mUserName;
    private EditText mUserSurname;
    private Button mSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        mUserName = (EditText) findViewById(R.id.edit_name);
        mUserSurname = (EditText) findViewById(R.id.edit_surname);
        mSaveButton = (Button) findViewById(R.id.save_button);

        mUUID = (UUID) getIntent().getExtras().getSerializable("nesto drugo");
        mUser = UserList.getUserByUUID(mUUID);

        mUserName.setText(mUser.getName().toString());
        mUserSurname.setText(mUser.getSurname().toString());

        UserList.updateUser(mUser.getUUID(), mUserName.getText().toString(), mUserSurname.getText().toString());

        mSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserList.getUserByUUID(mUser.getUUID()).setName(mUserName.getText().toString());
                UserList.getUserByUUID(mUser.getUUID()).setSurname(mUserSurname.getText().toString());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

    }
}
