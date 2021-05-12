// var arr = [
//   {"id":"1001","name":"值1","value":"111"},
//   {"id":"1001","name":"值1","value":"11111"},
//   {"id":"1002","name":"值2","value":"25462"},
//   {"id":"1002","name":"值2","value":"23131"},
//   {"id":"1002","name":"值2","value":"2315432"},
//   {"id":"1003","name":"值3","value":"333333"}
// ];

// var map = {},
//   dest = [];
// for(var i = 0; i < arr.length; i++){ //arr 为需要合并的数组
//   var ai = arr[i];
//   if(!map[ai.id]){   // 如果 map 对象中不存在数组中的key---id
//       dest.push({
//           id: ai.id,
//           value: [ai.value]
//       });
//       map[ai.id] = ai.value;
//   }else{
//       for(var j = 0; j < dest.length; j++){
//           var dj = dest[j];
//           if(dj.id == ai.id){
//               dj.value.push(ai.value);
//               break;
//           }
//       }
//   }
// }

// console.log(dest);
var arr = [
  {"id":"1001","name":"值1","value":"111"},
  {"id":"1001","name":"值1","value":"11111"},
  {"id":"1002","name":"值2","value":"25462"},
  {"id":"1002","name":"值2","value":"23131"},
  {"id":"1002","name":"值2","value":"2315432"},
  {"id":"1003","name":"值3","value":"333333"}
];

var map = {},
  dest = [];
for(var i = 0; i < arr.length; i++){ //arr 为需要合并的数组
  var ai = arr[i];
  if(!map[ai.name]){   // 如果 map 对象中不存在数组中的key---id
      dest.push({
          id: [ai.id],
          name: ai.name,
          value: [ai.value]

      });
      map[ai.name] = ai;
  }else{
      for(var j = 0; j < dest.length; j++){
          var dj = dest[j];
          if(dj.name === ai.name){
              dj.value.push(ai.value);
              dj.id.push(ai.id);
              break;
          }
      }
  }
}

console.log(dest);
