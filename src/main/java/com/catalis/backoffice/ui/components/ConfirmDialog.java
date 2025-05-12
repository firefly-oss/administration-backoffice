package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.function.Consumer;

/**
 * A reusable confirmation dialog component.
 * This component displays a dialog with a title, message, and confirm/cancel buttons.
 */
public class ConfirmDialog extends Dialog {

    /**
     * Dialog types.
     */
    public enum DialogType {
        CONFIRM,
        WARNING,
        DANGER
    }

    private final H3 titleH3;
    private final Paragraph messageP;
    private final Button confirmButton;
    private final Button cancelButton;
    private Consumer<ConfirmDialog> confirmHandler;
    private Consumer<ConfirmDialog> cancelHandler;

    /**
     * Creates a new confirmation dialog with the given title, message, and type.
     *
     * @param title the dialog title
     * @param message the dialog message
     * @param type the dialog type
     */
    public ConfirmDialog(String title, String message, DialogType type) {
        setWidth("400px");
        setCloseOnEsc(true);
        setCloseOnOutsideClick(false);
        setDraggable(true);
        setResizable(false);
        setModal(true);

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);

        // Create header with icon
        HorizontalLayout header = new HorizontalLayout();
        header.setAlignItems(FlexComponent.Alignment.CENTER);
        header.setSpacing(true);

        Icon icon;
        switch (type) {
            case WARNING:
                icon = VaadinIcon.WARNING.create();
                icon.setColor("var(--lumo-warning-color)");
                break;
            case DANGER:
                icon = VaadinIcon.EXCLAMATION_CIRCLE.create();
                icon.setColor("var(--lumo-error-color)");
                break;
            case CONFIRM:
            default:
                icon = VaadinIcon.QUESTION_CIRCLE.create();
                icon.setColor("var(--lumo-primary-color)");
                break;
        }

        titleH3 = new H3(title);
        titleH3.addClassNames(LumoUtility.Margin.NONE);

        header.add(icon, titleH3);
        layout.add(header);

        // Create message
        messageP = new Paragraph(message);
        messageP.addClassNames(LumoUtility.TextColor.SECONDARY);
        layout.add(messageP);

        // Create buttons
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttons.setWidthFull();
        buttons.setSpacing(true);

        cancelButton = new Button("Cancel");
        cancelButton.addClickListener(e -> {
            close();
            if (cancelHandler != null) {
                cancelHandler.accept(this);
            }
        });

        confirmButton = new Button("Confirm");
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        // Set button styling based on type
        switch (type) {
            case WARNING:
                confirmButton.setText("Proceed");
                break;
            case DANGER:
                confirmButton.setText("Delete");
                confirmButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
                break;
            case CONFIRM:
            default:
                // Default styling already applied
                break;
        }
        
        confirmButton.addClickListener(e -> {
            close();
            if (confirmHandler != null) {
                confirmHandler.accept(this);
            }
        });

        buttons.add(cancelButton, confirmButton);
        layout.add(buttons);

        add(layout);
    }

    /**
     * Creates a new confirmation dialog with the given title and message.
     *
     * @param title the dialog title
     * @param message the dialog message
     */
    public ConfirmDialog(String title, String message) {
        this(title, message, DialogType.CONFIRM);
    }

    /**
     * Creates a new warning dialog with the given title and message.
     *
     * @param title the dialog title
     * @param message the dialog message
     * @return a new warning dialog
     */
    public static ConfirmDialog warning(String title, String message) {
        return new ConfirmDialog(title, message, DialogType.WARNING);
    }

    /**
     * Creates a new danger dialog with the given title and message.
     *
     * @param title the dialog title
     * @param message the dialog message
     * @return a new danger dialog
     */
    public static ConfirmDialog danger(String title, String message) {
        return new ConfirmDialog(title, message, DialogType.DANGER);
    }

    /**
     * Sets the title of the dialog.
     *
     * @param title the title to set
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog setTitle(String title) {
        titleH3.setText(title);
        return this;
    }

    /**
     * Sets the message of the dialog.
     *
     * @param message the message to set
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog setMessage(String message) {
        messageP.setText(message);
        return this;
    }

    /**
     * Sets the text of the confirm button.
     *
     * @param text the text to set
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog setConfirmButtonText(String text) {
        confirmButton.setText(text);
        return this;
    }

    /**
     * Sets the text of the cancel button.
     *
     * @param text the text to set
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog setCancelButtonText(String text) {
        cancelButton.setText(text);
        return this;
    }

    /**
     * Sets the handler to be called when the confirm button is clicked.
     *
     * @param handler the handler to call
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog onConfirm(Consumer<ConfirmDialog> handler) {
        this.confirmHandler = handler;
        return this;
    }

    /**
     * Sets the handler to be called when the cancel button is clicked.
     *
     * @param handler the handler to call
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog onCancel(Consumer<ConfirmDialog> handler) {
        this.cancelHandler = handler;
        return this;
    }

    /**
     * Sets the handler to be called when the confirm button is clicked.
     *
     * @param handler the handler to call
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog onConfirm(Runnable handler) {
        this.confirmHandler = dialog -> handler.run();
        return this;
    }

    /**
     * Sets the handler to be called when the cancel button is clicked.
     *
     * @param handler the handler to call
     * @return this dialog instance for method chaining
     */
    public ConfirmDialog onCancel(Runnable handler) {
        this.cancelHandler = dialog -> handler.run();
        return this;
    }
}