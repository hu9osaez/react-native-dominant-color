import { NativeModules } from 'react-native';

const { RNDominantColor } = NativeModules;

export const getDominantColor = (url, callback) => {
  return RNDominantColor.getDominantColor(url, callback);
}
