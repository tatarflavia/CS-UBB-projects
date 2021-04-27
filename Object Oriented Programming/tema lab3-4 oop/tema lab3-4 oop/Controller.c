#include "Controller.h"
#include "Domain.h"
#include "Operations.h"
#include<stdlib.h>
#include<string.h>
#include<stdio.h>

int addCon(Controller* con, char type[], char adress[], float surface, float price) {
	int i = 0;
	for (i = 0; i < getArrayLength(con); i++) 
	{
		if (strcmp(con->array->offers[i].adress, adress) == 0)
			return 0;
	}
	Offer* of = createOffer(type, adress, surface, price);
	Offer* copie = copyOffer(of);
	add(con->array, *copie);
	Operation* oper = createOper(of, "add");
	pushOperation(con->undo, oper);
	destroyOper(oper);
	destroyOffer(of);
	return 1;
}

int undoCon(Controller* con)
{
	while (1) {if (isEmpty(con->undo))
	{
		return 0;
	}

	Operation* oper = pop(con->undo);

	if (strcmp(getOperType(oper), "add") == 0)
	{
		Offer* of = getOffer(oper);
		char adre[100];
		strcpy(adre, getAdress(of));
		for (int i = 0; i < getArrayLength(con); i++)
		{
			if (strcmp(con->array->offers[i].adress, adre) == 0)
			{
				deleteOffer(con->array, i);
			}
		}
		Operation* op2 = createOper(of, "delete");
		pushOperation(con->redo, op2);
		destroyOper(op2);
		destroyOper(oper);
		return 1;
	}
	else if (strcmp(getOperType(oper), "delete") == 0)
	{
		Offer* of = getOffer(oper);
		char adr[100], type[100];
		float surface = 0, price = 0;
		strcpy(adr, getAdress(of));
		strcpy(type, getType(of));
		surface = getSurface(of);
		price = getPrice(of);
		//addCon(con, type, adr, surface, price);
		Offer* oferta = createOffer(type, adr, surface, price);
		Offer* copie = copyOffer(oferta);
		add(con->array, *copie);
		destroyOffer(oferta);
		Operation* op1 = createOper(of, "add");
		pushOperation(con->redo, op1);
		destroyOper(op1);
		destroyOper(oper);
		return 1;
	}
	else if (strcmp(getOperType(oper), "update") == 0)
	{
		//we make an delete for the first offer on the stack which is the updated piece
		Offer* of = getOffer(oper);
		char adresa[100];
		strcpy(adresa, getAdress(of));
		for (int i = 0; i < getArrayLength(con); i++)
		{
			if (strcmp(con->array->offers[i].adress, adresa) == 0)
			{
				deleteOffer(con->array, i);
			}
		}
		Operation* oppp = createOper(of, "update");
		pushOperation(con->redo, oppp);
		destroyOper(oppp);
		destroyOper(oper);

		//we add the lost piece,the piece before the update

		Operation* op = pop(con->undo);
		Offer* ofer = getOffer(op);
		char adres[100], typ[100];
		float surfac = 0, pric = 0;
		strcpy(adres, getAdress(ofer));
		strcpy(typ, getType(ofer));
		surfac = getSurface(ofer);
		pric = getPrice(ofer);
		Offer* ofert = createOffer(typ, adres, surfac, pric);
		Offer* copie = copyOffer(ofert);
		add(con->array, *copie);
		destroyOffer(ofert);
		Operation* opp = createOper(ofer, "update");
		pushOperation(con->redo, opp);
		destroyOper(opp);
		destroyOper(op);
		return 1;
	}}
	
	
}


