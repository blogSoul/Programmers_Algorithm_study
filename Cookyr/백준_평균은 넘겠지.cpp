#include <iostream>
#include <string>
#include <vector>

using namespace std;

int main(void)
{
    int number = 0;
    int student_num = 0;
    int score_total = 0;
    double score_result = 0;
    int high_student_num = 0;

    int* p_student_score_arr = 0;
    double high_score_student_ratio = 0;

    cin >> number;
 
    for (int i = 0; i<number; i++)
    {
        int temp = 0;
        cin >> student_num;
        score_total = 0;
        score_result = 0;
        p_student_score_arr = new int[student_num];
        high_score_student_ratio = 0;
        high_student_num = 0;

        for ( int j = 0; j < student_num; j++)
        {
            cin >> temp;
            score_total += temp;
            p_student_score_arr[j] = temp;
        }

        score_result = score_total / student_num;
        
        for (int j = 0; j < student_num; j++)
        {
            if (score_result < p_student_score_arr[j])
            {
                high_student_num++;
            }
        }
    
        high_score_student_ratio = ((double)high_student_num / (double)student_num)*100;

        cout.precision(3);

        //cout << high_score_student_ratio << "%" << endl;
        printf("%.3f", high_score_student_ratio);
        printf("%%\n");
    }
    



    return 0;
}