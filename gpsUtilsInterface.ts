export declare interface GpsUtilInterface {
  isDeviceGPSOn: () => Promise<boolean>;
  checkDeviceGPS: () => Promise<void>;
}

/**
 * Only Android Device
 */
export const GpsUtils: GpsUtilInterface =
  require("react-native").NativeModules.GpsUtilsModule;
