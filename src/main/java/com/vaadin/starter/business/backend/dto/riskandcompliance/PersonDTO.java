/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.backend.dto.riskandcompliance;

import com.vaadin.starter.business.backend.Person;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for Person in the risk and compliance domain.
 */
public class PersonDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Person.Role role;
    private String email;
    private boolean randomBoolean;
    private int randomInteger;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public PersonDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id             the person ID
     * @param firstName      the person's first name
     * @param lastName       the person's last name
     * @param role           the person's role
     * @param email          the person's email
     * @param randomBoolean  a random boolean value
     * @param randomInteger  a random integer value
     * @param lastModified   the date the person was last modified
     */
    public PersonDTO(UUID id, String firstName, String lastName, Person.Role role,
                   String email, boolean randomBoolean, int randomInteger, LocalDate lastModified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.randomBoolean = randomBoolean;
        this.randomInteger = randomInteger;
        this.lastModified = lastModified;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person.Role getRole() {
        return role;
    }

    public void setRole(Person.Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRandomBoolean() {
        return randomBoolean;
    }

    public void setRandomBoolean(boolean randomBoolean) {
        this.randomBoolean = randomBoolean;
    }

    public int getRandomInteger() {
        return randomInteger;
    }

    public void setRandomInteger(int randomInteger) {
        this.randomInteger = randomInteger;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Get the person's full name.
     *
     * @return the person's full name
     */
    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * Get the person's initials.
     *
     * @return the person's initials
     */
    public String getInitials() {
        return firstName.substring(0, 1) + lastName.substring(0, 1);
    }
}