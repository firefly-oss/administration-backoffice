package com.vaadin.starter.business.backend.mapper.riskandcompliance;

import com.vaadin.starter.business.dummy.Person;
import com.vaadin.starter.business.backend.dto.riskandcompliance.PersonDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Person and PersonDTO objects.
 */
@Component
public class PersonMapper {

    /**
     * Convert a Person entity to a PersonDTO.
     *
     * @param person the Person entity to convert
     * @return the corresponding PersonDTO
     */
    public PersonDTO toDto(Person person) {
        if (person == null) {
            return null;
        }
        
        return new PersonDTO(
            person.getId(),
            person.getFirstName(),
            person.getLastName(),
            person.getRole(),
            person.getEmail(),
            person.getRandomBoolean(),
            person.getRandomInteger(),
            person.getLastModified()
        );
    }

    /**
     * Convert a PersonDTO to a Person entity.
     *
     * @param dto the PersonDTO to convert
     * @return the corresponding Person entity
     */
    public Person toEntity(PersonDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Person(
            dto.getId(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getRole(),
            dto.getEmail(),
            dto.isRandomBoolean(),
            dto.getRandomInteger(),
            dto.getLastModified()
        );
    }

    /**
     * Convert a collection of Person entities to a list of PersonDTOs.
     *
     * @param persons the collection of Person entities to convert
     * @return a list of corresponding PersonDTOs
     */
    public List<PersonDTO> toDtoList(Collection<Person> persons) {
        if (persons == null) {
            return List.of();
        }
        
        return persons.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of PersonDTOs to a list of Person entities.
     *
     * @param dtos the collection of PersonDTOs to convert
     * @return a list of corresponding Person entities
     */
    public List<Person> toEntityList(Collection<PersonDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}