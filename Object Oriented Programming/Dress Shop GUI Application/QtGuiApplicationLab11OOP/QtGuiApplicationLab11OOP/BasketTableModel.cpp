#include "BasketTableModel.h"



BasketTableModel::BasketTableModel(RepoForBasket& basket) :basket{basket}
{
}

int BasketTableModel::rowCount(const QModelIndex & parent) const
{
	return this->basket.get_size();
}

int BasketTableModel::columnCount(const QModelIndex & parent) const
{
	return 5;
}

QVariant BasketTableModel::data(const QModelIndex & index, int role) const
{
	int row = index.row();
	int column = index.column();
	WeddingDress dress = *this->basket.get_elem_from_pos(row);
	if (role == Qt::DisplayRole)
	{
		if (column == 0)
			return QString::fromStdString(std::to_string(dress.get_price()));
		else if(column==1)
			return QString::fromStdString(std::to_string(dress.get_size()));
		else if (column == 2)
			return QString::fromStdString(dress.get_colour());
		else if (column == 3)
			return QString::fromStdString(std::to_string(dress.get_quantity()));
		else if (column == 4)
			return QString::fromStdString(dress.get_photograph());
	}
	else return QVariant();
}

QVariant BasketTableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole && orientation == Qt::Horizontal)
	{
		if (section == 0)
			return "Price";
		else if (section == 1)
			return "Size";
		else if (section == 2)
			return "Colour";
		else if (section == 3)
			return "Quantity";
		else if (section == 4)
			return "Photograph";
	}
	return QVariant();
}

BasketTableModel::~BasketTableModel()
{
}
