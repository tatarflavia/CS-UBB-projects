#include "DynamicVector.h"



DynamicVector::DynamicVector()
{
	this->size = 0;
	this->capacity = 20;
	this->dresses = new WeddingDress[this->capacity];
}

int DynamicVector::get_size()
{
	return this->size;
}


DynamicVector::~DynamicVector()
{
	delete[] this->dresses;
}

WeddingDress * DynamicVector::get_all()
{
	return this->dresses;
}

void DynamicVector::add(WeddingDress& dr)
{
	if (this->size == this->capacity)
		this->resize();
	this->dresses[this->size] = dr;
	this->size++;
}

bool DynamicVector::search(WeddingDress& dr)
{
	int i = 0;
	for (i = 0; i < this->size; i++)
		if (this->dresses[i].get_photograph().find(dr.get_photograph()) == 0)
			return true;
	return false;
}

//throw exceptions in repo!!!!,LOOK FOR THEM BEFORE CALLING THE FUNCTIONS	


void DynamicVector::remove(const std::string& photo)
{
	int pos = -1;
	for (int i = 0; i < this->size; i++)
		if(this->dresses[i].get_photograph().find(photo)==0)
			pos = i;
	this->remove_from_pos(pos);
}

WeddingDress* DynamicVector::get_elem_from_pos(int pos)
{
	return &this->dresses[pos];
}

DynamicVector & DynamicVector::operator=(const DynamicVector & vector)
{
	if (this == &vector)
		return *this;
	this->capacity = vector.capacity;
	this->size = vector.size;

	delete[] this->dresses;
	
	this->dresses = new WeddingDress[this->capacity];
	for (int i = 0; i < this->size; i++)
		this->dresses[i] = vector.dresses[i];
	return *this;
}

DynamicVector& DynamicVector::operator+(WeddingDress & dr)
{
	if (this->size == this->capacity)
		this->resize();
	this->dresses[this->size] = dr;
	this->size++;
	return *this;
}




void DynamicVector::resize()
{
	this->capacity *= 2;
	//allocate new space
	WeddingDress* dress = new WeddingDress[this->capacity];
	int i = 0;
	//we copy the elems
	for (i = 0; i < this->size; i++)
		dress[i] = this->dresses[i];
	//delete old space and put the copied elems into the new array with new cap
	delete[] this->dresses;
	this->dresses = dress;
}

void DynamicVector::remove_from_pos(int pos)
{
	int i = 0;
	for (i = pos; i < this->size - 1; i++)
		this->dresses[i] = this->dresses[i + 1];
	this->size--;
}

 /*DynamicVector & DynamicVector::operator+(WeddingDress & dr, DynamicVector & vect)
{
	if (vect == this->capacity)
		this->resize();
	this->dresses[this->size] = dr;
	this->size++;
	return *this;
}
*/
 DynamicVector & operator+(WeddingDress & dr, DynamicVector & vect)
 {
	 vect.add(dr);
	 return vect;
 }
