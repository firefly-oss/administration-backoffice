package com.vaadin.starter.business.ui.views.customers;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.business.dummy.Segment;
import com.vaadin.starter.business.backend.service.CustomersService;
import com.vaadin.starter.business.ui.MainLayout;
import com.vaadin.starter.business.ui.components.FlexBoxLayout;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawer;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerFooter;
import com.vaadin.starter.business.ui.components.detailsdrawer.DetailsDrawerHeader;
import com.vaadin.starter.business.ui.layout.size.Horizontal;
import com.vaadin.starter.business.ui.layout.size.Top;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;
import com.vaadin.starter.business.ui.util.css.BoxSizing;
import com.vaadin.starter.business.ui.views.SplitViewFrame;

@Route(value = "customer-segmentation", layout = MainLayout.class)
@PageTitle("Customer Segmentation")
public class CustomerSegmentation extends SplitViewFrame {

    private Grid<Segment> grid;
    private ListDataProvider<Segment> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    private final CustomersService customersService;

    public CustomerSegmentation(CustomersService customersService) {
        this.customersService = customersService;

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
        dataProvider = DataProvider.ofCollection(customersService.getSegments());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Segment::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(Segment::getName)
                .setAutoWidth(true)
                .setHeader("Segment Name")
                .setSortable(true);
        grid.addColumn(Segment::getCustomerCount)
                .setAutoWidth(true)
                .setHeader("Customers")
                .setSortable(true)
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createAverageBalance))
                .setAutoWidth(true)
                .setHeader("Avg. Balance")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(Segment::getRiskProfile)
                .setAutoWidth(true)
                .setHeader("Risk Profile")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createActiveStatus))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(new ComponentRenderer<>(this::createDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Created")
                .setTextAlign(ColumnTextAlign.END);

        return grid;
    }

    private Component createAverageBalance(Segment segment) {
        return new Span(UIUtils.formatAmount(segment.getAverageBalance()));
    }

    private Component createActiveStatus(Segment segment) {
        Icon icon;
        if (segment.isActive()) {
            icon = UIUtils.createPrimaryIcon(VaadinIcon.CHECK);
        } else {
            icon = UIUtils.createDisabledIcon(VaadinIcon.CLOSE);
        }
        return icon;
    }

    private Component createDate(Segment segment) {
        return new Span(UIUtils.formatDate(segment.getCreatedDate()));
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
            UIUtils.showNotification("Changes saved.");
        });
        footer.addCancelListener(e -> detailsDrawer.hide());
        detailsDrawer.setFooter(footer);

        return detailsDrawer;
    }

    private void showDetails(Segment segment) {
        detailsDrawerHeader.setTitle(segment.getName());
        detailsDrawer.setContent(createDetails(segment));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Segment segment) {
        TextField id = new TextField();
        id.setValue(segment.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(segment.getName());
        name.setWidthFull();

        TextArea description = new TextArea();
        description.setValue(segment.getDescription());
        description.setWidthFull();
        description.setHeight("100px");

        NumberField customerCount = new NumberField();
        customerCount.setValue((double) segment.getCustomerCount());
        customerCount.setWidthFull();

        NumberField averageBalance = new NumberField();
        averageBalance.setValue(segment.getAverageBalance());
        averageBalance.setWidthFull();
        averageBalance.setPrefixComponent(new Span("$"));

        ComboBox<String> riskProfile = new ComboBox<>();
        riskProfile.setItems("Low", "Medium", "Medium-High", "High");
        riskProfile.setValue(segment.getRiskProfile());
        riskProfile.setWidthFull();

        RadioButtonGroup<String> status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(segment.isActive() ? "Active" : "Inactive");

        // Marketing targeting options
        ComboBox<String> marketingChannel = new ComboBox<>();
        marketingChannel.setItems("Email", "SMS", "Direct Mail", "Phone", "All Channels");
        marketingChannel.setValue("Email");
        marketingChannel.setWidthFull();

        ComboBox<String> productRecommendation = new ComboBox<>();
        productRecommendation.setItems("Savings Account", "Investment Account", "Credit Card", "Personal Loan", "Mortgage", "Insurance");
        productRecommendation.setValue("Savings Account");
        productRecommendation.setWidthFull();

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
        form.addFormItem(id, "Segment ID");
        form.addFormItem(name, "Segment Name");
        form.addFormItem(description, "Description");
        form.addFormItem(customerCount, "Customer Count");
        form.addFormItem(averageBalance, "Average Balance");
        form.addFormItem(riskProfile, "Risk Profile");
        form.addFormItem(status, "Status");
        form.addFormItem(marketingChannel, "Marketing Channel");
        form.addFormItem(productRecommendation, "Product Recommendation");

        return form;
    }
}
