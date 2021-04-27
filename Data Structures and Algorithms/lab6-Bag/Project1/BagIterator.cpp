#include "BagIterator.h"

BagIterator::BagIterator(const Bag & THEbag) :bag{ THEbag }, current{ 0 }, position{0}
{
}
//ok

TElem BagIterator::getCurrent()
{
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");
	return this->bag.elems[this->position];
}
//ok

bool BagIterator::valid()
{
	return this->current < this->bag.sizeOfTheBag;
}
//ok

void BagIterator::next()
{
	if (not(this->valid()))
		throw std::invalid_argument("received negative value");

	this->current+=1;
	this->position += 1;
	while ((this->bag.state_of_elems[this->position] == -1 || this->bag.state_of_elems[this->position]==1) && this->valid())
		this->position++;
}

void BagIterator::first()
{
	this->current = 0;
	this->position = 0;
}
//ok
