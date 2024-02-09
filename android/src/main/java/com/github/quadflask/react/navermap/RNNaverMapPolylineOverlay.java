package com.github.quadflask.react.navermap;

import android.content.Context;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.PolylineOverlay;

import java.util.List;

public class RNNaverMapPolylineOverlay extends ClickableRNNaverMapFeature<PolylineOverlay> {
    public RNNaverMapPolylineOverlay(EventEmittable emitter, Context context) {
        super(emitter, context);
        feature = new PolylineOverlay();
    }



    public void setCoords(List<LatLng> coords) {
        feature.setCoords(coords);
    }

    public void setLineWidth(float widthInScreenPx) {
        feature.setWidth(Math.round(widthInScreenPx));
    }

    public void setLineColor(int color) {
        feature.setColor(color);
    }

    public void setCapType(int value) {
        if(value == 1){
            feature.setCapType(PolylineOverlay.LineCap.Round);
        } else if (value == 2) {
            feature.setCapType(PolylineOverlay.LineCap.Square);
        }else {
            feature.setCapType(PolylineOverlay.LineCap.Butt);
        }
    }

    public void setJoinType(int value) {
        if(value == 1){
            feature.setJoinType(PolylineOverlay.LineJoin.Round);
        }else if(value == 2){
            feature.setJoinType(PolylineOverlay.LineJoin.Bevel);
        }else{
            feature.setJoinType(PolylineOverlay.LineJoin.Miter);
        }
    }

    public void setPattern(int[] pattern) {
        feature.setPattern(pattern);
    }

    public void setZIndex(int zIndex){
        feature.setZIndex(zIndex);
    }

    public void setGlobalZIndex(int globalZIndex){
        feature.setGlobalZIndex(globalZIndex);
    }
}
