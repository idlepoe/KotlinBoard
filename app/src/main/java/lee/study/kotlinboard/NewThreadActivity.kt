package lee.study.kotlinboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NewThreadActivity : AppCompatActivity() {

    companion object {
        val TAG = "newthread"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_thread)

        supportActionBar?.title = "NEW THREAD"

        val board = intent.getParcelableExtra<Board>(BoardActivity.USER_KEY)

        Log.d(TAG,"seq : ${board.seq},  boardname : ${board.name} ")
    }
}
