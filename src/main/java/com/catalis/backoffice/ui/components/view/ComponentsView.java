package com.catalis.backoffice.ui.components.view;

import com.catalis.backoffice.base.ui.view.MainLayout;
import com.catalis.backoffice.ui.components.*;
import com.catalis.backoffice.ui.components.ViewToolbar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * View that showcases all the reusable components.
 */
@Route(value = "components", layout = MainLayout.class)
@PageTitle("Components | Bank Backoffice")
public class ComponentsView extends VerticalLayout {

    public ComponentsView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        add(new ViewToolbar("Components"));

        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);

        content.add(new H2("Reusable Components"));
        content.add(new Paragraph("This page showcases all the reusable components available in the application."));

        // Add sections for each component type
        content.add(createCardSection());
        content.add(createStatusBadgeSection());
        content.add(createAlertSection());
        content.add(createSummaryCardSection());
        content.add(createProgressBarSection());
        content.add(createFilterBarSection());
        content.add(createMetricDisplaySection());
        content.add(createNotificationBannerSection());
        content.add(createConfirmDialogSection());

        // Add new sections requested in the issue
        content.add(createFileUploadSection());
        content.add(createAdvancedFiltersSection());
        content.add(createDocumentPreviewSection());
        content.add(createDataTableSection());

        add(content);
    }

    private VerticalLayout createCardSection() {
        VerticalLayout section = createSection("Card");

        HorizontalLayout cardsLayout = new HorizontalLayout();
        cardsLayout.setWidthFull();
        cardsLayout.setSpacing(true);

        // Simple card
        Card simpleCard = new Card("Simple Card");
        simpleCard.add(new Paragraph("This is a simple card with just a title and some content."));
        simpleCard.getContent().setWidth("30%");

        // Card with components
        Card componentsCard = new Card("Card with Components");
        componentsCard.add(
            new Paragraph("This card contains various components:"),
            new Button("Click Me"),
            new TextField("Enter text")
        );
        componentsCard.getContent().setWidth("30%");

        // Card with styling
        Card styledCard = new Card("Styled Card");
        styledCard.getContent().addClassNames(
            LumoUtility.Background.CONTRAST_5,
            LumoUtility.Border.ALL,
            LumoUtility.BorderColor.PRIMARY
        );
        styledCard.add(new Paragraph("This card has custom styling applied."));
        styledCard.getContent().setWidth("30%");

        cardsLayout.add(simpleCard, componentsCard, styledCard);
        section.add(cardsLayout);

        return section;
    }

    private VerticalLayout createStatusBadgeSection() {
        VerticalLayout section = createSection("Status Badge");

        HorizontalLayout badgesLayout = new HorizontalLayout();
        badgesLayout.setSpacing(true);

        // Create badges with different status types
        badgesLayout.add(new StatusBadge("Active", StatusBadge.StatusType.SUCCESS));
        badgesLayout.add(new StatusBadge("Inactive", StatusBadge.StatusType.ERROR));
        badgesLayout.add(new StatusBadge("Pending", StatusBadge.StatusType.WARNING));
        badgesLayout.add(new StatusBadge("Neutral", StatusBadge.StatusType.NEUTRAL));

        // Create badges with automatic status detection
        badgesLayout.add(new StatusBadge("Completed"));
        badgesLayout.add(new StatusBadge("Failed"));
        badgesLayout.add(new StatusBadge("In Progress"));

        section.add(new Paragraph("Status badges with different status types:"), badgesLayout);

        return section;
    }

    private VerticalLayout createAlertSection() {
        VerticalLayout section = createSection("Alert");

        VerticalLayout alertsLayout = new VerticalLayout();
        alertsLayout.setSpacing(true);
        alertsLayout.setPadding(false);

        // Create alerts with different severities
        alertsLayout.add(new Alert("This is an information alert", Alert.Severity.INFO));
        alertsLayout.add(new Alert("This is a success alert", Alert.Severity.SUCCESS));
        alertsLayout.add(new Alert("This is a warning alert", Alert.Severity.WARNING));
        alertsLayout.add(new Alert("This is an error alert", Alert.Severity.ERROR));

        // Create alerts using static factory methods
        alertsLayout.add(Alert.info("Info alert created with factory method"));
        alertsLayout.add(Alert.success("Success alert created with factory method"));
        alertsLayout.add(Alert.warning("Warning alert created with factory method"));
        alertsLayout.add(Alert.error("Error alert created with factory method"));

        section.add(new Paragraph("Alerts with different severities:"), alertsLayout);

        return section;
    }

    private VerticalLayout createSummaryCardSection() {
        VerticalLayout section = createSection("Summary Card");

        HorizontalLayout cardsLayout = new HorizontalLayout();
        cardsLayout.setWidthFull();
        cardsLayout.setSpacing(true);

        // Create summary cards with different configurations
        SummaryCard card1 = new SummaryCard("Total Clients", "1,245", VaadinIcon.USERS);
        card1.setWidth("25%");

        SummaryCard card2 = new SummaryCard("Revenue", "$1.2M", "+8.5% MoM", VaadinIcon.MONEY);
        card2.setWidth("25%");

        SummaryCard card3 = new SummaryCard("Transactions", "12,543", "-2.3% WoW", VaadinIcon.EXCHANGE);
        card3.setWidth("25%");

        SummaryCard card4 = new SummaryCard("Active Accounts", "952", VaadinIcon.CHECK_CIRCLE);
        card4.setSubValue("85% of total");
        card4.setWidth("25%");

        cardsLayout.add(card1, card2, card3, card4);
        section.add(cardsLayout);

        return section;
    }

    private VerticalLayout createProgressBarSection() {
        VerticalLayout section = createSection("Progress Bar");

        VerticalLayout barsLayout = new VerticalLayout();
        barsLayout.setSpacing(true);
        barsLayout.setPadding(false);

        // Create progress bars with different configurations
        barsLayout.add(new ProgressBarComponent(25));
        barsLayout.add(new ProgressBarComponent(50, true));
        barsLayout.add(new ProgressBarComponent(75, "Project Completion", true));

        ProgressBarComponent customBar = new ProgressBarComponent(90, "Custom Width", true);
        customBar.setProgressBarWidth("300px");
        barsLayout.add(customBar);

        section.add(barsLayout);

        return section;
    }

    private VerticalLayout createFilterBarSection() {
        VerticalLayout section = createSection("Filter Bar");

        // Create a filter bar with various filter components
        FilterBar filterBar = new FilterBar(true);

        TextField searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());

        ComboBox<String> statusFilter = new ComboBox<>("Status");
        statusFilter.setItems("All", "Active", "Inactive", "Pending");
        statusFilter.setValue("All");

        DatePicker startDate = new DatePicker("Start Date");
        DatePicker endDate = new DatePicker("End Date");

        filterBar.addFilters(searchField, statusFilter, startDate, endDate);
        filterBar.onApply(fb -> {
            Notification.show("Filters applied", 3000, Notification.Position.BOTTOM_END)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        section.add(filterBar);

        return section;
    }

    private VerticalLayout createMetricDisplaySection() {
        VerticalLayout section = createSection("Metric Display");

        HorizontalLayout metricsLayout = new HorizontalLayout();
        metricsLayout.setWidthFull();
        metricsLayout.setSpacing(true);

        // Create metric displays with different configurations
        VerticalLayout metric1Layout = new VerticalLayout();
        metric1Layout.add(new H3("Basic Metric:"));
        metric1Layout.add(new MetricDisplay("1,245"));

        VerticalLayout metric2Layout = new VerticalLayout();
        metric2Layout.add(new H3("Metric with Growth (Positive):"));
        metric2Layout.add(new MetricDisplay("$2.5M", 8.2, "since last month"));

        VerticalLayout metric3Layout = new VerticalLayout();
        metric3Layout.add(new H3("Metric with Growth (Negative):"));
        metric3Layout.add(new MetricDisplay("952", -3.7, "since yesterday"));

        metricsLayout.add(metric1Layout, metric2Layout, metric3Layout);
        section.add(metricsLayout);

        return section;
    }

    private VerticalLayout createNotificationBannerSection() {
        VerticalLayout section = createSection("Notification Banner");

        VerticalLayout bannersLayout = new VerticalLayout();
        bannersLayout.setSpacing(true);
        bannersLayout.setPadding(false);

        // Create notification banners with different types
        bannersLayout.add(NotificationBanner.info("This is an information notification banner."));
        bannersLayout.add(NotificationBanner.success("This is a success notification banner."));
        bannersLayout.add(NotificationBanner.warning("This is a warning notification banner."));
        bannersLayout.add(NotificationBanner.error("This is an error notification banner."));

        // Create a notification banner with an action button
        NotificationBanner actionBanner = new NotificationBanner("This notification has an action button.", NotificationBanner.NotificationType.INFO, true);
        actionBanner.addAction("Take Action", () -> {
            Notification.show("Action taken", 3000, Notification.Position.BOTTOM_END);
        });
        bannersLayout.add(actionBanner);

        section.add(bannersLayout);

        return section;
    }

    private VerticalLayout createConfirmDialogSection() {
        VerticalLayout section = createSection("Confirm Dialog");

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.setSpacing(true);

        // Create buttons to open different types of confirm dialogs
        Button confirmButton = new Button("Open Confirm Dialog");
        confirmButton.addClickListener(e -> {
            ConfirmDialog dialog = new ConfirmDialog("Confirm Action", "Are you sure you want to proceed with this action?");
            dialog.onConfirm(() -> {
                Notification.show("Action confirmed", 3000, Notification.Position.BOTTOM_END);
            });
            dialog.open();
        });

        Button warningButton = new Button("Open Warning Dialog");
        warningButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        warningButton.addClickListener(e -> {
            ConfirmDialog dialog = ConfirmDialog.warning("Warning", "This action may have consequences. Do you want to proceed?");
            dialog.onConfirm(() -> {
                Notification.show("Warning acknowledged", 3000, Notification.Position.BOTTOM_END);
            });
            dialog.open();
        });

        Button dangerButton = new Button("Open Danger Dialog");
        dangerButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        dangerButton.addClickListener(e -> {
            ConfirmDialog dialog = ConfirmDialog.danger("Delete Item", "This action cannot be undone. Are you sure you want to delete this item?");
            dialog.onConfirm(() -> {
                Notification.show("Item deleted", 3000, Notification.Position.BOTTOM_END);
            });
            dialog.open();
        });

        buttonsLayout.add(confirmButton, warningButton, dangerButton);
        section.add(buttonsLayout);

        return section;
    }

    private VerticalLayout createSection(String title) {
        VerticalLayout section = new VerticalLayout();
        section.setPadding(true);
        section.setSpacing(true);
        section.addClassNames(
                LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_10,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.Margin.Vertical.MEDIUM
        );

        H2 sectionTitle = new H2(title);
        sectionTitle.addClassNames(LumoUtility.Margin.Top.NONE, LumoUtility.Margin.Bottom.MEDIUM);
        section.add(sectionTitle);

        return section;
    }

    private VerticalLayout createFileUploadSection() {
        VerticalLayout section = createSection("File Upload");

        // Basic single file upload
        Paragraph basicUploadDesc = new Paragraph("Basic single file upload:");
        section.add(basicUploadDesc);

        MemoryBuffer singleBuffer = new MemoryBuffer();
        Upload singleUpload = new Upload(singleBuffer);
        singleUpload.setAcceptedFileTypes("image/*", ".pdf", ".docx", ".xlsx");
        singleUpload.setMaxFiles(1);
        singleUpload.setMaxFileSize(5 * 1024 * 1024); // 5MB

        singleUpload.addSucceededListener(event -> {
            Notification.show(
                "File uploaded: " + event.getFileName() + " (" + event.getContentLength() + " bytes)",
                3000,
                Notification.Position.BOTTOM_END
            ).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        section.add(singleUpload);

        // Multi-file upload with drag and drop
        Paragraph multiUploadDesc = new Paragraph("Multi-file upload with drag and drop:");
        section.add(multiUploadDesc);

        MemoryBuffer multiBuffer = new MemoryBuffer();
        Upload multiUpload = new Upload(multiBuffer);
        multiUpload.setDropAllowed(true);
        multiUpload.setMaxFiles(10);
        multiUpload.setAcceptedFileTypes("image/*", ".pdf", ".docx", ".xlsx");

        // Add a drop label
        multiUpload.setDropLabel(new Span("Drop files here (max 10 files)"));

        // Add upload success handler
        multiUpload.addSucceededListener(event -> {
            Notification.show(
                "File uploaded: " + event.getFileName() + " (" + event.getContentLength() + " bytes)",
                3000,
                Notification.Position.BOTTOM_END
            ).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        section.add(multiUpload);

        // Upload with custom button and progress bar
        Paragraph customUploadDesc = new Paragraph("Upload with custom button and styling:");
        section.add(customUploadDesc);

        MemoryBuffer customBuffer = new MemoryBuffer();
        Upload customUpload = new Upload(customBuffer);

        Button uploadButton = new Button("Upload Document");
        uploadButton.setIcon(VaadinIcon.UPLOAD.create());
        uploadButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        customUpload.setUploadButton(uploadButton);
        customUpload.setDropLabel(new Span("or drop files here"));

        // Style the upload component
        customUpload.addClassNames(
            LumoUtility.Border.ALL,
            LumoUtility.BorderColor.PRIMARY,
            LumoUtility.BorderRadius.MEDIUM,
            LumoUtility.Padding.MEDIUM
        );

        section.add(customUpload);

        return section;
    }

    private VerticalLayout createAdvancedFiltersSection() {
        VerticalLayout section = createSection("Advanced Filters");

        // Create a complex filter bar with multiple filter types
        Paragraph filterDesc = new Paragraph("Advanced filter bar with multiple filter types:");
        section.add(filterDesc);

        FilterBar advancedFilterBar = new FilterBar(true);

        // Text search field
        TextField searchField = new TextField();
        searchField.setPlaceholder("Search by keyword...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidth("250px");

        // Date range filters
        DatePicker startDate = new DatePicker("From");
        DatePicker endDate = new DatePicker("To");

        // Status filter with select
        Select<String> statusSelect = new Select<>();
        statusSelect.setLabel("Status");
        statusSelect.setItems("All", "Active", "Pending", "Completed", "Cancelled");
        statusSelect.setValue("All");

        // Amount range filter
        NumberField minAmount = new NumberField("Min Amount");
        minAmount.setWidth("150px");
        minAmount.setPrefixComponent(new Span("$"));

        NumberField maxAmount = new NumberField("Max Amount");
        maxAmount.setWidth("150px");
        maxAmount.setPrefixComponent(new Span("$"));

        // Add all filters to the filter bar
        advancedFilterBar.addFilters(searchField, startDate, endDate, statusSelect, minAmount, maxAmount);

        // Add filter apply handler
        advancedFilterBar.onApply(filterBar -> {
            Notification.show(
                "Filters applied with " + statusSelect.getValue() + " status",
                3000,
                Notification.Position.BOTTOM_END
            ).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        section.add(advancedFilterBar);

        // Create a tabbed filter section
        Paragraph tabbedFilterDesc = new Paragraph("Tabbed filters with different filter sets:");
        section.add(tabbedFilterDesc);

        // Create tabs for different filter sets
        Tab basicTab = new Tab("Basic Filters");
        Tab advancedTab = new Tab("Advanced Filters");
        Tab customTab = new Tab("Custom Filters");

        Tabs filterTabs = new Tabs(basicTab, advancedTab, customTab);
        filterTabs.addClassNames(LumoUtility.Margin.Vertical.MEDIUM);

        // Create filter content for each tab
        Div filterContent = new Div();
        filterContent.setSizeFull();
        filterContent.addClassNames(
            LumoUtility.Border.ALL,
            LumoUtility.BorderColor.CONTRAST_10,
            LumoUtility.BorderRadius.MEDIUM,
            LumoUtility.Padding.MEDIUM
        );

        // Create filter components for the first tab (shown by default)
        HorizontalLayout basicFilters = new HorizontalLayout();
        basicFilters.setWidthFull();
        basicFilters.setAlignItems(FlexComponent.Alignment.BASELINE);

        TextField basicSearch = new TextField("Search");
        basicSearch.setPlaceholder("Enter keywords...");

        Select<String> basicCategory = new Select<>();
        basicCategory.setLabel("Category");
        basicCategory.setItems("All", "Category 1", "Category 2", "Category 3");
        basicCategory.setValue("All");

        Button applyBasicButton = new Button("Apply", VaadinIcon.FILTER.create());
        applyBasicButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        basicFilters.add(basicSearch, basicCategory, applyBasicButton);
        filterContent.add(basicFilters);

        // Add tabs and content to the section
        section.add(filterTabs, filterContent);

        // Add tab change listener (in a real app, this would show different filter sets)
        filterTabs.addSelectedChangeListener(event -> {
            Notification.show(
                "Switched to " + event.getSelectedTab().getLabel() + " tab",
                3000,
                Notification.Position.BOTTOM_END
            );
        });

        return section;
    }

    private VerticalLayout createDocumentPreviewSection() {
        VerticalLayout section = createSection("Document Previsualizers");

        // Create a document preview component
        Paragraph previewDesc = new Paragraph("Document preview components for different file types:");
        section.add(previewDesc);

        // Create tabs for different document types
        Tab pdfTab = new Tab("PDF Document");
        Tab imageTab = new Tab("Image");
        Tab textTab = new Tab("Text Document");

        Tabs documentTabs = new Tabs(pdfTab, imageTab, textTab);
        documentTabs.addClassNames(LumoUtility.Margin.Vertical.MEDIUM);

        // Create content div for document previews
        Div previewContent = new Div();
        previewContent.setHeight("400px");
        previewContent.setWidth("100%");
        previewContent.addClassNames(
            LumoUtility.Border.ALL,
            LumoUtility.BorderColor.CONTRAST_10,
            LumoUtility.BorderRadius.MEDIUM
        );

        // Create image preview (shown by default)
        Image documentImage = new Image("images/bank-logo.png", "Document Preview");
        documentImage.setMaxHeight("380px");
        documentImage.addClassNames(
            LumoUtility.Margin.Horizontal.AUTO,
            LumoUtility.Display.BLOCK
        );

        previewContent.add(documentImage);

        // Add document info panel
        HorizontalLayout documentInfo = new HorizontalLayout();
        documentInfo.setWidthFull();
        documentInfo.setAlignItems(FlexComponent.Alignment.CENTER);
        documentInfo.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        Span documentName = new Span("sample-document.jpg");
        documentName.addClassNames(LumoUtility.FontWeight.BOLD);

        HorizontalLayout actionButtons = new HorizontalLayout();

        Button downloadButton = new Button("Download", VaadinIcon.DOWNLOAD.create());
        Button printButton = new Button("Print", VaadinIcon.PRINT.create());
        Button shareButton = new Button("Share", VaadinIcon.SHARE.create());

        actionButtons.add(downloadButton, printButton, shareButton);
        documentInfo.add(documentName, actionButtons);

        // Add document thumbnails for navigation
        HorizontalLayout thumbnails = new HorizontalLayout();
        thumbnails.setWidthFull();
        thumbnails.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        thumbnails.setSpacing(true);

        for (int i = 1; i <= 3; i++) {
            Div thumbnail = new Div();
            thumbnail.setText("Page " + i);
            thumbnail.setHeight("60px");
            thumbnail.setWidth("45px");
            thumbnail.addClassNames(
                LumoUtility.Border.ALL,
                LumoUtility.BorderColor.CONTRAST_10,
                LumoUtility.BorderRadius.SMALL,
                LumoUtility.Display.FLEX,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.JustifyContent.CENTER,
                LumoUtility.Background.CONTRAST_5
            );

            int pageNum = i;
            thumbnail.addClickListener(e -> {
                Notification.show("Navigated to page " + pageNum, 1000, Notification.Position.BOTTOM_END);
            });

            thumbnails.add(thumbnail);
        }

        section.add(documentTabs, previewContent, documentInfo, thumbnails);

        // Add tab change listener to simulate changing document types
        documentTabs.addSelectedChangeListener(event -> {
            String tabName = event.getSelectedTab().getLabel();
            Notification.show(
                "Switched to " + tabName + " preview",
                3000,
                Notification.Position.BOTTOM_END
            );
        });

        return section;
    }

    private VerticalLayout createDataTableSection() {
        VerticalLayout section = createSection("Data Tables");

        // Create a basic data table
        Paragraph basicTableDesc = new Paragraph("Basic data table with sorting and filtering:");
        section.add(basicTableDesc);

        // Create a grid for transactions
        Grid<Transaction> grid = new Grid<>(Transaction.class, false);
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeight("300px");

        // Add columns
        grid.addColumn(Transaction::getId).setHeader("ID").setSortable(true).setAutoWidth(true);
        grid.addColumn(Transaction::getDate).setHeader("Date").setSortable(true).setAutoWidth(true);
        grid.addColumn(Transaction::getDescription).setHeader("Description").setSortable(true);
        grid.addColumn(Transaction::getAmount).setHeader("Amount").setSortable(true).setAutoWidth(true);
        grid.addColumn(Transaction::getStatus).setKey("status").setHeader("Status").setSortable(true).setAutoWidth(true);

        // Add a renderer for the status column with badges
        grid.getColumnByKey("status").setRenderer(new ComponentRenderer<>(transaction -> {
            StatusBadge badge = new StatusBadge(transaction.getStatus());
            return badge;
        }));

        // Add action column
        grid.addComponentColumn(transaction -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);

            Button viewButton = new Button(VaadinIcon.EYE.create());
            viewButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            viewButton.addClickListener(e -> {
                Notification.show("Viewing transaction " + transaction.getId(), 
                    3000, Notification.Position.BOTTOM_END);
            });

            Button editButton = new Button(VaadinIcon.EDIT.create());
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            editButton.addClickListener(e -> {
                Notification.show("Editing transaction " + transaction.getId(), 
                    3000, Notification.Position.BOTTOM_END);
            });

            actions.add(viewButton, editButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);

        // Generate sample data
        List<Transaction> transactions = generateSampleTransactions();
        grid.setItems(transactions);

        section.add(grid);

        // Create an advanced data table with filtering and pagination
        Paragraph advancedTableDesc = new Paragraph("Advanced data table with filtering controls:");
        section.add(advancedTableDesc);

        // Create filter controls
        HorizontalLayout filterControls = new HorizontalLayout();
        filterControls.setWidthFull();
        filterControls.setAlignItems(FlexComponent.Alignment.BASELINE);
        filterControls.addClassNames(
            LumoUtility.Padding.SMALL,
            LumoUtility.Background.CONTRAST_5,
            LumoUtility.BorderRadius.MEDIUM,
            LumoUtility.Margin.Bottom.SMALL
        );

        // Add text field filter for name
        TextField nameFilter = new TextField("Name");
        nameFilter.setPlaceholder("Filter by name");
        nameFilter.setClearButtonVisible(true);
        nameFilter.setWidth("250px");

        // Add a select filter for status
        Select<String> statusFilter = new Select<>();
        statusFilter.setLabel("Status");
        statusFilter.setItems("All", "Active", "Inactive", "Pending");
        statusFilter.setValue("All");
        statusFilter.setWidth("200px");

        Button applyFiltersButton = new Button("Apply Filters", VaadinIcon.FILTER.create());
        applyFiltersButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        filterControls.add(nameFilter, statusFilter, applyFiltersButton);
        section.add(filterControls);

        // Create a grid for clients
        Grid<Client> clientGrid = new Grid<>(Client.class, false);
        clientGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);
        clientGrid.setHeight("300px");

        // Add columns
        clientGrid.addColumn(Client::getId).setHeader("ID").setSortable(true).setAutoWidth(true);
        clientGrid.addColumn(Client::getName).setHeader("Name").setSortable(true).setAutoWidth(true);
        clientGrid.addColumn(Client::getEmail).setHeader("Email").setSortable(true).setAutoWidth(true);
        clientGrid.addColumn(Client::getPhone).setHeader("Phone").setSortable(true).setAutoWidth(true);
        clientGrid.addColumn(Client::getStatus).setKey("status").setHeader("Status").setSortable(true).setAutoWidth(true);

        // Add a renderer for the status column with badges
        clientGrid.getColumnByKey("status").setRenderer(new ComponentRenderer<>(client -> {
            StatusBadge badge = new StatusBadge(client.getStatus());
            return badge;
        }));

        // Generate sample client data
        List<Client> clients = generateSampleClients();
        clientGrid.setItems(clients);

        // Add filter functionality
        applyFiltersButton.addClickListener(e -> {
            String nameFilterValue = nameFilter.getValue().toLowerCase();
            String statusFilterValue = statusFilter.getValue();

            List<Client> filteredClients = clients.stream()
                .filter(client -> client.getName().toLowerCase().contains(nameFilterValue))
                .filter(client -> "All".equals(statusFilterValue) || client.getStatus().equals(statusFilterValue))
                .toList();

            clientGrid.setItems(filteredClients);

            Notification.show(
                "Filters applied: " + filteredClients.size() + " results",
                3000,
                Notification.Position.BOTTOM_END
            ).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        });

        section.add(clientGrid);

        return section;
    }

    // Helper method to generate sample transactions for the data table
    private List<Transaction> generateSampleTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        Random random = new Random();

        String[] descriptions = {
            "Salary payment", "Grocery shopping", "Utility bill", "Restaurant", 
            "Online purchase", "Subscription fee", "Transfer to savings"
        };

        String[] statuses = {"Completed", "Pending", "Failed", "Processing"};

        for (int i = 1; i <= 20; i++) {
            String id = "TRX-" + String.format("%04d", i);
            LocalDate date = LocalDate.now().minusDays(random.nextInt(30));
            String description = descriptions[random.nextInt(descriptions.length)];

            double amount = 10 + random.nextInt(990) + random.nextDouble();
            String formattedAmount = String.format("$%.2f", amount);

            String status = statuses[random.nextInt(statuses.length)];

            transactions.add(new Transaction(id, date.toString(), description, formattedAmount, status));
        }

        return transactions;
    }

    // Helper method to generate sample clients for the data table
    private List<Client> generateSampleClients() {
        List<Client> clients = new ArrayList<>();
        Random random = new Random();

        String[] firstNames = {"John", "Jane", "Michael", "Emily", "David", "Sarah", "Robert", "Lisa"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Wilson"};
        String[] statuses = {"Active", "Inactive", "Pending"};

        for (int i = 1; i <= 20; i++) {
            String id = "CLI-" + String.format("%04d", i);

            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String name = firstName + " " + lastName;

            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";

            String phone = "+1 " + (random.nextInt(900) + 100) + "-" + (random.nextInt(900) + 100) + 
                "-" + (random.nextInt(9000) + 1000);

            String status = statuses[random.nextInt(statuses.length)];

            clients.add(new Client(id, name, email, phone, status));
        }

        return clients;
    }

    // Transaction class for the data table
    private static class Transaction {
        private final String id;
        private final String date;
        private final String description;
        private final String amount;
        private final String status;

        public Transaction(String id, String date, String description, String amount, String status) {
            this.id = id;
            this.date = date;
            this.description = description;
            this.amount = amount;
            this.status = status;
        }

        public String getId() { return id; }
        public String getDate() { return date; }
        public String getDescription() { return description; }
        public String getAmount() { return amount; }
        public String getStatus() { return status; }
    }

    // Client class for the data table
    private static class Client {
        private final String id;
        private final String name;
        private final String email;
        private final String phone;
        private final String status;

        public Client(String id, String name, String email, String phone, String status) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.status = status;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getStatus() { return status; }
    }
}
