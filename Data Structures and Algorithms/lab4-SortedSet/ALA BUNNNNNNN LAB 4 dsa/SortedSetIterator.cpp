#include "SortedSetIterator.h"
#include "SortedSet.h"

//constructor
//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
SortedSetIterator::SortedSetIterator(const SortedSet& set) : set{set}
{
	this->current = set.head;
}

//Time complexity:always teta(1),makes only a verification and a return 
//extra space complexity:teta(1)
TComp SortedSetIterator::getCurrent() {
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	return this->set.elems[this->current];

}

//Time complexity:always teta(1),makes only a return and 3 verfications
//extra space complexity:teta(1)
bool SortedSetIterator::valid() {
	if (this->set.isEmpty())
		return false;
	if (this->current == -1)
		return false;
	if (this->set.indexes[current] != -1)
		return true;
	else 
	{return this->set.search(this->set.elems[this->current]);
	}
	
}

//Time complexity:always teta(1),makes only one verification and 2 steps
//extra space complexity:teta(1)
void SortedSetIterator::next() {
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	if (this->set.indexes[this->current] == -1)
		this->current = -1;
	else
		this->current = this->set.indexes[this->current];
}

//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
void SortedSetIterator::first() {
	this->current = this->set.head;
}

