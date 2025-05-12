package com.catalis.backoffice.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * A reusable card component with a title and content.
 * This component can be used to display information in a card-like container.
 */
public class Card extends Composite<VerticalLayout> {

    private final VerticalLayout content = new VerticalLayout();

    /**
     * Creates a new card with the given title.
     *
     * @param title the title of the card
     */
    public Card(String title) {
        content.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.SMALL,
                LumoUtility.Padding.MEDIUM
        );
        content.setSpacing(false);

        H3 header = new H3(title);
        header.addClassNames(
                LumoUtility.Margin.NONE,
                LumoUtility.FontWeight.MEDIUM,
                LumoUtility.TextColor.SECONDARY
        );

        content.add(header);
        getContent().add(content);
    }

    /**
     * Adds components to the card content.
     *
     * @param components the components to add
     * @return this card instance for method chaining
     */
    public Card add(Component... components) {
        content.add(components);
        return this;
    }
}