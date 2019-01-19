package com.vlsu.maps.database.mapper

interface BaseMapper<Model, Entity> {

    fun entityToModel(entity: Entity): Model

    fun modelToEntity(model: Model): Entity

    fun entityListToModelList(entities: List<Entity>): List<Model>

    fun modelListToEntityList(models: List<Model>): List<Entity>
}