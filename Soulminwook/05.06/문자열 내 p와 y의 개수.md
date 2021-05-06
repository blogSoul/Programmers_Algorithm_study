# 📕 Solution

```java
class Solution {
    boolean solution(String s) {
        int cnt_p = 0, cnt_y = 0;
        for(char word : s.toCharArray()){
            if(word == 'p' || word == 'P'){
                cnt_p++;
            } else if(word == 'y' || word == 'Y'){
                cnt_y++;
            }
        }
        if(cnt_p != cnt_y){
            return false;    
        }
        return true;
    }
}
```

## ❌ fail

```javaa

```
