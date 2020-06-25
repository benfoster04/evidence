package b.en.evidence

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SetAuthDialogue : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val pass = findViewById<EditText>(R.id.setAuthPassword)
        val cPass =findViewById<EditText>(R.id.setAuthPasswordConfirm)
        val passL =findViewById<TextView>(R.id.setAuthPassLength)
        pass.setOnKeyListener { /*view*/ _, i, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                Toast.makeText(this, "reeeee", Toast.LENGTH_SHORT).show()
                return@setOnKeyListener true
            }
            passL.text = pass.text.length.toString() + "/8"
            return@setOnKeyListener false
        }
        findViewById<Button>(R.id.setAuthNext).setOnClickListener {
            if (pass.text.isBlank() || cPass.text.isBlank() ) {
                // Nothing in either field
            }
            if (pass.text.length < 8) {
                // Not long enough
            }
            if (pass.text != cPass.text) {
                // Don't match
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_auth)
    }
}