#include <cstdlib>
#include <ctime>

#include <string>
using namespace std;

string ToEnglish(unsigned int number);

int main(void)
{
	srand((unsigned int)time(NULL));

	for(int i=0;i<=10;i++)
	{
		unsigned int n = 0;
		for(int j=0;j<i;j++)
			n = (n*10) + (rand()%10);

		printf("%u = %s\n", n, ToEnglish(n).c_str());
	}

	system("pause");
	return 0;
}
