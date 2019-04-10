package com.vlsu.maps.domain.interactor.region

import com.google.gson.Gson
import com.vlsu.maps.data.database.repository.RegionRepository
import com.vlsu.maps.data.database.transaction.DbTransaction
import com.vlsu.maps.domain.interactor.AssetManager
import com.vlsu.maps.domain.model.Region
import com.vlsu.maps.domain.interactor.offlinemap.Constants
import com.vlsu.maps.presentation.fragment.offlinemap.adapter.RegionItem
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RegionsProvider @Inject constructor(
    private val assetManager: AssetManager,
    private val dbTransaction: DbTransaction,
    private val regionRepository: RegionRepository,
    private val gson: Gson
) {
    private fun loadRegions(): Completable {
        return Single
            .fromCallable {
                assetManager.getFileContent(Constants.REGIONS_FILE_NAME)
            }
            .map { gson.fromJson(it, RegionsList::class.java) }
            .flatMapCompletable {
                Completable.fromAction {
                    dbTransaction.runInTransaction {
                        regionRepository.dropRegions()
                        regionRepository.insert(it.regions)
                    }
                }
            }
    }

    fun regions(): Single<List<RegionItem>> {
        return loadRegions()
            .andThen(
                Single.fromCallable { regionRepository.getByOrder() }
            )
            .map { mapToItems(it) }
    }

    fun findRegionByName(name: String): Single<Region> {
        return Single.fromCallable {
            regionRepository.findByName(name)
        }
    }

    private fun mapToItems(regions: List<Region>): List<RegionItem> {
        return regions.map {
            RegionItem(
                id = it.id,
                name = it.name
            )
        }
    }

    class RegionsList(var regions: List<Region>)
}