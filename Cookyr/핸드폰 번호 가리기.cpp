#include <iostream>
#include <string>
#include <vector>

using namespace std;

string solution(string phone_number) {
    string answer = phone_number;
    string star = "";


    int len = phone_number.length();
    int starlen = len - 4;

    for (int i = 0; i < starlen; i++)
    {
        star.push_back('*');
    }

    answer.replace(0, len - 4, star);
    
    return answer;
}

void main(void)
{


    cout << solution("01112341234") << endl;

   

}