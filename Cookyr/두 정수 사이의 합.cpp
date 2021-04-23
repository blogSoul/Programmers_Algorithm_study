#include <iostream>
#include <string>
#include <vector>

using namespace std;

long long solution(int a, int b) {
    long long answer = 0;

    if (a < b)
    {
        for (int i = 0; i <= (b - a); i++)
        {
            answer += (a + i);
        }
    }
    else if (b < a)
    {
        for (int i = 0; i <= (a - b); i++)
        {
            answer += (b + i);
        }
    }
    else
    {
        answer = a;
    }

    return answer;
}

void main(void)
{
    int a = 3;
    int b = 5;
    int expected_answer = 12;

    int answer = 0;

    answer = solution(a, b);

    if (answer == expected_answer)
    {
        std::cout << "Correct" << endl;
    }
    else
    {
        std::cout << "Fail" << endl;
    }

}
