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
class RecordEvidenceWidget : AppWidgetProvider() {
    val x: String = "evidence_startrecording_widget"
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, x)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent?) {
        if (x == intent?.action) {
            Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show()
            val appWidId = intent.getIntExtra("appWidgetId", 0)
            updateAppWidget(context, AppWidgetManager.getInstance(context), appWidId, x)
        }
        super.onReceive(context, intent)
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    x: String
) {
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.record_evidence_widget)
    views.setImageViewResource(R.id.widget_btn, R.drawable._icon_round)
    val i = Intent(context, RecordEvidenceWidget::class.java)
    i.action = x
    i.putExtra("appWidgetId", appWidgetId)
    val pi =PendingIntent.getBroadcast(context, 0, i, 0)
    views.setOnClickPendingIntent(R.id.widget_btn, pi)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}