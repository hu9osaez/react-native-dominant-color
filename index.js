import { NativeModules } from 'react-native';

const { RNDominantColor } = NativeModules;

export const colorsFromUrl = (url, callback) => {
  return RNDominantColor.colorsFromUrl(url, callback);
}
