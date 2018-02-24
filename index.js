import { NativeModules } from 'react-native';

const { RNDominantColor } = NativeModules;

export const colorsFromUrl = (url) => {
  return RNDominantColor.colorsFromUrl(url);
}