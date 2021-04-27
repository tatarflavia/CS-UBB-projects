#include "SortedBagIterator.h"
#include "SortedBag.h"

//constructor
//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
SortedBagIterator::SortedBagIterator(const SortedBag& ba) :bag{ ba }, current{ ba.head }, CurrentFreq{ 0 }
{
}

//Time complexity:always teta(1),makes only a verification and a return 
//extra space complexity:teta(1)
TComp SortedBagIterator::getCurrent() {
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	return this->current->get_info();

}

//Time complexity:always teta(1),makes only 2 steps
//extra space complexity:teta(1)
bool SortedBagIterator::valid() {
	if (this->bag.size() == 0)
		return false;
	return this->current != this->bag.tail || this->current == this->bag.tail && this->CurrentFreq < this->bag.tail->get_freq() - 1;
}

//Time complexity:always teta(1),makes only a verification and a couple of steps
//extra space complexity:teta(1)
void SortedBagIterator::next() {
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	if (this->CurrentFreq == this->current->get_freq())
	{
		this->current = this->current->get_next();
		this->CurrentFreq = 0;
	}
	else {
		this->CurrentFreq += 1;
		if (this->CurrentFreq == this->current->get_freq())
		{
			this->current = this->current->get_next();
			this->CurrentFreq = 0;
		}
	}
}

//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
void SortedBagIterator::first() {
	this->current = this->bag.head;
}

