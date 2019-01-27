package com.vlsu.maps.ui.activity.main.fragment.map


import android.os.Bundle
import android.view.*
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState
import com.vlsu.maps.R
import com.vlsu.maps.dagger.Dagger
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapPresenter
import com.vlsu.maps.ui.activity.main.fragment.map.mvp.MapView
import kotlinx.android.synthetic.main.content_control.*
import kotlinx.android.synthetic.main.fragment_map.*
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint


class MapFragment : MvpViewStateFragment<MapView, MapPresenter>(), MapView {

    private val component = Dagger.getComponent().mapComponent()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        layersButton.setOnClickListener { presenter.onLayersButtonClicked() }
        zoomInButton.setOnClickListener { presenter.onZoomInButtonClicked() }
        zoomOutButton.setOnClickListener { presenter.onZoomOutButtonClicked() }
        locationButton.setOnClickListener { presenter.onLocationButtonClicked() }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(map) {
            isClickable = true
            isTilesScaledToDpi = true
            setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE)
            setMultiTouchControls(true)
            setOnGenericMotionListener { _, event ->
                if (event.source and InputDevice.SOURCE_CLASS_POINTER != 0) {
                    when (event.action) {
                        MotionEvent.ACTION_SCROLL -> {
                            val scrollLocation = map.projection.fromPixels(event.x.toInt(), event.y.toInt())
                            if (event.getAxisValue(MotionEvent.AXIS_VSCROLL) < 0.0f) {
                                map.controller.zoomOut()
                            } else {
                                map.controller.zoomIn()
                            }
                            map.controller.animateTo(scrollLocation)
                        }
                    }
                    return@setOnGenericMotionListener true
                }
                return@setOnGenericMotionListener false
            }
        }
    }

    override fun zoomIn() {
        map.controller.zoomIn()
    }

    override fun zoomOut() {
        map.controller.zoomOut()
    }

    override fun showLayersDialog() {
        //TODO Layers dialog
    }

    override fun setOriginLocation(location: GeoPoint) {
        map.controller.animateTo(location)
    }

    override fun createViewState(): ViewState<MapView> {
        return component.mapViewState()
    }

    override fun onNewViewStateInstance() {

    }

    override fun createPresenter(): MapPresenter {
        return component.mapPresenter()
    }

}
