import React, {} from 'react';
import {
  SafeAreaView,
  Text,
  TouchableHighlightBase,
  View,
} from 'react-native';





// function ParameterDecorator<T>(target:any, propertyKey:string, descriptor:TypedPropertyDescriptor<T>) {
//   let set = descriptor.set;
//   descriptor.set = function(value:T) {
//     let type = Reflect.getMetadata("design:type", target, propertyKey)
//     if (!(value instanceof type)) {
//       throw new TypeError("Invalid type.");
//     }
//     set.call(target, value);
//   }
// }

// class A {
//   name:string = "PCloud"
//   age:number = 26
// }

// /**
//  * Object 클래스를 다루는 방법
//  * @param constructor 
//  */
// console.log(Object.getOwnPropertyNames(new A())) // 멤버필드 변수 명
// console.log(Object.keys(new A())) // 멤버필드 변수 명
// console.log(Object.getPrototypeOf(new A())) // 멤버필드 변수 명 및 값



// function Service(constructor:Function) {
//   let data = Reflect.getMetadata("design:paramtypes", constructor)
//   console.log(data)
// }

const Service = (): ClassDecorator => {
  return target => {
    console.log("Service")
    let args = Reflect.getMetadata('design:paramtypes', target)
    Object.keys(args).forEach((key)=> {
      let arg = Reflect.getMetadata("Component", args[key].prototype ,key)
      console.log(arg)
      args[key] = arg
    })
  }
}


function Component(constructor:Function) {
  console.log("HI!:", constructor.name)
  Reflect.defineMetadata('Component', Object.create(constructor.prototype), constructor.prototype, constructor.name)
}

@Component
class Bar {
  hello() {
    console.log("Hello!!!")
  }
}

@Component
class Coo {
  hello() {
    console.log("Talk")
  }
}

@Component
class Too {
  hello() {
    console.log("Too")
  }
}

@Service()
// @Reflect.metadata('design:paramtypes', {Bar, Coo})
class Foo {
  bar:Bar
  coo:Coo
  too:Too
  msg:string
  constructor(bar: Bar, coo: Coo, too:Too, msg:string) {
    this.bar = bar
    this.coo = coo
    this.too = too
    this.msg = msg
  } 
}
// let data = Reflect.getMetadata("Component", Bar.prototype, Bar.name)

let data = Reflect.getMetadata("Component", Foo.prototype, Foo.name)

// console.log(data)

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
