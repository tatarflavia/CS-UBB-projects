#include "UI.h"
#include"Controller.h"
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

UI* createUI(Controller* ctrl) {
	UI* ui = (UI*)malloc(sizeof(UI));
	if (ui == NULL)
		return NULL;
	ui->con = ctrl;
	return ui;
}


void destroyUI(UI* ui) {
	if (ui == NULL)
		return NULL;
	destroyController(ui->con);
	free(ui);

}
void menu()
{
	printf("This is the menu for the real estate agency application:\n");
	printf("0.Exit\n");
	printf("1.addOffer\n");
	printf("2.printOffer\n");
	printf("3.deleteOffer\n");
	printf("4.updateOffer\n");
	printf("5.display all offers whose address contains a given string, sorted ascending by price\n");
	printf("6.display all offers, sorted ascending by price for a given surface\n");
	printf("7.see all offers of a given type, having the surface greater than a given value\n");
	printf("8.undo\n");
	printf("9.redo\n");
	
}

void addUI(UI* ui, char type[], char adress[],float surface,float price)
{
	if (addCon(ui->con, type, adress, surface, price) == 0)
		printf("The element already exists! Please change the address!\n");
	else 
	{
		printf("The offer has been succesfully added! Check the list!\n");
	}
}

void deleteOfferUI(UI* ui, char adr[]) 
{
	if (deleteCon(ui->con, adr) == 0)
		printf("The offer doesn't exist and couldn't be deleted.Please insert a valid address!\n");
	else { printf("The offer has been deleted! Check the list!\n"); }
}

void updateOfferUI(UI* ui, char adre[], char typ[],float surfac,float pric) 
{
	if (updateOfferCon(ui->con,adre, typ,surfac, pric) == 0)
		printf("The offer doesn't exist and couldn't be updated.Please insert a valid address!\n");
	else { printf("The offer has been updated! Check the list!\n"); }
}

void searchByStrUI(UI* ui, char strin[])
{
	DynamicArray* vector = createArray(100);
	seacrhByStrCon(ui->con, strin,vector);
	if (vector->size == 0)
		printf("No lines matching!\n");
	else
	{
		compareByPrice(ui->con, vector);
		int i = 0;
		printf("These are the offers that match:\n");
		for (i = 0; i < vector->size; i++)
		{
			toStr(&vector->offers[i]);
		}
	}
}

void searchBySurfaceUI(UI* ui, float sur) 
{
	DynamicArray* vect = createArray(100);
	seacrhBySurfaceCon(ui->con, sur, vect);
	if (vect->size == 0)
		printf("No lines matching!\n");
	else
	{
		compareByPrice(ui->con, vect);
		int i = 0;
		printf("These are the offers that match:\n");
		for (i = 0; i < vect->size; i++)
		{
			toStr(&vect->offers[i]);
		}
	}
}

void searchByTypeUI(UI* ui,char tip[],float surf) 
{
	DynamicArray* vecto = createArray(100);
	seacrhByTypeCon(ui->con, tip,surf,vecto);
	if (vecto->size == 0)
		printf("No lines matching!\n");
	else
	{
		int i = 0;
		printf("These are the offers that match:\n");
		for (i = 0; i < vecto->size; i++)
		{
			toStr(&vecto->offers[i]);
		}
	}
}

void printOffersUI(UI* ui) 
{
	printf("These are the offers:\n");
	int i = 0;
	for (i = 0; i < getArrayLength(ui->con); i++) 
	{
		toStr(&ui->con->array->offers[i]);
	}
}



int start(UI* ui)
{
	while (1)
	{
		menu();
		int n = 0;
		printf("Please give a number for the command:");
		scanf("%d", &n);
		if(n==0)
		{
			printf("You chose to exit!");
			return 0;
		}
		else if (n == 1) 
		{
			char type[30], adress[30];
			float surface = 0, price = 0;
			printf("Please give the type for the offer(can be  house, apartment or penthouse):");
			scanf("%s", type);
			printf("Please give the address for the offer(must be unique):");
			scanf("%s", adress);
			printf("Please give the surface for the offer:");
			scanf("%f", &surface);
			printf("Please give the price for the offer:");
			scanf("%f", &price);
			addUI(ui,type, adress, surface, price);
			
		}
		else if (n == 2) 
				{
					printOffersUI(ui);
				}
		else if (n == 3) 
		{
			char adr[30];
			printf("Please give the address for the offer you want to delete:");
			scanf("%s", adr);
			deleteOfferUI(ui,adr);
		}
		else if (n == 4)
		{
			char adre[30],typ[30];
			float surfac = 0, pric = 0;
			printf("Please give the address for the offer you want to update:");
			scanf("%s", adre);
			printf("Please give the new type for the offer(can be  house, apartment or penthouse):");
			scanf("%s", typ);
			printf("Please give the new surface for the offer:");
			scanf("%f", &surfac);
			printf("Please give the new price for the offer:");
			scanf("%f", &pric);
			updateOfferUI(ui, adre, typ, surfac, pric);
		}
		else if (n == 5)
		{
			char ch[100];
			printf("Do you want to enter an empty line or a word? Enter <line> or <the word you want>?\n");
			printf("Please enter your choice:");
			scanf("%s", ch);
			if(strcmp(ch,"line")==0)
				printOffersUI(ui);
			else
			{
				searchByStrUI(ui, ch);
			}
		}
		else if (n == 6) 
		{
			float sur = 0;
			printf("Please enter the surface choice:");
			scanf("%f", &sur);
			searchBySurfaceUI(ui, sur);
		}
		else if (n == 7)
		{
			float surf = 0;
			char tip[50];
			printf("Please enter the type you want to see:");
			scanf("%s", tip);
			printf("Please enter the value choice:");
			scanf("%f", &surf);
			searchByTypeUI(ui,tip, surf);
		}
		else if (n == 8)
		{
			if (undoCon(ui->con) == 0)
				printf("No more undo's!\n");
			else
				printf("Undo has been successful.Check the list!\n");
		}
		else if (n == 9)
		{
			if (redoCon(ui->con) == 0)
				printf("No more redo's!\n");
			else
				printf("Redo has been successful.Check the list!\n");
		}
		else 
		{ 
			printf("Invalid command!\n");
		}
	}
}