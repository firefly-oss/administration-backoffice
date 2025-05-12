package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable status badge component.
 * This component displays a status text with appropriate styling based on the status type.
 */
public class StatusBadge extends Composite<Span> {

    /**
     * Status types for the badge.
     */
    public enum StatusType {
        SUCCESS,
        ERROR,
        WARNING,
        NEUTRAL
    }

    /**
     * Creates a new status badge with the given text and status type.
     *
     * @param text the text to display in the badge
     * @param type the type of status (determines the styling)
     */
    public StatusBadge(String text, StatusType type) {
        Span badge = getContent();
        badge.setText(text);
        badge.getElement().getThemeList().clear();
        
        // Apply base badge styling
        badge.getElement().getThemeList().add("badge");
        
        // Apply status-specific styling
        switch (type) {
            case SUCCESS:
                badge.getElement().getThemeList().add("success");
                break;
            case ERROR:
                badge.getElement().getThemeList().add("error");
                break;
            case WARNING:
                badge.getElement().getThemeList().add("contrast");
                badge.getStyle().set("background-color", "var(--lumo-warning-color)");
                badge.getStyle().set("color", "white");
                break;
            case NEUTRAL:
            default:
                // Default badge styling is already applied
                break;
        }
    }

    /**
     * Creates a new status badge with the given text and automatically determines
     * the status type based on common status text values.
     *
     * @param text the text to display in the badge
     */
    public StatusBadge(String text) {
        this(text, determineStatusType(text));
    }

    /**
     * Determines the status type based on common status text values.
     *
     * @param text the status text
     * @return the determined status type
     */
    private static StatusType determineStatusType(String text) {
        String lowerText = text.toLowerCase();
        
        if (lowerText.contains("active") || 
            lowerText.contains("success") || 
            lowerText.contains("completed") || 
            lowerText.contains("approved") ||
            lowerText.contains("current")) {
            return StatusType.SUCCESS;
        } else if (lowerText.contains("error") || 
                  lowerText.contains("failed") || 
                  lowerText.contains("rejected") || 
                  lowerText.contains("inactive") ||
                  lowerText.contains("late") ||
                  lowerText.contains("blocked")) {
            return StatusType.ERROR;
        } else if (lowerText.contains("warning") || 
                  lowerText.contains("pending") || 
                  lowerText.contains("in progress")) {
            return StatusType.WARNING;
        } else {
            return StatusType.NEUTRAL;
        }
    }
}