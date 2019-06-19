package com.example.signatureclientdevice;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;


public class SarverDataFrom extends Fragment {

    public final String TAG = "FORMFRAGMENTLOG";
    Button submitBtn;
    static View mainView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sarver_data_from, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        submitBtn = (Button) getView().findViewById(R.id.submitDataBtn);

        mainView = getView();


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // here i have problem with the getting element from main activitiy
                    WebView wv = (WebView) getActivity().findViewById(R.id.mainWebView);

                    EditText serverProtocol = (EditText) mainView.findViewById(R.id.serverProtocolEdit);
                    EditText serverHostname = (EditText) mainView.findViewById(R.id.serverHostnameEdit);
                    EditText serverPort = (EditText)mainView.findViewById(R.id.serverPortEdit);
                    EditText serverSub = (EditText) mainView.findViewById(R.id.serverSubEdit);

                    String serverProtocolString = serverProtocol.getText().toString();
                    String serverHostnameString = serverHostname.getText().toString();
                    String serverPortString = serverPort.getText().toString();
                    String serverSubString = serverSub.getText().toString();

                    Log.i(TAG, serverProtocolString + "://" + serverHostnameString + ":" + serverPortString + "/" + serverSubString);

                    wv.loadUrl(serverProtocolString + "://" + serverHostnameString + ":" + serverPortString + "/" + serverSubString);
                    wv.setWebViewClient(new WebViewClientControler(getActivity()));

                    getActivity().findViewById(R.id.getServerDataFragmentContainer).setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    Log.i(TAG, e.getMessage(), e);
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach");

        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
    }
}
