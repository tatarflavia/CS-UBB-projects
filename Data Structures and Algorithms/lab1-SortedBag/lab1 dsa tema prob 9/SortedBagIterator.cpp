#include "SortedBagIterator.h"
#include "SortedBag.h"

//constructor
//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
SortedBagIterator::SortedBagIterator(const SortedBag& ba) :bag(ba), current(0)
{
}

//Time complexity:always teta(1),makes only a verification and a return 
//extra space complexity:teta(1)
TComp SortedBagIterator::getCurrent() {
	if (this->current == this->bag.size())
		throw std::invalid_argument("received negative value");
	return this->bag.elems[this->current];

}

//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)
bool SortedBagIterator::valid() {
	return this->current < this->bag.size();
}

//Time complexity:always teta(1),makes only a verification and a step
//extra space complexity:teta(1)
void SortedBagIterator::next() {
	if (this->current == this->bag.size())
		throw std::invalid_argument("received negative value");
	this->current++;
}

//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
void SortedBagIterator::first() {
	this->current = 0;
}

