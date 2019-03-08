package com.vlsu.maps.di

class Dagger {
    companion object {
        private lateinit var appComponentInstance: AppComponent
        private lateinit var databaseComponentInstance: DatabaseComponent
        private lateinit var dialogComponentInstance: DialogComponent

        var appComponent: AppComponent
            get() = appComponentInstance
            set(value) {
                appComponentInstance = value
            }
        var databaseComponent: DatabaseComponent
            get() = databaseComponentInstance
            set(value) {
                databaseComponentInstance = value
            }
        var dialogComponent: DialogComponent
            get() = dialogComponentInstance
            set(value) {
                dialogComponentInstance = value
            }
    }
}