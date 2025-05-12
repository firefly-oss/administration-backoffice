package com.catalis.backoffice.clients.ui.view;

import com.catalis.backoffice.base.ui.component.ViewToolbar;
import com.catalis.backoffice.base.ui.view.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Route(value = "clients", layout = MainLayout.class)
@PageTitle("Clients | Bank Backoffice")
public class ClientsView extends VerticalLayout {

    private final Random random = new Random();
    private final Map<Tab, Component> tabsToPages = new HashMap<>();
    private final Div pages = new Div();
    private final Grid<Client> clientGrid = new Grid<>(Client.class, false);

    public ClientsView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Create toolbar with actions
        Button createButton = new Button("New Client", VaadinIcon.PLUS.create());
        createButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        Button importButton = new Button("Import", VaadinIcon.UPLOAD.create());
        Button exportButton = new Button("Export", VaadinIcon.DOWNLOAD.create());
        
        HorizontalLayout actions = new HorizontalLayout(createButton, importButton, exportButton);
        actions.setSpacing(true);

        add(new ViewToolbar("Clients", actions));
        
        // Create tabs
        Tab clientsTab = new Tab(VaadinIcon.USERS.create(), new Span("Clients CRUD"));
        Tab documentsTab = new Tab(VaadinIcon.FILE_TEXT.create(), new Span("Documents"));
        
        Tabs tabs = new Tabs(clientsTab, documentsTab);
        tabs.addClassNames(LumoUtility.Padding.Horizontal.MEDIUM);
        
        // Create pages for tabs
        Component clientsPage = createClientsPage();
        Component documentsPage = createDocumentsPage();
        
        tabsToPages.put(clientsTab, clientsPage);
        tabsToPages.put(documentsTab, documentsPage);
        
        // Configure pages
        pages.setSizeFull();
        pages.add(clientsPage, documentsPage);
        
