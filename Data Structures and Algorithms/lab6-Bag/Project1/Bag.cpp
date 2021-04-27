#include "Bag.h"
#include <iostream>

//time complexity:teta(1), only a couple of steps
//extra space complexity:teta(1)
TElem Bag::h(TElem k, int i) const
{
	if (k < 0)
	{
		k = this->hashCode(k);
		return (k%this->m + i * (1 + (k % (this->m - 1))))%this->m;
	}
	else { return (k%this->m + i * (1 + (k % (this->m - 1)))) % this->m;}
	
}

//time complexity:teta(1), only a step
//extra space complexity:teta(1)
TElem Bag::hashCode(TElem k) const
{
	return std::abs(k);
}

//time complexity:BC=teta(1) when we find in a couple of steps a divisor for number, WC=teta(number/2-1) when the number is prime
//OC:O(number/2)
//extra space complexity:teta(1)
bool Bag::isPrime(int number)
{
	

	for (int i = 2; i <= number / 2; ++i)
	{
		if (number%i == 0)
		{
			return false;
		}
	}
	return true;
}

//time complexity:BC=teta(1)+teta(numer/2) when we find quickly a prime number, WC=O(n)+teta(1) where n can be even infinity when finding hard a prime number
//extra space complexity:teta(1)
int Bag::firstPrime(int number)
{
	//finds the next prime number after number
	number++;
	while (not(this->isPrime(number)))
		number++;
	return number;
}

//constructor for the bag
//time complexity:teta(m) because we have that for for init for the array of state of elems(0 occupied, 1 deleted, -1 empty)
//extra space complexity:teta(1)
Bag::Bag()
{
	//init for the private attributes and init for the elems array of the hash table
	this->m = 17;
	this->sizeOfTheBag = 0;
	this->elems = new TElem[this->m];
	this->state_of_elems = new TElem[this->m];
	//init for the state, all empty at first
	for (int i = 0; i < this->m; i++)
		this->state_of_elems[i] = -1;
}


//time complexity:BC: when we find quicly an empty pos where to put e =>teta(1)
//WC: we don't find that quicly an empty pos=>O(m)+we need resing and rehashing: (first new init for the longer state_of elems):
//teta(prev_m*2)+(adding the old elems into the newer array)O(prev_m*2)+(add e from the function antet)O(prev_m*2) => teta(prev_m*2)
//prev_m*2=new_m
//OC:O(new m)
//extra space complexity:teta(1)
void Bag::add(TElem e)
{
	int i = 0;
	int pos = this->h(e, i);
	while (i < this->m && this->state_of_elems[pos] != -1 && this->state_of_elems[pos]!=1)
	{
		i++;
		pos = this->h(e, i);
	}
	//resize
	if (i == this->m)
	{
		//we need resize and rehashing of the table
		int prev_m = this->m;
		this->m = this->m * 2;
		this->m = this->firstPrime(this->m); //we need a prime number as to avoid collisions
		TElem* el = new TElem[this->m];
		TElem* state_el = new TElem[this->m];
		int i = 0;
		for (int l = 0; l < this->m; l++)  //teta(m)
			state_el[l] = -1;
		while (i < prev_m) //teta(prev_m)*O(prev_m*2) =>O(prev_m*2)
		{
			//if (this->state_of_elems[i] == 0 || this->state_of_elems[i]==1)
			//{
				int j = 0;
				int pos = this->h(this->elems[i], j);
				while (j < this->m && state_el[pos] != -1)
				{
					j++;
					pos = this->h(this->elems[i], j);
				}
				el[pos] = this->elems[i];
				state_el[pos] = 0;
				i++;
			//}
			//else { i++; }
		}
		//now add e from the add function antet
		//O(pre_m*2)
		int index = 0;
		int position = this->h(e, index);
		while (index < this->m && state_el[position] != -1)
		{
			index++;
			position = this->h(e, index);
		}
		this->sizeOfTheBag++;
		el[position] = e;
		state_el[position] = 0;
		delete[] this->elems;
		delete[] this->state_of_elems;
		this->elems = el;
		this->state_of_elems = state_el;
		//finished resising and rehashing
	}
	else {this->sizeOfTheBag++;
	this->elems[pos] = e;
	this->state_of_elems[pos] = 0;
	}
	
}

