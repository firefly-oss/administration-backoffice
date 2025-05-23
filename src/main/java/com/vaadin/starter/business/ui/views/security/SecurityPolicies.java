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
import com.vaadin.starter.business.backend.dto.security.PolicyDTO;
import com.vaadin.starter.business.backend.service.SecurityService;
import com.vaadin.starter.business.ui.MainLayout;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Grid<PolicyDTO> grid;
    private ListDataProvider<PolicyDTO> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    private final SecurityService securityService;

    @Autowired
    public SecurityPolicies(SecurityService securityService) {
        this.securityService = securityService;
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
        dataProvider = DataProvider.ofCollection(securityService.getPolicies());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(PolicyDTO::getName)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Policy Name")
                .setSortable(true);
        grid.addColumn(PolicyDTO::getCategory)
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

    private Component createActiveStatus(PolicyDTO policy) {
        Icon icon;
        if (policy.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(PolicyDTO policy) {
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

    private void showDetails(PolicyDTO policy) {
        detailsDrawerHeader.setTitle(policy.getName());
        detailsDrawer.setContent(createDetails(policy));
        detailsDrawer.show();
    }

    private FormLayout createDetails(PolicyDTO policy) {
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
