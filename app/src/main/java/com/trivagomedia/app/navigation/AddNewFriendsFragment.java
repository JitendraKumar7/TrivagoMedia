package com.trivagomedia.app.navigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.trivagomedia.app.R;
import com.trivagomedia.app.home.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewFriendsFragment extends Fragment implements View.OnClickListener {

    private View rootView;
    private EditText txtFirstame, txtLastname, txtUsername,
            txtEmailid, txtPassword, txtConPassword, txtInviterid;

    public AddNewFriendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_new_friends, container, false);
        ((HomeActivity) getActivity()).setPosition(7);
        initView(rootView);
        return rootView;
    }

    private void initView(final View v) {

        txtFirstame = (EditText) v.findViewById(R.id.add_txtFirstname);
        txtLastname = (EditText) v.findViewById(R.id.add_txtLastname);
        txtUsername = (EditText) v.findViewById(R.id.add_txtUsername);
        txtEmailid = (EditText) v.findViewById(R.id.add_txtEmailid);
        txtPassword = (EditText) v.findViewById(R.id.add_txtPassword);
        txtInviterid = (EditText) v.findViewById(R.id.add_txtInviter);
        txtConPassword = (EditText) v.findViewById(R.id.add_txtConPassword);

        v.findViewById(R.id.add_txtBtnAdd).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.add_txtBtnAdd:
                break;

            default:
                break;
        }
    }

}
