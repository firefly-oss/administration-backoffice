package com.vaadin.starter.business.ui.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.constants.NavigationConstants;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Uniform;

@PageTitle(NavigationConstants.HELP)
@Route(value = "help", layout = MainLayout.class)
public class Help extends ViewFrame {

    public Help() {
        setId("help");
        setViewContent(createContent());
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout();
        content.setFlexDirection(FlexDirection.COLUMN);
        content.setMargin(Horizontal.AUTO);
        content.setMaxWidth("840px");
        content.setPadding(Uniform.RESPONSIVE_L);

        // Title
        H2 title = new H2("Help Center");
        content.add(title);
        
        // Introduction
        Paragraph intro = new Paragraph("Welcome to the Administration Backoffice Help Center. " +
                "This page provides information about all active sections and their functionality.");
        content.add(intro);

        // Products and Services
        content.add(createSectionHelp(
                NavigationConstants.PRODUCTS_AND_SERVICES,
                "Manage all product-related configurations and settings.",
                new String[][] {
                        {NavigationConstants.PRODUCT_CATALOG, "View and manage the catalog of financial products offered by the institution."},
                        {NavigationConstants.RATES_AND_FEES, "Configure interest rates and fees associated with different products."},
                        {NavigationConstants.LENDING_CONFIGURATION, "Set up and manage lending parameters, terms, and conditions."}
                }
        ));

        // Users and Security
        content.add(createSectionHelp(
                NavigationConstants.USERS_AND_SECURITY,
                "Manage user accounts, permissions, and security settings.",
                new String[][] {
                        {NavigationConstants.INTERNAL_USERS, "Create and manage internal user accounts for staff and administrators."},
                        {NavigationConstants.ROLES_AND_PERMISSIONS, "Define roles and assign permissions to control access to system features."}
                }
        ));

        // Channels and External Services
        content.add(createSectionHelp(
                NavigationConstants.CHANNELS_AND_EXTERNAL_SERVICES,
                "Configure integration with external channels and service providers.",
                new String[][] {
                        {NavigationConstants.CHANNEL_INTEGRATION, "Set up and manage integration with different distribution channels."},
                        {NavigationConstants.SERVICE_PROVIDERS, "Configure connections with external service providers and partners."}
                }
        ));

        // System Configuration
        content.add(createSectionHelp(
                NavigationConstants.SYSTEM_CONFIGURATION,
                "Configure system-wide settings and parameters.",
                new String[][] {
                        {NavigationConstants.GENERAL_CONFIGURATION, "Manage general system settings and parameters."},
                        {NavigationConstants.WORKFLOWS, "Configure business process workflows and approval chains."},
                        {NavigationConstants.NOTIFICATIONS, "Set up system notifications and alerts."},
                        {NavigationConstants.MASTER_DATA, "Manage reference data and master data records."}
                }
        ));

        return content;
    }

    private Component createSectionHelp(String sectionTitle, String sectionDescription, String[][] subsections) {
        VerticalLayout section = new VerticalLayout();
        section.setPadding(false);
        section.setSpacing(true);
        
        // Section title
        H3 title = new H3(sectionTitle);
        section.add(title);
        
        // Section description
        Paragraph description = new Paragraph(sectionDescription);
        section.add(description);
        
        // Subsections
        for (String[] subsection : subsections) {
            String subsectionTitle = subsection[0];
            String subsectionDescription = subsection[1];
            
            Html subsectionContent = new Html(
                    "<div style='margin-left: 20px; margin-bottom: 10px;'>" +
                    "<strong>" + subsectionTitle + "</strong>: " +
                    subsectionDescription +
                    "</div>"
            );
            
            section.add(subsectionContent);
        }
        
        return section;
    }
}