import React from 'react';
import {
  SafeAreaView,
  Text,
  View,
} from 'react-native';

function Type(type) { return Reflect.metadata("design:type", type); }

function logType(target:any, key:string) {
  var t = Reflect.getMetadata("design:type", target, key)
  console.log(`${key} type: ${t}`)
}

function logType2(...types) {
  return (target:any, propertyKey:any) => {
    { Reflect.defineMetadata("design:paramtypes", types, target, propertyKey); }
  }
}

class AAA {

}

class Demo {
  @Type(AAA)
  test:AAA
  constructor() {
    this.test = new AAA()
  }
}

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
