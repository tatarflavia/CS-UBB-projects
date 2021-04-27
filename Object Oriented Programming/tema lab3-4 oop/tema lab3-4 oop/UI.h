#pragma once
#include "Controller.h"


typedef struct {
	Controller* con;
}UI;

UI* createUI(Controller* ctrl);


void destroyUI(UI* ui);

void addUI(UI* ui, char type[], char adress[], float surface, float price);

void deleteOfferUI(UI* ui, char adr[]);

void updateOfferUI(UI* ui, char adre[], char typ[], float surfac, float pric);

void searchByStrUI(UI* ui, char strin[]);

void searchBySurfaceUI(UI* ui, float sur);

void searchByTypeUI(UI* ui, char tip[], float surf);

void printOffersUI(UI* ui);

void menu();

int start(UI* ui);