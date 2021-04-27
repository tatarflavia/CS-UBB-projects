#include "PriorityQueue.h"
#include <stdexcept>

/*theta(capacity)*/
void PriorityQueue::resize()
{
	std::pair<TElem, TPriority> *newElements= new std::pair<TElem, TPriority>[2*this->capacity];
	for (int i = 0; i < this->capacity; i++)
	{
		newElements[i] = this->elements[i];
	}
	this->elements = newElements;
	this->capacity *= 2;
}

/*theta(1)*/
PriorityQueue::PriorityQueue(Relation r)
{
	this->relation = r;
	this->capacity = 5;
	this->elements = new std::pair<TElem, TPriority>[this->capacity];
}

/*worst case: theta(n) - when resize is called
best case: best case from bubbleUp theta(1)
average case: average case from bubbleUp theta(log(n))
overall: O(n)*/
void PriorityQueue::push(TElem e, TPriority p)
{
	if (this->size == this->capacity)
	{
		this->resize();
	}
	this->size++;
	this->elements[this->size - 1] = std::make_pair(e, p);
	this->bubbleUp(this->size-1);
}

/*theta(1)*/
Element PriorityQueue::top() const
{
	if (this->isEmpty())
		throw std::runtime_error("Queue is empty!");
	return this->elements[0];
}

/*best case: theta(1) - when the element is on the right position
worst case: theta(log(n)) - when the element should be on the top position
average: theta(log(n))
overall: O(log(n))
n being the size of the queue*/
void PriorityQueue::bubbleUp(int position)
{
	Element element = this->elements[position];
	int parentPosition = (position - 1) / 4;
	while (position > 0 && !this->relation(this->elements[parentPosition].second, element.second))
	{
		this->elements[position] = this->elements[parentPosition];
		position = parentPosition;
		parentPosition = (position - 1) / 4;
	}
	this->elements[position] = element;
}

/*best case: theta(1) - when the new top has no children
worst case: theta(log(n)) - when the element on the top should be on the last row
average: theta(log(n))
n being the size of the queue*/
void PriorityQueue::bubbleDown(int position)
{
	Element elem = this->elements[position];
	while (position < this->size)
	{
		int maxChild = this->getBiggestChildPos(position);
		if (maxChild != -1 && this->relation(this->elements[maxChild].second, elem.second))
		{
			Element aux = this->elements[position];
			this->elements[position] = this->elements[maxChild];
			this->elements[maxChild] = aux;
			position = maxChild;

		}
		else 
		{
			position = this->size + 1;
		}
	}

}

/*theta(1)*/
int PriorityQueue::getBiggestChildPos(int parentPosition)
{
	int sizeOfArrayForChild = this->size - (4 * parentPosition + 1);
	if (sizeOfArrayForChild > 4)
		sizeOfArrayForChild = 4;
	if (sizeOfArrayForChild <= 0)
		return -1;
	Element max = this->elements[parentPosition * 4 + 1];
	int maxPosition = parentPosition * 4 + 1;
	for (int i = 1; i < sizeOfArrayForChild; i++)
		if (this->relation(this->elements[parentPosition * 4 + 1 + i].second, max.second))
		{
			max = this->elements[parentPosition * 4 + 1 + i];
			maxPosition = parentPosition * 4 + 1 + i;
		}
			
	return maxPosition;

}

/*same as bubbleDown*/
Element PriorityQueue::pop()
{
	if (this->isEmpty())
		throw std::runtime_error("Queue is empty!");
	Element deletedElement = this->elements[0];
	this->elements[0] = this->elements[this->size - 1];
	this->size -= 1;
	this->bubbleDown(0);
	return deletedElement;

}

/*theta(1)*/
bool PriorityQueue::isEmpty() const
{
	return this->size == 0;
}

/*theta(1)*/
PriorityQueue::~PriorityQueue()
{
	delete[] this->elements;
}
