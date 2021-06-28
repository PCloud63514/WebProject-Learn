import React, {} from 'react';
import {
  SafeAreaView,
  Text,
  TouchableHighlightBase,
  View,
} from 'react-native';
import { Colors } from 'react-native/Libraries/NewAppScreen';


// design:type: 데코레이터가 적용된 객체의 타입
// design:paramtypes: 데코레이터가 적용된 곳의 파라미터의 타입을 배열로 반환.
// design:returntype: 데코레이터가 적용된 함수가 반환해야 하는 return 타입을 반환

/**
 * 생성자 데코레이터
 * @param constructor Function
 */
// function ConstructorDecorator(constructor:Function) {
//   Reflect.getMetadata("design:paramtypes", constructor) // 생성자에 필요한 매개변수의 타입을 알 수 있다.

//   let bar = new Bar()
//   let coo = new Coo()
//   let too = new Too()
//   let msg = "하이"
//   /**
//    * Object.create vs Reflect.construct
//    * 두 함수의 결과는 instance의 생성이므로 동일하지만 과정에 차이가 존재한다.
//    * Object는 create(Funtion) 후에 Function.prototype.apply()를 사용해 생성자에 필요한 매개변수를 주입하는 과정으로 진행된다.
//    * 이때 객체 생성에 new 키워드가 호출되지 않으므로 new.target 연산자가 undefined를 가리키게 된다.
//    * 
//    * Reflect.construct()를 호출하면 newTarget이 존재하면 new.target연산자가 newTarget 존재하지 않는다면 target을 가리킨다.
//    */
//   let instance = Reflect.construct(constructor, [bar, coo, too, msg]) // new 연산자와 동일하게 동작한다. args는 순서를 지켜야한다.

//   /** prototype과 name으로 찾는 방법 */
//   Reflect.defineMetadata("Instance", instance, constructor.prototype, constructor.name) //Metadata에 Instance를 등록한다.
//   Reflect.getMetadata("Instance", constructor.prototype, constructor.name) // Metadata에서 Instance를 가져온다.
  
//   /** constructor로 찾는 방법 */
//   Reflect.defineMetadata("Instance", instance, constructor) //Metadata에 Instance를 등록한다.
//   Reflect.getMetadata("Instance", constructor) // Metadata에서 Instance를 가져온다.
// }


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
