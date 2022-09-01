package com.example.bookapp.wode;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.bookapp.R;
import com.example.bookapp.shangcheng.shuchengFragment;
import com.example.bookapp.shujiaActivity;

public class wodeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private String userName;
    private String userPwd;
    private TextView welcome;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wode_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout login = getActivity().findViewById(R.id.login);
        LinearLayout register = (LinearLayout)view.findViewById(R.id.register);
        LinearLayout change = (LinearLayout)view.findViewById(R.id.change);
        LinearLayout exit = (LinearLayout)view.findViewById(R.id.exit);
        welcome = (TextView) view.findViewById(R.id.welcome);
        Intent intent = getActivity().getIntent();
        userName = intent.getStringExtra("userName");
        userPwd = intent.getStringExtra("userPwd");
        welcome.setText("欢迎"+userName+"登录");
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        change.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.login:
                intent = new Intent(getActivity(), WodeLoginActivity.class);
                startActivity(intent);
//                shujiaActivity activity = (shujiaActivity)v.getContext();
//                activity.finish();
                break;
            case R.id.register:
                if(getActivity().getIntent().getStringExtra("userName") == null){
                    intent = new Intent(getActivity(), WodeRegisterActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "登录状态不能注册", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.change:
                intent = new Intent(getActivity(), WodeChangeActivity.class);
                intent.putExtra("userPwd",userPwd);
                intent.putExtra("userName",userName);
                startActivity(intent);
                break;
            case R.id.exit:
                getActivity().getIntent().removeExtra("userName");
                intent = getActivity().getIntent();
                userName = intent.getStringExtra("userName");
                userPwd = intent.getStringExtra("userPwd");
                welcome.setText("欢迎"+userName+"登录");
                shujiaActivity activity1 = (shujiaActivity) getActivity();
                activity1.mark2 = true;
                Toast.makeText(activity1, "注销成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
