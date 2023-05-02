package com.milan.notebook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Random;

public class NoteActivity extends AppCompatActivity {

    EditText etTitle, etDescription;
    Spinner category;
    Button addNotes;
    String noteCategory;
    RecyclerView rvColor;
    NoteColorAdapter noteAdapter;
    Integer selectedColor = Color.WHITE;
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noteactivity);

        category = findViewById(R.id.spinnerCategory);
        etTitle = findViewById(R.id.et_note_title);
        etDescription = findViewById(R.id.et_note_description);
        addNotes = findViewById(R.id.addNote);
        rvColor = findViewById(R.id.color_list);
        toolbar = findViewById(R.id.add_note_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i <= 50; i++) {
            Random random = new Random();
            int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            colors.add(color);
        }

        noteAdapter = new NoteColorAdapter(colors, new NoteColorListner() {
            @Override
            public void onNoteColorClick(int color) {
                selectedColor = color;
            }
        });

        rvColor.setAdapter(noteAdapter);

        String[] items = {"Normal", "urgent", "quick"};
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        category.setAdapter(spinnerAdapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                noteCategory = category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        addNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();
//                String category = category.toString()

//                Toast.makeText(NoteActivity.this, "Title:" + title + ",Description:" + description + " Note Category:" + noteCategory, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("note_title", title);
                intent.putExtra("description", description);
                intent.putExtra("category", noteCategory);
                intent.putExtra("color", selectedColor);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
