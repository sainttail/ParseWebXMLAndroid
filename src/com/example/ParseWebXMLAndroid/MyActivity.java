package com.example.ParseWebXMLAndroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import org.apache.http.Header;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private static final String TAG = "MyActivity";

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                client.get("http://www.google.com", new TextHttpResponseHandler(){

                    @Override
                    public void onStart() {
                        Log.d(TAG, "start request");
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseBody) {

                        Log.d(TAG, responseBody);

                        Document doc = Jsoup.parse(responseBody);
                        Element titleTag = doc.getElementsByTag("title").get(0);
                        Log.d(TAG, "Title : " + titleTag.text());

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                        Log.d(TAG, "request fail : " + error.getLocalizedMessage());
                    }
                });
            }
        });
    }
}
