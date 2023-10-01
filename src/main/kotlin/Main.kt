class Note(val title: String, val content: String)

class NoteManager {
    private val notes = mutableListOf<Note>()

    // Method to add a new note
    fun addNote() {
        println("\n==== Add a New Note ====")
        print("Enter note title: ")
        val title = readlnOrNull() ?: ""
        print("Enter note content: ")
        val content = readlnOrNull() ?: ""
        val note = Note(title, content)
        notes.add(note)
        println("Note added successfully!")
    }

    // Method to edit an existing note
    fun editNote() {
        println("\n==== Edit Note ====")
        print("Enter the note number you want to edit: ")
        val noteNumber = readlnOrNull()?.toIntOrNull() ?: return

        if (noteNumber !in 1..notes.size) {
            println("Invalid note number.")
            return
        }

        println("Editing Note ${noteNumber}:")
        print("Enter new title: ")
        val newTitle = readlnOrNull() ?: ""
        print("Enter new content: ")
        val newContent = readlnOrNull() ?: ""

        notes[noteNumber - 1] = Note(newTitle, newContent)
        println("Note edited successfully!")
    }

    // Method to delete an existing note
    fun deleteNote() {
        println("\n==== Delete Note ====")
        print("Enter the note number you want to delete: ")
        val noteNumber = readlnOrNull()?.toIntOrNull() ?: return

        if (noteNumber !in 1..notes.size) {
            println("Invalid note number.")
            return
        }

        notes.removeAt(noteNumber - 1) // Remove the note at the specified index
        println("Note deleted successfully!")
    }

    // Method to view all notes
    fun viewNotes() {
        if (notes.isEmpty()) {
            println("No notes available.")
            return
        }

        println("\n==== Your Notes ====")
        for ((index, note) in notes.withIndex()) {
            println("Note ${index + 1}:")
            println("Title: ${note.title}")
            println("Content: ${note.content}")
            println()
        }
    }
}

// Method to display the menu and get user input
fun menu(): Int? {
    println("\n==== Menu ====")
    println("1. Write a Note")
    println("2. View or Manage Notes")
    println("3. Quit")
    print("Please select an option: ")

    val userInputString = readlnOrNull()
    return userInputString?.toIntOrNull()
}

fun main() {
    val noteManager = NoteManager()

    var userInput: Int? = 0

    // Main program loop to handle user input and execute actions accordingly
    while (userInput != 3) {
        userInput = menu()
        when (userInput) {
            1 -> noteManager.addNote() // Option to add a new note
            2 -> {
                noteManager.viewNotes() // Option to view notes
                print("If you want to manage your notes, press 1 to enter edit mode, 2 for delete mode, or 0 to return to menu: ")
                val editOrDeleteOption = readlnOrNull()?.toIntOrNull() ?: 0

                when (editOrDeleteOption) {
                    1 -> noteManager.editNote() // Option to edit a note
                    2 -> noteManager.deleteNote() // Option to delete a note
                    0 -> { /* Cancel, do nothing */ }
                    else -> println("Invalid option. Please try again.")
                }
            }
            3 -> println("Exiting. Goodbye!") // Option to exit the program
            else -> println("Invalid selection. Please try again.")
        }
    }
}
