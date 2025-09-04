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


package com.vaadin.starter.business.backend.dto.admintools;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Data Transfer Object for VersionInfo.
 */
public class VersionInfoDTO {

    private UUID id;
    private String versionNumber;
    private String description;
    private String status;
    private String environment;
    private LocalDate releaseDate;
    private String releasedBy;
    private String changeLog;

    /**
     * Default constructor.
     */
    public VersionInfoDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id            the version ID
     * @param versionNumber the version number
     * @param description   the description
     * @param status        the status
     * @param environment   the environment
     * @param releaseDate   the release date
     * @param releasedBy    the person who released it
     * @param changeLog     the change log
     */
    public VersionInfoDTO(UUID id, String versionNumber, String description, String status,
                        String environment, LocalDate releaseDate, String releasedBy, String changeLog) {
        this.id = id;
        this.versionNumber = versionNumber;
        this.description = description;
        this.status = status;
        this.environment = environment;
        this.releaseDate = releaseDate;
        this.releasedBy = releasedBy;
        this.changeLog = changeLog;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleasedBy() {
        return releasedBy;
    }

    public void setReleasedBy(String releasedBy) {
        this.releasedBy = releasedBy;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }
}