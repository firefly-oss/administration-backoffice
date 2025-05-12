package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.util.function.Consumer;

/**
 * A reusable filter bar component.
 * This component provides a horizontal layout for filter controls with an optional apply button.
 */
public class FilterBar extends Composite<HorizontalLayout> {

    private final HorizontalLayout layout;
    private Button applyButton;
    private Consumer<FilterBar> applyHandler;

    /**
     * Creates a new filter bar.
     *
     * @param showApplyButton whether to show an apply button
     */
    public FilterBar(boolean showApplyButton) {
        layout = getContent();
        layout.setWidthFull();
        layout.setPadding(true);
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.BASELINE);
        layout.addClassNames(
                LumoUtility.Background.CONTRAST_5,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Margin.Vertical.SMALL
        );

        if (showApplyButton) {
            applyButton = new Button("Apply Filters", VaadinIcon.FILTER.create());
            applyButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            applyButton.addClickListener(e -> {
                if (applyHandler != null) {
                    applyHandler.accept(this);
                }
            });
            layout.add(applyButton);
        }
    }

    /**
     * Creates a new filter bar without an apply button.
     */
    public FilterBar() {
        this(false);
    }

    /**
     * Adds filter components to the filter bar.
     *
     * @param components the components to add
     * @return this filter bar instance for method chaining
     */
    public FilterBar addFilters(Component... components) {
        if (applyButton != null) {
            // Add all components except the apply button
            for (Component component : components) {
                layout.addComponentAtIndex(layout.getComponentCount() - 1, component);
            }
        } else {
            // Add all components
            layout.add(components);
        }
        return this;
    }

    /**
     * Sets the handler to be called when the apply button is clicked.
     *
     * @param handler the handler to call
     * @return this filter bar instance for method chaining
     */
    public FilterBar onApply(Consumer<FilterBar> handler) {
        this.applyHandler = handler;
        return this;
    }

    /**
     * Shows the apply button.
     *
     * @return this filter bar instance for method chaining
     */
    public FilterBar showApplyButton() {
        if (applyButton == null) {
            applyButton = new Button("Apply Filters", VaadinIcon.FILTER.create());
            applyButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            applyButton.addClickListener(e -> {
                if (applyHandler != null) {
                    applyHandler.accept(this);
                }
            });
            layout.add(applyButton);
        } else if (applyButton.getParent() == null) {
            layout.add(applyButton);
        }
        return this;
    }

    /**
     * Hides the apply button.
     *
     * @return this filter bar instance for method chaining
     */
    public FilterBar hideApplyButton() {
        if (applyButton != null && applyButton.getParent() != null) {
            layout.remove(applyButton);
        }
        return this;
    }
}
