package com.example.notekeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var notePosition = POSITION_NOT_SET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val adapterCourses = ArrayAdapter<CourseInfo>(this,android.R.layout.simple_spinner_item, DataManager.courses.values.toList())
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_course.adapter = adapterCourses


        notePosition = savedInstanceState?.getInt(NOTE_POSITION, POSITION_NOT_SET)?:intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)

        if (notePosition != POSITION_NOT_SET){

            displayNote()

        } else {

            DataManager.notes.add(NoteInfo())
            notePosition  = DataManager.notes.lastIndex

        }


    }


    override fun onPause() {

        super.onPause()
        saveNote()

    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
        outState.putInt(NOTE_POSITION, notePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        if (notePosition >= DataManager.notes.lastIndex){
            val menuItem = menu?.findItem(R.id.action_next)

            if (menuItem != null){

                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false
            }

        }

        if (notePosition == 0){

            val menuItem = menu?.findItem(R.id.action_previous)

            if (menuItem != null){

                menuItem.icon = getDrawable(R.drawable.ic_block_white_24dp)
                menuItem.isEnabled = false

            }

        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {

                displayNextNote()
                return true
            }
            R.id.action_previous -> {

                displayPreviousNote()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun displayPreviousNote() {

        --notePosition
        displayNote()
        invalidateOptionsMenu()

    }

    private fun displayNextNote() {

        ++notePosition
        displayNote()
        invalidateOptionsMenu()

    }

    private fun displayNote() {

        val note = DataManager.notes[notePosition]
        note_title.setText(note.title)
        note_text.setText(note.text)

        val coursePosition = DataManager.courses.values.indexOf(note.course)
        spinner_course.setSelection(coursePosition)

    }

    private fun saveNote() {

        val note = DataManager.notes[notePosition]
        note.title = note_title.text.toString()
        note.text = note_text.text.toString()
        note.course = spinner_course.selectedItem as CourseInfo

    }


}
