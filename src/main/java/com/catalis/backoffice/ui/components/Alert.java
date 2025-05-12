package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable alert component.
 * This component displays an alert message with an icon and appropriate styling based on the severity.
 */
public class Alert extends Composite<HorizontalLayout> {

    /**
     * Alert severity levels.
     */
    public enum Severity {
        SUCCESS,
        WARNING,
        ERROR,
        INFO
    }

    /**
     * Creates a new alert with the given message, icon, and severity.
     *
     * @param message the alert message
     * @param icon the icon to display
     * @param severity the severity of the alert
     */
    public Alert(String message, VaadinIcon icon, Severity severity) {
        HorizontalLayout layout = getContent();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setSpacing(true);
        layout.setPadding(false);
        layout.addClassNames(LumoUtility.Padding.Vertical.XSMALL);

        Icon alertIcon = icon.create();
        switch (severity) {
            case ERROR:
                alertIcon.setColor("var(--lumo-error-color)");
                break;
            case WARNING:
                alertIcon.setColor("var(--lumo-warning-color)");
                break;
            case SUCCESS:
                alertIcon.setColor("var(--lumo-success-color)");
                break;
            case INFO:
            default:
                alertIcon.setColor("var(--lumo-primary-color)");
                break;
        }

        Span alertMessage = new Span(message);
        alertMessage.addClassNames(LumoUtility.FontSize.SMALL);

        layout.add(alertIcon, alertMessage);
    }

    /**
     * Creates a new alert with the given message and severity.
     * The icon is automatically selected based on the severity.
     *
     * @param message the alert message
     * @param severity the severity of the alert
     */
    public Alert(String message, Severity severity) {
        this(message, getIconForSeverity(severity), severity);
    }

    /**
     * Creates a new info alert with the given message.
     *
     * @param message the alert message
     * @return a new info alert
     */
    public static Alert info(String message) {
        return new Alert(message, Severity.INFO);
    }

    /**
     * Creates a new success alert with the given message.
     *
     * @param message the alert message
     * @return a new success alert
     */
    public static Alert success(String message) {
        return new Alert(message, Severity.SUCCESS);
    }

    /**
     * Creates a new warning alert with the given message.
     *
     * @param message the alert message
     * @return a new warning alert
     */
    public static Alert warning(String message) {
        return new Alert(message, Severity.WARNING);
    }

    /**
     * Creates a new error alert with the given message.
     *
     * @param message the alert message
     * @return a new error alert
     */
    public static Alert error(String message) {
        return new Alert(message, Severity.ERROR);
    }

    /**
     * Gets the appropriate icon for the given severity.
     *
     * @param severity the severity
     * @return the icon for the severity
     */
    private static VaadinIcon getIconForSeverity(Severity severity) {
        switch (severity) {
            case ERROR:
                return VaadinIcon.EXCLAMATION_CIRCLE;
            case WARNING:
                return VaadinIcon.WARNING;
            case SUCCESS:
                return VaadinIcon.CHECK_CIRCLE;
            case INFO:
            default:
                return VaadinIcon.INFO_CIRCLE;
        }
    }
}