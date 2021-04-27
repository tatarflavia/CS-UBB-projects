#include "Operations.h"
#include<stdio.h>
#include<stdlib.h>
Operation* createOper(Offer* offer, char* operType) 
{
	Operation* oper = (Operation*)malloc(sizeof(Operation));
	oper->offer = copyOffer(offer);
	if (operType != NULL)
	{
		oper->operType = (char*)malloc(sizeof(char)*(strlen(operType) + 1));
		strcpy(oper->operType, operType);
	}
	else
		oper->operType = NULL;
	return oper;
}
char* getOperType(Operation* oper) 
{
	return oper->operType;
}
Offer* getOffer(Operation* oper) {
	return oper->offer;
}
Operation* copyOper(Operation* oper) 
{
	if (oper == NULL)
		return NULL;
	Operation* newOper = createOper(getOffer(oper), getOperType(oper));
	return newOper;
}
void destroyOper(Operation* oper) 
{
	if (oper == NULL)
		return NULL;
	destroyOffer(oper->offer);
	free(oper->operType);
	free(oper);
}

StackForOperations* createStackForOpers() {
	StackForOperations* stack = (StackForOperations*)malloc(sizeof(StackForOperations*));
	stack->len = 0;
	return stack;
}

int isEmpty(StackForOperations* stack) 
{
	if (stack->len == 0)
		return 1;
	return 0;
}
int isFull(StackForOperations* stack) {
	if (stack->len == 200)
		return 1;
	return 0;
}
void pushOperation(StackForOperations* stack, Operation* oper) 
{
	stack->opers[stack->len] = copyOper(oper);
	stack->len++;
}
Operation* pop(StackForOperations* stack)
{
	if (isEmpty(stack) == 1)
		return 0;
	stack->len--;
	return stack->opers[stack->len];
	
}
void destroyStack(StackForOperations* stack) 
{
	if (stack == NULL)
		return NULL;
	int i = 0;
	for (i = 0; i < stack->len; i++)
		destroyOper(stack->opers[i]);
	free(stack);
}