package com.vaadin.starter.business.ui.views.masterdata.notification;

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
import com.vaadin.starter.business.ui.constants.NavigationConstants;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import com.vaadin.starter.business.ui.views.masterdata.message.MessageTypeCatalogDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "notification-messages", layout = MainLayout.class)
@PageTitle(NavigationConstants.NOTIFICATION_MESSAGES)
public class NotificationMessages extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<NotificationMessageCatalogDTOWrapper> grid;
    private Registration resizeListener;
    private ListDataProvider<NotificationMessageCatalogDTOWrapper> dataProvider;
    private UI ui;

    // Search form fields
    private TextField messageCodeFilter;
    private TextField eventTypeFilter;
    private TextField descriptionFilter;
    private ComboBox<String> statusFilter;
    private ComboBox<Long> messageTypeFilter;

    public NotificationMessages() {
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
        messageCodeFilter = new TextField();
        messageCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        messageCodeFilter.setClearButtonVisible(true);

        eventTypeFilter = new TextField();
        eventTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        eventTypeFilter.setClearButtonVisible(true);

        descriptionFilter = new TextField();
        descriptionFilter.setValueChangeMode(ValueChangeMode.EAGER);
        descriptionFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        messageTypeFilter = new ComboBox<>();
        // Populate with message type IDs from mock data
        List<Long> typeIds = new ArrayList<>();
        typeIds.add(1L);
        typeIds.add(2L);
        typeIds.add(3L);
        messageTypeFilter.setItems(typeIds);
        messageTypeFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createMessageButton = UIUtils.createSuccessButton("Create Notification Message");
        createMessageButton.addClickListener(e -> openCreateMessageDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Notification Message on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createMessageButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(messageCodeFilter, "Message Code");
        formLayout.addFormItem(eventTypeFilter, "Event Type");
        formLayout.addFormItem(descriptionFilter, "Description");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(messageTypeFilter, "Message Type");

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

    private Grid<NotificationMessageCatalogDTOWrapper> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("notification-messages");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(NotificationMessageCatalogDTOWrapper::getMessageId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Message ID")
                .setSortable(true);
        grid.addColumn(NotificationMessageCatalogDTOWrapper::getMessageCode)
                .setAutoWidth(true)
                .setHeader("Message Code")
                .setSortable(true);
        grid.addColumn(message -> message.getMessageType() != null ? message.getMessageType().getTypeName() : "")
                .setAutoWidth(true)
                .setHeader("Message Type")
                .setSortable(true);
        grid.addColumn(NotificationMessageCatalogDTOWrapper::getEventType)
                .setAutoWidth(true)
                .setHeader("Event Type")
                .setSortable(true);
        grid.addColumn(NotificationMessageCatalogDTOWrapper::getDescription)
                .setAutoWidth(true)
                .setHeader("Description")
                .setSortable(true);
        grid.addColumn(NotificationMessageCatalogDTOWrapper::getDefaultSubject)
                .setAutoWidth(true)
                .setHeader("Default Subject")
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
        dataProvider = DataProvider.ofCollection(getMockNotificationMessages());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(NotificationMessageCatalogDTOWrapper message) {
        Icon icon;
        if (message.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(NotificationMessageCatalogDTOWrapper message) {
        return new Span(UIUtils.formatDate(message.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(NotificationMessageCatalogDTOWrapper message) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(message));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteMessage(message));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(NotificationMessageCatalogDTOWrapper message) {
        NotificationMessageDetails messageDetails = new NotificationMessageDetails(message);
        messageDetails.open();
    }

    private void deleteMessage(NotificationMessageCatalogDTOWrapper message) {
        // This would be implemented to delete the message
        System.out.println("[DEBUG_LOG] Delete notification message: " + message.getMessageCode());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply message code filter if not empty
        if (messageCodeFilter.getValue() != null && !messageCodeFilter.getValue().isEmpty()) {
            String messageCodeFilterValue = messageCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(message -> 
                message.getMessageCode() != null &&
                message.getMessageCode().toLowerCase().contains(messageCodeFilterValue));
        }

        // Apply event type filter if not empty
        if (eventTypeFilter.getValue() != null && !eventTypeFilter.getValue().isEmpty()) {
            String eventTypeFilterValue = eventTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(message -> 
                message.getEventType() != null &&
                message.getEventType().toLowerCase().contains(eventTypeFilterValue));
        }

        // Apply description filter if not empty
        if (descriptionFilter.getValue() != null && !descriptionFilter.getValue().isEmpty()) {
            String descriptionFilterValue = descriptionFilter.getValue().toLowerCase();
            dataProvider.addFilter(message -> 
                message.getDescription() != null &&
                message.getDescription().toLowerCase().contains(descriptionFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            NotificationMessageCatalogDTOWrapper.Status statusValue = "Active".equals(statusFilter.getValue()) 
                ? NotificationMessageCatalogDTOWrapper.Status.ACTIVE 
                : NotificationMessageCatalogDTOWrapper.Status.INACTIVE;
            dataProvider.addFilter(message -> 
                message.getStatus() == statusValue);
        }

        // Apply message type filter if selected
        if (messageTypeFilter.getValue() != null) {
            Long messageTypeId = messageTypeFilter.getValue();
            dataProvider.addFilter(message -> 
                message.getTypeId() != null && 
                message.getTypeId().equals(messageTypeId));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        messageCodeFilter.clear();
        eventTypeFilter.clear();
        descriptionFilter.clear();
        statusFilter.clear();
        messageTypeFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateMessageDialog() {
        // This would be implemented to open a dialog for creating a new notification message
        NotificationMessageCatalogDTOWrapper newMessage = NotificationMessageCatalogDTOWrapper.builder()
                .status(NotificationMessageCatalogDTOWrapper.Status.ACTIVE)
                .build();
        NotificationMessageDetails messageDetails = new NotificationMessageDetails(newMessage);
        messageDetails.open();
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
        List<Grid.Column<NotificationMessageCatalogDTOWrapper>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<NotificationMessageCatalogDTOWrapper> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<NotificationMessageCatalogDTOWrapper> getMockNotificationMessages() {
        List<NotificationMessageCatalogDTOWrapper> messages = new ArrayList<>();

        // Create message types
        MessageTypeCatalogDTO emailType = MessageTypeCatalogDTO.builder()
                .typeId(1L)
                .typeCode("EMAIL")
                .typeName("Email")
                .description("Email notifications")
                .build();

        MessageTypeCatalogDTO smsType = MessageTypeCatalogDTO.builder()
                .typeId(2L)
                .typeCode("SMS")
                .typeName("SMS")
                .description("SMS notifications")
                .build();

        MessageTypeCatalogDTO pushType = MessageTypeCatalogDTO.builder()
                .typeId(3L)
                .typeCode("PUSH")
                .typeName("Push Notification")
                .description("Mobile push notifications")
                .build();

        // Create sample parameters
        Map<String, Object> welcomeParams = new HashMap<>();
        welcomeParams.put("userName", "String");
        welcomeParams.put("activationLink", "String");

        Map<String, Object> passwordResetParams = new HashMap<>();
        passwordResetParams.put("userName", "String");
        passwordResetParams.put("resetLink", "String");
        passwordResetParams.put("expiryTime", "Number");

        Map<String, Object> transactionParams = new HashMap<>();
        transactionParams.put("amount", "Number");
        transactionParams.put("currency", "String");
        transactionParams.put("date", "Date");
        transactionParams.put("accountNumber", "String");

        // Create notification messages
        messages.add(NotificationMessageCatalogDTOWrapper.builder()
                .messageId(1L)
                .messageCode("WELCOME_EMAIL")
                .typeId(1L)
                .messageType(emailType)
                .eventType("USER_REGISTRATION")
                .description("Welcome email sent after registration")
                .defaultSubject("Welcome to our platform!")
                .defaultMessage("Hello {userName}, welcome to our platform. Please activate your account using this link: {activationLink}")
                .parameters(welcomeParams)
                .status(NotificationMessageCatalogDTOWrapper.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(30))
                .dateUpdated(LocalDateTime.now().minusDays(5))
                .build());

        messages.add(NotificationMessageCatalogDTOWrapper.builder()
                .messageId(2L)
                .messageCode("PASSWORD_RESET")
                .typeId(1L)
                .messageType(emailType)
                .eventType("PASSWORD_RESET_REQUEST")
                .description("Password reset instructions")
                .defaultSubject("Password Reset Request")
                .defaultMessage("Hello {userName}, you requested a password reset. Use this link: {resetLink}. It expires in {expiryTime} minutes.")
                .parameters(passwordResetParams)
                .status(NotificationMessageCatalogDTOWrapper.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(25))
                .dateUpdated(LocalDateTime.now().minusDays(4))
                .build());

        messages.add(NotificationMessageCatalogDTOWrapper.builder()
                .messageId(3L)
                .messageCode("TRANSACTION_ALERT_SMS")
                .typeId(2L)
                .messageType(smsType)
                .eventType("TRANSACTION_COMPLETED")
                .description("SMS alert for completed transactions")
                .defaultSubject("")
                .defaultMessage("Transaction of {amount} {currency} completed on {date} for account {accountNumber}.")
                .parameters(transactionParams)
                .status(NotificationMessageCatalogDTOWrapper.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(20))
                .dateUpdated(LocalDateTime.now().minusDays(3))
                .build());

        messages.add(NotificationMessageCatalogDTOWrapper.builder()
                .messageId(4L)
                .messageCode("TRANSACTION_ALERT_PUSH")
                .typeId(3L)
                .messageType(pushType)
                .eventType("TRANSACTION_COMPLETED")
                .description("Push notification for completed transactions")
                .defaultSubject("Transaction Alert")
                .defaultMessage("Transaction of {amount} {currency} completed")
                .parameters(transactionParams)
                .status(NotificationMessageCatalogDTOWrapper.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(15))
                .dateUpdated(LocalDateTime.now().minusDays(2))
                .build());

        messages.add(NotificationMessageCatalogDTOWrapper.builder()
                .messageId(5L)
                .messageCode("ACCOUNT_LOCKED")
                .typeId(1L)
                .messageType(emailType)
                .eventType("ACCOUNT_SECURITY")
                .description("Account locked notification")
                .defaultSubject("Your Account Has Been Locked")
                .defaultMessage("Hello {userName}, your account has been locked due to multiple failed login attempts.")
                .parameters(welcomeParams)
                .status(NotificationMessageCatalogDTOWrapper.Status.INACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(10))
                .dateUpdated(LocalDateTime.now().minusDays(1))
                .build());

        return messages;
    }
}