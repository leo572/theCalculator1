package com.example.thecalculator;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.util.Httputil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Fragment3 extends Fragment {
    ImageView imageView3;
    Button button3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout4,container,false);
        imageView3=view.findViewById(R.id.imageView3);
        button3=view.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                String address="http://guolin.tech/api/bing_pic";
                Httputil.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String img=response.body().string();
                        SharedPreferences.Editor editor= PreferenceManager.
                                getDefaultSharedPreferences(getContext()).edit();
                        editor.putString("img",img);
                        editor.apply();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                Glide.with(getContext()).load(img).into(imageView3);
                            }
                        });
                    }
                });
            }
        });
        return view;
    }
}
