#pragma once
#include "Domain.h"
typedef struct {
	Offer* offers;
	int size;
	int capacity;
}DynamicArray;

DynamicArray* createArray(int capacity);

void resize(DynamicArray* array);
void destroy(DynamicArray* array);
void add(DynamicArray* array,Offer offers);
void deleteOffer(DynamicArray* array, int pos);
void updateOffer(DynamicArray* array, Offer* of, char typ[], float surfac, float pric);
void interchange(DynamicArray* array, Offer* of1, Offer* of2);
int getSize(DynamicArray* array);
