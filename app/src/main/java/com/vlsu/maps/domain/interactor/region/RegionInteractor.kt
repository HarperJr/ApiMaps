package com.vlsu.maps.domain.interactor.region

import com.google.gson.Gson
import com.vlsu.maps.data.database.repository.RegionRepository
import com.vlsu.maps.data.database.transaction.DbTransaction
import com.vlsu.maps.domain.interactor.AssetManager
import com.vlsu.maps.domain.model.Region
import com.vlsu.maps.domain.rx.AppSchedulerProvider
import com.vlsu.maps.presentation.fragment.map.interactor.Constants
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class RegionInteractor @Inject constructor(
    private val assetManager: AssetManager,
    private val dbTransaction: DbTransaction,
    private val regionRepository: RegionRepository,
    private val gson: Gson
) {
    fun loadRegions(): Completable {
        return Single
            .fromCallable {
                assetManager.getFileContent(Constants.REGIONS_FILE_NAME)
            }
            .map { gson.fromJson(it, RegionsList::class.java) }
            .flatMapCompletable {
                Completable.fromAction {
                    dbTransaction.runInTransaction {
                        regionRepository.insert(it.regions)
                    }
                }
            }
    }

    fun regions(): Single<List<Region>> {
        return Single
            .fromCallable {
                regionRepository.getByOrder()
            }
            .observeOn(AppSchedulerProvider.db())
    }

    fun findRegionByName(name: String): Single<Region> {
        return Single.fromCallable {
            regionRepository.findByName(name)
        }
    }

    class RegionsList(var regions: List<Region>)
}