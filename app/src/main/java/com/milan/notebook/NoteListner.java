package com.milan.notebook;

public interface NoteListner {
    void onNoteClick(Note note);
    void onNoteEditPress(Note note);
    void onNoteDeletePress(Note note);

}
