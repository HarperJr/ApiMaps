package com.vlsu.maps.database.repository

import android.arch.persistence.db.SimpleSQLiteQuery
import com.vlsu.maps.database.dao.BaseDao
import com.vlsu.maps.database.mapper.BaseMapper

abstract class RepositoryImpl<Entity, Model, Id> : BaseRepository<Model, Id> {

    protected abstract val dao: BaseDao<Entity>

    protected abstract val mapper: BaseMapper<Model, Entity>

    protected val table by lazy(LazyThreadSafetyMode.NONE) {
        val className = dao::class.simpleName!!
        className.substring(0, className.length - "Dao_Impl".length)
    }

    private val idColumn = "id"

    override fun find(id: Id): Model? {
        val query = SimpleSQLiteQuery("SELECT * FROM $table WHERE $idColumn = ?", arrayOf(id as Any))
        return mapper.entityToModel(dao.getSingle(query))
    }

    override fun getAll(): List<Model>? {
        val query = SimpleSQLiteQuery("SELECT * FROM $table")
        return mapper.entityListToModelList(dao.getList(query))
    }

    override fun insert(model: Model) {
        dao.insert(mapper.modelToEntity(model))
    }

    override fun insert(models: List<Model>) {
        dao.insert(mapper.modelListToEntityList(models))
    }

    override fun update(model: Model) {
        dao.update(mapper.modelToEntity(model))
    }

    override fun delete(model: Model) {
        dao.delete(mapper.modelToEntity(model))
    }

    override fun delete(models: List<Model>) {
        dao.delete(mapper.modelListToEntityList(models))
    }
}