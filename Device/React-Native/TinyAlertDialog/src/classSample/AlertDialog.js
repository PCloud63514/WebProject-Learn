import React from 'react'
import { Modal, View, TouchableNativeFeedback } from 'react-native'

export default props => {
    const {visible, close, children} = props

    const wrapChildren = children != undefined ? React.cloneElement(children) : undefined
    return (
        <Modal
            visible={visible}
            transparent={true}
            animationType={'slide'}>
            <View 
                style={{flex:1,
                    backgroundColor: "rgba(0,0,0,0.3)",
                    justifyContent:"center",
                    alignContent:"center",
                    alignItems:"center"}}>
            <TouchableNativeFeedback onPress={()=> close()}>
                <View style={{width:25, height:25, backgroundColor:'red'}}>

                </View>
            </TouchableNativeFeedback>
                {wrapChildren}
            </View>
            
        </Modal>
    )
}