#pragma once
#include "dynamicArray.h"
#include "Domain.h"
#include "Operations.h"

typedef struct {
	DynamicArray* array;
	StackForOperations* undo;
	StackForOperations* redo;
}Controller;

Controller* createController(DynamicArray* array,StackForOperations* undo, StackForOperations* redo);

void destroyController(Controller* ctrl);

int undoCon(Controller* con);

int addCon(Controller* con, char type[], char adress[], float surface, float price);

int deleteCon(Controller* con, char adr[]);

int updateOfferCon(Controller* con, char adre[], char typ[], float surfac, float pric);

void seacrhByStrCon(Controller* con,char strin[],DynamicArray* vector);

void seacrhBySurfaceCon(Controller* con,float sur,DynamicArray* vect);

void seacrhByTypeCon(Controller* con, char tip[], float surf, DynamicArray* vecto);


void compareByPrice(Controller* con, DynamicArray* vector);

int getArrayLength(Controller* ctrl);