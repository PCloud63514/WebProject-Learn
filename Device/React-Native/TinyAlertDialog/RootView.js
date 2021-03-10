import React from 'react';
import { SafeAreaView, View, Text, TouchableNativeFeedback } from 'react-native'
import {alertContext} from './src/classSample/AlertDialogProvider'


export class ClassRootView extends React.PureComponent {
  static contextType = alertContext
  
  constructor(props) {
    super(props)
  }

  render() {
    const alert = this.context

    return(
    <SafeAreaView style={{flex:1, backgroundColor:'black', justifyContent: 'center', alignItems: 'center'}}>
        <Text style={{color:'white'}}> Alert Dialog Sample Page</Text>
        <TouchableNativeFeedback onPress={()=>{alert(true, (<View style={{width:'40%', height:'40%', backgroundColor:'pink'}}/>))}}>
            <View style={{width:50, height:50, backgroundColor:'orange'}}/>
        </TouchableNativeFeedback>
    </SafeAreaView>
    )
  }
}


/**Funtion Component */
// const RootView = () => {
//   const alert = useAlert()
    
//     return (
//       <SafeAreaView style={{flex:1, backgroundColor:'black', justifyContent: 'center', alignItems: 'center'}}>
//           <Text style={{color:'white'}}> Alert Dialog Sample Page</Text>
//           <TouchableNativeFeedback onPress={()=>{alert(true, (<View style={{width:'40%', height:'40%', backgroundColor:'pink'}}/>))}}>
//               <View style={{width:50, height:50, backgroundColor:'orange'}}/>
//           </TouchableNativeFeedback>
//       </SafeAreaView>
//     )
//   }
  
//   export default React.memo(RootView)