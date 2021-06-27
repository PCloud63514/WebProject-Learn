import React from 'react';
import {
  SafeAreaView,
  Text,
  View,
} from 'react-native';

class T {

}

class ABC {
  color:string = 'red'

  constructor(num:number) {

  }

  @Deco
  fly():void {
    console.log('hihihi')
  }
}

function Deco(target:any, key:string) {
  console.log(`target:${target}`)
  console.log(`key:${key}`)
  Reflect.defineMetadata('secret', 123, target, key)
}

const secret = Reflect.getMetadata('secret', ABC.prototype, 'fly');
console.log(secret)

let me = {
  name:'pcloud',
  job:'programmer'
}

const proxiedMe = new Proxy(me, {
  get:function(target, property, a) {
    console.log("Hi Proxy")
    return target[property.valueOf()]
  }
})

console.log(proxiedMe.job)

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
