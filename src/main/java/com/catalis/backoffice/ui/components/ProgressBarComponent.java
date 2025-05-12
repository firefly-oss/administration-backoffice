package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable progress bar component.
 * This component displays a progress bar with an optional label and percentage.
 */
public class ProgressBarComponent extends Composite<Div> {

    private final ProgressBar progressBar;
    private final Span labelSpan;
    private final Span percentageSpan;

    /**
     * Creates a new progress bar component with the given value and label.
     *
     * @param value the progress value (0-100)
     * @param label the label to display (can be null)
     * @param showPercentage whether to show the percentage value
     */
    public ProgressBarComponent(double value, String label, boolean showPercentage) {
        Div container = getContent();
        container.addClassNames(LumoUtility.Padding.XSMALL);

        // Create the progress bar
        progressBar = new ProgressBar();
        progressBar.setMin(0);
        progressBar.setMax(100);
        progressBar.setValue(value);
        progressBar.setWidth("100%");

        // Add the progress bar to the container
        container.add(progressBar);

        // If a label or percentage is needed, create a layout for them
        if ((label != null && !label.isEmpty()) || showPercentage) {
            HorizontalLayout infoLayout = new HorizontalLayout();
            infoLayout.setWidthFull();
            infoLayout.setJustifyContentMode(JustifyContentMode.BETWEEN);
            infoLayout.setSpacing(true);
            infoLayout.setPadding(false);
            infoLayout.addClassNames(LumoUtility.Margin.Top.XSMALL);

            // Add label if provided
            if (label != null && !label.isEmpty()) {
                labelSpan = new Span(label);
                labelSpan.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
                infoLayout.add(labelSpan);
            } else {
                labelSpan = null;
            }

            // Add percentage if requested
            if (showPercentage) {
                percentageSpan = new Span(String.format("%.0f%%", value));
                percentageSpan.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
                infoLayout.add(percentageSpan);
            } else {
                percentageSpan = null;
            }

            container.add(infoLayout);
        } else {
            labelSpan = null;
            percentageSpan = null;
        }
    }

    /**
     * Creates a new progress bar component with the given value.
     *
     * @param value the progress value (0-100)
     */
    public ProgressBarComponent(double value) {
        this(value, null, false);
    }

    /**
     * Creates a new progress bar component with the given value and showing the percentage.
     *
     * @param value the progress value (0-100)
     * @param showPercentage whether to show the percentage value
     */
    public ProgressBarComponent(double value, boolean showPercentage) {
        this(value, null, showPercentage);
    }

    /**
     * Sets the progress value.
     *
     * @param value the progress value (0-100)
     * @return this progress bar component instance for method chaining
     */
    public ProgressBarComponent setValue(double value) {
        progressBar.setValue(value);
        if (percentageSpan != null) {
            percentageSpan.setText(String.format("%.0f%%", value));
        }
        return this;
    }

    /**
     * Sets the label.
     *
     * @param label the label to set
     * @return this progress bar component instance for method chaining
     */
    public ProgressBarComponent setLabel(String label) {
        if (labelSpan != null) {
            labelSpan.setText(label);
        }
        return this;
    }

    /**
     * Sets the width of the progress bar.
     *
     * @param width the width to set (e.g., "100px", "50%")
     * @return this progress bar component instance for method chaining
     */
    public ProgressBarComponent setProgressBarWidth(String width) {
        progressBar.setWidth(width);
        return this;
    }
}
