package com.milan.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAddNote;
    ArrayList<Note> notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNote = findViewById(R.id.btnAddNote);

        notes = new ArrayList<>();
        notes.add(new Note("Note1","Desc 1","Cat1"));
        notes.add(new Note("Note2","Desc 1","Cat1"));
        notes.add(new Note("Note3","Desc 1","Cat1"));
        notes.add(new Note("Note4","Desc 1","Cat1"));
        notes.add(new Note("Note5","Desc 1","Cat1"));
        notes.add(new Note("Note6","Desc 1","Cat1"));
        notes.add(new Note("Note7","Desc 1","Cat1"));
        notes.add(new Note("Note8","Desc 1","Cat1"));

        RecyclerView rv = findViewById(R.id.rv_notes);
        rv.setAdapter(new NotesAdapter(notes));

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });
    }
}