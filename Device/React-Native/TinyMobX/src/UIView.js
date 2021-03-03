import React from 'react'
import { View, Text } from 'react-native'
import { observer } from 'mobx-react'
import Button from './components/Button'
import ViewModel from './ViewModel'

const UIView = observer(
class UIView extends React.Component {

    render() {
        return (
            <View style={{justifyContent: 'center', alignItems: 'center', flex:1}}>
                <View style={{flex:1, justifyContent: 'center', alignItems: 'center'}}>
                    <Text>A = {ViewModel.getA()}</Text>
                    <Text>B = {ViewModel.getB()}</Text>

                </View>
                <View style={{flex:1, justifyContent: 'space-around', alignItems: 'center'}}>
                    <View style={{flexDirection:'row', justifyContent: 'space-around', width:'100%'}}>
                        <Button text="A" onPress={() => ViewModel.pulsA()}/>
                        <Button text="B" onPress={() => ViewModel.pulsB()}/>
                    </View>
                    <View style={{}}>
                        <Button text="다음" onPress={() => this.props.navigation.navigate("B")}/>
                    </View>
                </View>
            </View>
        )
    }
}
)

export default UIView