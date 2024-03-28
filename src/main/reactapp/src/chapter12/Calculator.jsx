import { useState } from "react";
import TemperatureInput from "./TemperatureInput";

function BoilingVerdict(props){
    if(props.celsius >= 100){
        return(<p>물이 끓고 있습니다.</p>)
    }
    return <p>물이 끓고 있지 않습니다.</p>
}

function toCelsius (fahrenheit){
    return((fahrenheit-32)*5)/9;
}

function toFahrenhit (celsius){
    return(celsius*9)/5 +32;
}

function tryConvert(Temperatur,convert){
    const input=parseFloat(Temperatur);
    if(Number.isNaN(input)){
        return '';
    }
    const output = convert(input);
    console.log(output);
    const rounded=Math.round(output *1000)/1000;
    console.log(rounded);
    return rounded.toString();
}

export default function Calculator(props){
    const [temperatur,setTemperatur]=useState("");
    const [scale ,setScale]=useState("c");

    const handleCelsiusChange = (temperatur)=>{
        setTemperatur(temperatur);
        setScale("c")
    }

    const handleFahrenhitChange=(temperatur)=>{
        setTemperatur(temperatur);
        setScale("f")
    }

    const celsius =
        scale ==="f" ? tryConvert(temperatur,toCelsius) : temperatur;
    const fahrenhit =
    scale ==="c" ? tryConvert(temperatur,toFahrenhit) : temperatur;

    return(<div>
        <TemperatureInput
            scale="c"
            temperatur={celsius}
            onTemperatureChange={handleCelsiusChange}
        />
        <TemperatureInput
            scale="f"
            temperatur={fahrenhit}
            onTemperatureChange={handleFahrenhitChange}
        />
        <BoilingVerdict celsius={parseFloat(celsius)}/>
    </div>)


}
