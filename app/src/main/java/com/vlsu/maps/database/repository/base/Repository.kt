package com.vlsu.maps.database.repository.base

interface Repository<Model, Id> {

    fun find(id: Id): Model?

    fun getAll(): List<Model>?

    fun insert(model: Model)

    fun insert(models: List<Model>)

    fun update(model: Model)

    fun delete(model: Model)

    fun delete(models: List<Model>)
}