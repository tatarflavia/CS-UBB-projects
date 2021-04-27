#include<stdio.h>
#include <stdlib.h>
#include<string.h>
#include "Domain.h"


Offer* createOffer(char *type, char *adress, float Surface, float Price) 
{
	
	Offer* of = (Offer*)malloc(sizeof(Offer));
	of->type = (char*)malloc(sizeof(char)*(strlen(type) + 1));
	strcpy(of->type, type);
	of->adress = (char*)malloc(sizeof(char)*(strlen(adress) + 1));
	strcpy(of->adress, adress);
	of->price = Price;
	of->surface = Surface;
	return of;
}

char* getType(Offer* of)
{
	
	return of->type;
}

char* getAdress(Offer* of) 
{
	
	return of->adress;
}

float getSurface(Offer* of)
{
	
	return of->surface;
}
float getPrice(Offer* of)
{
	
	return of->price;
}

Offer* copyOffer(Offer* of) 
{
	
	if (of == NULL)
		return NULL;
	Offer* newof = createOffer(getType(of), getAdress(of), getSurface(of), getPrice(of));
	return newof;

}

void destroyOffer(Offer* of)
{
	
	free(of->type);
	free(of->adress);
	free(of);
}

void toStr(Offer* of) 
{
	
	printf("The Offer of a %s is located on %s, has a surface of %f and a price of %f.\n", of->type, of->adress, of->surface, of->price);
}