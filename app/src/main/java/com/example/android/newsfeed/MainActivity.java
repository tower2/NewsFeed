package com.example.android.newsfeed;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Created by jermainegoins on 8/21/17.
     */

    public static class News extends MainActivity{

        /**
         *  Variables that extract data from API
         */
        private String webTitle;
        private String id;
        private Date webPublicationDate;
        private String webUrl;

        /**
         * Constructs a new {@link News}.
         *
         * @param webTitle  is the web title of API
         * @param id is id of article
         * @param webPublicationDate  is the date of publication
         * @param webUrl is the website Url
         */

        public News(String webTitle, String id, Date webPublicationDate, String webUrl) {
            this.webTitle = webTitle;
            this.id = id;
            this.webPublicationDate = webPublicationDate;
            this.webUrl = webUrl;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


                    String news_request_url = "https://content.guardianapis.com/search?api-key=e990e3cd-bf98-4145-822c-8dc3c0481939";

                    if (getIntent() != null && getIntent().getExtras() != null) {
                        query = getIntent().getExtras().getString("query");
                    }

                    // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
                    View emptyView = findViewById(R.id.empty_view);

                    // Connectivity Manager sets connect manager
                    ConnectivityManager connected = (ConnectivityManager)
                            getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                    // Sets connection status and either exits early if connect isn't establish or performs search
                    NetworkInfo activeConnection = connected.getActiveNetworkInfo();
                    boolean isConnected = activeConnection != null & activeConnection.isConnectedOrConnecting();

                    if (isConnected) {

                        // Hide TextViews, EditText, and Button after being clicked
                        EditText searchText = (EditText) findViewById(R.id.book_edit_text);

                        // Grabs information from EditText, converts to String, and removes all white spaces and "+"
                        String search = searchText.toString();

                        //  String search = searchText.getText().toString().replace(" ", "");

                        // Concatenate book_request_url with search
                        book_request_url = book_request_url + search;
                        book_request_url = book_request_url.replaceAll(" ", "");

                        // Creates an AsyncTask to perform HTTP request to the URL in background thread
                        BookAsyncTask task = new BookAsyncTask();
                        task.execute(book_request_url);
                    } else {
                        Toast.makeText(Book.this, "Connect Device to WiFi or LAN", Toast.LENGTH_SHORT).show();
                    }
                }
            }
}
