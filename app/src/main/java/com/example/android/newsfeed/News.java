package com.example.android.newsfeed;

import android.net.Uri;

);

        updateUi(bookTitle, author);
    }

    /**
     * Update the UI with the given book information.
     */
    private void updateUi(String bookTitle, String author) {


        TextView authorTextView = (TextView) findViewById(R.id.author_textView);
        TextView titleTextView = (TextView) findViewById(R.id.title_textView);

        // Will set only Title
        if (bookTitle != null || !bookTitle.equals("") &&
                author == null || author.equals("")) {

            titleTextView.getText();
            titleTextView.setText(bookTitle);

            authorTextView.getText();
            authorTextView.setText("");
        }
        // Will set Author to value and set title to empty string
        else if (author != null || !author.equals("") &&
                bookTitle == null || bookTitle.equals("")) {

            authorTextView.getText();
            authorTextView.setText(author);

            titleTextView.getText();
            titleTextView.setText("");
        }
        // Will set both Title & Author
        else {
            authorTextView.getText();
            authorTextView.setText(author);

            titleTextView.getText();
            titleTextView.setText(bookTitle);
        }
    }

    private class BookAsyncTask extends AsyncTask<String, Void, Book> {

        // Performed in a background thread
        @Override
        protected Book doInBackground(String... urls) {

            // Exits early and doesn't perform URL request if there isn't an URL present
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            Uri groundWorkUri = Uri.parse(urls[0]);
            Uri.Builder uriBuilder = groundWorkUri.buildUpon();
            uriBuilder.appendQueryParameter("q", query);

            // Performs the HTTP request for book data and process the response.
            return (Book) Utils.fetchBookData(uriBuilder.toString());
        }

        // Performed in the main UI thread
        protected void onPostExecute(List<Items> data) {

            // If there is no result, do nothing
            if (data == null) {
                return;
            }

            // Update the information displayed to the user.
            data.clear();
        }
    }

    public String getbookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }
}