int redoCon(Controller* con)
{
	while (1) {
		if (isEmpty(con->redo))
		{
			return 0;
		}

		Operation* oper = pop(con->redo);

		if (strcmp(getOperType(oper), "add") == 0)
		{
			Offer* of = getOffer(oper);
			char adre[100];
			strcpy(adre, getAdress(of));
			for (int i = 0; i < getArrayLength(con); i++)
			{
				if (strcmp(con->array->offers[i].adress, adre) == 0)
				{
					deleteOffer(con->array, i);
				}
			}
			
			destroyOper(oper);
			return 1;
		}
		else if (strcmp(getOperType(oper), "delete") == 0)
		{
			Offer* of = getOffer(oper);
			char adr[100], type[100];
			float surface = 0, price = 0;
			strcpy(adr, getAdress(of));
			strcpy(type, getType(of));
			surface = getSurface(of);
			price = getPrice(of);
			//addCon(con, type, adr, surface, price);
			Offer* oferta = createOffer(type, adr, surface, price);
			Offer* copie = copyOffer(oferta);
			add(con->array, *copie);
			destroyOffer(oferta);
			destroyOper(oper);
			return 1;
		}
		else if (strcmp(getOperType(oper), "update") == 0)
		{
			//we add the lost piece,the piece before the update

			
			Offer* ofer = getOffer(oper);
			char adres[100], typ[100];
			float surfac = 0, pric = 0;
			strcpy(adres, getAdress(ofer));
			strcpy(typ, getType(ofer));
			surfac = getSurface(ofer);
			pric = getPrice(ofer);
			Offer* ofert = createOffer(typ, adres, surfac, pric);
			Offer* copie = copyOffer(ofert);
			add(con->array, *copie);
			destroyOffer(ofert);
			destroyOper(oper);
			//we make an delete for the first offer on the stack which is the updated piece
			Operation* op = pop(con->redo);
			Offer* of = getOffer(op);
			char adresa[100];
			strcpy(adresa, getAdress(of));
			for (int i = 0; i < getArrayLength(con); i++)
			{
				if (strcmp(con->array->offers[i].adress, adresa) == 0)
				{
					deleteOffer(con->array, i);
				}
			}
			destroyOper(op);

			return 1;
		}
	}


}

Controller* createController(DynamicArray* array, StackForOperations* undo, StackForOperations* redo)
{
	Controller* ctrl = (Controller*)malloc(sizeof(Controller));
	if (ctrl == NULL)
		return NULL;
	ctrl->array = array;
	ctrl->undo = undo;
	ctrl->redo = redo;
	return ctrl;
}

void destroyController(Controller* ctrl) {
	if (ctrl == NULL)
		return NULL;
	destroy(ctrl->array);
	destroyStack(ctrl->undo);
	free(ctrl);
}

int deleteCon(Controller* ctrl, char adr[]) 
{
	int i = 0;
	for (i = 0; i < getArrayLength(ctrl); i++)
	{
		if (strcmp(ctrl->array->offers[i].adress, adr) == 0)
		{
			Operation* op = createOper(&ctrl->array->offers[i], "delete");
			pushOperation(ctrl->undo, op);
			destroyOper(op);
			deleteOffer(ctrl->array, i);
			return 1;
		}
			
	}
	return 0;
}

int updateOfferCon(Controller* ctrl, char adre[], char typ[], float surfac, float pric) 
{
	int i = 0;
	for (i = 0; i < getArrayLength(ctrl); i++)
	{
		if (strcmp(ctrl->array->offers[i].adress, adre) == 0)
		{
			
			Operation* oper = createOper(&ctrl->array->offers[i], "update");
			pushOperation(ctrl->undo,oper);
			destroyOper(oper);
			updateOffer(ctrl->array, &ctrl->array->offers[i],typ,surfac,pric);
			Operation* op = createOper(&ctrl->array->offers[i], "update");
			pushOperation(ctrl->undo,op);
			destroyOper(op);
			return 1;
		}

	}
	return 0;
}

void seacrhByStrCon(Controller* ctrl, char strin[],DynamicArray* vector) 
{
	int i = 0;
	for (i = 0; i < getArrayLength(ctrl); i++)
	{
		if (strstr(ctrl->array->offers[i].adress, strin)!=NULL)
		{
			add(vector, ctrl->array->offers[i]);
		}

	}

	/*printf("These are the offers that match:\n");
	for (j = 0; j < vector->size; j++)
	{
		printf("printeaza ce a luat444");
		toStr(&vector->offers[j]);
	}
	return vector;*/
	//destroy(vector);
	//vector = NULL;

}

void seacrhBySurfaceCon(Controller* ctrl, float sur, DynamicArray* vect)
{
	int i = 0;
	for (i = 0; i < getArrayLength(ctrl); i++)
	{
		if (ctrl->array->offers[i].surface==sur)
		{
			add(vect, ctrl->array->offers[i]);
		}

	}
}

void seacrhByTypeCon(Controller* con, char tip[], float surf, DynamicArray* vecto) 
{
	int i = 0;
	for (i = 0; i < getArrayLength(con); i++)
	{
		if (strcmp(con->array->offers[i].type,tip)==0 && con->array->offers[i].surface>surf)
		{
			add(vecto, con->array->offers[i]);
		}

	}
}

void compareByPrice(Controller* con, DynamicArray* vector)
{
	int i = 0, j = 0;
	for(i=0;i<vector->size-1;i++)
	{ for (j = i+1; j < vector->size; j++)
			{
		if (vector->offers[i].price > vector->offers[j].price)
			interchange(vector,&vector->offers[i], &vector->offers[j]);
			}
	
	}
}

int getArrayLength(Controller* ctrl)
{
	return getSize(ctrl->array);
}