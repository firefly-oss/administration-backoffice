package com.vaadin.starter.business.ui.views.channelsandservices;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.service.ChannelsAndServicesService;
import com.vaadin.starter.business.dummy.Flow;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Route(value = "provider-flows-configuration", layout = MainLayout.class)
@PageTitle("Provider Flows Configuration")
public class ProviderFlowsConfiguration extends ViewFrame {

    private final ChannelsAndServicesService channelsAndServicesService;

    private Grid<Flow> defaultFlowsGrid;
    private Grid<Flow> providerFlowsGrid;
    private ListDataProvider<Flow> defaultFlowsDataProvider;
    private ListDataProvider<Flow> providerFlowsDataProvider;

    private Tabs tabs;
    private Map<Tab, Component> tabsToPages = new HashMap<>();
    private Div pages;

    private Flow selectedFlow;
    private TextArea defaultFlowsXmlEditor;
    private TextArea providerFlowsXmlEditor;

    private Long providerId = 4000L; // Default provider ID for demo purposes

    @Autowired
    public ProviderFlowsConfiguration(ChannelsAndServicesService channelsAndServicesService) {
        this.channelsAndServicesService = channelsAndServicesService;
        setViewContent(createContent());
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createTabsLayout());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createTabsLayout() {
        // Create tabs
        Tab defaultFlowsTab = new Tab("Default flows");
        Tab providerFlowsTab = new Tab("Provider flows");

        tabs = new Tabs(defaultFlowsTab, providerFlowsTab);
        tabs.setWidthFull();

        // Create pages
        pages = new Div();
        pages.setSizeFull();

        // Create tab content
        Component defaultFlowsPage = createDefaultFlowsPage();
        Component providerFlowsPage = createProviderFlowsPage();

        // Map tabs to pages
        tabsToPages.put(defaultFlowsTab, defaultFlowsPage);
        tabsToPages.put(providerFlowsTab, providerFlowsPage);

        // Show default flows tab by default
        pages.add(defaultFlowsPage);

        // Add tab change listener
        tabs.addSelectedChangeListener(event -> {
            pages.removeAll();
            Tab selectedTab = tabs.getSelectedTab();
            pages.add(tabsToPages.get(selectedTab));
        });

        // Create layout
        VerticalLayout layout = new VerticalLayout(tabs, pages);
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);

