package com.example.assignmentone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toast;

/** Displays the responsive home screen and handles service button clicks. */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Keep the weighted content clear of system bars and camera cutouts.
        View content = findViewById(R.id.main);
        content.setOnApplyWindowInsetsListener((view, windowInsets) -> {
            applySystemInsets(view, windowInsets);
            return windowInsets;
        });
        content.requestApplyInsets();
    }

    // Part E: both XML layouts connect their buttons to these public click handlers.
    public void onFacilitiesClick(View view) {
        Toast.makeText(this, R.string.facilities_clicked, Toast.LENGTH_SHORT).show();

        // Part G: open the detail page and identify the selected service.
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_INDEX, DetailActivity.TOPIC_FACILITIES);
        startActivity(intent);
    }

    public void onEventsClick(View view) {
        Toast.makeText(this, R.string.events_clicked, Toast.LENGTH_SHORT).show();

        // Part G: open the detail page and identify the selected service.
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_INDEX, DetailActivity.TOPIC_EVENTS);
        startActivity(intent);
    }

    public void onClubsClick(View view) {
        Toast.makeText(this, R.string.clubs_clicked, Toast.LENGTH_SHORT).show();

        // Part G: open the detail page and identify the selected service.
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_INDEX, DetailActivity.TOPIC_CLUBS);
        startActivity(intent);
    }

    public void onSupportClick(View view) {
        Toast.makeText(this, R.string.support_clicked, Toast.LENGTH_SHORT).show();

        // Part G: open the detail page and identify the selected service.
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_TOPIC_INDEX, DetailActivity.TOPIC_SUPPORT);
        startActivity(intent);
    }

    @SuppressWarnings("deprecation")
    private void applySystemInsets(View view, WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Insets safeArea = windowInsets.getInsets(
                    WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
            view.setPadding(safeArea.left, safeArea.top, safeArea.right, safeArea.bottom);
        } else {
            // These older inset accessors preserve support for the required minimum API 24.
            view.setPadding(windowInsets.getSystemWindowInsetLeft(),
                    windowInsets.getSystemWindowInsetTop(),
                    windowInsets.getSystemWindowInsetRight(),
                    windowInsets.getSystemWindowInsetBottom());
        }
    }
}
