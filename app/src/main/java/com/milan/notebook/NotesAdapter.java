package com.milan.notebook;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    ArrayList<Note> notes;
    NoteListner noteListner;

    public NotesAdapter(ArrayList<Note> notes, NoteListner noteListner) {
        this.notes = notes;
        this.noteListner = noteListner;
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.bindView(notes.get(position));
    }

    public void addData(Note note){
        notes.add(note);
        notifyItemInserted(notes.size());
    }

    public void deleteNote(Note note){
        Integer index = notes.indexOf(note);
        Log.d("tag", index.toString());
        notes.remove(note);
        notifyItemRemoved(index);


    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView category;
        TextView description;




        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.desc);
            category = itemView.findViewById(R.id.cat);
        }

        public void bindView(Note note) {
            title.setText(note.getTitle());
            description.setText(note.getDis());
            category.setText(note.getCategory());
            itemView.setBackgroundColor(note.getColor());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    noteListner.onNoteClick(note);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    noteListner.onNoteDeletePress(note);
                    return false;
                }
            });
        }
    }
}
