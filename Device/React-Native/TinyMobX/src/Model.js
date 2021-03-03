import { decorate, obserable, makeAutoObservable } from 'mobx'

class Model {
    NumA = 10
    NumB = 10
    constructor() {
        makeAutoObservable(this)
    }
}

export default Model