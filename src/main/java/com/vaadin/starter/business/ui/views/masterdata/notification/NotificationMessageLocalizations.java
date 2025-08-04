package com.vaadin.starter.business.ui.views.masterdata.notification;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Route(value = "notification-message-localizations", layout = MainLayout.class)
@PageTitle(NavigationConstants.NOTIFICATION_MESSAGE_LOCALIZATIONS)
public class NotificationMessageLocalizations extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<NotificationMessageLocalization> grid;
    private Registration resizeListener;
    private ListDataProvider<NotificationMessageLocalization> dataProvider;
    private UI ui;

    // Search form fields
    private TextField messageIdFilter;
    private TextField localeIdFilter;
    private TextField subjectFilter;
    private TextField messageFilter;
    private ComboBox<String> statusFilter;
    private DatePicker creationDateFilter;

    public NotificationMessageLocalizations() {
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
        messageIdFilter = new TextField();
        messageIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        messageIdFilter.setClearButtonVisible(true);

        localeIdFilter = new TextField();
        localeIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        localeIdFilter.setClearButtonVisible(true);

        subjectFilter = new TextField();
        subjectFilter.setValueChangeMode(ValueChangeMode.EAGER);
        subjectFilter.setClearButtonVisible(true);

        messageFilter = new TextField();
        messageFilter.setValueChangeMode(ValueChangeMode.EAGER);
        messageFilter.setClearButtonVisible(true);

        statusFilter = new ComboBox<>();
        statusFilter.setItems("Active", "Inactive");
        statusFilter.setClearButtonVisible(true);

        creationDateFilter = new DatePicker();
        creationDateFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilter());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilter());

        Button createLocalizationButton = UIUtils.createSuccessButton("Create Message Localization");
        createLocalizationButton.addClickListener(e -> openCreateLocalizationDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Message Localization on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createLocalizationButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(messageIdFilter, "Message ID");
        formLayout.addFormItem(localeIdFilter, "Locale ID");
        formLayout.addFormItem(subjectFilter, "Subject");
        formLayout.addFormItem(messageFilter, "Message");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(creationDateFilter, "Creation Date");

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

    private Grid<NotificationMessageLocalization> createGrid() {
        grid = new Grid<>();
        grid.addThemeName("mobile");

        grid.setId("notification-message-localizations");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(NotificationMessageLocalization::getLocalizationId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Localization ID")
                .setSortable(true);
        grid.addColumn(NotificationMessageLocalization::getMessageId)
                .setAutoWidth(true)
                .setHeader("Message ID")
                .setSortable(true);
        grid.addColumn(localization -> localization.getNotificationMessage() != null ? 
                        localization.getNotificationMessage().getMessageCode() : "")
                .setAutoWidth(true)
                .setHeader("Message Code")
                .setSortable(true);
        grid.addColumn(NotificationMessageLocalization::getLocaleId)
                .setAutoWidth(true)
                .setHeader("Locale ID")
                .setSortable(true);
        grid.addColumn(NotificationMessageLocalization::getLocaleCode)
                .setAutoWidth(true)
                .setHeader("Locale Code")
                .setSortable(true);
        grid.addColumn(NotificationMessageLocalization::getLocaleName)
                .setAutoWidth(true)
                .setHeader("Locale Name")
                .setSortable(true);
        grid.addColumn(NotificationMessageLocalization::getSubject)
                .setAutoWidth(true)
                .setHeader("Subject")
                .setSortable(true);
        grid.addColumn(NotificationMessageLocalization::getMessage)
                .setAutoWidth(true)
                .setHeader("Message")
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
        dataProvider = DataProvider.ofCollection(getMockNotificationMessageLocalizations());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private Component createActive(NotificationMessageLocalization localization) {
        Icon icon;
        if (localization.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(NotificationMessageLocalization localization) {
        return new Span(UIUtils.formatDate(localization.getDateCreated().toLocalDate()));
    }

    private Component createActionButtons(NotificationMessageLocalization localization) {
        // Create layout for buttons
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        // Create view details button with eye icon
        Button viewDetailsButton = UIUtils.createButton(VaadinIcon.EYE);
        viewDetailsButton.addClickListener(e -> showDetails(localization));
        viewDetailsButton.getElement().getThemeList().add("small");
        viewDetailsButton.getElement().getThemeList().add("tertiary");
        viewDetailsButton.getElement().setAttribute("title", "View Details");
        UIUtils.setPointerCursor(viewDetailsButton);

        // Create delete button with trash icon
        Button deleteButton = UIUtils.createButton(VaadinIcon.TRASH);
        deleteButton.addClickListener(e -> deleteLocalization(localization));
        deleteButton.getElement().getThemeList().add("small");
        deleteButton.getElement().getThemeList().add("error");
        deleteButton.getElement().getThemeList().add("tertiary");
        deleteButton.getElement().setAttribute("title", "Delete");
        UIUtils.setPointerCursor(deleteButton);

        layout.add(viewDetailsButton, deleteButton);
        return layout;
    }

    private void showDetails(NotificationMessageLocalization localization) {
        NotificationMessageLocalizationDetails localizationDetails = new NotificationMessageLocalizationDetails(localization);
        localizationDetails.open();
    }

    private void deleteLocalization(NotificationMessageLocalization localization) {
        // This would be implemented to delete the localization
        System.out.println("[DEBUG_LOG] Delete notification message localization: " + localization.getLocalizationId());
    }

    private void filter() {
        // Default filter - show all
        dataProvider.clearFilters();
    }

    private void applyFilter() {
        dataProvider.clearFilters();

        // Apply message ID filter if not empty
        if (messageIdFilter.getValue() != null && !messageIdFilter.getValue().isEmpty()) {
            try {
                Long messageIdValue = Long.parseLong(messageIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getMessageId() != null &&
                    localization.getMessageId().equals(messageIdValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply locale ID filter if not empty
        if (localeIdFilter.getValue() != null && !localeIdFilter.getValue().isEmpty()) {
            try {
                Long localeIdValue = Long.parseLong(localeIdFilter.getValue());
                dataProvider.addFilter(localization -> 
                    localization.getLocaleId() != null &&
                    localization.getLocaleId().equals(localeIdValue));
            } catch (NumberFormatException e) {
                // Ignore invalid number format
            }
        }

        // Apply subject filter if not empty
        if (subjectFilter.getValue() != null && !subjectFilter.getValue().isEmpty()) {
            String subjectFilterValue = subjectFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getSubject() != null &&
                localization.getSubject().toLowerCase().contains(subjectFilterValue));
        }

        // Apply message filter if not empty
        if (messageFilter.getValue() != null && !messageFilter.getValue().isEmpty()) {
            String messageFilterValue = messageFilter.getValue().toLowerCase();
            dataProvider.addFilter(localization -> 
                localization.getMessage() != null &&
                localization.getMessage().toLowerCase().contains(messageFilterValue));
        }

        // Apply status filter if selected
        if (statusFilter.getValue() != null) {
            NotificationMessageLocalization.Status statusValue = "Active".equals(statusFilter.getValue()) 
                ? NotificationMessageLocalization.Status.ACTIVE 
                : NotificationMessageLocalization.Status.INACTIVE;
            dataProvider.addFilter(localization -> 
                localization.getStatus() == statusValue);
        }

        // Apply creation date filter if selected
        if (creationDateFilter.getValue() != null) {
            LocalDate dateValue = creationDateFilter.getValue();
            dataProvider.addFilter(localization -> 
                localization.getDateCreated() != null &&
                localization.getDateCreated().toLocalDate().equals(dateValue));
        }
    }

    private void clearFilter() {
        // Clear all filter fields
        messageIdFilter.clear();
        localeIdFilter.clear();
        subjectFilter.clear();
        messageFilter.clear();
        statusFilter.clear();
        creationDateFilter.clear();

        // Reset filters
        dataProvider.clearFilters();
    }

    private void openCreateLocalizationDialog() {
        // This would be implemented to open a dialog for creating a new notification message localization
        NotificationMessageLocalization newLocalization = NotificationMessageLocalization.builder()
                .status(NotificationMessageLocalization.Status.ACTIVE)
                .build();
        NotificationMessageLocalizationDetails localizationDetails = new NotificationMessageLocalizationDetails(newLocalization);
        localizationDetails.open();
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
        List<Grid.Column<NotificationMessageLocalization>> columns = grid.getColumns();

        // "Desktop" columns
        for (Grid.Column<NotificationMessageLocalization> column : columns) {
            column.setVisible(!mobile);
        }
    }

    // Mock data for the grid
    private List<NotificationMessageLocalization> getMockNotificationMessageLocalizations() {
        List<NotificationMessageLocalization> localizations = new ArrayList<>();

        // Create message types
        MessageTypeCatalogDTO emailType = MessageTypeCatalogDTO.builder()
                .typeId(1L)
                .typeCode("EMAIL")
                .typeName("Email")
                .description("Email notifications")
                .build();

        // Create notification messages
        NotificationMessageCatalog welcomeEmail = NotificationMessageCatalog.builder()
                .messageId(1L)
                .messageCode("WELCOME_EMAIL")
                .typeId(1L)
                .messageType(emailType)
                .eventType("USER_REGISTRATION")
                .description("Welcome email sent after registration")
                .defaultSubject("Welcome to our platform!")
                .defaultMessage("Hello {userName}, welcome to our platform. Please activate your account using this link: {activationLink}")
                .status(NotificationMessageCatalog.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(30))
                .dateUpdated(LocalDateTime.now().minusDays(5))
                .build();

        NotificationMessageCatalog passwordReset = NotificationMessageCatalog.builder()
                .messageId(2L)
                .messageCode("PASSWORD_RESET")
                .typeId(1L)
                .messageType(emailType)
                .eventType("PASSWORD_RESET_REQUEST")
                .description("Password reset instructions")
                .defaultSubject("Password Reset Request")
                .defaultMessage("Hello {userName}, you requested a password reset. Use this link: {resetLink}. It expires in {expiryTime} minutes.")
                .status(NotificationMessageCatalog.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(25))
                .dateUpdated(LocalDateTime.now().minusDays(4))
                .build();

        // Create localizations
        localizations.add(NotificationMessageLocalization.builder()
                .localizationId(1L)
                .messageId(1L)
                .notificationMessage(welcomeEmail)
                .localeId(1L)
                .localeCode("en-US")
                .localeName("English (US)")
                .subject("Welcome to our platform!")
                .message("Hello {userName}, welcome to our platform. Please activate your account using this link: {activationLink}")
                .status(NotificationMessageLocalization.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(30))
                .dateUpdated(LocalDateTime.now().minusDays(5))
                .build());

        localizations.add(NotificationMessageLocalization.builder()
                .localizationId(2L)
                .messageId(1L)
                .notificationMessage(welcomeEmail)
                .localeId(2L)
                .localeCode("es-ES")
                .localeName("Spanish (Spain)")
                .subject("¡Bienvenido a nuestra plataforma!")
                .message("Hola {userName}, bienvenido a nuestra plataforma. Por favor, active su cuenta usando este enlace: {activationLink}")
                .status(NotificationMessageLocalization.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(29))
                .dateUpdated(LocalDateTime.now().minusDays(4))
                .build());

        localizations.add(NotificationMessageLocalization.builder()
                .localizationId(3L)
                .messageId(1L)
                .notificationMessage(welcomeEmail)
                .localeId(3L)
                .localeCode("fr-FR")
                .localeName("French (France)")
                .subject("Bienvenue sur notre plateforme !")
                .message("Bonjour {userName}, bienvenue sur notre plateforme. Veuillez activer votre compte en utilisant ce lien : {activationLink}")
                .status(NotificationMessageLocalization.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(28))
                .dateUpdated(LocalDateTime.now().minusDays(3))
                .build());

        localizations.add(NotificationMessageLocalization.builder()
                .localizationId(4L)
                .messageId(2L)
                .notificationMessage(passwordReset)
                .localeId(1L)
                .localeCode("en-US")
                .localeName("English (US)")
                .subject("Password Reset Request")
                .message("Hello {userName}, you requested a password reset. Use this link: {resetLink}. It expires in {expiryTime} minutes.")
                .status(NotificationMessageLocalization.Status.ACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(25))
                .dateUpdated(LocalDateTime.now().minusDays(2))
                .build());

        localizations.add(NotificationMessageLocalization.builder()
                .localizationId(5L)
                .messageId(2L)
                .notificationMessage(passwordReset)
                .localeId(2L)
                .localeCode("es-ES")
                .localeName("Spanish (Spain)")
                .subject("Solicitud de restablecimiento de contraseña")
                .message("Hola {userName}, has solicitado un restablecimiento de contraseña. Usa este enlace: {resetLink}. Caduca en {expiryTime} minutos.")
                .status(NotificationMessageLocalization.Status.INACTIVE)
                .dateCreated(LocalDateTime.now().minusDays(24))
                .dateUpdated(LocalDateTime.now().minusDays(1))
                .build());

        return localizations;
    }
}