package com.example.assignmentone;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.TextView;

/** Displays the selected service using matching entries from resource arrays. */
public class DetailActivity extends Activity {
    // Part G: use one shared key for sending and receiving the topic index.
    public static final String EXTRA_TOPIC_INDEX = "com.example.assignmentone.extra.TOPIC_INDEX";

    // These indices follow the order of the supplied topic arrays for Part H.
    public static final int TOPIC_FACILITIES = 0;
    public static final int TOPIC_EVENTS = 1;
    public static final int TOPIC_CLUBS = 2;
    public static final int TOPIC_SUPPORT = 3;

    // The same index selects the banner name, title, content and image.
    private int topicIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Read the selected topic; default to Facilities when no extra is provided.
        topicIndex = getIntent().getIntExtra(EXTRA_TOPIC_INDEX, TOPIC_FACILITIES);

        // Part H: corresponding positions in these arrays describe one topic.
        String[] topics = getResources().getStringArray(R.array.string_array_topics);
        String[] titles = getResources().getStringArray(R.array.string_array_titles);
        String[] descriptions = getResources().getStringArray(R.array.string_array_content);
        String[] imageNames = getResources().getStringArray(R.array.string_array_images);

        // Keep the received index within the available topics without conditional branches.
        topicIndex = Math.max(TOPIC_FACILITIES, Math.min(topicIndex, titles.length - 1));

        TextView topicView = findViewById(R.id.detail_topic);
        TextView titleView = findViewById(R.id.detail_title);
        TextView contentView = findViewById(R.id.detail_content);
        ImageView iconView = findViewById(R.id.detail_icon);

        topicView.setText(topics[topicIndex]);
        titleView.setText(titles[topicIndex].trim());
        contentView.setText(descriptions[topicIndex].trim());

        // Resolve the supplied drawable name and update its accessible description.
        int imageId = getResources().getIdentifier(
                imageNames[topicIndex].trim(), "drawable", getPackageName());
        iconView.setImageResource(imageId);
        iconView.setContentDescription(topics[topicIndex]);

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
        // These accessors work from the required API 24 onward without a version branch.
        view.setPadding(windowInsets.getSystemWindowInsetLeft(),
                windowInsets.getSystemWindowInsetTop(),
                windowInsets.getSystemWindowInsetRight(),
                windowInsets.getSystemWindowInsetBottom());
    }
}
