// import Model from './Model'
import { decorate, obserable, makeAutoObservable } from 'mobx'
class ViewModel {
    // model = new Model()
    NumA = 10
    NumB = 10
    constructor() {
        makeAutoObservable(this)
    }
    
    getA() {
        console.log(this.NumA)
        return this.NumA
    }

    getB() {
        return this.NumB
    }

    pulsA() {
        this.NumA++
    }

    pulsB() {
        this.NumB++
    }
}

export default new ViewModel()