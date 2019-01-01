package com.vlsu.maps.dagger

class Dagger {
    companion object {
        private var component: AppComponent? = null

        fun setComponent(component: AppComponent) {
            this.component = component
        }

        fun getComponent() = component
    }
}