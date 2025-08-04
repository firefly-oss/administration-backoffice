package com.vaadin.starter.business.ui.views.masterdata.message;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Route(value = "message-types", layout = MainLayout.class)
@PageTitle("Message Types")
public class MessageTypes extends ViewFrame {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<MessageType> grid;
    private Registration resizeListener;
    private ListDataProvider<MessageType> dataProvider;
    private UI ui;

    // Search form fields
    private TextField typeCodeFilter;
    private TextField typeNameFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;

    public MessageTypes() {
        setViewContent(createContent());

        // Initialize with default filter
        filter();
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        typeCodeFilter = new TextField();
        typeCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        typeCodeFilter.setClearButtonVisible(true);

        typeNameFilter = new TextField();
        typeNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        typeNameFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createMessageTypeButton = UIUtils.createSuccessButton("Create Message Type");
        createMessageTypeButton.addClickListener(e -> openCreateMessageTypeDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Message Type on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createMessageTypeButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(typeCodeFilter, "Type Code");
        formLayout.addFormItem(typeNameFilter, "Type Name");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );

        // Create a container for the form and buttons
        Div formContainer = new Div(formLayout, buttonLayout);
        formContainer.getStyle().set("padding", "1em");
        formContainer.getStyle().set("box-shadow", "0 0 0 1px var(--lumo-contrast-10pct)");
        formContainer.getStyle().set("border-radius", "var(--lumo-border-radius)");
        formContainer.getStyle().set("background-color", "var(--lumo-base-color)");
        formContainer.getStyle().set("margin-bottom", "1em");

        return formContainer;
    }

    private Grid<MessageType> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("message-types");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(MessageType::getTypeId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Type ID")
                .setSortable(true);
        grid.addColumn(MessageType::getTypeCode)
                .setAutoWidth(true)
                .setHeader("Type Code")
                .setSortable(true);
        grid.addColumn(MessageType::getTypeName)
                .setAutoWidth(true)
                .setHeader("Type Name")
                .setSortable(true);
        grid.addColumn(MessageType::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActive))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Creation Date")
                .setTextAlign(ColumnTextAlign.END);

        // Add Actions column with view and delete buttons
        grid.addColumn(new ComponentRenderer<>(this::createActionButtons))
                .setHeader("Actions")
                .setWidth("150px")
                .setFlexGrow(0)
                .setTextAlign(ColumnTextAlign.CENTER);

