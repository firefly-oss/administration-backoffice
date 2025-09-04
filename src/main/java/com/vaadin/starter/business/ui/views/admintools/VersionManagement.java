/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.ui.views.admintools;

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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.VersionInfo;
import com.vaadin.starter.business.backend.service.AdminToolsService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.Initials;
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
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "version-management", layout = MainLayout.class)
@PageTitle("Version Management")
public class VersionManagement extends SplitViewFrame {

    private Grid<VersionInfo> grid;
    private ListDataProvider<VersionInfo> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    private final AdminToolsService adminToolsService;

    @Autowired
    public VersionManagement(AdminToolsService adminToolsService) {
        this.adminToolsService = adminToolsService;
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
        dataProvider = DataProvider.ofCollection(adminToolsService.getVersions());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(VersionInfo::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createVersionInfo))
                .setAutoWidth(true)
                .setHeader("Version");
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createEnvironment))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Environment")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createReleaseDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Release Date")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createVersionInfo(VersionInfo versionInfo) {
        // Get the first letter of each word in the released by name for initials
        String releasedBy = versionInfo.getReleasedBy();
        String initials = releasedBy.substring(0, 1);
        if (releasedBy.contains(" ")) {
            initials += releasedBy.substring(releasedBy.lastIndexOf(" ") + 1, releasedBy.lastIndexOf(" ") + 2);
        }

        ListItem item = new ListItem(
                new Initials(initials), 
                "Version " + versionInfo.getVersionNumber(),
                "Released by " + versionInfo.getReleasedBy());
        item.setPadding(Vertical.XS);
        item.setSpacing(Right.M);
        return item;
    }

    private Component createStatus(VersionInfo versionInfo) {
        String status = versionInfo.getStatus();
        Icon icon;
        String color;

        switch (status) {
            case "Current":
                icon = UIUtils.createSuccessIcon(VaadinIcon.CHECK);
                color = "var(--lumo-success-color)";
                break;
            case "Deprecated":
                icon = UIUtils.createErrorIcon(VaadinIcon.CLOSE_CIRCLE);
                color = "var(--lumo-error-color)";
                break;
            case "Planned":
                icon = UIUtils.createPrimaryIcon(VaadinIcon.CALENDAR);
                color = "var(--lumo-primary-color)";
                break;
            default: // Testing
                icon = UIUtils.createSecondaryIcon(VaadinIcon.FLASK);
                color = "var(--lumo-contrast-color)";
                break;
        }

        Span span = new Span(icon, new Span(" " + status));
        span.getElement().getStyle().set("color", color);
        return span;
    }

    private Component createEnvironment(VersionInfo versionInfo) {
        return new Span(versionInfo.getEnvironment());
    }

    private Component createReleaseDate(VersionInfo versionInfo) {
        return new Span(UIUtils.formatDate(versionInfo.getReleaseDate()));
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
            UIUtils.showNotification("Version information updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(VersionInfo versionInfo) {
        detailsDrawerHeader.setTitle("Version " + versionInfo.getVersionNumber());
        detailsDrawer.setContent(createDetails(versionInfo));
        detailsDrawer.show();
    }

    private FormLayout createDetails(VersionInfo versionInfo) {
        TextField versionNumber = new TextField();
        versionNumber.setValue(versionInfo.getVersionNumber());
        versionNumber.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(versionInfo.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        ComboBox<String> status = new ComboBox<>();
        String[] statuses = {"Current", "Deprecated", "Planned", "Testing"};
        status.setItems(statuses);
        status.setValue(versionInfo.getStatus());
        status.setWidthFull();

        ComboBox<String> environment = new ComboBox<>();
        String[] environments = {"Production", "Staging", "QA", "Development"};
        environment.setItems(environments);
        environment.setValue(versionInfo.getEnvironment());
        environment.setWidthFull();

        DatePicker releaseDate = new DatePicker();
        releaseDate.setValue(versionInfo.getReleaseDate());
        releaseDate.setWidthFull();

        TextField releasedBy = new TextField();
        releasedBy.setValue(versionInfo.getReleasedBy());
        releasedBy.setWidthFull();

        TextArea changeLog = new TextArea();
        changeLog.setValue(versionInfo.getChangeLog());
        changeLog.setWidthFull();
        changeLog.setMinHeight("150px");

        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("1024px", 3,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(versionNumber, "Version Number");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(environment, "Environment");
        form.addFormItem(releaseDate, "Release Date");
        form.addFormItem(releasedBy, "Released By");
        form.addFormItem(changeLog, "Change Log");

        return form;
    }
}
