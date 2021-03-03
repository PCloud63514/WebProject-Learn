import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createStackNavigator } from '@react-navigation/stack'
import UIView from '../UIView'
import SumPage from '../screen/SumPage'
const stack = createStackNavigator()


const NavigationManager = (props) => {
    return (
        <NavigationContainer>
            <stack.Navigator initialRouteName={'A'}>
                <stack.Screen name={'A'} component={UIView}/>
                <stack.Screen name={'B'} component={SumPage}/>
            </stack.Navigator>
        </NavigationContainer>
    )
}

export default NavigationManager