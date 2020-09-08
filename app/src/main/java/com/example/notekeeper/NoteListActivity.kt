package com.example.notekeeper

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.app_bar_items.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val mNoteLayoutManager by lazy { LinearLayoutManager(this)}
    private val mNoteRecyclerAdapter by lazy { NoteRecyclerAdaper(this, DataManager.notes)}

    private val mCourseLayoutManager by lazy { GridLayoutManager(this, 2)}
    private val mCourseRecyclerAdapter by lazy { CourseRecyclerAdapter(this, DataManager.courses.values.toList())}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        val toggle = ActionBarDrawerToggle(this,
            drawer_layout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        displayNotes()

    }

    override fun onResume() {
        super.onResume()
        list_notes.adapter?.notifyDataSetChanged()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.nav_courses -> {

                displayCourses()

            }

            R.id.nav_notes -> {

                displayNotes()

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun displayCourses() {

        list_notes.layoutManager = mCourseLayoutManager
        list_notes.adapter = mCourseRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_courses).isCheckable = true

    }

    private fun displayNotes() {

        list_notes.layoutManager = mNoteLayoutManager
        list_notes.adapter = mNoteRecyclerAdapter

        nav_view.menu.findItem(R.id.nav_notes).isCheckable = true

    }


}
