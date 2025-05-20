package com.vaadin.starter.business.ui.views.products;

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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Route(value = "contract-management", layout = MainLayout.class)
@PageTitle("Contract Management")
public class ContractManagement extends SplitViewFrame {

    private Grid<Contract> grid;
    private ListDataProvider<Contract> dataProvider;

    private DetailsDrawer detailsDrawer;
    private DetailsDrawerHeader detailsDrawerHeader;

    // Sample Contract class for demonstration
    private static class Contract {
        private String id;
        private String name;
        private String type;
        private String client;
        private String status;
        private LocalDate startDate;
        private LocalDate endDate;
        private String assignedTo;
        private String documentUrl;

        public Contract(String id, String name, String type, String client, 
                      String status, LocalDate startDate, LocalDate endDate, 
                      String assignedTo, String documentUrl) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.client = client;
            this.status = status;
            this.startDate = startDate;
            this.endDate = endDate;
            this.assignedTo = assignedTo;
            this.documentUrl = documentUrl;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public String getClient() {
            return client;
        }

        public String getStatus() {
            return status;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getAssignedTo() {
            return assignedTo;
        }

        public String getDocumentUrl() {
            return documentUrl;
        }

        public boolean isActive() {
            return "Active".equals(status);
        }
    }

    // Sample data
    private List<Contract> getContracts() {
        LocalDate now = LocalDate.now();
        return Arrays.asList(
            new Contract("C001", "Personal Loan Agreement", "Loan", "John Smith", 
                "Active", now.minusDays(30), now.plusYears(5), 
                "Sarah Johnson", "/documents/loans/C001.pdf"),
            new Contract("C002", "Mortgage Contract", "Mortgage", "Emily Davis", 
                "Active", now.minusDays(60), now.plusYears(30), 
                "Michael Brown", "/documents/mortgages/C002.pdf"),
            new Contract("C003", "Credit Card Agreement", "Credit Card", "Robert Wilson", 
                "Active", now.minusDays(45), now.plusYears(2), 
                "Jennifer Lee", "/documents/cards/C003.pdf"),
            new Contract("C004", "Business Loan Contract", "Business Loan", "Acme Corporation", 
                "Pending", now.plusDays(15), now.plusYears(7), 
                "David Miller", "/documents/business/C004.pdf"),
            new Contract("C005", "Savings Account Terms", "Account", "Lisa Taylor", 
                "Active", now.minusDays(90), null, 
                "James Anderson", "/documents/accounts/C005.pdf"),
            new Contract("C006", "Investment Portfolio Agreement", "Investment", "Thomas Moore", 
                "Expired", now.minusYears(3), now.minusMonths(1), 
                "Patricia White", "/documents/investments/C006.pdf"),
            new Contract("C007", "Insurance Policy", "Insurance", "Karen Martinez", 
                "Draft", null, null, 
                "Robert Johnson", "/documents/insurance/C007.pdf")
        );
    }

    public ContractManagement() {
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
        dataProvider = DataProvider.ofCollection(getContracts());
        grid.setDataProvider(dataProvider);
        grid.setSizeFull();

        grid.addColumn(Contract::getId)
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setFrozen(true)
                .setHeader("ID")
                .setSortable(true);
        grid.addColumn(Contract::getName)
                .setAutoWidth(true)
                .setHeader("Contract Name")
                .setSortable(true);
        grid.addColumn(Contract::getType)
                .setAutoWidth(true)
                .setHeader("Type")
                .setSortable(true);
        grid.addColumn(Contract::getClient)
                .setAutoWidth(true)
                .setHeader("Client")
                .setSortable(true);
        grid.addColumn(new ComponentRenderer<>(this::createStatusBadge))
                .setAutoWidth(true)
                .setHeader("Status")
                .setTextAlign(ColumnTextAlign.CENTER);
        grid.addColumn(new ComponentRenderer<>(this::createStartDate))
                .setAutoWidth(true)
                .setFlexGrow(0)
                .setHeader("Start Date")
                .setTextAlign(ColumnTextAlign.END);
        grid.addColumn(Contract::getAssignedTo)
                .setAutoWidth(true)
                .setHeader("Assigned To")
                .setSortable(true);

        return grid;
    }

    private Component createStatusBadge(Contract contract) {
        String status = contract.getStatus();
        String theme = "";
        
        if ("Active".equals(status)) {
            theme = "success";
        } else if ("Pending".equals(status)) {
            theme = "warning";
        } else if ("Expired".equals(status)) {
            theme = "error";
        } else if ("Draft".equals(status)) {
            theme = "contrast";
        }
        
        Span badge = new Span(status);
        badge.getElement().setAttribute("theme", "badge " + theme);
        return badge;
    }

    private Component createStartDate(Contract contract) {
        if (contract.getStartDate() == null) {
            return new Span("Not set");
        }
        return new Span(UIUtils.formatDate(contract.getStartDate()));
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

    private void showDetails(Contract contract) {
        detailsDrawerHeader.setTitle(contract.getName());
        detailsDrawer.setContent(createDetails(contract));
        detailsDrawer.show();
    }

    private FormLayout createDetails(Contract contract) {
        TextField id = new TextField();
        id.setValue(contract.getId());
        id.setWidthFull();

        TextField name = new TextField();
        name.setValue(contract.getName());
        name.setWidthFull();

        ComboBox<String> type = new ComboBox<>();
        type.setItems("Loan", "Mortgage", "Credit Card", "Account", "Business Loan", "Investment", "Insurance");
        type.setValue(contract.getType());
        type.setWidthFull();

        TextField client = new TextField();
        client.setValue(contract.getClient());
        client.setWidthFull();

        ComboBox<String> status = new ComboBox<>();
        status.setItems("Draft", "Pending", "Active", "Expired", "Terminated");
        status.setValue(contract.getStatus());
        status.setWidthFull();

        // Date fields would typically use DatePicker, but for simplicity using TextField
        TextField startDate = new TextField();
        startDate.setValue(contract.getStartDate() != null ? UIUtils.formatDate(contract.getStartDate()) : "");
        startDate.setWidthFull();

        TextField endDate = new TextField();
        endDate.setValue(contract.getEndDate() != null ? UIUtils.formatDate(contract.getEndDate()) : "");
        endDate.setWidthFull();

        TextField assignedTo = new TextField();
        assignedTo.setValue(contract.getAssignedTo());
        assignedTo.setWidthFull();

        TextField documentUrl = new TextField();
        documentUrl.setValue(contract.getDocumentUrl());
        documentUrl.setWidthFull();

        Upload documentUpload = new Upload();
        documentUpload.setMaxFiles(1);
        documentUpload.setDropLabel(new Span("Upload contract document (or drop here)"));

        TextArea notes = new TextArea();
        notes.setPlaceholder("Enter notes about this contract...");
        notes.setWidthFull();
        notes.setHeight("150px");

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
        form.addFormItem(id, "Contract ID");
        form.addFormItem(name, "Contract Name");
        form.addFormItem(type, "Type");
        form.addFormItem(client, "Client");
        form.addFormItem(status, "Status");
        form.addFormItem(startDate, "Start Date");
        form.addFormItem(endDate, "End Date");
        form.addFormItem(assignedTo, "Assigned To");
        form.addFormItem(documentUrl, "Document URL");
        form.addFormItem(documentUpload, "Upload Document");
        form.addFormItem(notes, "Notes");
        
        return form;
    }
}