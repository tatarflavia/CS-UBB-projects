#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <iostream>
#include<stdio.h>

using namespace std;

//constructorfor the sorted bag
//Time complexity:always teta(1)
//extra space complexity:teta(1)
SortedBag::SortedBag(Relation r) {
	this->siz = 0;
	this->capacity = 10;
	this->elems = new TComp[capacity];
	this->rel = r;
	
}
//copy constructor
//Time complexity:always teta(size of the const SortedBag& param)=teta(n) because we have a for
//extra space complexity:teta(n),because we need a new sorted bag
SortedBag::SortedBag(const SortedBag& bag) 
{
	//we first copy the params in the class we work in(this)
	this->siz = bag.siz;
	this->capacity = bag.capacity;
	this->rel = bag.rel;
	//we copy the elems after allocationg space for them
	this->elems = new TComp[this->capacity];
	int i = 0;
	for (i = 0; i < this->siz; i++)
		this->elems[i] = bag.elems[i];
}

//function for operator '='
//Time complexity:BC=teta(1) if this==&bag,WC=teta(size of the const SortedBag& param)=teta(n) because we have a for
//AC=teta(n), total Time complexity=O(n)
//extra space complexity:teta(n) because we need a new sorted bag
SortedBag& SortedBag::operator=(const SortedBag& bag) 
{
	//first verify if the const SortedBag = this
	if (this == &bag)
		return *this;
	//we copy the params in the bag sorted bag
	this->siz = bag.siz;
	this->capacity = bag.capacity;
	this->rel = bag.rel;
	//we delete the space required for the old elems
	delete[] this->elems;
	//we allocate space for the new elems
	this->elems = new TComp[this->capacity];
	//we copy the elems
	int i = 0;
	for (i = 0; i < this->siz; i++)
		this->elems[i] = bag.elems[i];
	//we return the new bag
	return *this;

}

//Time complexity:BC=(we don't need resizing) teta(n) because of the while that looks for the position where el must be put and the for 
//that goes from there on to make space for el
//WC:(we need resizing) teta(n+n)=teta(2n)=>teta(n), AC=teta(n), total=teta(n)they are all equal
//extra space complexity:teta(n) because we need a new allocation
void SortedBag::add(TComp el) 
{
	//we check for resizing
	if (this->siz == this->capacity)
	{
		this->capacity = this->capacity * 2;
		TComp* el = new TComp[this->capacity]; //this if has teta(n) Time complexity
		for (int i = 0; i < this->siz; i++)
			el[i] = this->elems[i];
		delete[] this->elems;
		this->elems = el;
	}
	//we add directly according to the relation
	int i = 0;
	//search for the position where el must be added
	while (not(this->rel(el, this->elems[i])) && i < this->siz)
		i++;
	//we make space for el
	int j = 0;
	for (j = this->siz; j > i; j--)
		this->elems[j] = this->elems[j - 1];
	//we add el
	this->elems[i] = el;
	this->siz++;

}


//Time complexity: BC=teta(1) if the function exits at one of the first 4 if's
//WC=teta(n) if the element searched for is on the last pos, AC=teta(n),total=O(n),n=size of the bag
//extra space complexity:teta(1)
bool SortedBag::search(TComp e) const 
{
	if (this->isEmpty())
		return false;
	//if e is in relation with the first elem ,then return false
	if (this->rel(e, this->elems[0])&& this->elems[0] != e)
		return false;
	//if e is not in realation with the last elem,ret false
	if (not(this->rel(e, this->elems[this->siz - 1])))
		return false;
	//check if it is the first one
	if (this->elems[0] == e)
		return true;
	//we search for it
	int i = 0;
	while (i < this->siz&&this->elems[i] != e)
		i++;
	if (this->elems[i] == e)
		return true;
	return false;
}


//Time complexity:BC=teta(1) if e is not in the bag(first if),WC=teta(n) because of the while that looks for the position where el is to be removed and the for 
//that goes from there on to put elements over it to remove it
//AC=teta(n),total=O(n)
//extra space complexity:teta(1)
bool SortedBag::remove(TComp e) 
{
	//if search(e)==false,we have nothing to remove
	if (not(this->search(e)))
		return false;
	//we search for it
	int i = 0;
	while (i < this->siz&&this->elems[i] != e)
		i++;
	//we put elements over it so that we remove it
	for (int j = i; j < this->siz-1; j++)
		this->elems[j] = this->elems[j + 1];
	this->siz--;
	return true;
}

//Time complexity:BC=teta(1) if e is not in the bag(first if),WC=teta(n) if e is on the las positions because the while goes till there
//AC=teta(n),total=O(n)
//extra space complexity:teta(1)
int SortedBag::nrOccurrences(TComp e) const 
{
	if (not(this->search(e)))
		return 0;
	//we search for it and we count it
	int count = 0,i=0;
	while (i < this->siz&&this->rel(this->elems[i],e)) 
	{
		if (this->elems[i] == e)
			count++;
		i++;
	}
	return count;
		
}

//Time complexity:always teta(1),same number of steps always
//extra space complexity:teta(1)
int SortedBag::size() const 
{
	return this->siz;
}

//Time complexity:always teta(1),makes only a verification
//extra space complexity:teta(1)
bool SortedBag::isEmpty() const 
{
	if (this->siz == 0)
		return true;
	return false;
}
 
void SortedBag::toStr() 
{
	for (int i = 0; i < this->siz; i++)
		cout << this->elems[i] << " ";
	cout << "\n";
}



//extra space complexity=teta(1)
//time complexity: teta(n) because the 2 while's go only once through the array together
//the second while goes over the equal elements,the first while takes it from there, ignores the equal elements
int SortedBag::toSet() 
{
	int i = 0, count = 0; //count counts the number of removed elements
	TComp k = 0; 
	while (i < this->siz-1)
	{
		k = this->elems[i];
		int m = i+1; 
		while (m<this->siz && k == this->elems[m])
		{
			count++;
			int j = 0;
			for (int j = m; j < this->siz - 1; j++)
				this->elems[j] = this->elems[j + 1];
			this->siz--;
		}
		i = m;
	}
	return count;
}

//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)
SortedBagIterator SortedBag::iterator() const 
{
	return SortedBagIterator(*this);
}

//destructor
//Time complexity:always teta(1),makes only a step
//extra space complexity:teta(1)
SortedBag::~SortedBag() {
	delete[] this->elems;
}
