package com.vaadin.starter.business.ui.views.cards;

import com.catalis.core.banking.cards.sdk.model.CardDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;
import com.vaadin.starter.business.backend.mapper.cards.CardsMapper;
import com.vaadin.starter.business.backend.sdks.services.CardsService;
import com.vaadin.starter.business.backend.sdks.services.rest.cards.CardFilterRequest;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@PageTitle("Cards")
@Route(value = "cards", layout = MainLayout.class)
public class Cards extends ViewFrame {

    public static final int MOBILE_BREAKPOINT = 480;
    private Grid<CardDTO> grid;
    private Registration resizeListener;
    private ListDataProvider<CardDTO> dataProvider;
    private UI ui;

    // Filter form fields
    private TextField cardNumberFilter;
    private TextField cardTypeFilter;
    private TextField currencyCodeFilter;
    private NumberField minBalanceFilter;
    private NumberField maxBalanceFilter;
    private TextField statusFilter;
    private TextField cardHolderNameFilter;
    private TextField cardHolderIdFilter;
    private DatePicker issuanceDateFilter;
    private DatePicker issuanceDateFromFilter;
    private DatePicker issuanceDateToFilter;
    private DatePicker expirationDateFilter;
    private DatePicker expirationDateFromFilter;
    private DatePicker expirationDateToFilter;

    private final CardsService cardsService;
    private final CardsMapper cardsMapper;

    @Autowired
    public Cards(CardsService cardsService, CardsMapper cardsMapper) {
        this.cardsService = cardsService;
        this.cardsMapper = cardsMapper;
        setViewContent(createContent());
    }

    private Component createContent() {
        FlexBoxLayout content = new FlexBoxLayout(createFilterForm(), createGrid());
        content.setBoxSizing(BoxSizing.BORDER_BOX);
        content.setHeightFull();
        content.setPadding(Horizontal.RESPONSIVE_X, Top.RESPONSIVE_X);
        content.setFlexDirection(FlexDirection.COLUMN);
        return content;
    }