//time complexity:happy case: first if => O(m) from search or when we find quicly e, WC: when i is almost m =>O(m) + (the init for removing the last elem) teta(m) => teta(m)
//OC:O(m)
//extra space complexity:teta(1)
bool Bag::remove(TElem e)
{
	//happy case when we don't have what to remove : O(m) from search
	if (this->search(e) == 0)
		return false;
	int i = 0, counter = 0;
	int pos = this->h(e, i);
	while (i < this->m && this->state_of_elems[pos] != -1)
	{
		if (this->elems[pos] == e && this->state_of_elems[pos]!=1)
		{
			this->state_of_elems[pos] = 1;
			this->elems[pos] = INT_MAX; 
			this->sizeOfTheBag--;
			if (this->sizeOfTheBag == 0)
			{
				for (int j = 0; j < this->m; j++)
					this->state_of_elems[j] = -1;
			}
			return true;
		}
		else {i++;
		pos = this->h(e, i);}
		
	}
	return false;

}

//time complexity:BC when we find the first pos to be empty=>teta(1), WC when we go through all i's and not find e=> teta(m)
//OC:O(m)
//extra space complexity:teta(1)
bool Bag::search(TElem e) const
{
	int i = 0;
	int pos = this->h(e, i);
	//if first is empty, then =>false
	if (this->state_of_elems[pos] == -1)
		return false;
	else 
	{
		//deleted=occupied =>we go on, we can only talk about empty and not empty
		while (i < this->m && this->state_of_elems[pos] != -1)
		{
			if (e == this->elems[pos])
				return true;
			i++;
			pos = this->h(e, i);
		}
		if (e == this->elems[pos] )
			return true;
		return false;
	}
}


//time complexity: BC when we find quickly an empty pos as to finish the search => teta(1), WC=when we have to look thorogh all i's => teta(m)
//overall: O(m)
//extra space complexity:teta(1)
int Bag::nrOccurrences(TElem e) const
{
	int i = 0;
	int count = 0; //counter
	int pos = this->h(e, i);
	//if first is empty, then =>0
	if (this->state_of_elems[pos] == -1)
		return 0;
	else
	{
		//deleted=occupied =>we go on, we can only talk about empty and not empty
		while (i < this->m && this->state_of_elems[pos] != -1)
		{
			if (e == this->elems[pos])
				count++;
			i++;
			pos = this->h(e, i);
		}
		return count;
	}






	//int i = 0;
	//int count = 0;
	//int pos = this->h(e, i);
	//
	//while (i < this->m && this->elems[pos]!=30000)
	//{
	//	if (this->elems[pos] == e)
	//		count++;
	//	i++;
	//	pos = this->h(e, i);
	//}

	//return count;
}


//time complexity:teta(1), only a return
//extra space complexity:teta(1)
int Bag::size() const
{
	return this->sizeOfTheBag;
}

//iterator,always teta(1) time complexity and the same for the extra space complex
BagIterator Bag::iterator() const
{
	return BagIterator(*this);
}

//time complexity:teta(1), only a verification to be done
//extra space complexity:teta(1)
bool Bag::isEmpty() const
{
	if (this->sizeOfTheBag == 0)
		return true;
	else return false;
	
}

void Bag::toStr()
{
	for (int i = 0; i < this->m; i++)
	{
		std::cout << this->elems[i] << " ";
	}
	std::cout << "\n";
	for (int i = 0; i < this->m; i++)
	{
		std::cout << this->state_of_elems[i] << " ";
	}
	std::cout << "\n";


	//TElem* newer = new TElem[this->m];
	//newer = this->elems;
	//for (int i = 0; i < this->m-1; i++)
	//	for(int j=i+1;j<this->m;j++)
	//		if (newer[i] > newer[j])
	//		{
	//			TElem aux = newer[i];
	//			newer[i] = newer[j];
	//			newer[j] = aux;
	//		}
	//for (int i = 0; i < this->m; i++)
	//	std::cout << newer[i] << ",";
	std::cout << "\n";
	std::cout << "\n";
}

//destructor for the bag
//time complexity:teta(1), only a couple of steps
//extra space complexity:teta(1)
Bag::~Bag()
{
	delete[] this->elems;
	delete[] this->state_of_elems;
}
