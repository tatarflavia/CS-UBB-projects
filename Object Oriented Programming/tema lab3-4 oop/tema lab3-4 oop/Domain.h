#pragma once

typedef struct
{
	char *type;
	char *adress;
	float surface;
	float price;
}Offer;

Offer* createOffer(char *type, char *adress, float surface, float price);
//function that creates a pointer to the Offer structure
	//input:type, adress, Surface, Price
	//#preconditions : char *type, char *adress, float Surface, float Price
	//output : of
	//postconditions : of is a pointer to the Offer structure

char* getType(Offer* of);
//function that gets the type of the offer
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : type
	//postconditions : type is a string*
char* getAdress(Offer* of);
//function that gets the address of the offer
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : address
	//postconditions : address is a string*
float getSurface(Offer* of);
//function that gets the surface of the offer
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : surface
	//postconditions : surface is a float
float getPrice(Offer* of);
//function that gets the price of the offer
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : price
	//postconditions : price is a string*
Offer* copyOffer(Offer* of);
//function that returns a copy of a pointer to the Offer structure
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : newof
	//postconditions : newof is a newOffer pointer to the stucture

void destroyOffer(Offer* of);
//function that destroys an offer
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : -
	//postconditions : the offer is destroyed
void toStr(Offer *of);
//function that writes an offer
	//input:of
	//#preconditions : of is a pointer to Offer structure
	//output : -
	//postconditions :