    private Component createFilterForm() {
        // Initialize filter fields
        cardNumberFilter = new TextField();
        cardNumberFilter.setValueChangeMode(ValueChangeMode.EAGER);
        cardNumberFilter.setClearButtonVisible(true);

        cardTypeFilter = new TextField();
        cardTypeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        cardTypeFilter.setClearButtonVisible(true);

        currencyCodeFilter = new TextField();
        currencyCodeFilter.setValueChangeMode(ValueChangeMode.EAGER);
        currencyCodeFilter.setClearButtonVisible(true);

        minBalanceFilter = new NumberField();
        minBalanceFilter.setClearButtonVisible(true);

        maxBalanceFilter = new NumberField();
        maxBalanceFilter.setClearButtonVisible(true);

        statusFilter = new TextField();
        statusFilter.setValueChangeMode(ValueChangeMode.EAGER);
        statusFilter.setClearButtonVisible(true);

        cardHolderNameFilter = new TextField();
        cardHolderNameFilter.setValueChangeMode(ValueChangeMode.EAGER);
        cardHolderNameFilter.setClearButtonVisible(true);

        cardHolderIdFilter = new TextField();
        cardHolderIdFilter.setValueChangeMode(ValueChangeMode.EAGER);
        cardHolderIdFilter.setClearButtonVisible(true);

        issuanceDateFilter = new DatePicker();
        issuanceDateFilter.setClearButtonVisible(true);

        issuanceDateFromFilter = new DatePicker();
        issuanceDateFromFilter.setClearButtonVisible(true);

        issuanceDateToFilter = new DatePicker();
        issuanceDateToFilter.setClearButtonVisible(true);

        expirationDateFilter = new DatePicker();
        expirationDateFilter.setClearButtonVisible(true);

        expirationDateFromFilter = new DatePicker();
        expirationDateFromFilter.setClearButtonVisible(true);

        expirationDateToFilter = new DatePicker();
        expirationDateToFilter.setClearButtonVisible(true);

        // Create buttons
        Button searchButton = UIUtils.createPrimaryButton("Search");
        searchButton.addClickListener(e -> applyFilters());

        Button clearButton = UIUtils.createTertiaryButton("Clear");
        clearButton.addClickListener(e -> clearFilters());

        Button createCardButton = UIUtils.createSuccessButton("Create Card");
        createCardButton.addClickListener(
                e -> UIUtils.showNotification("Not implemented yet."));
        //createCardButton.addClickListener(e -> openCreateCardDialog());

        // Create a wrapper for search and clear buttons (right side)
        HorizontalLayout rightButtons = new HorizontalLayout(searchButton, clearButton);
        rightButtons.setSpacing(true);

        // Create button layout with Create Card on left and search/clear on right
        HorizontalLayout buttonLayout = new HorizontalLayout(createCardButton, rightButtons);
        buttonLayout.setWidthFull();
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        // Create form layout
        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(cardNumberFilter, "Card Number");
        formLayout.addFormItem(cardTypeFilter, "Card Type");
        formLayout.addFormItem(currencyCodeFilter, "Currency");
        formLayout.addFormItem(statusFilter, "Status");
        formLayout.addFormItem(cardHolderNameFilter, "Card Holder Name");
        formLayout.addFormItem(cardHolderIdFilter, "Card Holder ID");
        formLayout.addFormItem(minBalanceFilter, "Min Balance");
        formLayout.addFormItem(maxBalanceFilter, "Max Balance");
        formLayout.addFormItem(issuanceDateFromFilter, "Issued From");
        formLayout.addFormItem(issuanceDateToFilter, "Issued To");
        formLayout.addFormItem(expirationDateFromFilter, "Expires From");
        formLayout.addFormItem(expirationDateToFilter, "Expires To");

        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("900px", 4, FormLayout.ResponsiveStep.LabelsPosition.TOP)
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

    private Grid<CardDTO> createGrid() {
        grid = new Grid<>();
        grid.addSelectionListener(event -> event.getFirstSelectedItem().ifPresent(this::viewDetails));
        grid.addThemeName("mobile");

        grid.setId("cards");
        grid.setSizeFull();

        // Configure grid columns
        grid.addColumn(CardDTO::getCardId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("Card ID")
                .setSortable(true);
        grid.addColumn(CardDTO::getCardNumber)
                .setAutoWidth(true)
                .setHeader("Card Number")
                .setSortable(true);
        grid.addColumn(CardDTO::getMaskedCardNumber)
                .setAutoWidth(true)
                .setHeader("Masked Card Number")
                .setSortable(true);
        grid.addColumn(card -> card.getCardTypeId() != null ? card.getCardTypeId().toString() : "")
                .setAutoWidth(true)
                .setHeader("Card Type")
                .setSortable(true);
        grid.addColumn(CardDTO::getCurrencyCode)
                .setAutoWidth(true)
                .setHeader("Currency")
                .setSortable(true);
        grid.addColumn(card -> card.getCardStatus() != null ? card.getCardStatus().toString() : "")
                .setAutoWidth(true)
                .setHeader("Status")
                .setSortable(true);
        grid.addColumn(CardDTO::getCardHolderName)
                .setAutoWidth(true)
                .setHeader("Card Holder")
                .setSortable(true);
        grid.addColumn(card -> card.getAvailableBalance() != null ? card.getAvailableBalance().toString() : "N/A")
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Available Balance")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(card -> {
                    LocalDateTime date = card.getExpirationDate();
                    return date != null ? date.toLocalDate().toString() : "";
                })
                .setAutoWidth(true)
                .setHeader("Expiration Date")
                .setSortable(true);

        // Initialize with empty data provider
        dataProvider = new ListDataProvider<>(List.of());
        grid.setDataProvider(dataProvider);

        return grid;
    }

