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

	HANDLE m_queueMutex;

	void DispatchOneMessage();

	bool GetOneMessage(int* message);

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

	m_queueMutex = CreateMutex(NULL, FALSE, NULL);

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
	if (m_queueMutex) 
	{
		CloseHandle(m_queueMutex);
		m_queueMutex = NULL;
	}
}

void MessageQueue::EnqueueOutgoingMessage(int message)
{
	// need the queueMutex before we can modify the queue
	DWORD waitResult = WaitForSingleObject(m_queueMutex, -1);
	if (waitResult == WAIT_OBJECT_0) 
	{
		__try
		{
			m_messageQueue.push(message);
		}
		__finally 
		{
			ReleaseMutex(m_queueMutex);
		}
	} 
	else 
	{
		// error while trying to aquire mutex.
		throw(waitResult);
	}
}


void MessageQueue::DispatchOneMessage()
{
	int message;
	if (GetOneMessage(&message))
	{
		m_messageWriter->WriteMessage(message);	
	}
}

// Safely get the next message off of the queue, if there is one.
// returns false if we have no messages.
bool MessageQueue::GetOneMessage(int* message)
{
	DWORD waitResult = WaitForSingleObject(m_queueMutex, -1);
	if (waitResult == WAIT_OBJECT_0) 
	{
		__try 
		{
			if(!m_messageQueue.empty())
			{
				*message = m_messageQueue.front();
				m_messageQueue.pop();
				return true;
			}
			else 
			{
				return false;
			}
		}
		__finally
		{
			ReleaseMutex(m_queueMutex);
		}
	} 
	else 
	{
		// failed to aquire the mutex, things are bad.
		throw(waitResult);
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
