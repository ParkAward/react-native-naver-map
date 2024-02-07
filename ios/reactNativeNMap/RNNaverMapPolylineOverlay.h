//
//  RNNaverMapPolylineOverlay.h
//
//  Created by flask on 18/04/2019.
//  Copyright © 2019 flask. All rights reserved.
//

#import <React/RCTBridge.h>
#import <React/RCTComponent.h>
#import <NMapsMap/NMFPolylineOverlay.h>

#import "RCTConvert+NMFMapView.h"

@interface RNNaverMapPolylineOverlay : UIView

@property (nonatomic, weak) RCTBridge *bridge;
@property (nonatomic, strong) NMFPolylineOverlay *realOverlay;
@property (nonatomic) NMFOverlayLineCap capType;

@property (nonatomic, assign) NSArray<NMGLatLng *> *coordinates;
@property (nonatomic, assign) CGFloat strokeWidth;
@property (nonatomic, strong) UIColor *strokeColor;
@property (nonatomic, copy) RCTDirectEventBlock onClick;

@property (nonatomic, assign) CGFloat zIndex;
@property (nonatomic, assign) CGFloat globalZIndex;

@end
