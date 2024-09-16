package ir.fod.thisnotebook.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import ir.fod.thisnotebook.R
import ir.fod.thisnotebook.adapter.ListNoteAdapter
import ir.fod.thisnotebook.utils.NetWorkCheck
import ir.fod.thisnotebook.viewmodel.NoteViewModel

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



        noteAdapter = ListNoteAdapter { listModel ->




        }

        getApiNote()

    }

    private fun bottomSheetDialog() {

        var dialogView = layoutInflater.inflate(R.layout.bottomsheet , null)

        dialog = BottomSheetDialog(this , R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)

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


                }

                is NetWorkCheck.Lod     -> {



                }


            }



        }

        recyclerView.adapter = noteAdapter


    }
}