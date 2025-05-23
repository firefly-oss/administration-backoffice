package com.vaadin.starter.business.backend.mapper.reports;

import com.vaadin.starter.business.backend.Report;
import com.vaadin.starter.business.backend.dto.reports.ReportDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Report and ReportDTO objects.
 */
@Component
public class ReportMapper {

    /**
     * Convert a Report entity to a ReportDTO.
     *
     * @param report the Report entity to convert
     * @return the corresponding ReportDTO
     */
    public ReportDTO toDto(Report report) {
        if (report == null) {
            return null;
        }
        
        return new ReportDTO(
            report.getId(),
            report.getName(),
            report.getDescription(),
            report.getStatus(),
            report.getCategory(),
            report.getDataSource(),
            report.getOutputFormat(),
            report.isIncludeCharts(),
            report.isScheduledDelivery(),
            report.getCreatedBy(),
            report.getLastModified()
        );
    }

    /**
     * Convert a ReportDTO to a Report entity.
     *
     * @param dto the ReportDTO to convert
     * @return the corresponding Report entity
     */
    public Report toEntity(ReportDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Report(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getCategory(),
            dto.getDataSource(),
            dto.getOutputFormat(),
            dto.isIncludeCharts(),
            dto.isScheduledDelivery(),
            dto.getCreatedBy(),
            dto.getLastModified()
        );
    }

    /**
     * Convert a collection of Report entities to a list of ReportDTOs.
     *
     * @param reports the collection of Report entities to convert
     * @return a list of corresponding ReportDTOs
     */
    public List<ReportDTO> toDtoList(Collection<Report> reports) {
        if (reports == null) {
            return List.of();
        }
        
        return reports.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of ReportDTOs to a list of Report entities.
     *
     * @param dtos the collection of ReportDTOs to convert
     * @return a list of corresponding Report entities
     */
    public List<Report> toEntityList(Collection<ReportDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}