package lee.study.kotlinboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ThreadActivity : AppCompatActivity() {

    companion object {
        val TAG = "newthread"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        supportActionBar?.title = "THREAD LIST"

        val board = intent.getParcelableExtra<Board>(BoardActivity.USER_KEY)

        Log.d(NewThreadActivity.TAG,"seq : ${board.seq},  boardname : ${board.name} ")
    }
}
