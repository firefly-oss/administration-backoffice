package com.vaadin.starter.business.ui.views.systemconfig;

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
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.dto.systemconfig.ConfigItemDTO;
import com.vaadin.starter.business.backend.service.SystemConfigService;
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
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "general-configuration", layout = MainLayout.class)
@PageTitle("General Configuration")
public class GeneralConfiguration extends SplitViewFrame {

    private Grid<ConfigItemDTO> grid;
    private ListDataProvider<ConfigItemDTO> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final SystemConfigService systemConfigService;

    @Autowired
    public GeneralConfiguration(SystemConfigService systemConfigService) {
        this.systemConfigService = systemConfigService;
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
        dataProvider = DataProvider.ofCollection(systemConfigService.getConfigItems());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(ConfigItemDTO::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(ConfigItemDTO::getName)
                .setAutoWidth(true)
                .setHeader("Configuration")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(ConfigItemDTO::getCategory)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Category")
                .setTextAlign(ColumnTextAlign.END)
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createLastModified))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Last Modified")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createStatus(ConfigItemDTO configItem) {
        Icon icon;
        if (configItem.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOCK);
        }
        return icon;
    }

    private Component createLastModified(ConfigItemDTO configItem) {
        return new Span(UIUtils.formatDate(configItem.getLastModified()));
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
            UIUtils.showNotification("Configuration updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(ConfigItemDTO configItem) {
        detailsDrawerHeader.setTitle("Configuration: " + configItem.getName());
        detailsDrawer.setContent(createDetails(configItem));
        detailsDrawer.show();
    }

    private FormLayout createDetails(ConfigItemDTO configItem) {
        TextField configName = new TextField();
        configName.setValue(configItem.getName());
        configName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(configItem.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Pending", "Deprecated");
        status.setValue(configItem.getStatus());

        ComboBox<String> category = new ComboBox<>();
        String[] categories = {"Regional", "Security", "Interface", "Communication", "Performance"};
        category.setItems(categories);
        category.setValue(configItem.getCategory());
        category.setWidthFull();

        TextField value = new TextField();
        value.setValue(configItem.getValue());
        value.setWidthFull();

        ComboBox<String> scope = new ComboBox<>();
        scope.setItems("Global", "Regional", "Branch", "User");
        scope.setValue(configItem.getScope());
        scope.setWidthFull();

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
        form.addFormItem(configName, "Configuration Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(category, "Category");
        form.addFormItem(value, "Value");
        form.addFormItem(scope, "Scope");

        return form;
    }
}