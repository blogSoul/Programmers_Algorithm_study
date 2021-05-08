# 📕 Solution

```javascript
function solution(array, commands) {
    var size = commands.length;
    var answer = new Array(size);
    for(var i = 0; i < size; i++){
        var arr = array.slice(commands[i][0] - 1, commands[i][1]).sort((a, b) => a - b);
        answer[i] = arr[commands[i][2] - 1];
    }
    return answer;
}
```

## ❌ fail

```javaa

```
