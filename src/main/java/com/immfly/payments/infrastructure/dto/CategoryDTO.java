package com.immfly.payments.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Category")
public record CategoryDTO(
    @Schema(description = "Unique identifier of the category", example = "1") Long id,
    @Schema(description = "Name of the category", example = "Electronics") String name,
    @Schema(description = "Identifier of the parent category", example = "2", nullable = true) Long parentId
) { }
