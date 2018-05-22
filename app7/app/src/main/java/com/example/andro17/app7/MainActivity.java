package com.example.andro17.app7;

import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;
import java.util.HashMap;

import static android.os.StrictMode.*;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private EditText txtUrl;
    private ToggleButton btnPlayPause;
    private TextView txtSongTitle;
    private TextView txtSongArtist;

    private MediaPlayer mediaPlayer;
    private MediaMetadataRetriever mediaMetadataRetriever;
    private boolean isSongLoaded;
    private boolean isFirstSong = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        init();
        txtUrl.setText("http://softlab.technion.ac.il/kulik/1.mp3");
        btnPlayPause.setOnCheckedChangeListener(this);
        txtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isSongLoaded = false;
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            pauseAudio();
        } else if (isSongLoaded) {
            playAudio();
        } else {
            String url = txtUrl.getText().toString();
            if (URLUtil.isHttpUrl(url)) {
                try {
                    loadAudioFromUrl(url);
                    isSongLoaded = true;
                    playAudio();
                    loadAudioInformationFromUrl(url);
                } catch (IOException e) {
                    Toast.makeText(this, R.string.error_loading_audio, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.invalid_address, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void init() {
        txtUrl = findViewById(R.id.txtUrl);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        txtSongTitle = findViewById(R.id.txtSongTitle);
        txtSongArtist = findViewById(R.id.txtSongArtist);
        txtSongTitle.setText(getString(R.string.song_title, ""));
        txtSongArtist.setText(getString(R.string.song_artist, ""));
        mediaPlayer = new MediaPlayer();
        mediaMetadataRetriever = new MediaMetadataRetriever();
        isSongLoaded = false;
    }

    private void loadAudioInformationFromUrl(String url) {
        mediaMetadataRetriever.setDataSource(url, new HashMap<String, String>());
        String title = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String artist = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        txtSongTitle.setText(getString(R.string.song_title, title));
        txtSongArtist.setText(getString(R.string.song_artist, artist));

    }

    private void loadAudioFromUrl(String url) throws IOException {
        if (!isFirstSong) {
            mediaPlayer.reset();
        }
        isFirstSong = false;
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
    }

    private void playAudio() {
        mediaPlayer.start();
    }

    private void pauseAudio() {
        mediaPlayer.pause();
    }
}
