package com.vaadin.starter.business.ui.views.cards;

import com.catalis.core.banking.cards.sdk.model.CardDTO;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.ListSeries;
import com.vaadin.flow.component.charts.model.XAxis;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout.FlexDirection;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.backend.sdks.services.CardsService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.ListItem;
import com.vaadin.starter.business.ui.components.navigation.bar.AppBar;
import com.vaadin.starter.business.ui.layout.size.Bottom;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.layout.size.Vertical;
import com.vaadin.starter.business.ui.util.BoxShadowBorders;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.TextColor;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BorderRadius;
import com.vaadin.starter.business.ui.views.ViewFrame;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static com.vaadin.starter.business.dummy.DummyData.getImageSource;

@PageTitle("Card Details")
@Route(value = "card-details", layout = MainLayout.class)
public class CardDetails extends ViewFrame implements HasUrlParameter<Long> {

	public int RECENT_TRANSACTIONS = 4;

	private ListItem availability;
	private ListItem cardNumber;
	private ListItem updated;
	private ListItem created;

	private CardDTO card;
	private final CardsService cardsService;
	private boolean dataLoaded = false;
	private Long currentCardId;

	@Autowired
	public CardDetails(CardsService cardsService) {
		this.cardsService = cardsService;
		System.out.println("[DEBUG_LOG] CardDetails constructor called");
	}

	@Override
	public void setParameter(BeforeEvent beforeEvent, Long id) {
		System.out.println("[DEBUG_LOG] setParameter called with ID: " + id);

		if (id == null) {
			System.out.println("[DEBUG_LOG] ID is null, showing error");
			setViewContent(createErrorView("Invalid card ID"));
			return;
		}

		this.currentCardId = id;
		loadCardData(id);
	}

	private void loadCardData(Long id) {
		System.out.println("[DEBUG_LOG] Starting to load card data for ID: " + id);

		// Show loading indicator
		setViewContent(createLoadingView());

		try {
			// Add timeout and better error handling
			cardsService.getCard(id)
					.doOnSubscribe(subscription -> {
						System.out.println("[DEBUG_LOG] API call started for card ID: " + id);
					})
					.doOnNext(response -> {
						System.out.println("[DEBUG_LOG] Received response with status: " + response.getStatusCode());
						System.out.println("[DEBUG_LOG] Response body is null: " + (response.getBody() == null));
					})
					.subscribe(
							response -> {
								System.out.println("[DEBUG_LOG] In subscribe success callback");
								handleSuccessResponse(response);
							},
							error -> {
								System.out.println("[DEBUG_LOG] In subscribe error callback: " + error.getClass().getSimpleName());
								System.out.println("[DEBUG_LOG] Error message: " + error.getMessage());
								handleErrorResponse(error);
							}
					);
		} catch (Exception e) {
			System.out.println("[DEBUG_LOG] Exception in loadCardData: " + e.getMessage());
			handleErrorResponse(e);
		}
	}

