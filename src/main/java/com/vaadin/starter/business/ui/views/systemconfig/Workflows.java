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
import com.vaadin.starter.business.backend.dto.systemconfig.WorkflowDTO;
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

import java.util.stream.Collectors;

@Route(value = "workflows", layout = MainLayout.class)
@PageTitle("Workflows")
public class Workflows extends SplitViewFrame {

    private Grid<WorkflowDTO> grid;
    private ListDataProvider<WorkflowDTO> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;
    
    private final SystemConfigService systemConfigService;

    @Autowired
    public Workflows(SystemConfigService systemConfigService) {
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
        dataProvider = DataProvider.ofCollection(systemConfigService.getWorkflows());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(WorkflowDTO::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(WorkflowDTO::getName)
                .setAutoWidth(true)
                .setHeader("Workflow")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(WorkflowDTO::getCategory)
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

    private Component createStatus(WorkflowDTO workflow) {
        boolean isActive = workflow.isActive();
        Icon icon;
        String text;

        if (isActive) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
            text = "Active";
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOCK);
            text = "Draft";
        }

        Span span = new Span(icon, new Span(" " + text));
        return span;
    }

    private Component createLastModified(WorkflowDTO workflow) {
        return new Span(UIUtils.formatDate(workflow.getLastModified()));
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
            UIUtils.showNotification("Workflow updated.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(WorkflowDTO workflow) {
        detailsDrawerHeader.setTitle("Workflow: " + workflow.getName());
        detailsDrawer.setContent(createDetails(workflow));
        detailsDrawer.show();
    }

    private FormLayout createDetails(WorkflowDTO workflow) {
        TextField workflowName = new TextField();
        workflowName.setValue(workflow.getName());
        workflowName.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(workflow.getDescription());
        description.setWidthFull();
        description.setMinHeight("100px");

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Draft", "Archived");
        status.setValue(workflow.getStatus());

        ComboBox<String> category = new ComboBox<>();
        category.setItems("Customer", "Finance", "Legal", "Security", "Operations");
        category.setValue(workflow.getCategory());
        category.setWidthFull();

        TextField steps = new TextField();
        steps.setValue(Integer.toString(workflow.getStepsCount()));
        steps.setWidthFull();

        ComboBox<String> roles = new ComboBox<>();
        roles.setItems(workflow.getRoles());
        roles.setValue(workflow.getRoles().get(0));
        roles.setWidthFull();

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
        form.addFormItem(workflowName, "Workflow Name");
        form.addFormItem(description, "Description");
        form.addFormItem(status, "Status");
        form.addFormItem(category, "Category");
        form.addFormItem(steps, "Number of Steps");
        form.addFormItem(roles, "Primary Role");

        return form;
    }
}