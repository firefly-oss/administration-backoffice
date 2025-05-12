package com.catalis.backoffice.base.ui.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.avatar.AvatarVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;
import com.vaadin.flow.server.menu.MenuConfiguration;
import com.vaadin.flow.server.menu.MenuEntry;

import static com.vaadin.flow.theme.lumo.LumoUtility.*;

@Layout
public final class MainLayout extends AppLayout {

    MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToDrawer(createHeader(), new Scroller(createSideNav()), createUserMenu());
    }

    private Div createHeader() {
        // Bank logo
        var appLogo = VaadinIcon.BUILDING_O.create();
        appLogo.addClassName(TextColor.PRIMARY);
        appLogo.addClassName(IconSize.LARGE);

        var appName = new Span("Bank Backoffice");
        appName.addClassNames(FontWeight.SEMIBOLD, FontSize.LARGE);

        var header = new Div(appLogo, appName);
        header.addClassNames(Display.FLEX, Padding.MEDIUM, Gap.MEDIUM, AlignItems.CENTER);
        return header;
    }

    private SideNav createSideNav() {
        var nav = new SideNav();
        nav.addClassNames(Margin.Horizontal.MEDIUM);

        // Dashboard section
        SideNavItem dashboardItem = new SideNavItem("Dashboard", "dashboard", VaadinIcon.DASHBOARD.create());
        nav.addItem(dashboardItem);

        // Contracts section
        SideNavItem contractsItem = new SideNavItem("Contracts", "contracts", VaadinIcon.FILE_TEXT.create());
        nav.addItem(contractsItem);

        // Clients section
        SideNavItem clientsItem = new SideNavItem("Clients", "clients", VaadinIcon.USERS.create());
        SideNavItem clientsCrudItem = new SideNavItem("Clients CRUD", "clients/crud");
        SideNavItem documentsItem = new SideNavItem("Documents", "clients/documents");
        clientsItem.addItem(clientsCrudItem);
        clientsItem.addItem(documentsItem);
        nav.addItem(clientsItem);

        // Accounts section
        SideNavItem accountsItem = new SideNavItem("Accounts", "accounts", VaadinIcon.CREDIT_CARD.create());
        SideNavItem transactionsItem = new SideNavItem("Transactions", "accounts/transactions");
        accountsItem.addItem(transactionsItem);
        nav.addItem(accountsItem);

        // Lending section
        SideNavItem lendingItem = new SideNavItem("Lending", "lending", VaadinIcon.MONEY.create());
        nav.addItem(lendingItem);

        // ERP section
        SideNavItem erpItem = new SideNavItem("ERP", "erp", VaadinIcon.BUILDING.create());
        nav.addItem(erpItem);

        // Configurations section
        SideNavItem configurationsItem = new SideNavItem("Configurations", "configurations", VaadinIcon.COG.create());
        SideNavItem masterDataItem = new SideNavItem("Master Data", "configurations/master-data");
        SideNavItem platformConfigItem = new SideNavItem("Common Platform Config", "configurations/platform-config");
        SideNavItem usersItem = new SideNavItem("Users", "configurations/users");
        SideNavItem productsItem = new SideNavItem("Products", "configurations/products");
        configurationsItem.addItem(masterDataItem);
        configurationsItem.addItem(platformConfigItem);
        configurationsItem.addItem(usersItem);
        configurationsItem.addItem(productsItem);
        nav.addItem(configurationsItem);

        // Components section
        SideNavItem componentsItem = new SideNavItem("Components", "components", VaadinIcon.CUBES.create());
        nav.addItem(componentsItem);

        // Add any menu entries from the configuration
        MenuConfiguration.getMenuEntries().forEach(entry -> nav.addItem(createSideNavItem(entry)));

        return nav;
    }

    private SideNavItem createSideNavItem(MenuEntry menuEntry) {
        if (menuEntry.icon() != null) {
            return new SideNavItem(menuEntry.title(), menuEntry.path(), new Icon(menuEntry.icon()));
        } else {
            return new SideNavItem(menuEntry.title(), menuEntry.path());
        }
    }

    private Component createUserMenu() {
        var avatar = new Avatar("Bank Admin");
        avatar.addThemeVariants(AvatarVariant.LUMO_XSMALL);
        avatar.addClassNames(Margin.Right.SMALL);
        avatar.setColorIndex(2); // Blue color for bank theme

        var userMenu = new MenuBar();
        userMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE);
        userMenu.addClassNames(Margin.MEDIUM);

        var userMenuItem = userMenu.addItem(avatar);
        userMenuItem.add("Bank Admin");
        userMenuItem.getSubMenu().addItem("View Profile");
        userMenuItem.getSubMenu().addItem("Manage Settings");
        userMenuItem.getSubMenu().addItem("Logout", e -> {
            // In a real application, this would log the user out
            getUI().ifPresent(ui -> ui.navigate("login"));
        });

        return userMenu;
    }

}
