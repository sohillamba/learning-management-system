package com.lms.lms.mappers;

import com.lms.lms.dto.CourseDTO;
import com.lms.lms.entities.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    Course toCourse(CourseDTO courseDTO);
    CourseDTO toCourseDTO(Course course);
}
