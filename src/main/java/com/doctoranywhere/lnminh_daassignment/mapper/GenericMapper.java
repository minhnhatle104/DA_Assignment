package com.doctoranywhere.lnminh_daassignment.mapper;

public interface GenericMapper<T, DTO> {
    T toEntity(DTO object);
    DTO toDTO(T object);
}
