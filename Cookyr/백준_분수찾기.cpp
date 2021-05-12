#include <iostream>
#include <string>
#include <vector>

using namespace std;

int condition(int n, int order)
{
    if ((n * (n + 1) / 2 < order) && (order <= (n + 1) * (n + 1 + 1) / 2))
    {
        return 0;
    }
    else
    {
        return 1;
    }
}

int main(void)
{
    int y = 1;
    int x = 1;
    int order = 0;
    int line_num = 0;
    int number_in_linenum = 0;

    int i=0;

    int subnum = 0;
    int subnum_now = 0;

    cin >> order;

    if (order == 1)
    {
        cout << '1' << '/' << '1';
    }
    else
    {
        /*
        for (int n = 1; condition(n, order); n++)
        {
            line_num = n+1;
        }
        line_num += 1;*/

        do
        {
            i++;

        }         while (condition(i, order));

        line_num = i+1;

        number_in_linenum = order - (((line_num-1) * (line_num + 1 -1) / 2));

        if (line_num % 2 == 0) //Â¦¼ö
        {
            y = number_in_linenum;
            x = line_num - number_in_linenum + 1;
        }
        else // È¦¼ö
        {
            y = line_num - number_in_linenum + 1;
            x = number_in_linenum;
        }
       

        cout << y << '/' << x;
    }
    

    return 0;
}