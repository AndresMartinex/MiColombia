package com.example.micolombia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class INICIO extends AppCompatActivity {

    // Mismo orden que ciudades_array
    private final int[] imagenes = {
            R.drawable.medellin,
            R.drawable.villavicencio,
            R.drawable.cali
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner spinner = findViewById(R.id.spinner_ciudades);
        TextView title = findViewById(R.id.title);
        ImageView headerImage = findViewById(R.id.header_image);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.ciudades_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                title.setText(parent.getItemAtPosition(position).toString());
                headerImage.setImageResource(imagenes[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        findViewById(R.id.btn_salir).setOnClickListener(v -> finishAffinity());

        findViewById(R.id.fab).setOnClickListener(v -> {
            Intent intent = new Intent(INICIO.this, DetalleActivity.class);
            intent.putExtra("posicion", spinner.getSelectedItemPosition());
            startActivity(intent);
        });
    }
}