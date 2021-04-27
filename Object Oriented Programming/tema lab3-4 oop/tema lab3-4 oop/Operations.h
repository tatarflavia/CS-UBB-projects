#pragma once
#include "Domain.h"

typedef struct 
{
	Offer* offer;
	char* operType;
}Operation;

Operation* createOper(Offer* offer, char* operType);
//function that creates a pointer to the Operation structure
	//input:offer,opertype
	//#preconditions : offer is a pointer to the Offer structure,opertype is a char*
	//output : oper
	//postconditions : oper is a pointer to the Operation structure

char* getOperType(Operation* oper);
//function that gets the type of the offer
	//input:oper
	//#preconditions : oper is a pointer to Operation structure
	//output : type
	//postconditions : type is a string*

Offer* getOffer(Operation* oper);
//function that gets the offer of the operation
	//input:oper
	//#preconditions : oper is a pointer to Operation structure
	//output : of
	//postconditions : of is a pointer to the Offer structure
Operation* copyOper(Operation* oper);
//function that returns a copy of a pointer to the Operation structure
	//input:oper
	//#preconditions : oper is a pointer to Operation structure
	//output : newoper
	//postconditions : newoper is a newOperation pointer to the stucture
void destroyOper(Operation* oper);
//function that destroys an operation
	//input:oper
	//#preconditions : oper is a pointer to Operation structure
	//output : -
	//postconditions : the operation is destroyed



typedef struct 
{
	Operation* opers[200];
	int len;
}StackForOperations;

StackForOperations* createStackForOpers();
//function that creates a pointer to the StackForOperations structure
	//input:-
	//#preconditions : -
	//output : stack 
	//postconditions :stack is a pointer to the StackForOperations structure
int isEmpty(StackForOperations* stack);
//function that checks if the stack is empty
	//input:stack
	//#preconditions : stack is a pointer to the StackForOperations structure
	//output : 0 or 1
	//postconditions :1 if the stack is empty and 0 if it is not
int isFull(StackForOperations* stack);
//function that checks if the stack is full
	//input:stack
	//#preconditions : stack is a pointer to the StackForOperations structure
	//output : 0 or 1
	//postconditions :1 if the stack is full and 0 if it is not
void pushOperation(StackForOperations* stack, Operation* oper);
//function that pushes at the final of the stack a copy of the operation oper
	//input:ostack,oper
	//#preconditions :  stack is a pointer to StackForOperations structure, oper is a pointer to the Operation structure
	//output : stack->len++
	//postconditions : the operation is destroyed
Operation* pop(StackForOperations* stack);
//function that returns the operation from the last position of the stack
	//input:stack
	//#preconditions :  stack is a pointer to StackForOperations structure
	//output : stack->opers[stack->len]
	//postconditions :  stack->len--,the operation is returned
void destroyStack(StackForOperations* stack);
//function that destroys a stack
	//input:stack
	//#preconditions : stack is a pointer to StackForOperations structure
	//output : -
	//postconditions : the stack is destroyed