package com.example.assignmentone;

import android.app.Activity;
import android.graphics.Insets;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

/** Displays the responsive XML home screen required for Part B. */
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