        // Show only active page
        for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
            component.setVisible(false);
        }
        clientsPage.setVisible(true);
        
        // Add tab change listener
        tabs.addSelectedChangeListener(event -> {
            for (Component component : pages.getChildren().collect(java.util.stream.Collectors.toList())) {
                component.setVisible(false);
            }
            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
            selectedPage.setVisible(true);
        });
        
        add(tabs, pages);
    }
    
    private Component createClientsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        
        // Configure grid
        configureClientGrid();
        
        // Add search field
        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.setWidthFull();
        searchLayout.setPadding(true);
        searchLayout.addClassNames(LumoUtility.Background.CONTRAST_5);
        
        com.vaadin.flow.component.textfield.TextField searchField = new com.vaadin.flow.component.textfield.TextField();
        searchField.setPlaceholder("Search clients...");
        searchField.setPrefixComponent(VaadinIcon.SEARCH.create());
        searchField.setWidthFull();
        
        searchLayout.add(searchField);
        
        layout.add(searchLayout, clientGrid);
        
        // Load mock data
        loadMockClientData();
        
        return layout;
    }
    
    private Component createDocumentsPage() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(true);
        
        // Create documents grid
        Grid<Document> documentsGrid = new Grid<>(Document.class, false);
        documentsGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        documentsGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        documentsGrid.setSizeFull();
        
        documentsGrid.addColumn(Document::getId).setHeader("Document ID").setAutoWidth(true).setSortable(true);
        documentsGrid.addColumn(Document::getClientName).setHeader("Client").setAutoWidth(true).setSortable(true);
        documentsGrid.addColumn(Document::getType).setHeader("Type").setAutoWidth(true).setSortable(true);
        documentsGrid.addColumn(Document::getUploadDate).setHeader("Upload Date").setAutoWidth(true).setSortable(true);
        documentsGrid.addColumn(Document::getSize).setHeader("Size").setAutoWidth(true).setSortable(true);
        
        // Add action column
        documentsGrid.addComponentColumn(document -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);
            
            Button viewButton = new Button(VaadinIcon.EYE.create());
            viewButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            viewButton.getElement().setAttribute("aria-label", "View");
            
            Button downloadButton = new Button(VaadinIcon.DOWNLOAD.create());
            downloadButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            downloadButton.getElement().setAttribute("aria-label", "Download");
            
            Button deleteButton = new Button(VaadinIcon.TRASH.create());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            deleteButton.getElement().setAttribute("aria-label", "Delete");
            
            actions.add(viewButton, downloadButton, deleteButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
        
        // Add upload button
        Button uploadButton = new Button("Upload Document", VaadinIcon.UPLOAD.create());
        uploadButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        
        // Load mock document data
        List<Document> documents = new ArrayList<>();
        String[] clientNames = {"John Smith", "Jane Doe", "Robert Johnson", "Emily Williams", 
                               "Michael Brown", "Sarah Davis", "David Miller", "Lisa Wilson"};
        
        String[] documentTypes = {"ID Card", "Passport", "Proof of Address", "Bank Statement", 
                                 "Tax Return", "Income Certificate", "Contract", "Application Form"};
        
        for (int i = 1; i <= 20; i++) {
            String id = String.format("DOC-%06d", i);
            String clientName = clientNames[random.nextInt(clientNames.length)];
            String type = documentTypes[random.nextInt(documentTypes.length)];
            String uploadDate = LocalDate.now().minusDays(random.nextInt(365)).toString();
            String size = (random.nextInt(10) + 1) + "." + random.nextInt(99) + " MB";
            
            documents.add(new Document(id, clientName, type, uploadDate, size));
        }
        
        documentsGrid.setItems(documents);
        
        layout.add(uploadButton, documentsGrid);
        
        return layout;
    }
    
    private void configureClientGrid() {
        clientGrid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);
        clientGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_ROW_STRIPES);
        clientGrid.setSizeFull();
        
        clientGrid.addColumn(Client::getId).setHeader("Client ID").setAutoWidth(true).setSortable(true);
        clientGrid.addColumn(Client::getName).setHeader("Name").setAutoWidth(true).setSortable(true);
        clientGrid.addColumn(Client::getEmail).setHeader("Email").setAutoWidth(true).setSortable(true);
        clientGrid.addColumn(Client::getPhone).setHeader("Phone").setAutoWidth(true).setSortable(true);
        clientGrid.addColumn(Client::getAddress).setHeader("Address").setAutoWidth(true).setSortable(true);
        clientGrid.addColumn(Client::getSegment).setHeader("Segment").setAutoWidth(true).setSortable(true);
        clientGrid.addColumn(this::createStatusComponent).setHeader("Status").setAutoWidth(true);
        
        // Add action column
        clientGrid.addComponentColumn(client -> {
            HorizontalLayout actions = new HorizontalLayout();
            actions.setSpacing(true);
            
            Button editButton = new Button(VaadinIcon.EDIT.create());
            editButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY);
            editButton.getElement().setAttribute("aria-label", "Edit");
            
            Button deleteButton = new Button(VaadinIcon.TRASH.create());
            deleteButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);
            deleteButton.getElement().setAttribute("aria-label", "Delete");
            
            actions.add(editButton, deleteButton);
            return actions;
        }).setHeader("Actions").setAutoWidth(true);
    }
    
    private Span createStatusComponent(Client client) {
        Span status = new Span(client.getStatus());
        status.getElement().getThemeList().clear();
        
        switch (client.getStatus()) {
            case "Active":
                status.getElement().getThemeList().add("badge success");
                break;
            case "Inactive":
                status.getElement().getThemeList().add("badge error");
                break;
            case "Pending":
                status.getElement().getThemeList().add("badge");
                break;
            default:
                status.getElement().getThemeList().add("badge contrast");
        }
        
        return status;
    }
    
    private void loadMockClientData() {
        List<Client> clients = new ArrayList<>();
        
        // Generate mock clients
        String[] firstNames = {"John", "Jane", "Robert", "Emily", "Michael", "Sarah", "David", "Lisa", 
                              "William", "Jennifer", "Richard", "Elizabeth", "Joseph", "Susan", "Thomas", "Karen"};
        
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Davis", "Garcia", 
                             "Rodriguez", "Wilson", "Martinez", "Anderson", "Taylor", "Thomas", "Hernandez", "Moore"};
        
        String[] segments = {"Retail", "Premium", "Private Banking", "Corporate", "SME"};
        String[] statuses = {"Active", "Inactive", "Pending"};
        
        for (int i = 1; i <= 50; i++) {
            String id = String.format("CLI-%06d", i);
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String name = firstName + " " + lastName;
            
            String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@example.com";
            String phone = "+1 " + (random.nextInt(900) + 100) + "-" + (random.nextInt(900) + 100) + "-" + (random.nextInt(9000) + 1000);
            
            String address = (random.nextInt(9000) + 1000) + " " + getRandomStreetName() + ", " + getRandomCity() + ", " + getRandomState() + " " + (random.nextInt(90000) + 10000);
            
            String segment = segments[random.nextInt(segments.length)];
            String status = statuses[random.nextInt(statuses.length)];
            
            clients.add(new Client(id, name, email, phone, address, segment, status));
        }
        
        clientGrid.setItems(clients);
    }
    
    private String getRandomStreetName() {
        String[] streets = {"Main St", "Oak Ave", "Maple Rd", "Washington Blvd", "Park Ave", 
                           "Cedar Ln", "Pine St", "Elm St", "River Rd", "Lake Dr"};
        return streets[random.nextInt(streets.length)];
    }
    
    private String getRandomCity() {
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", 
                          "Philadelphia", "San Antonio", "San Diego", "Dallas", "San Jose"};
        return cities[random.nextInt(cities.length)];
    }
    
    private String getRandomState() {
        String[] states = {"NY", "CA", "IL", "TX", "AZ", "PA", "FL", "OH", "MI", "GA"};
        return states[random.nextInt(states.length)];
    }
    
    // Client data class
    public static class Client {
        private final String id;
        private final String name;
        private final String email;
        private final String phone;
        private final String address;
        private final String segment;
        private final String status;
        
        public Client(String id, String name, String email, String phone, String address, String segment, String status) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.segment = segment;
            this.status = status;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getPhone() {
            return phone;
        }
        
        public String getAddress() {
            return address;
        }
        
        public String getSegment() {
            return segment;
        }
        
        public String getStatus() {
            return status;
        }
    }
    
    // Document data class
    public static class Document {
        private final String id;
        private final String clientName;
        private final String type;
        private final String uploadDate;
        private final String size;
        
        public Document(String id, String clientName, String type, String uploadDate, String size) {
            this.id = id;
            this.clientName = clientName;
            this.type = type;
            this.uploadDate = uploadDate;
            this.size = size;
        }
        
        public String getId() {
            return id;
        }
        
        public String getClientName() {
            return clientName;
        }
        
        public String getType() {
            return type;
        }
        
        public String getUploadDate() {
            return uploadDate;
        }
        
        public String getSize() {
            return size;
        }
    }
}