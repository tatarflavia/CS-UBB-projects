#include "SortedBag.h"
#include "SortedBagIterator.h"
#include <iostream>
#include<stdio.h>


using namespace std;
//constructor,always teta(1) time complexity and the same for the extra space complex
DLLNode::DLLNode(TComp info)
{
	this->next = NULL;
	this->prev = NULL;
	this->frequency = 1;
	this->info = info;
}
//default constructor,always teta(1) time complexity and the same for the extra space complex
DLLNode::DLLNode()
{
	this->info = -1000;
	this->prev = NULL;
	this->frequency = 0;
	this->next = NULL;
}

void DLLNode::toStr()
{
	cout << "elem:" << this->info << " freq:" << this->frequency << "\n";
}

////copy constructor
//DLLNode::DLLNode(DLLNode& node)
//{
//	//we first copy the params in the class we work in(this)
//	this->info = node.info;
//	this->frequency = node.frequency;
//	//we copy the elems after allocationg space for them
//	//this->next = new DLLNode[DLLNode];
//	this->next = node.next;
//	//this->prev = new DLLNode[DLLNode];
//	this->prev = node.prev;
//}


//always teta(1) time complexity and the same for the extra space complex
DLLNode& DLLNode::operator=(const DLLNode& node)
{
	//first verify if the node = this
	if (this == &node)
		return *this;
	//we copy the params
	this->info = node.info;
	this->frequency = node.frequency;
	//we return the new node
	return *this;

}
//getters and setters for DLLnode, always teta(1) time complexity and the same for the extra space complex
TComp DLLNode::get_info() { return this->info; }
DLLNode* DLLNode::get_next() { return this->next; }
DLLNode* DLLNode::get_prev() { return this->prev; };
int DLLNode::get_freq() { return this->frequency; };
void DLLNode::set_info(TComp i) { this->info = i; }
void DLLNode::set_next(DLLNode* next) { this->next = next; }
void DLLNode::set_prev(DLLNode* prev) { this->prev = prev; }
void DLLNode::set_freq(int i) { this->frequency = i; }



//destructor,always teta(1) time complexity and the same for the extra space complex
DLLNode::~DLLNode() {
}


//constructor
SortedBag::SortedBag(Relation r) {
	this->rel = r;
	DLLNode* elNew = new DLLNode;
	elNew->set_next(NULL);
	elNew->set_prev(NULL);
	this->head = elNew;
	DLLNode* elNe = new DLLNode;
	elNe->set_next(NULL);
	elNe->set_prev(NULL);
	this->tail = elNe;
}
////copy constructor
//SortedBag::SortedBag(const SortedBag& bag)
//{
//	//we first copy the params in the class we work in(this)
//	this->siz = bag.siz;
//	this->capacity = bag.capacity;
//	this->rel = bag.rel;
//	//we copy the elems after allocationg space for them
//	this->elems = new TComp[this->capacity];
//	int i = 0;
//	for (i = 0; i < this->siz; i++)
//		this->elems[i] = bag.elems[i];
//}

//function for operator '='
//SortedBag& SortedBag::operator=(const SortedBag& bag)
//{
//	//first verify if the const sortedbag = this
//	if (this == &bag)
//		return *this;
//	//we copy the params in the bag sorted bag
//	this->rel = bag.rel;
//	//we delete the space required for the old elems
//	delete[] this->head;
//	delete[] this->tail;
//	//we allocate space for the new elems and copy them
//	DLLNode hea(-1000);
//	this->head = hea;
//	this->head = bag.head;
//	DLLNode tai(-1000);
//	this->tail = tai;
//	this->tail = bag.tail;
//	//we return the new bag
//	return *this;
//
//}

void SortedBag::add(TComp el)
{
	if (this->isEmpty())
	{
		//if the list is empty initialise the head
		this->head->set_info(el);
		this->head->set_freq(1);
	}
	else if (this->size() == 1)
	{
		//if only the head is in the list, then initialise the tail
		if (this->rel(this->head->get_info(), el))
		{
			this->tail->set_info(el);
			this->tail->set_freq(1);
			this->tail->set_prev(this->head);
			this->head->set_next(this->tail);
		}
		else
		{
			this->tail->set_prev(this->head);
			this->tail->set_freq(1);
			this->tail->set_info(this->head->get_info());
			this->head->set_freq(1);
			this->head->set_next(this->tail);
			this->head->set_info(el);

		}

	}
	else
	{
		//else insert the new elem

		//at the head
		if (this->rel(el, this->head->get_info()))
		{
			if (this->head->get_info() != el) {//insert at the beggining
				DLLNode* elNew = new DLLNode;
				elNew->set_info(el);
				elNew->set_freq(1);
				elNew->set_next(this->head);
				this->head->set_prev(elNew);
				elNew->set_prev(NULL);
				this->head = elNew;
			}
			else { this->head->set_freq(this->head->get_freq() + 1); }
		}

		//at the end
		else if (this->rel(this->tail->get_info(), el))
		{
			if (this->tail->get_info() != el) {//insert at the end
				DLLNode* elNew = new DLLNode;
				elNew->set_info(el);
				elNew->set_freq(1);
				elNew->set_next(NULL);
				elNew->set_prev(this->tail);
				this->tail->set_next(elNew);
				this->tail = elNew;
			}
			else { this->tail->set_freq(this->tail->get_freq() + 1); }
		}
		//else insert it according to the rel
		else {
			DLLNode* current;
			current = this->head;
			while (current->get_next() != NULL && this->rel(current->get_info(), el) && current->get_info() != el)
				current = current->get_next();
			if (current->get_info() == el)
				current->set_freq(current->get_freq() + 1);
			else
			{
				DLLNode* elNew = new DLLNode;
				elNew->set_info(el);
				elNew->set_freq(1);
				elNew->set_next(current);
				elNew->set_prev(current->get_prev());
				current->get_prev()->set_next(elNew);
				current->set_prev(elNew);
			}
		}

	}

}


