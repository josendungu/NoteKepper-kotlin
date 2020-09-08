package com.example.notekeeper

object DataManager {

    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses(){

        var course = CourseInfo("android_intents", "Android Programming with Intents")
        courses[course.courseId] = course

        course = CourseInfo("android_async", "Android Async Programming and Services")
        courses[course.courseId] = course

        course = CourseInfo(title = "Java Fundamentals: The Java Language", courseId = "java_lang")
        courses[course.courseId] = course

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform")
        courses[course.courseId] = course
    }

    fun addNote(course: CourseInfo, noteTitle: String, noteText: String): Int{

        val note = NoteInfo(course, noteTitle, noteText)
        notes.add(note)

        return notes.lastIndex

    }

    fun findNote(course: CourseInfo, noteText: String, noteTitle: String): NoteInfo?{

        for(note in notes){

            if(course === note.course && noteText === note.text && noteTitle === note.title)
                return note

        }
        return null

    }

    private fun initializeNotes() {

        insertNote(
            "android_intents",
            "Dynamic intent resolution",
            "Wow, intents allow components to be resolved at runtime"
        )
        insertNote(
            "android_intents",
            "Delegating intents",
            "PendingIntents are powerful; they delegate much more than just a component invocation"
        )

        insertNote(
            "android_async",
            "Service default threads",
            "Did you know that by default an Android Service will tie up the UI thread?"
        )
        insertNote(
            "android_async",
            "Long running operations",
            "Foreground Services can be tied to a notification icon"
        )

        insertNote(
            "java_lang",
            "Parameters",
            "Leverage variable-length parameter lists?"
        )
        insertNote(
            "java_lang",
            "Anonymous classes",
            "Anonymous classes simplify implementing one-use types"
        )

        insertNote(
            "java_core",
            "Compiler options",
            "The -jar option isn't compatible with with the -cp option"
        )
        insertNote(
            "java_core",
            "Serialization",
            "Remember to include SerialVersionUID to assure version compatibility"
        )

    }

    private fun insertNote(courseId: String, noteTitle: String, noteText: String){

        val note = courses[courseId]?.let { NoteInfo(it, noteTitle, noteText) }

        note?.let { notes.add(it) }

    }
}