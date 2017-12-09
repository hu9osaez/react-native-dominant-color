
# react-native-dominant-color

## Getting started

`$ npm install react-native-dominant-color --save`

### Mostly automatic installation

`$ react-native link react-native-dominant-color`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import cl.hasaezs.rndominantcolor.RNDominantColorPackage;` to the imports at the top of the file
  - Add `new RNDominantColorPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-dominant-color'
  	project(':react-native-dominant-color').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-dominant-color/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-dominant-color')
  	```


## Usage
```javascript
import RNDominantColor from 'react-native-dominant-color';

// TODO: What to do with the module?
RNDominantColor;
```
  