#include "ShoppingBasketWindow.h"

ShoppingBasketWindow::ShoppingBasketWindow(BasketTableModel* my_model,QWidget *parent)
	: my_model{my_model},QWidget(parent)
{
	ui.setupUi(this);
	this->ui.shopping_basket_view->setModel(my_model);
	this->ui.shopping_basket_view->resizeColumnsToContents();
}

ShoppingBasketWindow::~ShoppingBasketWindow()
{
}