        // Initialize with data provider
        dataProvider = DataProvider.ofCollection(getMockMessageTypes());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(MessageType messageType) {
        Icon icon;
        if (messageType.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(MessageType messageType) {
        return new Span(UIUtils.formatDate(messageType.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(MessageType messageType) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(messageType));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteMessageType(messageType));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(MessageType messageType) {
        MessageTypeDetails messageTypeDetails = new MessageTypeDetails(messageType);
        messageTypeDetails.open();
    }

    private void deleteMessageType(MessageType messageType) {
        // This would be implemented to delete the message type
        System.out.println("[DEBUG_LOG] Delete message type: " + messageType.getTypeCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply type code filter if not empty
        if (typeCodeFilter.getValue() != null && !typeCodeFilter.getValue().isEmpty()) {
            String typeCodeFilterValue = typeCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(messageType -> 
                messageType.getTypeCode() != null &&
                messageType.getTypeCode().toLowerCase().contains(typeCodeFilterValue));
        }

        // Apply type name filter if not empty
        if (typeNameFilter.getValue() != null && !typeNameFilter.getValue().isEmpty()) {
            String typeNameFilterValue = typeNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(messageType -> 
                messageType.getTypeName() != null &&
                messageType.getTypeName().toLowerCase().contains(typeNameFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(messageType -> 
                messageType.getDescription() != null &&
                messageType.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            boolean isActive = "Active".equals(statusFilter.getValue());
            dataProvider.addFilter(messageType -> 
                messageType.isActive() == isActive);
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        typeCodeFilter.clear();
        typeNameFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateMessageTypeDialog() {
        // This would be implemented to open a dialog for creating a new message type
        MessageType newMessageType = new MessageType();
        newMessageType.setStatus(Status.ACTIVE);
        MessageTypeDetails messageTypeDetails = new MessageTypeDetails(newMessageType);
        messageTypeDetails.open();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        if (resizeListener != null) {
            resizeListener.remove();
        }
        super.onDetach(detachEvent);
    }

    private void updateVisibleColumns(int width) {
        boolean mobile = width < MOBILE_BREAKPOINT;
        List<Grid.Column<MessageType>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<MessageType> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<MessageType> getMockMessageTypes() {
        List<MessageType> messageTypes = new ArrayList<>();

        messageTypes.add(new MessageType(1L, "EMAIL", "Email", "Standard email message type", Status.ACTIVE, LocalDateTime.now().minusDays(30), LocalDateTime.now().minusDays(5)));
        messageTypes.add(new MessageType(2L, "SMS", "SMS", "Short message service type", Status.ACTIVE, LocalDateTime.now().minusDays(25), LocalDateTime.now().minusDays(4)));
        messageTypes.add(new MessageType(3L, "PUSH", "Push Notification", "Mobile app push notification", Status.ACTIVE, LocalDateTime.now().minusDays(20), LocalDateTime.now().minusDays(3)));
        messageTypes.add(new MessageType(4L, "WHATSAPP", "WhatsApp", "WhatsApp messaging service", Status.ACTIVE, LocalDateTime.now().minusDays(15), LocalDateTime.now().minusDays(2)));
        messageTypes.add(new MessageType(5L, "TELEGRAM", "Telegram", "Telegram messaging service", Status.ACTIVE, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(1)));
        messageTypes.add(new MessageType(6L, "INAPP", "In-App Message", "In-application messaging", Status.ACTIVE, LocalDateTime.now().minusDays(5), LocalDateTime.now()));
        messageTypes.add(new MessageType(7L, "WEBHOOK", "Webhook", "Webhook notification", Status.INACTIVE, LocalDateTime.now().minusDays(3), LocalDateTime.now()));
        messageTypes.add(new MessageType(8L, "SLACK", "Slack", "Slack messaging integration", Status.ACTIVE, LocalDateTime.now().minusDays(2), LocalDateTime.now()));
        messageTypes.add(new MessageType(9L, "TEAMS", "Microsoft Teams", "Microsoft Teams integration", Status.INACTIVE, LocalDateTime.now().minusDays(1), LocalDateTime.now()));
        messageTypes.add(new MessageType(10L, "FACEBOOK", "Facebook Messenger", "Facebook Messenger integration", Status.ACTIVE, LocalDateTime.now(), LocalDateTime.now()));

        return messageTypes;
    }

    // MessageType model class
    public static class MessageType {
        private Long typeId;
        private String typeCode;
        private String typeName;
        private String description;
        private Status status;
        private LocalDateTime dateCreated;
        private LocalDateTime dateUpdated;

        public MessageType() {
        }

        public MessageType(Long typeId, String typeCode, String typeName,
                      String description, Status status, 
                      LocalDateTime dateCreated, LocalDateTime dateUpdated) {
            this.typeId = typeId;
            this.typeCode = typeCode;
            this.typeName = typeName;
            this.description = description;
            this.status = status;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
        }

        public Long getTypeId() {
            return typeId;
        }

        public void setTypeId(Long typeId) {
            this.typeId = typeId;
        }

        public String getTypeCode() {
            return typeCode;
        }

        public void setTypeCode(String typeCode) {
            this.typeCode = typeCode;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public boolean isActive() {
            return status == Status.ACTIVE;
        }

        public LocalDateTime getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(LocalDateTime dateCreated) {
            this.dateCreated = dateCreated;
        }

        public LocalDateTime getDateUpdated() {
            return dateUpdated;
        }

        public void setDateUpdated(LocalDateTime dateUpdated) {
            this.dateUpdated = dateUpdated;
        }
    }
}
