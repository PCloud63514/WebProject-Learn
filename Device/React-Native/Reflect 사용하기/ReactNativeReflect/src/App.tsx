import React, {} from 'react';
import {
  SafeAreaView,
  Text,
  TouchableHighlightBase,
  View,
} from 'react-native';

import Aoo from 'Component/Aoo'
import Boo from 'Component/Boo'
import Coo from 'Component/Coo'

let instnace = Reflect.getMetadata("Component", Aoo)

console.log(instnace)

class App extends React.Component {

  componentDidMount() {
  }

  render() {
    return (<View style={{justifyContent:'center', flex:1, alignItems:'center'}}>
      <Text>undefined 멈춰!!!</Text>
    </View>)
  }
}

export default App;
