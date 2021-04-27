/********************************************************************************
** Form generated from reading UI file 'ShoppingBasketWindow.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_SHOPPINGBASKETWINDOW_H
#define UI_SHOPPINGBASKETWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QTableView>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_ShoppingBasketWindow
{
public:
    QHBoxLayout *horizontalLayout;
    QTableView *shopping_basket_view;

    void setupUi(QWidget *ShoppingBasketWindow)
    {
        if (ShoppingBasketWindow->objectName().isEmpty())
            ShoppingBasketWindow->setObjectName(QString::fromUtf8("ShoppingBasketWindow"));
        ShoppingBasketWindow->resize(400, 300);
        horizontalLayout = new QHBoxLayout(ShoppingBasketWindow);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        shopping_basket_view = new QTableView(ShoppingBasketWindow);
        shopping_basket_view->setObjectName(QString::fromUtf8("shopping_basket_view"));
        shopping_basket_view->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:1, stop:0 rgba(220, 170, 255, 255), stop:1 rgba(255, 255, 255, 255));"));

        horizontalLayout->addWidget(shopping_basket_view);


        retranslateUi(ShoppingBasketWindow);

        QMetaObject::connectSlotsByName(ShoppingBasketWindow);
    } // setupUi

    void retranslateUi(QWidget *ShoppingBasketWindow)
    {
        ShoppingBasketWindow->setWindowTitle(QApplication::translate("ShoppingBasketWindow", "ShoppingBasketWindow", nullptr));
    } // retranslateUi

};

namespace Ui {
    class ShoppingBasketWindow: public Ui_ShoppingBasketWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_SHOPPINGBASKETWINDOW_H
