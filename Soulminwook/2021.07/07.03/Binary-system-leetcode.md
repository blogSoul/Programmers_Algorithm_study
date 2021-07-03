# 📕 Solution

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
    public static ArrayList<ArrayList<String>> db;
    public static void init(){
        for(int i = 0; i < 6; i++){
            db.add(new ArrayList<>());
        }
        String[] sample1 = {"bool", "char", "int", "long"};
        String[] sample2 = {"int", "long", "int", "long"};
        String[] sample3 = {"bool", "char", "bool", "string"};
        String[] sample4 = {"bool", "char", "bool", "char"};
        String[] sample5 = {"bool", "bool", "bool", "bool", "bool", "bool", "bool", "bool", "string"};
        String[] sample6 = {"bool", "string", "bool", "bool", "bool", "bool"};
        for(String str : sample1){
            db.get(0).add(str);
        }
        for(String str : sample2){
            db.get(1).add(str);
        }
        for(String str : sample3){
            db.get(2).add(str);
        }
        for(String str : sample4){
            db.get(3).add(str);
        }
        for(String str : sample5){
            db.get(4).add(str);
        }
        for(String str : sample6){
            db.get(5).add(str);
        }
    }
    public static String answer;
    public static void Check(int index, int cnt, String str, ArrayList<String> list){
        // bool : 1, char : 2, string : 4, int : 8, long : 16
        //System.out.println(index + " " + cnt + " " + str + " " + answer);
        if(index >= list.size()){
            //System.out.println(index + " " + cnt + " " + str + " " + answer);
            if(cnt > 0){
                for(int i = 0; i < (8 - cnt) % 8; i++){
                    str += "*";
                }
                if((8 - cnt) % 8 > 0) str += ",";
                answer += str;
            }
            return;
        }
        if(list.get(index).equals("long")){
            for(int i = 0; i < (8 - cnt) % 8; i++){
                str += "*";
            }
            if((8 - cnt) % 8 > 0) str += ",";
            str += "########,########,";
            answer += str;
            Check(index + 1, 0, "", list);
        } else if(list.get(index).equals("int")){
            for(int i = 0; i < (8 - cnt) % 8; i++){
                str += "*";
            }
            if((8 - cnt) % 8 > 0) str += ",";
            str += "########,";
            answer += str;
            Check(index + 1, 0, "", list);
        } else if(list.get(index).equals("string")){
            if(cnt < 4){
                str += "####";
                Check(index + 1, cnt + 4, str, list);
            } else if(cnt == 4){
                str += "####,";
                answer += str;
                Check(index + 1, 0, "", list);
            } else {
                for(int i = 0; i < (8 - cnt) % 8; i++){
                    str += "*";
                }
                if((8 - cnt) % 8 > 0) str += ",";
                answer += str;
                Check(index + 1, 4, "####", list);
            }
        } else if(list.get(index).equals("char")){
            if(cnt < 6){
                str += "##";
                Check(index + 1, cnt + 2, str, list);
            } else if(cnt == 6){
                str += "##,";
                answer += str;
                Check(index + 1, 0, "", list);
            } else {
                for(int i = 0; i < (8 - cnt) % 8; i++){
                    str += "*";
                }
                if((8 - cnt) % 8 > 0) str += ",";
                answer += str;
                Check(index + 1, 2, "##", list);
            }
        } else if(list.get(index).equals("bool")){
            // bool : 1, char : 2, string : 4, int : 8, long : 16
            if(index + 1 < list.size() && list.get(index + 1).equals("char")){
                if(cnt < 4){
                    str += "#*##";
                    Check(index + 2, cnt + 4, str, list);
                } else if(cnt == 4){
                    str += "#*##,";
                    answer += str;
                    Check(index + 2, 0, "", list);
                } else {
                    for(int i = 0; i < (8 - cnt) % 8; i++){
                        str += "*";
                    }
                    if((8 - cnt) % 8 > 0) str += ",";
                    answer += str;
                    Check(index + 2, 4, "#*##", list);
                }
            } else if(index + 1 < list.size() && list.get(index + 1).equals("string")) {
                for (int i = 0; i < (8 - cnt) % 8; i++) {
                    str += "*";
                }
                if ((8 - cnt) % 8 > 0) str += ",";
                str += "#***####,";
                answer += str;
                Check(index + 2, 0, "", list);
            } else if(cnt < 7){
                str += "#";
                Check(index + 1, cnt + 1, str, list);
            } else if(cnt == 7){
                str += "#,";
                answer += str;
                Check(index + 1, 0, "", list);
            } else {
                str += ",";
                answer += str;
                Check(index + 1, 1, "#", list);
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        db = new ArrayList<>();
        init();
        int db_size = db.size();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < db_size; i++){
            //System.out.println("# " + (i + 1));
            for(String str : db.get(i)){
                sb.append(str + " ");
            }
            sb.append("\n");
            answer = "";
            Check(0, 0, "", db.get(i));
            if(answer.charAt(answer.length() - 1) == ','){
                answer =answer.substring(0, answer.length() - 1);
            }
            sb.append(answer + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
```

## ❌ fail

```java

```
