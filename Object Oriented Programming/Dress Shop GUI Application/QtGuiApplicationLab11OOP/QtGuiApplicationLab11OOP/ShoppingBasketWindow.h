#pragma once

#include <QWidget>
#include "ui_ShoppingBasketWindow.h"
#include "BasketTableModel.h"

class ShoppingBasketWindow : public QWidget
{
	Q_OBJECT

private:
	BasketTableModel* my_model;
public:
	ShoppingBasketWindow(BasketTableModel* my_model,QWidget *parent = Q_NULLPTR);
	~ShoppingBasketWindow();

private:
	Ui::ShoppingBasketWindow ui;
};
