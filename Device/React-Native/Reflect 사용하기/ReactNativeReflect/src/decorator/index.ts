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
    Reflect.defineMetadata("Component", instance, constructor)
}