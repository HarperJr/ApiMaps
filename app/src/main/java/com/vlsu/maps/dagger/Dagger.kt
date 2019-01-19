package com.vlsu.maps.dagger

class Dagger {
    companion object {
        private lateinit var component: AppComponent

        fun setComponent(component: AppComponent) {
            this.component = component
        }

        fun getComponent() = component
    }
}