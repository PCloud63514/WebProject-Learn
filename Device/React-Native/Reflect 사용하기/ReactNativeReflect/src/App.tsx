import React, {} from 'react';
import {
  Text,
  View,
} from 'react-native';

import Duck from '@components/Duck'

let instanceDuck:Duck = Reflect.getMetadata("Instance", Duck)
console.log(instanceDuck)

instanceDuck.aoo.hello()


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
