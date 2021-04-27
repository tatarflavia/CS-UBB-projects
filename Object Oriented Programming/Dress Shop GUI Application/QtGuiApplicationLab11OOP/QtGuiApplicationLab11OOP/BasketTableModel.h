#pragma once
#include <QAbstractTableModel>
#include "RepoForBasket.h"

class BasketTableModel:public QAbstractTableModel
{
private:
	RepoForBasket& basket;

public:
	BasketTableModel(RepoForBasket& basket);
	int QAbstractItemModel::columnCount(const QModelIndex &parent = QModelIndex()) const;
	int QAbstractItemModel::rowCount(const QModelIndex &parent = QModelIndex()) const;
	QVariant QAbstractItemModel::data(const QModelIndex &index, int role = Qt::DisplayRole) const;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const;
	~BasketTableModel();
};