        return layout;
    }

    private Component createDefaultFlowsPage() {
        // Create grid
        defaultFlowsGrid = new Grid<>();
        defaultFlowsGrid.addThemeName("mobile");
        defaultFlowsGrid.setHeight("300px");

        // Configure grid columns
        defaultFlowsGrid.addColumn(Flow::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("ID")
                .setSortable(true);
        defaultFlowsGrid.addColumn(Flow::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        defaultFlowsGrid.addColumn(Flow::getDescription)
                .setAutoWidth(true)
                .setHeader("Description");
        defaultFlowsGrid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);

        // Add selection listener
        defaultFlowsGrid.addSelectionListener(event -> {
            event.getFirstSelectedItem().ifPresent(flow -> {
                selectedFlow = flow;
                defaultFlowsXmlEditor.setValue(flow.getXmlDefinition());
                defaultFlowsXmlEditor.setReadOnly(true); // Default flows are read-only
            });
        });

        // Initialize with data provider
        List<Flow> defaultFlows = channelsAndServicesService.getFlowsForProvider(null).stream()
                .filter(flow -> flow.getType() == Flow.FlowType.DEFAULT)
                .collect(Collectors.toList());
        defaultFlowsDataProvider = new ListDataProvider<>(defaultFlows);
        defaultFlowsGrid.setDataProvider(defaultFlowsDataProvider);

        // Create view BPMN diagram button
        Button viewBpmnButton = UIUtils.createButton(VaadinIcon.CHART, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        viewBpmnButton.getElement().setAttribute("title", "View BPMN Diagram");
        viewBpmnButton.addClickListener(e -> showBpmnDiagram(defaultFlowsXmlEditor.getValue()));
        UIUtils.setPointerCursor(viewBpmnButton);

        // Create a horizontal layout for the label and button
        HorizontalLayout labelLayout = new HorizontalLayout();
        labelLayout.setSpacing(true);
        labelLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        labelLayout.add(new Span("Flow XML Definition"));
        labelLayout.add(viewBpmnButton);

        // Create XML editor
        defaultFlowsXmlEditor = new TextArea();
        defaultFlowsXmlEditor.setLabel(null); // Remove default label since we're using a custom one
        defaultFlowsXmlEditor.setWidthFull();
        defaultFlowsXmlEditor.setHeight("300px");
        defaultFlowsXmlEditor.getStyle().set("font-family", "monospace");
        defaultFlowsXmlEditor.setReadOnly(true); // Default flows are read-only

        // Create a vertical layout for the label and editor
        VerticalLayout xmlEditorLayout = new VerticalLayout();
        xmlEditorLayout.setWidthFull();
        xmlEditorLayout.setPadding(false);
        xmlEditorLayout.setSpacing(false);
        xmlEditorLayout.add(labelLayout);
        xmlEditorLayout.add(defaultFlowsXmlEditor);

        // Set spacing between components
        xmlEditorLayout.setMargin(false);

        // Create layout
        VerticalLayout layout = new VerticalLayout();
        layout.add(new H3("Default Flows"));
        layout.add(defaultFlowsGrid);
        layout.add(xmlEditorLayout);
        layout.setSizeFull();

        return layout;
    }

    private void showBpmnDiagram(String xmlContent) {
        if (xmlContent == null || xmlContent.isEmpty()) {
            Notification.show("No XML content to display", 3000, Notification.Position.MIDDLE);
            return;
        }

        // Create dialog for displaying the BPMN diagram
        Dialog dialog = new Dialog();
        dialog.setWidth("80%");
        dialog.setHeight("85%");
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(true);

        // Create a vertical layout for the dialog content
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setSizeFull();
        dialogLayout.setHeight("90%");
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);

        // Create an iframe to display the BPMN diagram
        IFrame bpmnViewer = new IFrame();
        bpmnViewer.setSizeFull();
        bpmnViewer.getElement().setAttribute("srcdoc", createBpmnViewerHtml(xmlContent));

        // Add components to the dialog
        dialogLayout.add(bpmnViewer);
        dialog.add(dialogLayout);

        // Add a close button
        Button closeButton = UIUtils.createButton("Close", ButtonVariant.LUMO_PRIMARY);
        closeButton.addClickListener(e -> dialog.close());
        dialog.add(new HorizontalLayout(closeButton));

        dialog.open();
    }

    private String createBpmnViewerHtml(String xmlContent) {
        // Escape the XML content for embedding in HTML
        String escapedXml = xmlContent.replace("\"", "\\\"").replace("\n", "\\n");

        // Create HTML with embedded bpmn-js viewer
        return "<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "  <meta charset=\"UTF-8\" />\n" +
               "  <title>BPMN Viewer</title>\n" +
               "  <style>\n" +
               "    html, body, #canvas { height: 100%; margin: 0; padding: 0; }\n" +
               "  </style>\n" +
               "  <script src=\"https://unpkg.com/bpmn-js@9.0.3/dist/bpmn-viewer.development.js\"></script>\n" +
               "</head>\n" +
               "<body>\n" +
               "  <div id=\"canvas\"></div>\n" +
               "  <script>\n" +
               "    const viewer = new BpmnJS({ container: '#canvas' });\n" +
               "    const xml = \"" + escapedXml + "\";\n" +
               "    viewer.importXML(xml).then(({ warnings }) => {\n" +
               "      if (warnings.length) {\n" +
               "        console.log('Warnings', warnings);\n" +
               "      }\n" +
               "      viewer.get('canvas').zoom('fit-viewport');\n" +
               "    }).catch(err => {\n" +
               "      console.error('Error', err);\n" +
               "    });\n" +
               "  </script>\n" +
               "</body>\n" +
               "</html>";
    }

    private Component createProviderFlowsPage() {
        // Create grid
        providerFlowsGrid = new Grid<>();
        providerFlowsGrid.addThemeName("mobile");
        providerFlowsGrid.setHeight("300px");

        // Configure grid columns
        providerFlowsGrid.addColumn(Flow::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("ID")
                .setSortable(true);
        providerFlowsGrid.addColumn(Flow::getName)
                .setAutoWidth(true)
                .setHeader("Name")
                .setSortable(true);
        providerFlowsGrid.addColumn(Flow::getDescription)
                .setAutoWidth(true)
                .setHeader("Description");
        providerFlowsGrid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
        providerFlowsGrid.addColumn(new ComponentRenderer<>(this::createToggleActiveButton))
                .setAutoWidth(true)
                .setHeader("Actions")
                .setTextAlign(ColumnTextAlign.CENTER);

        // Add selection listener
        providerFlowsGrid.addSelectionListener(event -> {
            event.getFirstSelectedItem().ifPresent(flow -> {
                selectedFlow = flow;
                providerFlowsXmlEditor.setValue(flow.getXmlDefinition());
                providerFlowsXmlEditor.setReadOnly(false); // Provider flows are editable
            });
        });

        // Initialize with data provider
        List<Flow> providerFlows = channelsAndServicesService.getFlowsForProvider(providerId).stream()
                .filter(flow -> flow.getType() == Flow.FlowType.PROVIDER)
                .collect(Collectors.toList());
        providerFlowsDataProvider = new ListDataProvider<>(providerFlows);
        providerFlowsGrid.setDataProvider(providerFlowsDataProvider);

        // Create view BPMN diagram button
        Button viewBpmnButton = UIUtils.createButton(VaadinIcon.CHART, ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
        viewBpmnButton.getElement().setAttribute("title", "View BPMN Diagram");
        viewBpmnButton.addClickListener(e -> showBpmnDiagram(providerFlowsXmlEditor.getValue()));
        UIUtils.setPointerCursor(viewBpmnButton);

        // Create a horizontal layout for the label and button
        HorizontalLayout labelLayout = new HorizontalLayout();
        labelLayout.setSpacing(true);
        labelLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        labelLayout.add(new Span("Flow XML Definition"));
        labelLayout.add(viewBpmnButton);

        // Create XML editor
        providerFlowsXmlEditor = new TextArea();
        providerFlowsXmlEditor.setLabel(null); // Remove default label since we're using a custom one
        providerFlowsXmlEditor.setWidthFull();
        providerFlowsXmlEditor.setHeight("300px");
        providerFlowsXmlEditor.getStyle().set("font-family", "monospace");

        // Create add flow button
        Button addFlowButton = UIUtils.createSuccessButton("Add Provider Flow");
        addFlowButton.addClickListener(e -> openAddFlowDialog());

        // Create save button
        Button saveButton = UIUtils.createPrimaryButton("Save XML");
        saveButton.addClickListener(e -> {
            if (selectedFlow != null && selectedFlow.getType() == Flow.FlowType.PROVIDER) {
                selectedFlow.setXmlDefinition(providerFlowsXmlEditor.getValue());
                Notification.show("XML definition saved", 3000, Notification.Position.BOTTOM_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        // Create button layout
        HorizontalLayout buttonLayout = new HorizontalLayout(addFlowButton, saveButton);
        buttonLayout.setSpacing(true);

        // Create a vertical layout for the label and editor
        VerticalLayout xmlEditorLayout = new VerticalLayout();
        xmlEditorLayout.setWidthFull();
        xmlEditorLayout.setPadding(false);
        xmlEditorLayout.setSpacing(false);
        xmlEditorLayout.add(labelLayout);
        xmlEditorLayout.add(providerFlowsXmlEditor);

        // Set spacing between components
        xmlEditorLayout.setMargin(false);

        // Create layout
        VerticalLayout layout = new VerticalLayout();
        layout.add(new H3("Provider Flows"));
        layout.add(buttonLayout);
        layout.add(providerFlowsGrid);
        layout.add(xmlEditorLayout);
        layout.setSizeFull();

        return layout;
    }

    private Component createActiveStatus(Flow flow) {
        Icon icon;
        if (flow.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createToggleActiveButton(Flow flow) {
        Button toggleButton = new Button();

        if (flow.isActive()) {
            toggleButton = UIUtils.createTertiaryButton(VaadinIcon.CLOSE_CIRCLE);
            toggleButton.setText("Deactivate");
        } else {
            toggleButton = UIUtils.createTertiaryButton(VaadinIcon.CHECK_CIRCLE);
            toggleButton.setText("Activate");
        }

        toggleButton.addClickListener(e -> {
            // Toggle the active status
            boolean newStatus = !flow.isActive();

            // Update the flow in the service
            Flow updatedFlow = channelsAndServicesService.updateFlowActiveStatus(flow.getId(), newStatus);

            // Update the UI
            if (updatedFlow != null) {
                // Refresh the grid
                providerFlowsDataProvider.refreshItem(updatedFlow);

                // Show notification
                String message = updatedFlow.isActive() ? "Flow activated" : "Flow deactivated";
                Notification.show(message, 3000, Notification.Position.BOTTOM_CENTER)
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        });

        return toggleButton;
    }

    private void openAddFlowDialog() {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setCloseOnEsc(true);
        dialog.setCloseOnOutsideClick(false);

        H3 dialogTitle = new H3("Add New Provider Flow");
        dialogTitle.getStyle().set("margin-top", "0");

        // Create form
        FormLayout form = new FormLayout();

        TextField nameField = new TextField("Name");
        nameField.setWidthFull();
        nameField.setRequired(true);

        TextField descriptionField = new TextField("Description");
        descriptionField.setWidthFull();
        descriptionField.setRequired(true);

        TextArea xmlField = new TextArea("XML Definition");
        xmlField.setWidthFull();
        xmlField.setHeight("200px");
        xmlField.getStyle().set("font-family", "monospace");
        xmlField.setRequired(true);
        xmlField.setValue("<flow name=\"newFlow\">\n  <step id=\"1\" type=\"validation\"/>\n  <step id=\"2\" type=\"processing\"/>\n</flow>");

        form.add(nameField, descriptionField, xmlField);
        form.setColspan(xmlField, 2);

        // Create buttons
        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> dialog.close());

        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            if (nameField.isEmpty() || descriptionField.isEmpty() || xmlField.isEmpty()) {
                Notification.show("Please fill all required fields", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }

            // Create new flow
            Flow newFlow = new Flow(
                    null, // ID will be generated by the service
                    nameField.getValue(),
                    descriptionField.getValue(),
                    Flow.FlowType.PROVIDER,
                    xmlField.getValue(),
                    providerId,
                    true // Active by default
            );

            // Add flow
            Flow addedFlow = channelsAndServicesService.addFlow(newFlow);

            // Update grid
            List<Flow> providerFlows = channelsAndServicesService.getFlowsForProvider(providerId).stream()
                    .filter(flow -> flow.getType() == Flow.FlowType.PROVIDER)
                    .collect(Collectors.toList());
            providerFlowsDataProvider = new ListDataProvider<>(providerFlows);
            providerFlowsGrid.setDataProvider(providerFlowsDataProvider);

            // Show notification
            Notification.show("Provider flow added successfully", 3000, Notification.Position.BOTTOM_CENTER)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);

            dialog.close();
        });

        // Create button layout
        HorizontalLayout buttonLayout = new HorizontalLayout(cancelButton, saveButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setWidthFull();

        // Create dialog layout
        VerticalLayout dialogLayout = new VerticalLayout(dialogTitle, form, buttonLayout);
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(true);
        dialogLayout.getStyle().set("padding", "1em");

        dialog.add(dialogLayout);
        dialog.open();
    }
}
