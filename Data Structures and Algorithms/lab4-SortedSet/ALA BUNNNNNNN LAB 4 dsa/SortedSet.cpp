#include "SortedSet.h"
#include "SortedSetIterator.h"
#include <iostream>
#include<stdio.h>

using namespace std;

//constructorfor the sorted set
//Time complexity:always teta(capacity) for the for
//extra space complexity:teta(1)
SortedSet::SortedSet(Relation r) {
	this->capacity = 20;
	this->elems = new TComp[capacity];
	this->indexes = new int[capacity];
	//init for the index list
	for (int i = 0; i < this->capacity - 1; i++)
		this->indexes[i] = i + 1;
	this->indexes[this->capacity - 1] = -1;
	this->head = -1;
	this->rel = r;
	this->firstEmpty = 0;

}


//Time complexity:BC=(we don't need resizing) teta(n) because of the while that looks for the position where el must be put and the for 
//that goes from there on to make space for el
//WC:(we need resizing) teta(capacity) from the second if + O(size) the last big if(BC=teta(1), when we add it to the head, WC= teta(size) when the else is in the worst case) => o(capacity) wc complex
//total=O(capacity)
//extra space complexity:teta(2*n)=teta(n) because we need a new allocation for the 2 DA's of the set
bool SortedSet::add(TComp el)
{
	//if the elem is here, we can't add it again
	if (this->search(el))
		return false;
	//we check for resizing: complex=teta(2*size+capacity) = teta(capacity)
	if (this->size()== this->capacity)
	{
		//double the capacity,copy the elems and new init for the indexes
		int cap = this->capacity;
		this->capacity = this->capacity * 2;
		TComp* el = new TComp[this->capacity]; 
		for (int i = 0; i < this->size(); i++)
			el[i] = this->elems[i];
		delete[] this->elems;
		this->elems = el;
		int* ele = new int[this->capacity]; 
		for (int i = 0; i < this->size(); i++)
			ele[i] = this->indexes[i];
		delete[] this->indexes;
		this->indexes = ele;
		for (int i = cap; i < this->capacity-1; i++)
			this->indexes[i] = i + 1;
		this->indexes[this->capacity - 1] = -1;
		this->firstEmpty = cap;
	}
	//we add directly according to the relation: BC=teta(1), when we add it to the head, WC= teta(size) when the else is in the worst case
	//=> O(size) from here afterwards
	if (this->isEmpty())
	{
		//if the list is empty initialise the head =>teta(1)
		this->head = 0;
		this->elems[this->head] = el;
		this->firstEmpty = this->indexes[this->firstEmpty];
		this->indexes[this->head] = -1;
		return true;
	}
	else //this one has BC:teta(1) first if==true, WC=teta(size), has to look through all the elems for the pos for the new el to be put at
	{
		//else insert the new elem

		//at the head : teta(1)
		if (this->rel(el, this->elems[this->head]))
		{
			//insert at the beggining
			this->elems[firstEmpty] = el;
			int next_empty = this->indexes[firstEmpty];
			this->indexes[firstEmpty] = this->head;
			this->head = this->firstEmpty;
			this->firstEmpty = next_empty;
			return true;
		}
		//else insert it according to the rel: O(size) to look for the position where the elem will be
		else {
			int current = this->head,prev=0;
			while (this->indexes[current] != -1 && this->rel(this->elems[current], el))
			{
				prev = current;
				current = this->indexes[current];
			}
			//teta(1) checks if the el is in rel with current => adds it to the end
			if (this->rel(this->elems[current], el))
			{
			this->elems[this->firstEmpty] = el;
			int ind = this->indexes[current];
			this->indexes[current] = this->firstEmpty;
			int next_emp = this->indexes[this->firstEmpty];
			this->indexes[this->firstEmpty] = ind;
			this->firstEmpty = next_emp;
			return true;
			}
			//teta(1) checks if the el is in rel with current => adds it before the end
			else
			{
				this->elems[this->firstEmpty] = el;
				this->indexes[prev] = this->firstEmpty;
				int next_empty = this->indexes[this->firstEmpty];
				this->indexes[this->firstEmpty] = current;
				this->firstEmpty = next_empty;
				return true;
			}

			
			
		}

	}

}


//Time complexity: BC=teta(1) if the function exits at one of the first 3 if's, if the set is empty, if e is in rel with the head or if e==head
//WC=teta(n) if the element searched for is on the last pos, AC=teta(n),total=O(n),n=size of one DA of the set
//extra space complexity:teta(1)
bool SortedSet::search(TComp e) const
{
	if (this->isEmpty())
		return false;
	//if e is in relation with the first elem ,then return false
	if (this->rel(e, this->elems[this->head]) && this->elems[this->head] != e)
		return false;
	//check if it is the first one
	if (this->elems[this->head] == e)
		return true;
	//we search for it
	int current = this->head;
	while (this->indexes[current] != -1 && this->elems[current] != e)
		current = this->indexes[current];
	//see if we found it and return t or f
	if (this->indexes[current]!=-1 || this->indexes[current]==-1 && this->elems[current]==e)
		return true;
	else
		return false;
}


