package b.en.evidence

import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.Executor

class ActivityEntrypoint : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrypoint)
        val bio = BiometricPrompt.Builder(this)
            .setTitle("Evidence SSO")
            .setSubtitle("Biometric Authentication")
            .setDescription("Authenticate through device biometrics")
            .setConfirmationRequired(true)
            .setNegativeButton("Use Password", Executor {
                return@Executor
            }, DialogInterface.OnClickListener
            { _, _ -> Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT) })
            .build()
        bio.authenticate(CancelSig(), Executor {
            Toast.makeText(this, "The Executable", Toast.LENGTH_SHORT)
        }, AuthCallback())
    }
    inner class AuthCallback : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            super.onAuthenticationSucceeded(result)
            Toast.makeText(this@ActivityEntrypoint, "Auth class succeeded", Toast.LENGTH_SHORT)
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            super.onAuthenticationError(errorCode, errString)
            Toast.makeText(this@ActivityEntrypoint, "Auth class Error", Toast.LENGTH_SHORT)
        }

        override fun onAuthenticationFailed() {
            super.onAuthenticationFailed()
            Toast.makeText(this@ActivityEntrypoint, "Auth class failed", Toast.LENGTH_SHORT)
        }

        override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
            super.onAuthenticationHelp(helpCode, helpString)
            Toast.makeText(this@ActivityEntrypoint, "Auth class help", Toast.LENGTH_SHORT)
        }
    }
    inner class CancelSig : CancellationSignal() {
        constructor() {
            this.setOnCancelListener {
                Toast.makeText(this@ActivityEntrypoint, "Cancel signal", Toast.LENGTH_SHORT)
            }
        }
    }
}



