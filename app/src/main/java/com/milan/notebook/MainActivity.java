package com.milan.notebook;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnAddNote;
    ArrayList<Note> notes;
    NotesAdapter adapter;
    NotebookDbHelper dbHelper;
    LinearLayout llNoNotes;
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
                                Integer color = data.getExtras().getInt("color");

                                Note note = new Note(title, description, category, color);
                                adapter.addData(note);
                                dbHelper.addNote(note);
                                if (llNoNotes.getVisibility() == View.VISIBLE) {
                                    llNoNotes.setVisibility(View.GONE);
                                }
                            }

                        }
                    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new NotebookDbHelper(getApplicationContext());

        btnAddNote = findViewById(R.id.btnAddNote);
        llNoNotes = findViewById(R.id.ll_no_notes);


        notes = new ArrayList<>();
        notes.addAll(dbHelper.getAllNotes());
        if (notes.size() < 1) {
            llNoNotes.setVisibility(View.VISIBLE);
        } else {
            llNoNotes.setVisibility(View.GONE);
        }
//        notes.add(new Note("Note1", "Desc 1", "Cat1"));
//        notes.add(new Note("Note2","Desc 1","Cat1"));
//        notes.add(new Note("Note3","Desc 1","Cat1"));
//        notes.add(new Note("Note4","Desc 1","Cat1"));
//        notes.add(new Note("Note5","Desc 1","Cat1"));
//        notes.add(new Note("Note6","Desc 1","Cat1"));
//        notes.add(new Note("Note7","Desc 1","Cat1"));
//        notes.add(new Note("Note8","Desc 1","Cat1"));

        adapter = new NotesAdapter(notes, new NoteListner() {
            @Override
            public void onNoteClick(Note note) {
                Intent intent = new Intent(MainActivity.this, DetailView.class);
                intent.putExtra("title", note.getTitle());
                intent.putExtra("description", note.getDis());
                intent.putExtra("category", note.getCategory());
                startActivity(intent);
            }

            @Override
            public void onNoteEditPress(Note note) {

            }

            @Override
            public void onNoteDeletePress(Note note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to Delete ?");
                builder.setTitle("Alert !");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dbHelper.deleteNote(note.getId());
                    adapter.deleteNote(note);
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
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