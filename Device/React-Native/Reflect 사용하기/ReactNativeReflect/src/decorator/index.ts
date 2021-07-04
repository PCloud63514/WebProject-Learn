export function Component(constructor:Function) {
    // instance 생성이라 함수호출은 잘 된다.
    // 문제는 instance의 name이나 prototype이 undefined가 되어 찾을 수가 없다
    // 직접 넣어주면 되기는한다..
    let instance = Reflect.construct(constructor, [])
    instance.name = constructor.name
    instance.prototype = constructor.prototype
    // 인자값인 prototype을 이용해서 새 객체를 만듬
    // 단순히 생성보단 상속에 활용하기 좋다.
    let instance2 = Object.create(constructor.prototype)
    // Reflect.defineMetadata("Component", constructor, constructor.name)
    Reflect.defineMetadata("Instance", instance, constructor)
}



// design:type: 데코레이터가 적용된 객체의 타입
// design:paramtypes: 데코레이터가 적용된 곳의 파라미터의 타입을 배열로 반환.
// design:returntype: 데코레이터가 적용된 함수가 반환해야 하는 return 타입을 반환

/**
 * 생성자 데코레이터
 * @param constructor Function
 */
export function ConstructorDecorator(constructor:Function) {
    // 생성자에 필요한 매개변수의 타입을 알 수 있다.
    let params = Reflect.getMetadata("design:paramtypes", constructor) 


    Object.keys(params).forEach((key) => {
        let param = Reflect.getMetadata("Instance", params[key])
        params[key] = param
    })
    let newInstance = Reflect.construct(constructor, params)
    newInstance.name = constructor.name
    newInstance.prototype = constructor.prototype
    Reflect.defineMetadata("Instance", newInstance, constructor)
}


export function Version(version:number) {
    return {
        
    }
}