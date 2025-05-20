package com.vaadin.starter.business.ui.views.security;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Right;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route(value = "security-policies", layout = MainLayout.class)
@PageTitle("Security Policies")
public class SecurityPolicies extends SplitViewFrame {

    private Grid<Policy> grid;
    private ListDataProvider<Policy> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample Policy class for demonstration
    private static class Policy {
        private String name;
        private String category;
        private String description;
        private boolean isActive;
        private LocalDate lastUpdated;

        public Policy(String name, String category, String description, boolean isActive, LocalDate lastUpdated) {
            this.name = name;
            this.category = category;
            this.description = description;
            this.isActive = isActive;
            this.lastUpdated = lastUpdated;
        }

        public String getName() {
            return name;
        }

        public String getCategory() {
            return category;
        }

        public String getDescription() {
            return description;
        }

        public boolean isActive() {
            return isActive;
        }

        public LocalDate getLastUpdated() {
            return lastUpdated;
        }
    }

    // Sample data
    private List<Policy> getPolicies() {
        return Arrays.asList(
            new Policy("Password Policy", "Authentication", 
                "Passwords must be at least 8 characters long and include uppercase, lowercase, numbers, and special characters.", 
                true, LocalDate.now().minusDays(15)),
            new Policy("Account Lockout Policy", "Authentication", 
                "Accounts will be locked after 5 failed login attempts.", 
                true, LocalDate.now().minusDays(30)),
            new Policy("Session Timeout Policy", "Session Management", 
                "User sessions will timeout after 30 minutes of inactivity.", 
                true, LocalDate.now().minusDays(45)),
            new Policy("Data Retention Policy", "Data Management", 
                "User data will be retained for a maximum of 2 years after account closure.", 
                true, LocalDate.now().minusDays(60)),
            new Policy("Two-Factor Authentication", "Authentication", 
                "Two-factor authentication is required for all administrative accounts.", 
                false, LocalDate.now().minusDays(90))
        );
    }

    public SecurityPolicies() {
        setViewContent(createContent());
        setViewDetails(createDetailsDrawer());
        setViewDetailsPosition(Position.BOTTOM);
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        return content;
    }

    private Grid createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem()
                .ifPresent(this::showDetails));
        dataProvider = DataProvider.ofCollection(getPolicies());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Policy::getName)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Policy Name")
                .setSortable(true);
        grid.addColumn(Policy::getCategory)
                .setAutoWidth(true)
                .setHeader("Category")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Updated")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createActiveStatus(Policy policy) {
        Icon icon;
        if (policy.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Policy policy) {
        return new Span(UIUtils.formatDate(policy.getLastUpdated()));
    }

    private DetailsDrawer createDetailsDrawer() {
        detailsDrawer = new DetailsDrawer(DetailsDrawer.Position.BOTTOM);

        // Header
        detailsDrawerHeader = new DetailsDrawerHeader("");
        detailsDrawerHeader.addCloseListener(buttonClickEvent -> detailsDrawer.hide());
        detailsDrawer.setHeader(detailsDrawerHeader);

        // Footer
        DetailsDrawerFooter footer = new DetailsDrawerFooter();
        footer.addSaveListener(e -> {
            detailsDrawer.hide();
            UIUtils.showNotification("Changes saved.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Policy policy) {
        detailsDrawerHeader.setTitle(policy.getName());
        detailsDrawer.setContent(createDetails(policy));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Policy policy) {
        TextField name = new TextField();
        name.setValue(policy.getName());
        name.setWidthFull();

        ComboBox<String> category = new ComboBox<>();
        category.setItems("Authentication", "Authorization", "Session Management", "Data Management", "Network Security");
        category.setValue(policy.getCategory());
        category.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(policy.getDescription());
        description.setWidthFull();
        description.setHeight("150px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(policy.isActive() ? "Active" : "Inactive");

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(name, "Policy Name");
        form.addFormItem(category, "Category");
        form.addFormItem(status, "Status");
        form.addFormItem(description, "Description");
        
        return form;
    }
}