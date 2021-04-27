#include"UI.h"
#include"dynamicArray.h"
#include "Controller.h"
#include "Domain.h"
#include<stdio.h>
#include <crtdbg.h>
#include "Operations.h"


int main()
{
	//this represents the main function and the entry point of the programm that ends after the user chooses to exit the programm
	//create the stuctures, add some elements in the repository, start the programm,destroy the structures created
	DynamicArray* repo = createArray(100);
	StackForOperations* undo = createStackForOpers();
	StackForOperations* redo = createStackForOpers();
	Controller* ctrl = createController(repo,undo,redo);
	UI* ui = createUI(ctrl);
	add(repo, *createOffer("house", "Arnsberg", 66.8, 800));
	add(repo, *createOffer("apartment", "Buna", 90, 600));
	add(repo, *createOffer("penthouse", "Unirii", 200, 890));
	add(repo, *createOffer("house", "Closca", 90, 500));
	add(repo, *createOffer("penthouse", "New", 300, 1600));
	add(repo, *createOffer("apartment", "Republicii", 40, 300));
	add(repo, *createOffer("house", "Arnsberg2", 70, 700));
	add(repo, *createOffer("penthouse", "Florii", 150, 1000));
	add(repo, *createOffer("aparment", "Unirii2", 30, 300));
	add(repo, *createOffer("house", "Alba", 60, 600));
	start(ui);
	destroyUI(ui);
	_CrtDumpMemoryLeaks();
	
	return 0;
}