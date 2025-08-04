package com.vaadin.starter.business.ui.views.security;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.starter.business.backend.dto.security.RoleDTO;
import com.vaadin.starter.business.backend.service.SecurityService;
import com.vaadin.starter.business.dummy.Person;
import com.vaadin.starter.business.ui.util.LumoStyles;
import com.vaadin.starter.business.ui.util.UIUtils;

import java.util.Collection;

/**
 * Dialog for displaying and editing user details, including role assignment.
 */
public class UserDetails extends Dialog {

    private final Person person;
    private final SecurityService securityService;
    
    private TextField firstName;
    private TextField lastName;
    private TextField username;
    private TextField email;
    private RadioButtonGroup<String> status;
    private ComboBox<RoleDTO> role;
    
    /**
     * Constructor for the UserDetails dialog.
     *
     * @param person the user to display details for
     * @param securityService the security service for accessing roles
     */
    public UserDetails(Person person, SecurityService securityService) {
        this.person = person;
        this.securityService = securityService;
        
        setWidth("800px");
        setHeight("auto");
        
        VerticalLayout content = new VerticalLayout();
        content.setPadding(true);
        content.setSpacing(true);
        
        H3 title = new H3("User Details: " + person.getName());
        content.add(title);
        
        content.add(createForm());
        content.add(createButtonLayout());
        
        add(content);
    }
    
    private FormLayout createForm() {
        firstName = new TextField();
        firstName.setValue(person.getFirstName());
        firstName.setWidthFull();
        
        lastName = new TextField();
        lastName.setValue(person.getLastName());
        lastName.setWidthFull();
        
        username = new TextField();
        username.setValue(person.getEmail().split("@")[0]);
        username.setWidthFull();
        
        email = new TextField();
        email.setValue(person.getEmail());
        email.setWidthFull();
        
        status = new RadioButtonGroup<>();
        status.setItems("Active", "Inactive");
        status.setValue(person.getRandomBoolean() ? "Active" : "Inactive");
        
        role = new ComboBox<>();
        role.setLabel("Role");
        role.setItemLabelGenerator(RoleDTO::getName);
        role.setItems(securityService.getRoles());
        
        // Try to find a matching role from the Person's role enum
        Collection<RoleDTO> roles = securityService.getRoles();
        for (RoleDTO roleDTO : roles) {
            try {
                Person.Role personRole = person.getRole();
                if (roleDTO.getName().equalsIgnoreCase(personRole.name())) {
                    role.setValue(roleDTO);
                    break;
                }
            } catch (Exception e) {
                // If there's no match, just leave it unselected
            }
        }
        
        role.setWidthFull();
        
        // Form layout
        FormLayout form = new FormLayout();
        form.addClassNames(LumoStyles.Padding.Bottom.L,
                LumoStyles.Padding.Horizontal.L, LumoStyles.Padding.Top.S);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2,
                        FormLayout.ResponsiveStep.LabelsPosition.TOP));
        form.addFormItem(firstName, "First Name");
        form.addFormItem(lastName, "Last Name");
        form.addFormItem(username, "Username");
        form.addFormItem(email, "Email");
        form.addFormItem(status, "Status");
        form.addFormItem(role, "Role");
        form.addFormItem(new Upload(), "Profile Picture");
        
        return form;
    }
    
    private HorizontalLayout createButtonLayout() {
        Button saveButton = UIUtils.createPrimaryButton("Save");
        saveButton.addClickListener(e -> {
            saveUser();
            close();
        });
        
        Button cancelButton = UIUtils.createTertiaryButton("Cancel");
        cancelButton.addClickListener(e -> close());
        
        HorizontalLayout buttonLayout = new HorizontalLayout(saveButton, cancelButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);
        
        return buttonLayout;
    }
    
    private void saveUser() {
        // In a real application, this would save the user data to the backend
        // For this example, we'll just show a notification
        UIUtils.showNotification("User details saved.");
        
        // Here you would update the user with the selected role
        // Example: person.setRole(convertToPersonRole(role.getValue()));
        // securityService.updateUser(person);
    }
}