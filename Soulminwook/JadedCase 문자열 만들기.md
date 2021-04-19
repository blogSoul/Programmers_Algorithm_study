# 📕 Solution

```java
class Solution {
    public String solution(String s) {
        String answer = "";
        boolean isTrue = true;
        for(char word : s.toLowerCase().toCharArray()){
            if(word == ' '){
                isTrue = true;
            } else if(isTrue){
                isTrue = false;
                if(word >= 'a' && word <= 'z'){
                    word += 'A' - 'a';
                }
            }
            answer += word;
        }
        return answer;
    }
}
```

## ❌ fail

```java

```
