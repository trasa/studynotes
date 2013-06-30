#include "windows.h"

#include <queue>
using namespace std;

#include "IMessageWriter.h"
#include "IMessageQueue.h"


class MessageQueue : public IMessageQueue
{
public:

	MessageQueue(int numThreads, IMessageWriter* writer);
	~MessageQueue();

	void EnqueueOutgoingMessage(int message);

private:

	IMessageWriter* m_messageWriter;

	bool m_shuttingDown;

	queue<int> m_messageQueue;

	vector<HANDLE> m_workerThreads;

	void DispatchOneMessage();

	static DWORD WINAPI StaticWorkerThread(LPVOID param);
	void WorkerThread();
};


IMessageQueue* CreateMessageQueue(int numThreads, IMessageWriter* writer)
{
	return new MessageQueue(numThreads, writer);
}


MessageQueue::MessageQueue(int numThreads, IMessageWriter* writer)
{
	m_messageWriter = writer;

	m_shuttingDown = false;

	for(int i=0;i<numThreads;i++)
	{
		HANDLE threadHandle = CreateThread(NULL, 0, StaticWorkerThread, (LPVOID)this, 0, NULL);
		m_workerThreads.push_back(threadHandle);
	}
}

MessageQueue::~MessageQueue()
{
	m_shuttingDown = true;
	WaitForMultipleObjects(m_workerThreads.size(), &m_workerThreads[0], TRUE, INFINITE);
}

void MessageQueue::EnqueueOutgoingMessage(int message)
{
	m_messageQueue.push(message);
}


void MessageQueue::DispatchOneMessage()
{
	if(!m_messageQueue.empty())
	{
		int message = m_messageQueue.front();
		m_messageQueue.pop();

		m_messageWriter->WriteMessage(message);
	}
}

DWORD WINAPI MessageQueue::StaticWorkerThread(LPVOID param)
{
	MessageQueue* instance = (MessageQueue*)param;
	instance->WorkerThread();
	return 0;
}

void MessageQueue::WorkerThread()
{
	while(m_shuttingDown == false)
	{
		DispatchOneMessage();
	}
}
