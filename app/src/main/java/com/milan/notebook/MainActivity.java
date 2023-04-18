package com.milan.notebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAddNote;
    ArrayList<Note> notes;
    NotesAdapter adapter;
    ActivityResultLauncher<Intent> resultIntent = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                String title = data.getExtras().getString("note_title");
                                String description = data.getExtras().getString("description");
                                String category = data.getExtras().getString("category");

                                adapter.addData(new Note(title,description,category));
                            }

                        }
                    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNote = findViewById(R.id.btnAddNote);

        notes = new ArrayList<>();
//        notes.add(new Note("Note1", "Desc 1", "Cat1"));
//        notes.add(new Note("Note2","Desc 1","Cat1"));
//        notes.add(new Note("Note3","Desc 1","Cat1"));
//        notes.add(new Note("Note4","Desc 1","Cat1"));
//        notes.add(new Note("Note5","Desc 1","Cat1"));
//        notes.add(new Note("Note6","Desc 1","Cat1"));
//        notes.add(new Note("Note7","Desc 1","Cat1"));
//        notes.add(new Note("Note8","Desc 1","Cat1"));

         adapter = new NotesAdapter(notes);
        RecyclerView rv = findViewById(R.id.rv_notes);
        rv.setAdapter(adapter);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                resultIntent.launch(intent);
            }
        });
    }
}