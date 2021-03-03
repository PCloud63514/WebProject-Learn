import React from 'react';
import {View, Text, TouchableHighlight} from 'react-native'

const Button = ({text, onPress}) => {
    const ImplButton = (
        <TouchableHighlight onPress={onPress}>
            <View style={{borderWidth:1, borderColor:'black', borderRadius:5, minWidth:150, minHeight:50
                        ,justifyContent: 'center', alignItems: 'center'}}>
                <Text style={{fontSize:16}}>{text}</Text>
            </View>
        </TouchableHighlight>
    )
    return ImplButton
}

export default React.memo(Button)