//Time complexity:BC=teta(1) if e is not in the set(first if),WC=teta(size+cap)=teta(capacity) because of the while that looks for the position where el is to be removed and the case where the set has been emptied and it must be initialised again
//AC=teta(capacity) like the WC, because the other if's have teta(1) complexity,total=O(capacity)
//extra space complexity:teta(1)
bool SortedSet::remove(TComp e)
{
	//if search(e)==false,we have nothing to remove
	if (not(this->search(e)))
		return false;
	//we search for it
	int i = 0;
	int current = this->head;
	int prev = 0;
	while (this->indexes[current] != -1 && this->elems[current] != e)
	{
		prev = current;
		current = this->indexes[current];
	}
	//we remove it
	//see if we got to the last elem
	if (this->indexes[current] != -1)
	{
		//if the head must be removed
		if (current == this->head)
		{
			int l = this->firstEmpty;
			int ind = this->indexes[current];
			this->firstEmpty = this->head;
			this->indexes[this->firstEmpty] = l;
			this->head = ind;
			return true;
		}
		else 
		{
			//else we remove a normal elem thatis not the end or the head
			this->indexes[prev] = this->indexes[current];
			int le = this->firstEmpty;
			this->firstEmpty = current;
			this->indexes[this->firstEmpty] = le;
			return true;
			}
			
			}
	else 
		//we got to the last elem and it must be removed
	{
		//if that was the last elem from the set, we need to initialise the set again
		if (this->size() == 1)
		{
			for (int i = 0; i < this->capacity - 1; i++)
				this->indexes[i] = i + 1;
			this->indexes[this->capacity - 1] = -1;
			this->head = -1;
			this->firstEmpty = 0;
			return true;
		}
		//else we remove the last elem
		else 
		{
			this->indexes[prev] = this->indexes[current];
			int le = this->firstEmpty;
			this->firstEmpty = current;
			this->indexes[this->firstEmpty] = le;
			return true;
		}
	}
	//else we haven't removed anything
	return false;
}



//Time complexity:WC=teta(n),it has to always go through all the elems to find the size
//BC=teta(1), if the first if is true and the list is empty
//TC=O(n)
//extra space complexity:teta(1)
int SortedSet::size() const
{
	if (this->head == -1)
		return 0;
	else {
		int current = this->head;
		int count = 0;
		while (this->indexes[current] !=-1)
		{
			count++;
			current = this->indexes[current];
		}
		count++;
		return count;
	}
}

//Time complexity:always teta(1),makes only a verification
//extra space complexity:teta(1)
bool SortedSet::isEmpty() const
{
	if (this->head == -1)
		return true;
	return false;
}

void SortedSet::tostr()
{
	cout << "The head is: " << this->head << "\n";
	cout << "First Empty is:" << this->firstEmpty << "\n";
	cout << "Size is: " << this->size() << "\n";
	int current = this->head;
	if (this->head == -1)
		cout << "None\n";
	else {cout << "The array:\n";
	while (this->indexes[current] != -1)
	{
		cout << this->elems[current] << ", ";
		current = this->indexes[current];
	}
	cout << this->elems[current] << "\n";
	for (int i = 0; i < this->capacity;i++)
		cout << this->elems[i] << " ";
	cout << "\n";
	cout << "The indexes:\n";
	for (int i = 0; i < this->capacity; i++)
		cout << this->indexes[i] << " ";
	cout << "\n";}
	
}


//Time complexity: always teta(n) for the while that looks for the last elem
//TC: teta(n)
int SortedSet::getRange() const
{
	if (this->head == -1)
		return 0;
	else {
		int min_elem = this->elems[this->head];
		int current = this->head;
		while (this->indexes[current] != -1)
		{
			current = this->indexes[current];  //teta(n)
		}
		int max_elem = this->elems[current];
		return max_elem - min_elem;
	}
}


//
//function getRange(SS) is:
//if SS.head = -1 then
//return_smallest_freq < -0
//else
//min_elem < -SS.elems[SS.head]
//	current < -SS.head
//	while  SS.indexes[current] != -1 execute :
//current < -SS.indexes[current]
//	endwhile
//	max_elem < -SS.elems[current]
//
//	getRange < -max_elem - min_elem
//
//



//Time complexity:always teta(1),makes only a return
//extra space complexity:teta(1)
SortedSetIterator SortedSet::iterator() const
{
	return SortedSetIterator(*this);
}

//destructor
//Time complexity:always teta(1),makes only 2 steps
//extra space complexity:teta(1)
SortedSet::~SortedSet() {
	delete[] this->elems;
	delete[] this->indexes;
}

