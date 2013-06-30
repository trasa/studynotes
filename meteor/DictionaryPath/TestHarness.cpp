#pragma warning(disable:4996)

#include <cstdlib>
#include <ctime>

#include <string>
#include <vector>
using namespace std;

vector<string> FindPath(vector<string>& dictionary, string a, string b);

vector<string> dictionary;

void LoadDictionary()
{
	FILE* dictFile = fopen("dictionary.in", "r");
	
	char buffer[1024];
	while(fgets(buffer, 1023, dictFile) != NULL)
	{
		string tmp = buffer;
		while(tmp[ tmp.size()-1 ] == '\r' || tmp[ tmp.size()-1 ] == '\n')
			tmp = tmp.substr(0, tmp.size()-1);

		dictionary.push_back(tmp);
	}

	fclose(dictFile);
}

int main(void)
{
	srand((unsigned int)time(NULL));

	LoadDictionary();

	for(unsigned int i=0;i<10;i++)
	{
		int a = rand() % dictionary.size();
		int b = rand() % dictionary.size();

		if(i == 0)
		{
			a = 0;
			b = 4;
		}

		vector<string> res = FindPath(dictionary, dictionary[a], dictionary[b]);

		printf("(%s, %s) => [", dictionary[a].c_str(), dictionary[b].c_str());
		for(unsigned int j=0;j<res.size();j++)
		{
			printf(res[j].c_str());
			if(j != res.size()-1)
				printf(", ");
		}
		printf("]\n");
	}

	system("pause");
	return 0;
}

