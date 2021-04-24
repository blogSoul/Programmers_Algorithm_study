#include <iostream>
#include <string>
#include <vector>

using namespace std;

#define ODD 1 // È¦¼ö
#define EVEN 0 // Â¦¼ö

string solution(string s) {
   
    char c_answer[3] = {0, };
    string answer(c_answer);

    int len = s.length();
    int IsOddOrEven = len % 2;

    if (IsOddOrEven == ODD)
    {
        s.copy(c_answer, 1, (len / 2));
    }
    else 
    {
        s.copy(c_answer, 2, (len / 2)-1);
    }

    answer = c_answer;
  

    return answer;
}


void main(void)
{


    cout << solution("abcd") << endl;

   

}