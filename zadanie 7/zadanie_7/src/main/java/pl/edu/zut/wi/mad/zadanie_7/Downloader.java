package pl.edu.zut.wi.mad.zadanie_7;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader extends Activity implements View.OnClickListener {

    private Button download;
    private ImageView imageShow;
    private ProgressBar progressBar;

    private static String urlToFile = "http://ns223506.ovh.net/rozne/bc46d3700d0f0821d6d90a7eb6110ee6/wallpaper-334629.jpg"; //http://wallbase.cc/wallpaper/334629
    public static final String PLAN_FOLDER = "/AA_MAD/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);

        download = (Button) findViewById(R.id.button);
        download.setOnClickListener(this);

        imageShow = (ImageView) findViewById(R.id.imageView);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                new ImageDownloader().execute(urlToFile);
                break;
        }
    }

    private class ImageDownloader extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setProgressBar(0);
            download.setEnabled(false);
            createFolder();
        }

        @Override
        protected String doInBackground(String... strings) {
            downloadFile(strings);
            return null;
        }

        @Override
        protected void onPostExecute(String fileName) {
            String imagePath = Environment.getExternalStorageDirectory().toString() + PLAN_FOLDER + "/pobrany.jpg";
            imageShow.setImageDrawable(Drawable.createFromPath(imagePath));
            download.setEnabled(true);
        }


        @Override
        protected void onProgressUpdate(Integer... progress) {
            setProgressBar(progress[0]);
        }

        private void createFolder() {
            String directoryPath = Environment.getExternalStorageDirectory().toString() + PLAN_FOLDER;
            File createdFolder = new File(directoryPath);
            if (!createdFolder.exists())
                createdFolder.mkdir();
            else
                createdFolder.delete();
        }

        private void setProgressBar(int progress) {
            progressBar.setProgress(progress);
        }

        private void downloadFile(String... strings) {
            int count;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                int lenghtOfFile = connection.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream(), 8192);
                OutputStream output = new FileOutputStream("/sdcard" + PLAN_FOLDER + "pobrany.jpg");

                byte data[] = new byte[1024];
                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress((int) (total * 100) / lenghtOfFile);
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }
        }
    }
}