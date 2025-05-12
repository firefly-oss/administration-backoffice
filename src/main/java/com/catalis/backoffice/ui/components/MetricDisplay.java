package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable metric display component.
 * This component displays a metric value with an optional growth indicator and time frame.
 */
public class MetricDisplay extends Composite<VerticalLayout> {

    private final H2 valueDisplay;
    private final Span growthIndicator;
    private final Span timeFrame;

    /**
     * Creates a new metric display with the given value, growth percentage, and time frame.
     *
     * @param value the metric value to display
     * @param growthPercentage the growth percentage (can be positive or negative)
     * @param timeFrameText the time frame text (e.g., "since last month")
     */
    public MetricDisplay(String value, double growthPercentage, String timeFrameText) {
        VerticalLayout layout = getContent();
        layout.setPadding(false);
        layout.setSpacing(false);

        // Create the value display
        valueDisplay = new H2(value);
        valueDisplay.addClassNames(
                LumoUtility.Margin.Vertical.NONE,
                LumoUtility.FontSize.XXXLARGE,
                LumoUtility.TextColor.PRIMARY
        );

        // Create the growth indicator
        String prefix = growthPercentage >= 0 ? "↑ " : "↓ ";
        String growthText = prefix + Math.abs(growthPercentage) + "%";
        
        growthIndicator = new Span(growthText);
        growthIndicator.addClassNames(
                growthPercentage >= 0 ? LumoUtility.TextColor.SUCCESS : LumoUtility.TextColor.ERROR,
                LumoUtility.FontWeight.BOLD
        );

        // Create the time frame
        timeFrame = new Span(timeFrameText);
        timeFrame.addClassNames(
                LumoUtility.TextColor.SECONDARY,
                LumoUtility.FontSize.XSMALL
        );

        // Create a layout for the growth indicator and time frame
        HorizontalLayout growthLayout = new HorizontalLayout(growthIndicator, timeFrame);
        growthLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        growthLayout.setSpacing(true);

        layout.add(valueDisplay, growthLayout);
    }

    /**
     * Creates a new metric display with the given value.
     *
     * @param value the metric value to display
     */
    public MetricDisplay(String value) {
        VerticalLayout layout = getContent();
        layout.setPadding(false);
        layout.setSpacing(false);

        // Create the value display
        valueDisplay = new H2(value);
        valueDisplay.addClassNames(
                LumoUtility.Margin.Vertical.NONE,
                LumoUtility.FontSize.XXXLARGE,
                LumoUtility.TextColor.PRIMARY
        );

        layout.add(valueDisplay);
        
        // Initialize fields as null since they're not used in this constructor
        growthIndicator = null;
        timeFrame = null;
    }

    /**
     * Sets the metric value.
     *
     * @param value the value to set
     * @return this metric display instance for method chaining
     */
    public MetricDisplay setValue(String value) {
        valueDisplay.setText(value);
        return this;
    }

    /**
     * Sets the growth percentage.
     *
     * @param growthPercentage the growth percentage to set
     * @return this metric display instance for method chaining
     */
    public MetricDisplay setGrowthPercentage(double growthPercentage) {
        if (growthIndicator != null) {
            String prefix = growthPercentage >= 0 ? "↑ " : "↓ ";
            String growthText = prefix + Math.abs(growthPercentage) + "%";
            
            growthIndicator.setText(growthText);
            growthIndicator.removeClassNames(LumoUtility.TextColor.SUCCESS, LumoUtility.TextColor.ERROR);
            growthIndicator.addClassName(growthPercentage >= 0 ? LumoUtility.TextColor.SUCCESS : LumoUtility.TextColor.ERROR);
        }
        return this;
    }

    /**
     * Sets the time frame text.
     *
     * @param timeFrameText the time frame text to set
     * @return this metric display instance for method chaining
     */
    public MetricDisplay setTimeFrame(String timeFrameText) {
        if (timeFrame != null) {
            timeFrame.setText(timeFrameText);
        }
        return this;
    }
}