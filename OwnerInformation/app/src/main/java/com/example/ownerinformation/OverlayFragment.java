package com.example.ownerinformation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class OverlayFragment extends Fragment {

    public static OverlayFragment newInstance() {
        OverlayFragment fragment = new OverlayFragment();

        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_overlay, container, false );
        TextView t = (TextView) view.findViewById( R.id.textView );
        t.setText( "Prerna Patra, call\n 9652504242 \nif lost" );
        Button close = (Button) view.findViewById( R.id.close );
        close.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        } );

        return view;
    }
}