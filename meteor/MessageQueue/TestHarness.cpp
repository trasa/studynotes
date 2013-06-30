#include "windows.h"

#include <conio.h>
#include <cstdlib>
#include <ctime>

#include <iostream>
#include <set>
#include <string>
using namespace std;

#include "IMessageWriter.h"
#include "IMessageQueue.h"

class MessageWriter : public IMessageWriter
{
public:

	MessageWriter()
	{
		containerLock = new CRITICAL_SECTION();
		InitializeCriticalSection(containerLock);

		messageReceivedCount = 0;
		contentionCount = 0;
	}

	~MessageWriter()
	{
		DeleteCriticalSection(containerLock);
		delete containerLock;
	}

	virtual void WriteMessage(int message)
	{
		bool writeStatusUpdate = false;
		int statusCount = 0;
		int statusExpected = 0;

		if(TryEnterCriticalSection(containerLock) == FALSE)
		{
			contentionCount++;
			EnterCriticalSection(containerLock);
		}
			messagesReceived.insert(message);
			threadsSeen.insert(GetCurrentThreadId());
			messageReceivedCount++;

			int x = 0;
			while(x == 0)
				x = rand() % 1000;

			if((messageReceivedCount % x) == 0)
			{
				writeStatusUpdate = true;
				statusCount = messageReceivedCount;
				statusExpected = numMessagesExpected;
			}

			Sleep(1);
		LeaveCriticalSection(containerLock);

		if(writeStatusUpdate == true)
			printf("Received %d / %d messages\n", statusCount, statusExpected);
	}

	set<int> messagesReceived;
	set<DWORD> threadsSeen;
	int messageReceivedCount;
	int numMessagesExpected;
	int contentionCount;
	LPCRITICAL_SECTION containerLock;
};

int main(void)
{
	const unsigned int messagesToSend = 10000;

	srand(72);

	set<int> sentMessages;

	MessageWriter writer;
	writer.numMessagesExpected = messagesToSend;

	IMessageQueue* messageQueue = CreateMessageQueue(10, &writer);

	for(unsigned int i=0;i<messagesToSend;i++)
	{
		int message = rand();
		messageQueue->EnqueueOutgoingMessage(message);
		sentMessages.insert(message);
	}

	while(writer.messageReceivedCount != messagesToSend)
	{
		Sleep(500);
		printf("Received %d / %d messages\n", writer.messageReceivedCount, messagesToSend);
	}

	if(writer.messagesReceived != sentMessages)
	{
		printf("Test failed.  The received messages did not match the sent messages.\n");
	}
	else if(writer.contentionCount == 0)
	{
		printf("Test failed.  The message queue must be capable of sending multiple messages to the message writer simultaneously.\n");
	}
	else
	{
		printf("Test succeeded.  All messages received.\n");
	}

	system("pause");
	return 0;
}
