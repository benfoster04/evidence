package b.en.evidence

import android.content.DialogInterface
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class ActivityEntrypoint : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrypoint)
        val ex = ContextCompat.getMainExecutor(this)
        val bio = BiometricPrompt.Builder(this)
            .setTitle("Evidence SSO")
            .setSubtitle("Biometric Authentication")
            .setDescription("Authenticate through device biometrics")
            .setConfirmationRequired(true)
            .setNegativeButton("Use Password", Executor {
                return@Executor
            }, DialogInterface.OnClickListener(function = ))
            .build()
//        bio.authenticate(CancellationSignal.)
    }
}