	private void handleSuccessResponse(org.springframework.http.ResponseEntity<CardDTO> response) {
		System.out.println("[DEBUG_LOG] Handling success response");

		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			System.out.println("[DEBUG_LOG] Response is successful with body");
			card = response.getBody();
			dataLoaded = true;

			// Use UI.access to ensure thread safety
			getUI().ifPresent(ui -> ui.access(() -> {
				System.out.println("[DEBUG_LOG] Updating UI with card data");
				setViewContent(createContent());
				updateUIWithCardData();
			}));
		} else {
			System.out.println("[DEBUG_LOG] Response not successful or body is null");
			String errorMsg = "Error loading card data: " + response.getStatusCode();
			getUI().ifPresent(ui -> ui.access(() -> {
				setViewContent(createErrorView(errorMsg));
			}));
		}
	}

	private void handleErrorResponse(Throwable error) {
		System.out.println("[DEBUG_LOG] Handling error response");

		String errorMessage;
		if (error instanceof java.util.concurrent.TimeoutException) {
			errorMessage = "Request timed out. The card service is not responding.";
			System.out.println("[DEBUG_LOG] Timeout exception occurred");
		} else if (error instanceof java.net.ConnectException) {
			errorMessage = "Cannot connect to card service. Please check your connection.";
			System.out.println("[DEBUG_LOG] Connection exception occurred");
		} else {
			errorMessage = "Error loading card data: " + error.getMessage();
			System.out.println("[DEBUG_LOG] Generic error occurred: " + error.getMessage());
		}

		getUI().ifPresent(ui -> ui.access(() -> {
			setViewContent(createErrorView(errorMessage));
		}));
	}

	private Component createLoadingView() {
		System.out.println("[DEBUG_LOG] Creating loading view");

		FlexBoxLayout loadingLayout = new FlexBoxLayout();
		loadingLayout.setFlexDirection(FlexDirection.COLUMN);
		loadingLayout.setAlignItems(FlexComponent.Alignment.CENTER);
		loadingLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		loadingLayout.setHeightFull();
		loadingLayout.setWidthFull();

		H3 loadingText = new H3("Loading card details...");
		ProgressBar progressBar = new ProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setWidth("50%");

		// Add debug info to loading view
		Span debugInfo = new Span("Card ID: " + currentCardId);
		debugInfo.getStyle().set("margin-top", "1rem");
		debugInfo.getStyle().set("font-size", "0.8rem");
		debugInfo.getStyle().set("color", "var(--lumo-secondary-text-color)");

		loadingLayout.add(loadingText, progressBar, debugInfo);
		return loadingLayout;
	}

	private Component createErrorView(String errorMessage) {
		System.out.println("[DEBUG_LOG] Creating error view with message: " + errorMessage);

		FlexBoxLayout errorLayout = new FlexBoxLayout();
		errorLayout.setFlexDirection(FlexDirection.COLUMN);
		errorLayout.setAlignItems(FlexComponent.Alignment.CENTER);
		errorLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		errorLayout.setHeightFull();
		errorLayout.setWidthFull();

		H3 errorTitle = new H3("Error");
		Span errorText = new Span(errorMessage);
		UIUtils.setTextColor(TextColor.ERROR, errorText);

		Button retryButton = UIUtils.createPrimaryButton("Retry");
		retryButton.addClickListener(e -> {
			System.out.println("[DEBUG_LOG] Retry button clicked");
			if (currentCardId != null) {
				loadCardData(currentCardId);
			} else {
				System.out.println("[DEBUG_LOG] Cannot retry: currentCardId is null");
			}
		});

		Button backButton = UIUtils.createTertiaryButton("Back to Cards");
		backButton.addClickListener(e -> {
			System.out.println("[DEBUG_LOG] Back button clicked");
			getUI().ifPresent(ui -> ui.navigate(Cards.class));
		});

		// Add debug information
		Div debugInfo = new Div();
		debugInfo.add(new Span("Card ID: " + currentCardId));
		debugInfo.add(new Span("Data loaded: " + dataLoaded));
		debugInfo.getStyle().set("margin-top", "1rem");
		debugInfo.getStyle().set("font-size", "0.8rem");
		debugInfo.getStyle().set("color", "var(--lumo-secondary-text-color)");
		debugInfo.getStyle().set("text-align", "center");

		FlexBoxLayout buttonLayout = new FlexBoxLayout(retryButton, backButton);
		buttonLayout.setFlexDirection(FlexDirection.ROW);
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
		buttonLayout.getStyle().set("gap", "1rem");
		buttonLayout.getStyle().set("margin-top", "1rem");

		errorLayout.add(errorTitle, errorText, buttonLayout, debugInfo);
		return errorLayout;
	}

	private Component createContent() {
		System.out.println("[DEBUG_LOG] Creating content view");

		if (card == null) {
			System.out.println("[DEBUG_LOG] Card is null when creating content!");
			return createErrorView("Card data is not available");
		}

		FlexBoxLayout content = new FlexBoxLayout(
				createLogoSection(),
				createRecentTransactionsHeader(),
				createRecentTransactionsList(),
				createMonthlyOverviewHeader(),
				createMonthlyOverviewChart()
		);
		content.setFlexDirection(FlexDirection.COLUMN);
		content.setMargin(Horizontal.AUTO, Vertical.RESPONSIVE_L);
		content.setMaxWidth("840px");
		return content;
	}

	private void updateUIWithCardData() {
		System.out.println("[DEBUG_LOG] Updating UI with card data");

		if (card == null) {
			System.out.println("[DEBUG_LOG] Cannot update UI: card is null");
			return;
		}

		try {
			initAppBar();
			getUI().ifPresent(ui -> ui.getPage().setTitle(card.getCardHolderName() != null ?
					card.getCardHolderName() : "Card Details"));

			// Update other UI components
			if (availability != null) {
				availability.setPrimaryText(UIUtils.formatAmount(card.getAvailableBalance() != null ?
						card.getAvailableBalance().doubleValue() : 0.0));
			}
			if (cardNumber != null) {
				cardNumber.setPrimaryText(card.getCardNumber());
				cardNumber.setSecondaryText(card.getCardTypeId() != null ?
						card.getCardTypeId().toString() : "Unknown");
			}
			if (updated != null) {
				updated.setPrimaryText(card.getExpirationDate() != null ? 
					UIUtils.formatDate(card.getExpirationDate().toLocalDate()) : "");
			}
			if (created != null) {
				created.setPrimaryText(card.getIssuanceDate() != null ? 
					UIUtils.formatDate(card.getIssuanceDate().toLocalDate()) : "");
			}

			System.out.println("[DEBUG_LOG] UI updated successfully");
		} catch (Exception e) {
			System.out.println("[DEBUG_LOG] Error updating UI: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// ... [resto de métodos sin cambios - createLogoSection, createRecentTransactionsHeader, etc.] ...

	private FlexBoxLayout createLogoSection() {
		// Create a smaller logo image
		Image image = new Image(getImageSource(), "");
		UIUtils.setBorderRadius(BorderRadius._50, image);
		// Make logo smaller as requested
		image.setHeight("70px");
		image.setWidth("70px");
		image.getStyle().set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)");

		// Create header with card holder name as owner
		Span ownerName = new Span(card.getCardHolderName() != null ? card.getCardHolderName() : "Card");
		ownerName.addClassName(LumoStyles.Heading.H2);

		// Create edit button with the same style as Create Card button
		Button editButton = UIUtils.createSuccessButton("Edit Card");
		editButton.addClickListener(e -> openEditCardDialog());

		// Create delete button with error styling
		Button deleteButton = UIUtils.createErrorButton("Delete Card");
		deleteButton.addClickListener(e -> openDeleteConfirmationDialog());

		// Create a container for owner name and buttons
		FlexBoxLayout nameAndEdit = new FlexBoxLayout(ownerName, editButton, deleteButton);
		nameAndEdit.setFlexDirection(FlexDirection.ROW);
		nameAndEdit.setAlignItems(FlexComponent.Alignment.CENTER);
		nameAndEdit.getStyle().set("gap", "8px");

		// Create a container for logo and card name with edit button
		FlexBoxLayout logoAndName = new FlexBoxLayout(image, nameAndEdit);
		logoAndName.setFlexDirection(FlexDirection.ROW);
		logoAndName.setAlignItems(FlexComponent.Alignment.CENTER);
		logoAndName.getStyle().set("gap", "16px");

		// Create a prominent balance display
		availability = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), "", "Balance");
		availability.getPrimary().addClassName(LumoStyles.Heading.H1);
		availability.getPrimary().getStyle().set("font-weight", "bold");
		availability.getPrimary().getStyle().set("color", "var(--lumo-primary-color)");
		availability.setId("availability");
		availability.setReverse(true);
		availability.setDividerVisible(false);

		// Create a container for the balance with a background to make it stand out
		Div balanceContainer = new Div(availability);
		balanceContainer.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		balanceContainer.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		balanceContainer.getStyle().set("padding", "16px");
		balanceContainer.getStyle().set("margin-top", "16px");
		balanceContainer.getStyle().set("margin-bottom", "16px");
		balanceContainer.setWidth("100%");

		// Group 1: Basic Card Information
		Div basicInfoGroup = new Div();
		basicInfoGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		basicInfoGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		basicInfoGroup.getStyle().set("padding", "16px");
		basicInfoGroup.setWidth("100%");

		Span basicInfoTitle = new Span("Basic Card Information");
		basicInfoTitle.getStyle().set("font-weight", "bold");
		basicInfoTitle.getStyle().set("margin-bottom", "8px");
		basicInfoTitle.getStyle().set("display", "block");

		cardNumber = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CREDIT_CARD), 
				card.getCardNumber() != null ? card.getCardNumber() : "N/A", "Card Number");
		cardNumber.setReverse(true);

		ListItem cardId = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.KEY), 
				card.getCardId() != null ? String.valueOf(card.getCardId()) : "N/A", "Card ID");
		cardId.setReverse(true);

		ListItem maskedCardNumber = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.LOCK), 
				card.getMaskedCardNumber() != null ? card.getMaskedCardNumber() : "N/A", "Masked Card Number");
		maskedCardNumber.setReverse(true);

		ListItem cardSequenceNumber = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.HASH), 
				card.getCardSequenceNumber() != null ? card.getCardSequenceNumber() : "N/A", "Card Sequence Number");
		cardSequenceNumber.setReverse(true);

		ListItem cardHolderName = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.USER), 
				card.getCardHolderName() != null ? card.getCardHolderName() : "N/A", "Card Holder Name");
		cardHolderName.setReverse(true);

		ListItem cardStatus = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CHECK), 
				card.getCardStatus() != null ? card.getCardStatus().toString() : "N/A", "Card Status");
		cardStatus.setReverse(true);

		FlexBoxLayout basicInfoContent = new FlexBoxLayout(cardId, cardNumber, maskedCardNumber, 
				cardSequenceNumber, cardHolderName, cardStatus);
		basicInfoContent.setFlexDirection(FlexDirection.COLUMN);
		basicInfoContent.getStyle().set("gap", "8px");

		basicInfoGroup.add(basicInfoTitle, basicInfoContent);

		// Group 2: Card Dates
		Div datesGroup = new Div();
		datesGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		datesGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		datesGroup.getStyle().set("padding", "16px");
		datesGroup.setWidth("100%");

		Span datesGroupTitle = new Span("Card Dates");
		datesGroupTitle.getStyle().set("font-weight", "bold");
		datesGroupTitle.getStyle().set("margin-bottom", "8px");
		datesGroupTitle.getStyle().set("display", "block");

		created = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getDateCreated() != null ? card.getDateCreated().toString() : "N/A", "Date Created");
		created.setReverse(true);

		updated = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getDateUpdated() != null ? card.getDateUpdated().toString() : "N/A", "Date Updated");
		updated.setReverse(true);

		ListItem activationDate = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getActivationDate() != null ? card.getActivationDate().toString() : "N/A", "Activation Date");
		activationDate.setReverse(true);

		ListItem issuanceDate = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getIssuanceDate() != null ? card.getIssuanceDate().toString() : "N/A", "Issuance Date");
		issuanceDate.setReverse(true);

		ListItem expirationDate = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getExpirationDate() != null ? card.getExpirationDate().toString() : "N/A", "Expiration Date");
		expirationDate.setReverse(true);

		ListItem lastUsedDate = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getLastUsedDate() != null ? card.getLastUsedDate().toString() : "N/A", "Last Used Date");
		lastUsedDate.setReverse(true);

		FlexBoxLayout datesContent = new FlexBoxLayout(created, updated, activationDate, 
				issuanceDate, expirationDate, lastUsedDate);
		datesContent.setFlexDirection(FlexDirection.COLUMN);
		datesContent.getStyle().set("gap", "8px");

		datesGroup.add(datesGroupTitle, datesContent);

		// Group 3: Security Information
		Div securityGroup = new Div();
		securityGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		securityGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		securityGroup.getStyle().set("padding", "16px");
		securityGroup.setWidth("100%");

		Span securityGroupTitle = new Span("Security Information");
		securityGroupTitle.getStyle().set("font-weight", "bold");
		securityGroupTitle.getStyle().set("margin-bottom", "8px");
		securityGroupTitle.getStyle().set("display", "block");

		ListItem cvv = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.LOCK), 
				card.getCvv() != null ? "***" : "N/A", "CVV (masked)");
		cvv.setReverse(true);

		ListItem pin = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.LOCK), 
				card.getPin() != null ? "****" : "N/A", "PIN (masked)");
		pin.setReverse(true);

		ListItem expirationMonth = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getExpirationMonth() != null ? String.valueOf(card.getExpirationMonth()) : "N/A", "Expiration Month");
		expirationMonth.setReverse(true);

		ListItem expirationYear = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CALENDAR), 
				card.getExpirationYear() != null ? String.valueOf(card.getExpirationYear()) : "N/A", "Expiration Year");
		expirationYear.setReverse(true);

		FlexBoxLayout securityContent = new FlexBoxLayout(cvv, pin, expirationMonth, expirationYear);
		securityContent.setFlexDirection(FlexDirection.COLUMN);
		securityContent.getStyle().set("gap", "8px");

		securityGroup.add(securityGroupTitle, securityContent);

		// Group 4: Card Type Information
		Div cardTypeGroup = new Div();
		cardTypeGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		cardTypeGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		cardTypeGroup.getStyle().set("padding", "16px");
		cardTypeGroup.setWidth("100%");

		Span cardTypeGroupTitle = new Span("Card Type Information");
		cardTypeGroupTitle.getStyle().set("font-weight", "bold");
		cardTypeGroupTitle.getStyle().set("margin-bottom", "8px");
		cardTypeGroupTitle.getStyle().set("display", "block");

		ListItem isPhysical = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CREDIT_CARD), 
				card.getIsPhysical() != null ? (card.getIsPhysical() ? "Yes" : "No") : "N/A", "Is Physical");
		isPhysical.setReverse(true);

		ListItem isVirtual = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.MOBILE), 
				card.getIsVirtual() != null ? (card.getIsVirtual() ? "Yes" : "No") : "N/A", "Is Virtual");
		isVirtual.setReverse(true);

		ListItem isPrimary = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.STAR), 
				card.getIsPrimary() != null ? (card.getIsPrimary() ? "Yes" : "No") : "N/A", "Is Primary");
		isPrimary.setReverse(true);

		ListItem isActive = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.CHECK), 
				card.getIsActive() != null ? (card.getIsActive() ? "Yes" : "No") : "N/A", "Is Active");
		isActive.setReverse(true);

		ListItem isLocked = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.LOCK), 
				card.getIsLocked() != null ? (card.getIsLocked() ? "Yes" : "No") : "N/A", "Is Locked");
		isLocked.setReverse(true);

		ListItem lockReason = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.EXCLAMATION_CIRCLE), 
				card.getLockReason() != null ? card.getLockReason() : "N/A", "Lock Reason");
		lockReason.setReverse(true);

		FlexBoxLayout cardTypeContent = new FlexBoxLayout(isPhysical, isVirtual, isPrimary, 
				isActive, isLocked, lockReason);
		cardTypeContent.setFlexDirection(FlexDirection.COLUMN);
		cardTypeContent.getStyle().set("gap", "8px");

		cardTypeGroup.add(cardTypeGroupTitle, cardTypeContent);

		// Group 5: Financial Details
		Div financialDetailsGroup = new Div();
		financialDetailsGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		financialDetailsGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		financialDetailsGroup.getStyle().set("padding", "16px");
		financialDetailsGroup.setWidth("100%");

		Span financialDetailsGroupTitle = new Span("Financial Details");
		financialDetailsGroupTitle.getStyle().set("font-weight", "bold");
		financialDetailsGroupTitle.getStyle().set("margin-bottom", "8px");
		financialDetailsGroupTitle.getStyle().set("display", "block");

		ListItem dailyLimit = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), 
				card.getDailyLimit() != null ? card.getDailyLimit().toString() : "N/A", "Daily Limit");
		dailyLimit.setReverse(true);

		ListItem monthlyLimit = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), 
				card.getMonthlyLimit() != null ? card.getMonthlyLimit().toString() : "N/A", "Monthly Limit");
		monthlyLimit.setReverse(true);

		ListItem creditLimit = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), 
				card.getCreditLimit() != null ? card.getCreditLimit().toString() : "N/A", "Credit Limit");
		creditLimit.setReverse(true);

		ListItem availableBalance = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.DOLLAR), 
				card.getAvailableBalance() != null ? card.getAvailableBalance().toString() : "N/A", "Available Balance");
		availableBalance.setReverse(true);

		ListItem currencyCode = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.MONEY), 
				card.getCurrencyCode() != null ? card.getCurrencyCode() : "N/A", "Currency Code");
		currencyCode.setReverse(true);

		FlexBoxLayout financialDetailsContent = new FlexBoxLayout(dailyLimit, monthlyLimit, 
				creditLimit, availableBalance, currencyCode);
		financialDetailsContent.setFlexDirection(FlexDirection.COLUMN);
		financialDetailsContent.getStyle().set("gap", "8px");

		financialDetailsGroup.add(financialDetailsGroupTitle, financialDetailsContent);

		// Group 6: Additional Information
		Div additionalInfoGroup = new Div();
		additionalInfoGroup.getStyle().set("background-color", "var(--lumo-contrast-5pct)");
		additionalInfoGroup.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		additionalInfoGroup.getStyle().set("padding", "16px");
		additionalInfoGroup.setWidth("100%");

		Span additionalInfoGroupTitle = new Span("Additional Information");
		additionalInfoGroupTitle.getStyle().set("font-weight", "bold");
		additionalInfoGroupTitle.getStyle().set("margin-bottom", "8px");
		additionalInfoGroupTitle.getStyle().set("display", "block");

		ListItem notes = new ListItem(UIUtils.createTertiaryIcon(VaadinIcon.NOTEBOOK), 
				card.getNotes() != null ? card.getNotes() : "N/A", "Notes");
		notes.setReverse(true);

		FlexBoxLayout additionalInfoContent = new FlexBoxLayout(notes);
		additionalInfoContent.setFlexDirection(FlexDirection.COLUMN);
		additionalInfoContent.getStyle().set("gap", "8px");

		additionalInfoGroup.add(additionalInfoGroupTitle, additionalInfoContent);

		// Create main content layout with all groups
		FlexBoxLayout mainContent = new FlexBoxLayout(
				logoAndName,
				balanceContainer,
				basicInfoGroup,
				datesGroup,
				securityGroup,
				cardTypeGroup,
				financialDetailsGroup,
				additionalInfoGroup
		);
		mainContent.setFlexDirection(FlexDirection.COLUMN);
		mainContent.setWidth("100%");
		mainContent.setPadding(Vertical.M, Horizontal.L);
		mainContent.getStyle().set("gap", "16px");

		// Create the main section layout with a card-like appearance
		FlexBoxLayout section = new FlexBoxLayout(mainContent);
		section.addClassName(BoxShadowBorders.BOTTOM);
		section.setAlignItems(FlexComponent.Alignment.CENTER);
		section.setFlexDirection(FlexDirection.COLUMN);
		section.setPadding(Vertical.L, Horizontal.M);
		section.getStyle().set("background-color", "var(--lumo-base-color)");
		section.getStyle().set("border-radius", "var(--lumo-border-radius-m)");
		return section;
	}

	private Component createRecentTransactionsHeader() {
		Span title = UIUtils.createH3Label("Recent Transactions");

		Button viewAll = UIUtils.createSmallButton("View All");
		viewAll.addClickListener(
				e -> UIUtils.showNotification("Not implemented yet."));
		viewAll.addClassName(LumoStyles.Margin.Left.AUTO);

		FlexBoxLayout header = new FlexBoxLayout(title, viewAll);
		header.setAlignItems(FlexComponent.Alignment.CENTER);
		header.setMargin(Bottom.M, Horizontal.RESPONSIVE_L, Top.L);
		return header;
	}

	private Component createRecentTransactionsList() {
		Div items = new Div();
		items.addClassNames(BoxShadowBorders.BOTTOM, LumoStyles.Padding.Bottom.L);

		// Sample transaction data
		String[] companies = {"Amazon", "Netflix", "Utility Bill", "Salary Deposit"};
		Double[] amounts = {-29.99, -14.99, -85.50, 1250.00};

		for (int i = 0; i < RECENT_TRANSACTIONS; i++) {
			// Use sample data or fallback to index-based values if we run out of samples
			int index = i % companies.length;
			Double amount = amounts[index];

			Span amountLabel = UIUtils.createAmountLabel(amount);
			if (amount > 0) {
				UIUtils.setTextColor(TextColor.SUCCESS, amountLabel);
			} else {
				UIUtils.setTextColor(TextColor.ERROR, amountLabel);
			}

			// Create a sample icon for the transaction
			Image logo = new Image("images/logos/payment.png", "Transaction");
			logo.setHeight("32px");
			logo.setWidth("32px");

			ListItem item = new ListItem(
					logo,
					companies[index],
					UIUtils.formatDate(LocalDate.now().minusDays(i)),
					amountLabel
			);
			// Dividers for all but the last item
			item.setDividerVisible(i < RECENT_TRANSACTIONS - 1);
			items.add(item);
		}

		return items;
	}

	private Component createMonthlyOverviewHeader() {
		Span header = UIUtils.createH3Label("Monthly Overview");
		header.addClassNames(LumoStyles.Margin.Vertical.L, LumoStyles.Margin.Responsive.Horizontal.L);
		return header;
	}

	private Component createMonthlyOverviewChart() {
		Chart chart = new Chart(ChartType.COLUMN);

		Configuration conf = chart.getConfiguration();
		conf.setTitle("");
		conf.getLegend().setEnabled(true);

		XAxis xAxis = new XAxis();
		xAxis.setCategories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
		conf.addxAxis(xAxis);

		conf.getyAxis().setTitle("Amount ($)");

		// Sample data for withdrawals and deposits
		int[] withdrawalData = {7500, 8200, 7800, 9100, 8600, 7900, 8300, 9500, 8800, 7700, 8100, 9200};
		int[] depositData = {8000, 8700, 8200, 9600, 9100, 8400, 8800, 10000, 9300, 8200, 8600, 9700};

		// Withdrawals and deposits
		ListSeries withDrawals = new ListSeries("Withdrawals");
		ListSeries deposits = new ListSeries("Deposits");

		// Add sample data for all 12 months
		for (int i = 0; i < 12; i++) {
			withDrawals.addData(withdrawalData[i]);
			deposits.addData(depositData[i]);
		}

		conf.addSeries(withDrawals);
		conf.addSeries(deposits);

		FlexBoxLayout card = new FlexBoxLayout(chart);
		card.setHeight("400px");
		return card;
	}

	/**
	 * Opens the edit card dialog with the current card data.
	 * When the card is successfully updated, it reloads the card data.
	 */
	private void openEditCardDialog() {
		if (card == null) {
			System.out.println("[DEBUG_LOG] Cannot open edit dialog: card is null");
			return;
		}

		// Create a runnable to reload the card data when the edit is successful
		Runnable onSuccess = () -> {
			System.out.println("[DEBUG_LOG] Card updated, reloading data");
			loadCardData(card.getCardId());
		};

		// TODO: Update EditCard class to accept CardsService and CardDTO
		// For now, we'll just show a notification
		UIUtils.showNotification("Edit Card functionality not implemented yet");

		// Commented out until EditCard is updated
		// EditCard editCardDialog = new EditCard(cardsService, card, onSuccess);
		// editCardDialog.open();
	}

	@Override
	protected void onAttach(AttachEvent attachEvent) {
		super.onAttach(attachEvent);
		System.out.println("[DEBUG_LOG] Component attached, dataLoaded: " + dataLoaded);

		// Only update UI if data is already loaded
		if (dataLoaded && card != null) {
			updateUIWithCardData();
		}
	}

	private AppBar initAppBar() {
		try {
			AppBar appBar = MainLayout.get().getAppBar();
			appBar.setNaviMode(AppBar.NaviMode.CONTEXTUAL);
			appBar.getContextIcon().addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(Cards.class)));
			appBar.setTitle(card.getCardHolderName() != null ? card.getCardHolderName() : "Card Details");
			return appBar;
		} catch (Exception e) {
			System.out.println("[DEBUG_LOG] Error initializing AppBar: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Opens a confirmation dialog for deleting the current card.
	 * If confirmed, calls the deleteCard method and navigates back to the Cards view.
	 */
	private void openDeleteConfirmationDialog() {
		if (card == null) {
			System.out.println("[DEBUG_LOG] Cannot open delete dialog: card is null");
			return;
		}

		// Create confirmation dialog
		Dialog confirmDialog = new Dialog();
		confirmDialog.setCloseOnEsc(false);
		confirmDialog.setCloseOnOutsideClick(false);

		// Add header
		H3 header = new H3("Confirm Delete");
		header.getStyle().set("margin-top", "0");

		// Add confirmation message
		Span message = new Span("Are you sure you want to delete this card? This action cannot be undone.");
		message.getStyle().set("color", "var(--lumo-error-text-color)");

		// Create buttons
		Button confirmButton = UIUtils.createErrorPrimaryButton("Delete");
		confirmButton.addClickListener(e -> {
			confirmDialog.close();
			deleteCard();
		});

		Button cancelButton = UIUtils.createTertiaryButton("Cancel");
		cancelButton.addClickListener(e -> confirmDialog.close());

		// Create button layout
		FlexBoxLayout buttonLayout = new FlexBoxLayout(confirmButton, cancelButton);
		buttonLayout.setFlexDirection(FlexDirection.ROW);
		buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
		buttonLayout.getStyle().set("gap", "8px");
		buttonLayout.getStyle().set("margin-top", "20px");

		// Add components to dialog
		Div content = new Div(header, message, buttonLayout);
		content.getStyle().set("padding", "20px");
		confirmDialog.add(content);

		// Open dialog
		confirmDialog.open();
	}

	/**
	 * Deletes the current card and navigates back to the Cards view if successful.
	 */
	private void deleteCard() {
		if (card == null) {
			System.out.println("[DEBUG_LOG] Cannot delete card: card is null");
			return;
		}

		System.out.println("[DEBUG_LOG] Deleting card with ID: " + card.getCardId());

		// Call the service to delete the card
		cardsService.deleteCard(card.getCardId())
			.subscribe(
				response -> {
					// Use UI.access() to safely update the UI from the async callback
					getUI().ifPresent(ui -> ui.access(() -> {
						if (response.getStatusCode().is2xxSuccessful()) {
							System.out.println("[DEBUG_LOG] Card deleted successfully");

							// Show success notification
							UIUtils.showNotification("Card deleted successfully");

							// Navigate back to Cards view
							ui.navigate(Cards.class);
						} else {
							System.out.println("[DEBUG_LOG] Failed to delete card: " + response.getStatusCode());

							// Show error notification
							UIUtils.showNotification("Failed to delete card: " + response.getStatusCode());
						}
					}));
				},
				error -> {
					System.out.println("[DEBUG_LOG] Error deleting card: " + error.getMessage());
					error.printStackTrace();

					// Handle error in UI thread
					getUI().ifPresent(ui -> ui.access(() -> {
						UIUtils.showNotification("Error deleting card: " + error.getMessage());
					}));
				}
			);
	}
}
