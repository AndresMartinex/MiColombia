package com.example.micolombia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleActivity extends AppCompatActivity {

    // Mismo orden que ciudades_array
    private final int[] imagenes = {
            R.drawable.medellin,
            R.drawable.villavicencio,
            R.drawable.cali
    };

    private final int[] audios = {
            R.raw.audio_medellin,
            R.raw.audio_villavicencio,
            R.raw.audio_cali,
    };

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int posicion = getIntent().getIntExtra("posicion", 0);

        String[] ciudades = getResources().getStringArray(R.array.ciudades_array);
        String[] descripciones = getResources().getStringArray(R.array.descripciones_array);

        ((TextView) findViewById(R.id.tv_titulo)).setText(ciudades[posicion]);
        ((TextView) findViewById(R.id.tv_descripcion)).setText(descripciones[posicion]);
        ((ImageView) findViewById(R.id.img_ciudad)).setImageResource(imagenes[posicion]);

        mediaPlayer = MediaPlayer.create(this, audios[posicion]);

        findViewById(R.id.btn_play).setOnClickListener(v -> {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        });

        findViewById(R.id.btn_pausa).setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        findViewById(R.id.btn_repetir).setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });

        findViewById(R.id.btn_volver).setOnClickListener(v -> finish());
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}