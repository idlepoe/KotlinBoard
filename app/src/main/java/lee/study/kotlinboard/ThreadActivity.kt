package lee.study.kotlinboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_thread.*

class ThreadActivity : AppCompatActivity() {

    companion object {
        val TAG = "threadlist"
        val USER_KEY = "USER_KEY"


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        supportActionBar?.title = "THREAD LIST"

        val board = intent.getParcelableExtra<Board>(BoardActivity.USER_KEY)

        Log.d(NewThreadActivity.TAG, "seq : ${board.seq},  boardname : ${board.name} ")

        newthread_button_thread.setOnClickListener { v: View? ->
            val intent = Intent(this, NewThreadActivity::class.java)
            intent.putExtra(USER_KEY,board)
            startActivity(intent)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_back -> {
                val intent = Intent(this, BoardActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.thread_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
