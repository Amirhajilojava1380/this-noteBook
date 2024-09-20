package ir.fod.thisnotebook.activity

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.saveable.autoSaver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import ir.fod.thisnotebook.R
import ir.fod.thisnotebook.adapter.ListNoteAdapter
import ir.fod.thisnotebook.utils.NetWorkCheck
import ir.fod.thisnotebook.viewmodel.NoteViewModel
import retrofit2.Response


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val noteViewModel : NoteViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView

    private lateinit var noteAdapter :ListNoteAdapter

    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var dialog : BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        floatingActionButton = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener{

            bottomSheetDialog()

        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


        noteAdapter = ListNoteAdapter { }




        getApiNote()

    }

    private fun bottomSheetDialog() {

        val dialogView = layoutInflater.inflate(R.layout.bottomsheet , null)

        dialog = BottomSheetDialog(this , R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)



        val behavior = BottomSheetBehavior.from(dialogView)

        behavior.isFitToContents = true
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED



        val textInputEditText = dialogView.findViewById<TextInputEditText>(R.id.textInputEditText)

        val button = dialogView.findViewById<Button>(R.id.button)

        button.setOnClickListener{

            val textInputTitle = textInputEditText.text.toString()

            if (textInputTitle.isNotEmpty()){

                noteViewModel.sendNoteAsHashMap(textInputTitle)
                noteViewModel.sendNoteStatus.observe(this@MainActivity){ response ->

                    when(response){

                        is NetWorkCheck.Message -> {

                            Log.d("MainActivity", "Response received: ${response.message}")
                            Toast.makeText(this, response.message.toString(), Toast.LENGTH_SHORT).show()
                            dialog.dismiss()

                            getApiNote()
                        }

                        is NetWorkCheck.Error   -> {

                            Log.e("MainActivity", "Error: ${response.message}")
                            Toast.makeText(this, response.message , Toast.LENGTH_SHORT).show()

                        }

                        is NetWorkCheck.Lod     -> {

                            Log.d("MainActivity", "Loading...")

                        }


                        else -> { Toast.makeText(this, response.message , Toast.LENGTH_SHORT).show()}
                    }



                }



            }


        }




        dialog.show()

    }


    private fun getApiNote() {

        recyclerView = findViewById(R.id.recyclerview_note)

        recyclerView.layoutManager = LinearLayoutManager(this)


        noteViewModel.getNote()

        noteViewModel.dataNote.observe(this) { response ->

            when(response){

                is NetWorkCheck.Success -> {

                    response.data?.let { noteAdapter.setData(it) }

                }

                is NetWorkCheck.Error   -> {

                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()

                }

                is NetWorkCheck.Lod     -> {}


                else -> {}
            }



        }

        recyclerView.adapter = noteAdapter


    }
}