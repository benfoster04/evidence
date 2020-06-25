package b.en.evidence

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.getSystemService

class SetAuthDialogue : AppCompatActivity() {
    fun showToast(txt: String ) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_auth)
        val pass = findViewById<EditText>(R.id.setAuthPassword)
        val cPass =findViewById<EditText>(R.id.setAuthPasswordConfirm)
        val passL =findViewById<TextView>(R.id.setAuthPassLength)
        println("cum")
        pass.setOnKeyListener { /*view*/ _, i, keyEvent ->
            println("Reeeee")
            passL.text = String.format(resources.getString(R.string.password_count), pass.text.length)
//            passL.text = pass.text.length.toString() + "/8"
            if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                if (cPass.text.isBlank()) {
                    cPass.requestFocus()
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(cPass, InputMethodManager.SHOW_IMPLICIT)
                }
                showToast("reeeee")
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        findViewById<Button>(R.id.setAuthNext).setOnClickListener {
            if (pass.text.isBlank() || cPass.text.isBlank() ) {
                showToast("Nothing in either field")
            }
            if (pass.text.length < 8) {
                showToast("Not long enough")
            }
            if (pass.text != cPass.text) {
                showToast("Don't match")
            }
        }
    }
}