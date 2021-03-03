import React from 'react'
import { View, Text } from 'react-native'
import { observer } from 'mobx-react'
import Button from '../components/Button'
import ViewModel from '../ViewModel'


class TestClass {
    _ViewModel = ViewModel
}

const testClass = new TestClass()

const SumPage = observer(
class SumPage extends React.Component {

    render() {
        return (
            <View style={{justifyContent: 'center', alignItems: 'center', flex:1}}>
                <View style={{flex:1, justifyContent: 'center', alignItems: 'center'}}>
                    <Text>A + B = {testClass._ViewModel.getA() + testClass._ViewModel.getB()}</Text>
                </View>
                <View style={{flex:1, justifyContent: 'space-around', alignItems: 'center'}}>
                    <View style={{flexDirection:'row', justifyContent: 'space-around', width:'100%'}}>
                        <Button text="A" onPress={() => testClass._ViewModel.pulsA()}/>
                        <Button text="B" onPress={() => testClass._ViewModel.pulsB()}/>
                    </View>
                    <View style={{}}>
                        <Button text="이전" onPress={() => this.props.navigation.goBack()}/>
                    </View>
                </View>
            </View>
        )
    }
}
)

export default SumPage