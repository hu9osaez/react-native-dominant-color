
# react-native-dominant-color
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
Extract the dominant colors of an image (Just for Android).

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
import React, { Component } from 'react';
import { StyleSheet, View } from 'react-native';
import { colorsFromUrl } from 'react-native-dominant-color';

const imageUrl = 'https://source.unsplash.com/random/800x600';

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center'
    },
    image: {
        width: 300,
        height: 300,
        borderRadius: 10
    }
});

class Example extends Component {
    constructor() {
        super();
        this.state = {
            color: '#ffffff',
        };
    }

    componentWillMount() {
        let self = this;
        colorsFromUrl(imageUrl, (err, colors) => {
            if(!err) {
                self.setState({ color: colors.averageColor });
            }
        });
    }

    render() {
        return (
            <View style={[styles.container, {backgroundColor: this.state.color }]}>
                <Image style={styles.image} source={{ uri: imageUrl}} />
            </View>
        );
    }
}

```

## API
#### Methods
* `colorsFromUrl(imageUrl, callback)`: Callback returns an object with the prominent colors from the image. Object properties are `averageColor`, `dominantColor`,  `vibrantColor`, `darkVibrantColor` and `lightVibrantColor`. If some color doesn't exist will return `#CCCCCC`.