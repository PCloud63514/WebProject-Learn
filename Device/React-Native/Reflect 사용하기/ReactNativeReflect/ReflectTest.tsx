import React from 'react'



/**
 * 
 * @param target 타입
 * @param key 이름
 */
export function logType(target:any, key:string) {
    console.log(`target:${target}`)
    console.log(`key:${key}`)

    var t = Reflect.getMetadata("design:type", target, key)
    console.log(t)
}
// 파라미터 타입
export function logParametersType() {

}
// 반환 타입
export function logReturnType() {
    
}