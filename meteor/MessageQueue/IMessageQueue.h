#ifndef IMESSAGEQUEUE_H
#define IMESSAGEQUEUE_H


class IMessageQueue
{
public:

	virtual void EnqueueOutgoingMessage(int message) = 0;
};

IMessageQueue* CreateMessageQueue(int numThreads, IMessageWriter* writer);

#endif
