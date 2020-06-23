package b.en.evidence

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Bundle
import android.os.CancellationSignal
import android.view.animation.Animation
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.DialogCompat
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor
import java.util.jar.Manifest

class ActivityEntrypoint : AppCompatActivity() {
    fun showToast( txt: String ) {
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrypoint)
        val btn = findViewById<Button>(R.id.nextModal)
        btn.setOnClickListener {
            setContentView(R.layout.activity_passdia)
        }
        if (ContextCompat.checkSelfPermission(this, "USE_BIOMETRICS") == PackageManager.PERMISSION_GRANTED) {
            val bio = BiometricPrompt.Builder(this)
                .setTitle("Evidence SSO")
                .setSubtitle("Biometric Authentication")
                .setDescription("Authenticate through device biometrics")
                .setConfirmationRequired(true)
                .setNegativeButton("Use Password", Executor {
                    return@Executor
                }, DialogInterface.OnClickListener
                { _, _ -> showToast("Cancelled") })
                .build()
            val cs = CancellationSignal()
            cs.setOnCancelListener {
                showToast("cs")
            }
            bio.authenticate(cs, Executor {
                showToast("The Executable")
            }, AuthCallback())
        } else {
            if (shouldShowRequestPermissionRationale("USE_BIOMETRICS")) {
                val al = AlertDialog.Builder(this)
                    .setTitle("Biometric Login")
                    .setMessage("Evidence can be used along side your device's biometric sensor.")
                    .setIcon(R.drawable.icon_round)
                    .setPositiveButton("Yes please",DialogInterface.OnClickListener { /*dialogInterface*/_, /*i*/_ ->  showToast("Yes Please")})
                    .setNegativeButton("No thanks", DialogInterface.OnClickListener { /*dialogInterface*/_, /*i*/_ ->  showToast("No thanks")})
                    .create()
                al.show()
            }

            findViewById<ProgressBar>(R.id.loader).setProgress(100, true)
            ObjectAnimator.ofFloat(btn, "alpha", 1f).apply {
                duration = 1000
                start()
            }
            showToast("Denied")
        }
    }
    inner class AuthCallback : BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            showToast("Auth class succeeded")
            super.onAuthenticationSucceeded(result)
        }

        override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
            showToast("Auth class Error")
            super.onAuthenticationError(errorCode, errString)
        }

        override fun onAuthenticationFailed() {
            showToast("Auth class failed")
            super.onAuthenticationFailed()
        }

        override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
            showToast("Auth class help")
            super.onAuthenticationHelp(helpCode, helpString)
        }
    }
}



