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


package com.vaadin.starter.business.backend;

import java.time.LocalDate;
import java.util.UUID;

public class VersionInfo {

    private UUID id;
    private String versionNumber;
    private String description;
    private String status;
    private String environment;
    private LocalDate releaseDate;
    private String releasedBy;
    private String changeLog;

    public VersionInfo(UUID id, String versionNumber, String description, String status, 
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

    public UUID getId() {
        return id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getEnvironment() {
        return environment;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getReleasedBy() {
        return releasedBy;
    }

    public String getChangeLog() {
        return changeLog;
    }
}