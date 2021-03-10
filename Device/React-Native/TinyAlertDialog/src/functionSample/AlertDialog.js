import React from 'react'
import { Modal, View } from 'react-native'

export default props => {
    const {visible, close, children} = props

    return (
        <Modal
            visible={visible}
            transparent={true}
            animationType={'slide'}>
            <View style={{flex:1,
                    backgroundColor: "rgba(0,0,0,0.3)",
                    justifyContent:"center",
                    alignContent:"center",
                    alignItems:"center"}}>
                {children}
            </View>
        </Modal>
    )
}