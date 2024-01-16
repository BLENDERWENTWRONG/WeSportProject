package com.example.we_sport.utils;

import com.gluonhq.maps.MapLayer;
import com.gluonhq.maps.MapPoint;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CostumMapLayer extends MapLayer {

    private final Node marker ;
    private  Point2D point2D;
    private MapPoint mapPointCore;

    public  CostumMapLayer (MapPoint mapPoint) {
        marker = new Circle(5, Color.BLUE);
        getChildren().add(marker);
        this.mapPointCore = mapPoint;
    }

    public CostumMapLayer(Node marker) {
        this.marker = marker;
    }

    @Override
    protected void layoutLayer() {
        Point2D point2D = getMapPoint(mapPointCore.getLatitude(), mapPointCore.getLongitude());

        this.marker.setTranslateX(point2D.getX());
        this.marker.setTranslateY(point2D.getY());
    }
}
