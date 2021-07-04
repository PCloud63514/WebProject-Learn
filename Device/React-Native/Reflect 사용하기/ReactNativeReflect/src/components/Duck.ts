import { ConstructorDecorator, Version } from '@decorator/index'
import Aoo from '@components/Aoo'
import Boo from '@components/Boo'
import Coo from '@components/Coo'


@ConstructorDecorator
export default class Duck {
    constructor(public aoo:Aoo, public boo:Boo, public coo:Coo) {
    }
}