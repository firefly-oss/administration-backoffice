package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable notification banner component.
 * This component displays a notification message with an icon, optional action buttons, and a close button.
 */
public class NotificationBanner extends Composite<Div> {

    /**
     * Notification types.
     */
    public enum NotificationType {
        INFO,
        SUCCESS,
        WARNING,
        ERROR
    }

    private final Div container;
    private final HorizontalLayout contentLayout;
    private final Span messageSpan;

    /**
     * Creates a new notification banner with the given message and type.
     *
     * @param message the notification message
     * @param type the notification type
     * @param closable whether the notification can be closed
     */
    public NotificationBanner(String message, NotificationType type, boolean closable) {
        container = getContent();
        container.addClassNames(
                LumoUtility.Padding.MEDIUM,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Margin.Vertical.MEDIUM,
                LumoUtility.Display.FLEX,
                LumoUtility.JustifyContent.BETWEEN,
                LumoUtility.AlignItems.CENTER
        );

        // Set background color based on type
        switch (type) {
            case SUCCESS:
                container.addClassNames(LumoUtility.Background.SUCCESS_10);
                break;
            case WARNING:
                container.addClassNames(LumoUtility.Background.WARNING_10);
                break;
            case ERROR:
                container.addClassNames(LumoUtility.Background.ERROR_10);
                break;
            case INFO:
            default:
                container.addClassNames(LumoUtility.Background.PRIMARY_10);
                break;
        }

        // Create content layout
        contentLayout = new HorizontalLayout();
        contentLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        contentLayout.setSpacing(true);
        contentLayout.setPadding(false);
        contentLayout.setWidthFull();

        // Add icon based on type
        Icon icon;
        switch (type) {
            case SUCCESS:
                icon = VaadinIcon.CHECK_CIRCLE.create();
                icon.setColor("var(--lumo-success-color)");
                break;
            case WARNING:
                icon = VaadinIcon.WARNING.create();
                icon.setColor("var(--lumo-warning-color)");
                break;
            case ERROR:
                icon = VaadinIcon.EXCLAMATION_CIRCLE.create();
                icon.setColor("var(--lumo-error-color)");
                break;
            case INFO:
            default:
                icon = VaadinIcon.INFO_CIRCLE.create();
                icon.setColor("var(--lumo-primary-color)");
                break;
        }

        // Create message span
        messageSpan = new Span(message);
        messageSpan.addClassNames(LumoUtility.FontWeight.MEDIUM);

        contentLayout.add(icon, messageSpan);
        container.add(contentLayout);

        // Add close button if closable
        if (closable) {
            Button closeButton = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
            closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            closeButton.addClickListener(e -> container.setVisible(false));
            container.add(closeButton);
        }
    }

    /**
     * Creates a new notification banner with the given message and type.
     *
     * @param message the notification message
     * @param type the notification type
     */
    public NotificationBanner(String message, NotificationType type) {
        this(message, type, true);
    }

    /**
     * Creates a new info notification banner with the given message.
     *
     * @param message the notification message
     * @return a new info notification banner
     */
    public static NotificationBanner info(String message) {
        return new NotificationBanner(message, NotificationType.INFO);
    }

    /**
     * Creates a new success notification banner with the given message.
     *
     * @param message the notification message
     * @return a new success notification banner
     */
    public static NotificationBanner success(String message) {
        return new NotificationBanner(message, NotificationType.SUCCESS);
    }

    /**
     * Creates a new warning notification banner with the given message.
     *
     * @param message the notification message
     * @return a new warning notification banner
     */
    public static NotificationBanner warning(String message) {
        return new NotificationBanner(message, NotificationType.WARNING);
    }

    /**
     * Creates a new error notification banner with the given message.
     *
     * @param message the notification message
     * @return a new error notification banner
     */
    public static NotificationBanner error(String message) {
        return new NotificationBanner(message, NotificationType.ERROR);
    }

    /**
     * Sets the notification message.
     *
     * @param message the message to set
     * @return this notification banner instance for method chaining
     */
    public NotificationBanner setMessage(String message) {
        messageSpan.setText(message);
        return this;
    }

    /**
     * Adds an action button to the notification.
     *
     * @param caption the button caption
     * @param clickListener the click listener
     * @return this notification banner instance for method chaining
     */
    public NotificationBanner addAction(String caption, Runnable clickListener) {
        Button actionButton = new Button(caption);
        actionButton.addThemeVariants(ButtonVariant.LUMO_SMALL);
        actionButton.addClickListener(e -> clickListener.run());
        contentLayout.add(actionButton);
        return this;
    }
}