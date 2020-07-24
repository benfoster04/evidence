package b.en.evidence

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

/**
 * Implementation of App Widget functionality.
 */
class RecordEvidenceDualWidget : AppWidgetProvider() {
    val la: String = "evidence_startrecording_widgetleft"
    val ra: String = "evidence_startrecording_widgetright"
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, la, ra)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (la == intent?.action) {
            Toast.makeText(context, "Record Audio", Toast.LENGTH_SHORT).show()
            val appWidId = intent.getIntExtra("appWidgetId", 0)
            updateAppWidget(context, AppWidgetManager.getInstance(context), appWidId, la, ra)
        } else if (ra == intent?.action) {
            Toast.makeText(context, "Record video", Toast.LENGTH_SHORT).show()
            val appWidId = intent.getIntExtra("appWidgetId", 0)
            updateAppWidget(context, AppWidgetManager.getInstance(context), appWidId, la, ra)
        }
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    la: String,
    ra: String
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.record_evidence_dual_widget)
    views.setImageViewResource(R.id.dualWidgetLeft, R.drawable.icon_microphone)
    views.setImageViewResource(R.id.dualWidgetRight,R.drawable.icon_camera)

    // Intents
    val li = Intent(context, RecordEvidenceDualWidget::class.java)
    li.action = la
    li.putExtra("appWidgetId", appWidgetId)
    val lpi = PendingIntent.getBroadcast(context,0, li, 0)
    val ri = Intent(context, RecordEvidenceDualWidget::class.java)
    ri.action = ra
    ri.putExtra("appWidgetId", appWidgetId)
    val rpi = PendingIntent.getBroadcast(context, 0, ri, 0)

    views.setOnClickPendingIntent(R.id.dualWidgetLeft, lpi)
    views.setOnClickPendingIntent(R.id.dualWidgetRight,rpi)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}