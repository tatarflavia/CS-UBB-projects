#include<stdio.h>
#include <stdlib.h>
#include<string.h>
#include "dynamicArray.h"

DynamicArray* createArray(int Capacity) {
	DynamicArray* dinarr = (DynamicArray*)malloc(sizeof(DynamicArray));
	if (dinarr == NULL)
		return NULL;
	dinarr->capacity = Capacity;
	dinarr->size = 0;
	dinarr->offers = (Offer*)malloc(sizeof(Offer)*Capacity);
	if (dinarr->offers == NULL)
		return NULL;
	return dinarr;

}

void destroy(DynamicArray* array) {
	if (array == NULL)
		return NULL;
	free(array->offers);
	free(array);
	
}

void resize(DynamicArray* array)
{
	if (array == NULL)
		return NULL;
	array->capacity = array->capacity * 2;
	Offer* new = (Offer*)realloc(array->offers, sizeof(Offer)*array->capacity);
	if (array == NULL)
		return NULL;
	array->offers = new;

}
void add(DynamicArray* array, Offer of) {
	//checks for resize if we must do it
	if (array == NULL)
		return NULL;
	if (array->size == array->capacity)
		resize(array);
	array->offers[array->size] = of;
	array->size++;
}

void deleteOffer(DynamicArray* array, int pos)
{
	int i = 0;//v[i]=v[i+1]
	for (i = pos; i < array->size-1; i++) 
	{
		array->offers[i] = array->offers[i + 1];
	}
	array->size--;
}

void updateOffer(DynamicArray* array, Offer* of,char typ[],float surfac,float pric) 
{
	of->price = pric;
	of->surface = surfac;
	of->type = typ;
}

void interchange(DynamicArray* array, Offer* of1, Offer* of2) 
{
	Offer* aux=createOffer("apartment", "Buna Ziua nr.50", 90, 600);
	aux->adress = of1->adress;
	aux->price = of1->price;
	aux->surface = of1->surface;
	aux->type = of1->type;
	of1->adress = of2->adress;
	of1->price = of2->price;
	of1->surface = of2->surface;
	of1->type = of2->type;
	of2->adress = aux->adress;
	of2->price = aux->price;
	of2->surface = aux->surface;
	of2->type = aux->type;

}

void getAarray(DynamicArray* array) 
{
	return array;
}

int getSize(DynamicArray* array) 
{
	return array->size;
}