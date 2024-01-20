export var temperature = 0;

export function sampleFunction()
{
let serviceUuid =  '8b7a8a01-cba3-4a61-85e8-a39c8c1f573c';
var charac ;


  if (serviceUuid.startsWith('0x')) {
    serviceUuid = parseInt(serviceUuid);
}

  let characteristicUuid = '57628d53-9795-4d4d-a382-cd899e022951';
  if (characteristicUuid.startsWith('0x')) {
    characteristicUuid = parseInt(characteristicUuid);
  }

  console.log('Requesting Bluetooth Device...');
  navigator.bluetooth.requestDevice({acceptAllDevices: true,
                                                                                                                                     optionalServices: [serviceUuid]})
  .then(device => {
    console.log('Connecting to GATT Server...');
    return device.gatt.connect();
  })
  .then(server => {
    console.log('Getting Service...');
    return server.getPrimaryService(serviceUuid);
  })
  .then(service => {
    console.log('Getting Characteristics...');
    if (characteristicUuid) {
      // Get all characteristics that match this UUID.
             //    thisChar = service.getCharacteristic(characteristicUuid); 
      return service.getCharacteristic(characteristicUuid);
    }
    // Get all characteristics.
    return service.getCharacteristics();
  })

  .then(characteristic => {
                 charac = characteristic
                 return characteristic.readValue(); 
})

.then(value => {
   console.log(`Value Read is ${value.getUint8(0)}`);
   let val;
   if (value.getUint8(0)==49)
               {
                                val = 48;
                                 console.log(`Value to set = 48`);
               }
               else
               {
                                val = 49;
                                console.log(`Value to set = 49`);
               }
   const boolToUtf8 = Uint8Array.of(val);
   charac.writeValue(boolToUtf8);
})

.catch(error => {
    console.log('Argh! ' + error);
  });


}

export function getTemperature()
{
let serviceUuid =  '8b7a8a01-cba3-4a61-85e8-a39c8c1f573c';
var charac 

// Create a buffer
var buf = new ArrayBuffer(4);
// Create a data view of it
var view = new DataView(buf);

  if (serviceUuid.startsWith('0x')) {
    serviceUuid = parseInt(serviceUuid);
}

  let characteristicUuid = '015a7e62-3912-4930-b4a8-5dd4ee7420d8';
  if (characteristicUuid.startsWith('0x')) {
    characteristicUuid = parseInt(characteristicUuid);
  }

  console.log('Requesting Bluetooth Device...');
  navigator.bluetooth.requestDevice({acceptAllDevices: true,
                                                                                                                                     optionalServices: [serviceUuid]})
  .then(device => {
    console.log('Connecting to GATT Server...');
    return device.gatt.connect();
  })
  .then(server => {
    console.log('Getting Service...');
    return server.getPrimaryService(serviceUuid);
  })
  .then(service => {
    console.log('Getting Characteristics...');
    if (characteristicUuid) {
      // Get all characteristics that match this UUID.
             //    thisChar = service.getCharacteristic(characteristicUuid); 
      return service.getCharacteristic(characteristicUuid);
    }
    // Get all characteristics.
    return service.getCharacteristics();
  })

  .then(characteristic => {
                 charac = characteristic
                 return characteristic.readValue(); 
})

.then(value => {
   console.log(`Value Read is ${value.getUint8(3)}  ${value.getUint8(2)}  ${value.getUint8(1)}  ${value.getUint8(0)}`);
   view.setUint8(0, value.getUint8(3));
   view.setUint8(1, value.getUint8(2));
   view.setUint8(2, value.getUint8(1));
   view.setUint8(3, value.getUint8(0));


// Read the bits as a float; note that by doing this, we're implicitly
// converting it from a 32-bit float into JavaScript's native 64-bit double
var num = view.getFloat32(0);

temperature = num;

// Done
console.log(num);

return num

})
.catch(error => {
    console.log('Argh! ' + error);
  });


}