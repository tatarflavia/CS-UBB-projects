#pragma once
#include "WeddingDress.h"

template <typename Elems>
class DynamicVector
{

private:
	int size;
	int capacity;
	Elems* dresses;

public:
	DynamicVector();
	int get_size();
	~DynamicVector();
	Elems* get_all();
	void add(Elems& dr);
	bool search(Elems& dr);
	void remove(const std::string& photo);
	Elems* get_elem_from_pos(int pos);
	DynamicVector& operator=(const DynamicVector<Elems> & vector);
	DynamicVector& operator+(Elems& dr);
	friend DynamicVector& operator+(Elems& dr, DynamicVector<Elems>& vect);
	

	

private:
	void resize();
	void remove_from_pos(int pos);
};

template<typename Elems>
inline DynamicVector<Elems>::DynamicVector()
{
	this->size = 0;
	this->capacity = 20;
	this->dresses = new Elems[this->capacity];
}

template<typename Elems>
inline int DynamicVector<Elems>::get_size()
{
	return this->size;
}

template<typename Elems>
inline DynamicVector<Elems>::~DynamicVector()
{
	delete[] this->dresses;
}

template<typename Elems>
inline Elems * DynamicVector<Elems>::get_all()
{
	return this->dresses;
}

template<typename Elems>
inline void DynamicVector<Elems>::add(Elems & dr)
{
	if (this->size == this->capacity)
		this->resize();
	this->dresses[this->size] = dr;
	this->size++;
}

template<typename Elems>
inline bool DynamicVector<Elems>::search(Elems & dr)
{
	int i = 0;
	for (i = 0; i < this->size; i++)
		if (this->dresses[i].get_photograph().find(dr.get_photograph()) == 0)
			return true;
	return false;
}

template<typename Elems>
inline void DynamicVector<Elems>::remove(const std::string & photo)
{
	int pos = -1;
	for (int i = 0; i < this->size; i++)
		if (this->dresses[i].get_photograph().find(photo) == 0)
			pos = i;
	this->remove_from_pos(pos);
}

template<typename Elems>
inline Elems * DynamicVector<Elems>::get_elem_from_pos(int pos)
{
	return &this->dresses[pos];
}

template<typename Elems>
inline DynamicVector<Elems> & DynamicVector<Elems>::operator=(const DynamicVector<Elems> & vector)
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

template<typename Elems>
inline DynamicVector<Elems> & DynamicVector<Elems>::operator+(Elems & dr)
{
	if (this->size == this->capacity)
		this->resize();
	this->dresses[this->size] = dr;
	this->size++;
	return *this;
}

template<typename Elems>
inline void DynamicVector<Elems>::resize()
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

template<typename Elems>
inline void DynamicVector<Elems>::remove_from_pos(int pos)
{
	int i = 0;
	for (i = pos; i < this->size - 1; i++)
		this->dresses[i] = this->dresses[i + 1];
	this->size--;
}

template<typename Elems>
inline DynamicVector<Elems> & operator+(Elems & dr, DynamicVector<Elems> & vect)
{
	vect.add(dr);
	return vect;
}
