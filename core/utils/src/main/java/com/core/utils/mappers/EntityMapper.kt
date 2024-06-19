package com.core.utils.mappers

interface EntityMapper {

  fun <Entity, Response> toResponse(model: Entity): Response

  fun <Entity, Response> toEntity(model: Response): Entity
}