//time complex: BC=the first if is true, the bag is empty, teta(1),or any of the next if's to ret false=> teta(1)
//WC:it will go through all the elems without finding e,teta(n)
//TC:O(n)
bool SortedBag::search(TComp e) const
{
	//treat the case where the list is empty
	if (this->isEmpty())
		return false;
	//case where only the head exists
	DLLNode* current;
	current = this->head;
	if (current->get_next() == NULL && current->get_info() != e)
		return false;
	//if e is in relation with head ,then return false
	if (this->rel(e, this->head->get_info()) && this->head->get_info() != e)
		return false;
	//if e is not in relation with the last elem,ret false
	if (not(this->rel(e, this->tail->get_info())))
		return false;
	//check if it is the first one
	if (this->head->get_info() == e)
		return true;
	//we search for it
	while (current->get_next() != NULL) {
		if (current->get_info() == e)
			return true;
		current = current->get_next();
	}
	if (this->tail->get_info() == e)
		return true;
	return false;
}

bool SortedBag::remove(TComp e)
{
	//if search(e)==false,we have nothing to remove
	if (not(this->search(e)))
		return false;
	//we search for it
	int i = 0;
	DLLNode* current;
	current = this->head;
	while (current->get_next() != NULL && current->get_info() != e)
		current = current->get_next();
	if (current != NULL)
	{
		if (current->get_freq() != 1) {
			current->set_freq(current->get_freq() - 1);
			return true;
		}

		else {
			if (current == this->head)
			{
				if (this->size() == 1)
				{
					this->head->set_next(NULL);
					this->head->set_info(-1000);
					return true;
				}
				else
				{
					this->head = current->get_next();
					this->head->set_prev(NULL);
					return true;
				}

			}
			else if (current == this->tail)
			{
				if (this->size() == 1)
				{
					this->tail->set_prev(NULL);
					this->tail->set_info(-1000);
					this->head->set_info(-1000);
					return true;
				}
				else {
					this->tail = current->get_prev();
					this->tail->set_next(NULL);
					return true;
				}

			}
			else {
				current->get_next()->set_prev(current->get_prev());
				current->get_prev()->set_next(current->get_next());
				current->set_next(NULL);
				current->set_prev(NULL);
				return true;
			}

		}

		return false;
	}

}

//time complex: BC=the first if is true, e is not in the bag, teta(1)
//WC:it will go through all the elems with e as tail info,teta(n)
//TC:O(n)
int SortedBag::nrOccurrences(TComp e) const
{
	if (not(this->search(e)))
		return 0;
	//we search for it and we count it
	int count = 0;
	DLLNode* current;
	current = this->head;
	while (current->get_next() != NULL && current->get_info() != e)
	{
		current = current->get_next();
	}
	return current->get_freq();
}

//time complex: BC=the first if is true, the bag is empty, teta(1)
//WC:it will go through all the elems,teta(n)
//TC:O(n)
int SortedBag::size() const
{
	if (this->head->get_info() == -1000)
		return 0;
	else {
		DLLNode* current;
		current = this->head;
		int count = 0;
		while (current->get_next() != NULL)
		{
			count += current->get_freq();
			current = current->get_next();
		}
		count += current->get_freq();
		return count;
	}

}
//makes only one step,always teta(1) time complexity and the same for the extra space complex
bool SortedBag::isEmpty() const
{
	if (this->head->get_info() == -1000)
		return true;
	return false;
}


//Time Complexity:
//BC: the first if==true if the bag is empty and complex= teta(1)
//WC:teta(n), because the while will look through all the elems from the bag
//TC: O(n)

//This function returns the TComp of the node with the smallest frequency from the SortedBag
//input:-
//preconditions:this is a sortedbag
//output: e
//postconditions: e is the TComp with the smallest frequency from the bag
TComp SortedBag::return_smallest_freq()
{
	if (this->head->get_info() == -1000)
		return NULL_TCOMP;
	int min = 10;
	TComp e;
	DLLNode* current;
	current = this->head;
	while (current->get_next() != NULL)
	{
		if (current->get_freq() < min)
		{
			min = current->get_freq();
			e = current->get_info();
		}
		current = current->get_next();
	}

	if (current->get_freq() < min)
	{
		min = current->get_freq();
		e = current->get_info();
	}
	return e;
	
}

//function return_smallest_freq(SB) is:
//if SB.head.get_info() = -1000 then
//return_smallest_freq < -NIL_TCOMP
//	endif
//
//	min = 10
//	TComp e
//	DLLNode* current
//	current < -SB.head
//
//	while current.get_next() != NIL execute :
//
//if current.get_freq() < min then
//	min < -current.get_freq()
//	e < -current.get_info()
//	endif
//
//	current < -current.get_next()
//
//	endwhile
//
//	if current.get_freq() < min then
//		min < -current.get_freq()
//		e < -current.get_info()
//		endif
//
//		return_smallest_freq < -e

void SortedBag::toStr()
{
	DLLNode* current;
	current = this->head;
	while (current->get_next() != NULL)
	{
		current->toStr();
		current = current->get_next();
	}
	current->toStr();
	cout << "\n";
}

//iterator,always teta(1) time complexity and the same for the extra space complex
SortedBagIterator SortedBag::iterator() const
{
	return SortedBagIterator(*this);
}

//destructor,always teta(n) time complexity and teta(1) for the extra space complex
SortedBag::~SortedBag() {
	
}
