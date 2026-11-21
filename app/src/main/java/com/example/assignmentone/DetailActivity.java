package com.example.assignmentone;

import android.app.Activity;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

/** Displays the detail layout and receives the service selected on the home screen. */
public class DetailActivity extends Activity {
    // Part G: use one shared key for sending and receiving the topic index.
    public static final String EXTRA_TOPIC_INDEX = "com.example.assignmentone.extra.TOPIC_INDEX";

    // These indices follow the order of the supplied topic arrays for Part H.
    public static final int TOPIC_FACILITIES = 0;
    public static final int TOPIC_EVENTS = 1;
    public static final int TOPIC_CLUBS = 2;
    public static final int TOPIC_SUPPORT = 3;

    // Part H will use the received index to populate the detail views.
    private int topicIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Read the selected topic; default to Facilities when no extra is provided.
        topicIndex = getIntent().getIntExtra(EXTRA_TOPIC_INDEX, TOPIC_FACILITIES);

        // Keep the detail page clear of system bars and camera cutouts.
        View content = findViewById(R.id.detail_main);
        content.setOnApplyWindowInsetsListener((view, windowInsets) -> {
            applySystemInsets(view, windowInsets);
            return windowInsets;
        });
        content.requestApplyInsets();
    }

    @SuppressWarnings("deprecation")
    private void applySystemInsets(View view, WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Insets safeArea = windowInsets.getInsets(
                    WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout());
            view.setPadding(safeArea.left, safeArea.top, safeArea.right, safeArea.bottom);
        } else {
            // Use the compatible inset accessors on Android 7 through Android 10.
            view.setPadding(windowInsets.getSystemWindowInsetLeft(),
                    windowInsets.getSystemWindowInsetTop(),
                    windowInsets.getSystemWindowInsetRight(),
                    windowInsets.getSystemWindowInsetBottom());
        }
    }
}
