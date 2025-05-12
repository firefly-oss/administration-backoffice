package com.catalis.backoffice.security.ui.view;

import com.catalis.backoffice.base.ui.view.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("login")
@PageTitle("Login | Bank Backoffice")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();

    public LoginView() {
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        // Create a container for login components with a white background and shadow
        VerticalLayout loginContainer = new VerticalLayout();
        loginContainer.addClassNames(
                LumoUtility.Background.BASE,
                LumoUtility.BorderRadius.MEDIUM,
                LumoUtility.BoxShadow.MEDIUM,
                LumoUtility.Padding.LARGE
        );
        loginContainer.setMaxWidth("400px");
        loginContainer.setAlignItems(Alignment.CENTER);

        // Add bank logo (placeholder)
        Image logo = new Image("images/bank-logo.png", "Bank Logo");
        logo.setWidth("150px");
        logo.setHeight("150px");
        // Use a default Vaadin icon as placeholder
        logo.getElement().setAttribute("icon", "vaadin:bank");

        H1 header = new H1("Bank Backoffice");
        header.addClassNames(
                LumoUtility.Margin.NONE,
                LumoUtility.FontSize.XXLARGE,
                LumoUtility.TextColor.PRIMARY
        );

        // Configure login form
        login.setAction("login");
        login.setForgotPasswordButtonVisible(true);

        // Add a demo login button for easy access during development
        Button demoLoginButton = new Button("Demo Login", e -> {
            // In a real application, this would be replaced with actual authentication
            Notification.show("Logged in as demo user", 3000, Notification.Position.BOTTOM_END)
                    .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().navigate(MainView.class);
        });
        demoLoginButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        demoLoginButton.addClassNames(LumoUtility.Margin.Top.MEDIUM);

        loginContainer.add(logo, header, login, demoLoginButton);
        add(loginContainer);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        // Check for error parameters in the URL
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}