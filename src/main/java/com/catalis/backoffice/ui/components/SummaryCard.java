package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable summary card component.
 * This component displays a summary with a title, value, optional subvalue, and an icon.
 */
public class SummaryCard extends Composite<VerticalLayout> {

    private final VerticalLayout layout;
    private final Span valueSpan;
    private final Span subValueSpan;

    /**
     * Creates a new summary card with the given title, value, subvalue, and icon.
     *
     * @param title the title of the summary
     * @param value the main value to display
     * @param subValue the subvalue to display (can be null)
     * @param icon the icon to display
     */
    public SummaryCard(String title, String value, String subValue, VaadinIcon icon) {
        layout = getContent();
        layout.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Padding.SMALL
        );
        layout.setSpacing(false);
        
        // Create header with icon and title
        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        
        Icon cardIcon = icon.create();
        cardIcon.setColor("var(--lumo-primary-color)");
        
        Span titleSpan = new Span(title);
        titleSpan.addClassNames(
                LumoUtility.FontWeight.MEDIUM,
                LumoUtility.TextColor.SECONDARY
        );
        
        header.add(cardIcon, titleSpan);
        
        // Create value and subvalue spans
        valueSpan = new Span(value);
        valueSpan.addClassNames(
                LumoUtility.FontSize.XLARGE,
                LumoUtility.FontWeight.BOLD,
                LumoUtility.TextColor.PRIMARY
        );
        
        subValueSpan = new Span(subValue != null ? subValue : "");
        subValueSpan.addClassNames(
                LumoUtility.FontSize.SMALL,
                LumoUtility.TextColor.SECONDARY
        );
        
        if (subValue == null || subValue.isEmpty()) {
            subValueSpan.setVisible(false);
        }
        
        layout.add(header, valueSpan, subValueSpan);
    }

    /**
     * Creates a new summary card with the given title, value, and icon.
     *
     * @param title the title of the summary
     * @param value the main value to display
     * @param icon the icon to display
     */
    public SummaryCard(String title, String value, VaadinIcon icon) {
        this(title, value, null, icon);
    }

    /**
     * Sets the main value of the summary card.
     *
     * @param value the value to set
     * @return this summary card instance for method chaining
     */
    public SummaryCard setValue(String value) {
        valueSpan.setText(value);
        return this;
    }

    /**
     * Sets the subvalue of the summary card.
     *
     * @param subValue the subvalue to set
     * @return this summary card instance for method chaining
     */
    public SummaryCard setSubValue(String subValue) {
        if (subValue == null || subValue.isEmpty()) {
            subValueSpan.setVisible(false);
        } else {
            subValueSpan.setText(subValue);
            subValueSpan.setVisible(true);
        }
        return this;
    }

    /**
     * Sets the width of the summary card.
     *
     * @param width the width to set (e.g., "25%", "300px")
     * @return this summary card instance for method chaining
     */
    public SummaryCard setWidth(String width) {
        layout.setWidth(width);
        return this;
    }
}