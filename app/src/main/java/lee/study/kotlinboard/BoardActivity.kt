package lee.study.kotlinboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_board.*
import kotlinx.android.synthetic.main.row_board.view.*


class BoardActivity : AppCompatActivity() {

    companion object{
        val TAG = "dd"
    }

    var board_seq = 0
    val adapter = GroupAdapter<ViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)

        boardlist_recyclerview_board.adapter = adapter

        getBoardList()

        val seqRef = FirebaseDatabase.getInstance().getReference("/boards_seq")

        create_button_board.setOnClickListener { v: View? ->
            seqRef.addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    board_seq = p0.getValue(Int::class.java)!!
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            })

            val boardName = boardname_edittext_board.text.toString()
            val ref = FirebaseDatabase.getInstance().getReference("/boards/$boardName")
            seqRef.setValue( board_seq+1)
            ref.setValue(Board(board_seq,boardName))
        }
    }

    fun getBoardList(){
        val ref = FirebaseDatabase.getInstance().getReference("/boards")
        ref.addChildEventListener(object :ChildEventListener{
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val board = p0.getValue(Board::class.java)
                if(board!=null){
                    adapter.add(BoardRow(board))
                }
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }
        })
    }

}

class BoardRow(val board:Board): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.no_textview_rowboard.text = board.seq.toString()
        viewHolder.itemView.boardname_textview_rowboard.text = board.name
    }

    override fun getLayout(): Int {
        return R.layout.row_board
    }
}



class Board(val seq: Int, val name:String){
    constructor():this(0,"")
}