    private void loadCardsData() {
        CardFilterRequest filterRequest = new CardFilterRequest();
        filterRequest.setPaginationPageNumber(0);
        filterRequest.setPaginationPageSize(10);
        filterRequest.setPaginationSortBy("card_id");
        filterRequest.setPaginationSortDirection("DESC");

        System.out.println("[DEBUG_LOG] Starting to load cards data");

        cardsService.filterCards(filterRequest)
                .subscribe(response -> {
                    System.out.println("[DEBUG_LOG] Response status: " + response.getStatusCode());
                    if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                        List<CardDTO> sdkCards = response.getBody().getContent();
                        System.out.println("[DEBUG_LOG] Received cards: " + (sdkCards != null ? sdkCards.size() : "null"));

                        if (sdkCards != null && !sdkCards.isEmpty()) {
                            System.out.println("[DEBUG_LOG] First card ID: " + sdkCards.get(0).getCardId());
                        }

                        if (sdkCards != null) {
                            // Use UI.access() to safely update the UI from the async callback
                            ui.access(() -> {
                                try {
                                    dataProvider = new ListDataProvider<>(sdkCards);
                                    grid.setDataProvider(dataProvider);
                                    System.out.println("[DEBUG_LOG] Grid data provider updated successfully with " + sdkCards.size() + " items");
                                    ui.push(); // Force UI update to the client
                                } catch (Exception e) {
                                    System.out.println("[DEBUG_LOG] Error updating grid: " + e.getMessage());
                                }
                            });
                        }
                    } else {
                        System.out.println("[DEBUG_LOG] Response unsuccessful or body is null");
                    }
                }, error -> {
                    System.out.println("[DEBUG_LOG] Error in filteredCards: " + error.getMessage());

                    // Handle error in UI thread
                    ui.access(() -> {
                        // You could show a notification here
                        System.out.println("[DEBUG_LOG] Error handled in UI thread");
                    });
                });
    }

    private void viewDetails(CardDTO card) {
        UI.getCurrent().navigate(CardDetails.class, card.getCardId());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getUI().ifPresent(currentUI -> {
            this.ui = currentUI;
            Page page = currentUI.getPage();
            resizeListener = page.addBrowserWindowResizeListener(event -> updateVisibleColumns(event.getWidth()));
            page.retrieveExtendedClientDetails(details -> updateVisibleColumns(details.getBodyClientWidth()));

            // Load data after UI is completely initialized
            System.out.println("[DEBUG_LOG] UI attached, loading cards data");
            loadCardsData();
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
        List<Grid.Column<CardDTO>> columns = grid.getColumns();

        // "Desktop" columns
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setVisible(!mobile);
        }
    }

    private void applyFilters() {
        if (dataProvider == null) {
            System.out.println("[DEBUG_LOG] DataProvider is null, cannot apply filters");
            return;
        }

        dataProvider.clearFilters();

        // Apply card number filter
        if (cardNumberFilter.getValue() != null && !cardNumberFilter.getValue().isEmpty()) {
            String cardNumberValue = cardNumberFilter.getValue().toLowerCase();
            dataProvider.addFilter(card ->
                    card.getCardNumber() != null &&
                            card.getCardNumber().toLowerCase().contains(cardNumberValue));
        }

        // Apply card type filter
        if (cardTypeFilter.getValue() != null && !cardTypeFilter.getValue().isEmpty()) {
            String cardTypeValue = cardTypeFilter.getValue().toLowerCase();
            dataProvider.addFilter(card ->
                    card.getCardTypeId() != null &&
                            card.getCardTypeId().toString().toLowerCase().contains(cardTypeValue));
        }

        // Apply currency code filter
        if (currencyCodeFilter.getValue() != null && !currencyCodeFilter.getValue().isEmpty()) {
            String currencyValue = currencyCodeFilter.getValue().toLowerCase();
            dataProvider.addFilter(card ->
                    card.getCurrencyCode() != null &&
                            card.getCurrencyCode().toLowerCase().contains(currencyValue));
        }

        // Apply min balance filter
        if (minBalanceFilter.getValue() != null) {
            Double minValue = minBalanceFilter.getValue();
            dataProvider.addFilter(card ->
                    card.getAvailableBalance() != null &&
                            card.getAvailableBalance().doubleValue() >= minValue);
        }

        // Apply max balance filter
        if (maxBalanceFilter.getValue() != null) {
            Double maxValue = maxBalanceFilter.getValue();
            dataProvider.addFilter(card ->
                    card.getAvailableBalance() != null &&
                            card.getAvailableBalance().doubleValue() <= maxValue);
        }

        // Apply status filter
        if (statusFilter.getValue() != null && !statusFilter.getValue().isEmpty()) {
            String statusValue = statusFilter.getValue().toLowerCase();
            dataProvider.addFilter(card ->
                    card.getCardStatus() != null &&
                            card.getCardStatus().toString().toLowerCase().contains(statusValue));
        }

        // Apply card holder name filter
        if (cardHolderNameFilter.getValue() != null && !cardHolderNameFilter.getValue().isEmpty()) {
            String holderNameValue = cardHolderNameFilter.getValue().toLowerCase();
            dataProvider.addFilter(card ->
                    card.getCardHolderName() != null &&
                            card.getCardHolderName().toLowerCase().contains(holderNameValue));
        }

        // Apply card holder ID filter
        if (cardHolderIdFilter.getValue() != null && !cardHolderIdFilter.getValue().isEmpty()) {
            String holderIdValue = cardHolderIdFilter.getValue().toLowerCase();
            dataProvider.addFilter(card ->
                    card.getCardHolderId() != null &&
                            card.getCardHolderId().toLowerCase().contains(holderIdValue));
        }

        // Apply issuance date from filter
        if (issuanceDateFromFilter.getValue() != null) {
            LocalDate fromDate = issuanceDateFromFilter.getValue();
            dataProvider.addFilter(card ->
                    card.getIssuanceDate() != null &&
                            !card.getIssuanceDate().toLocalDate().isBefore(fromDate));
        }

        // Apply issuance date to filter
        if (issuanceDateToFilter.getValue() != null) {
            LocalDate toDate = issuanceDateToFilter.getValue();
            dataProvider.addFilter(card ->
                    card.getIssuanceDate() != null &&
                            !card.getIssuanceDate().toLocalDate().isAfter(toDate));
        }

        // Apply expiration date from filter
        if (expirationDateFromFilter.getValue() != null) {
            LocalDate fromDate = expirationDateFromFilter.getValue();
            dataProvider.addFilter(card ->
                    card.getExpirationDate() != null &&
                            !card.getExpirationDate().toLocalDate().isBefore(fromDate));
        }

        // Apply expiration date to filter
        if (expirationDateToFilter.getValue() != null) {
            LocalDate toDate = expirationDateToFilter.getValue();
            dataProvider.addFilter(card ->
                    card.getExpirationDate() != null &&
                            !card.getExpirationDate().toLocalDate().isAfter(toDate));
        }

        System.out.println("[DEBUG_LOG] Filters applied successfully");
    }

    private void clearFilters() {
        // Clear all filter fields
        cardNumberFilter.clear();
        cardTypeFilter.clear();
        currencyCodeFilter.clear();
        minBalanceFilter.clear();
        maxBalanceFilter.clear();
        statusFilter.clear();
        cardHolderNameFilter.clear();
        cardHolderIdFilter.clear();
        issuanceDateFilter.clear();
        issuanceDateFromFilter.clear();
        issuanceDateToFilter.clear();
        expirationDateFilter.clear();
        expirationDateFromFilter.clear();
        expirationDateToFilter.clear();

        // Clear all filters from data provider
        if (dataProvider != null) {
            dataProvider.clearFilters();
            System.out.println("[DEBUG_LOG] All filters cleared");
        }
    }

    private void openCreateCardDialog() {
        // This is a placeholder for the actual implementation
        // When CreateCard class is available, this should be replaced with:
        // CreateCard createCardDialog = new CreateCard(cardsService, this::loadCardsData);
        // createCardDialog.open();
        System.out.println("[DEBUG_LOG] Create Card dialog would open here");
    }
}
