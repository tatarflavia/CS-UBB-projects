/********************************************************************************
** Form generated from reading UI file 'UserDressShop.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_USERDRESSSHOP_H
#define UI_USERDRESSSHOP_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_UserDressShop
{
public:
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QListWidget *repoBasket;
    QPushButton *BuyDress;
    QListWidget *shoppingBasket;
    QPushButton *shopping_basket_button;

    void setupUi(QWidget *UserDressShop)
    {
        if (UserDressShop->objectName().isEmpty())
            UserDressShop->setObjectName(QString::fromUtf8("UserDressShop"));
        UserDressShop->resize(460, 445);
        UserDressShop->setStyleSheet(QString::fromUtf8("\n"
"background-color: rgb(255, 255, 127);\n"
"background-color: rgb(255, 255, 127);"));
        verticalLayout = new QVBoxLayout(UserDressShop);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        repoBasket = new QListWidget(UserDressShop);
        repoBasket->setObjectName(QString::fromUtf8("repoBasket"));
        repoBasket->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(255, 85, 255, 255), stop:1 rgba(255, 255, 255, 255));\n"
"background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(170, 235, 255, 255), stop:1 rgba(255, 255, 255, 255));"));

        horizontalLayout->addWidget(repoBasket);

        BuyDress = new QPushButton(UserDressShop);
        BuyDress->setObjectName(QString::fromUtf8("BuyDress"));
        BuyDress->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(0, 0, 0, 255), stop:1 rgba(255, 255, 255, 255));"));

        horizontalLayout->addWidget(BuyDress);

        shoppingBasket = new QListWidget(UserDressShop);
        shoppingBasket->setObjectName(QString::fromUtf8("shoppingBasket"));
        shoppingBasket->setStyleSheet(QString::fromUtf8("\n"
"background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(255, 170, 255, 255), stop:1 rgba(255, 255, 255, 255));"));

        horizontalLayout->addWidget(shoppingBasket);


        verticalLayout->addLayout(horizontalLayout);

        shopping_basket_button = new QPushButton(UserDressShop);
        shopping_basket_button->setObjectName(QString::fromUtf8("shopping_basket_button"));
        shopping_basket_button->setStyleSheet(QString::fromUtf8("background-color: qconicalgradient(cx:0.5, cy:0.5, angle:0, stop:0 rgba(170, 255, 173, 255), stop:1 rgba(255, 255, 255, 255));"));

        verticalLayout->addWidget(shopping_basket_button);


        retranslateUi(UserDressShop);

        QMetaObject::connectSlotsByName(UserDressShop);
    } // setupUi

    void retranslateUi(QWidget *UserDressShop)
    {
        UserDressShop->setWindowTitle(QApplication::translate("UserDressShop", "UserDressShop", nullptr));
        BuyDress->setText(QApplication::translate("UserDressShop", "BUY", nullptr));
        shopping_basket_button->setText(QApplication::translate("UserDressShop", "See Shopping Basket", nullptr));
    } // retranslateUi

};

namespace Ui {
    class UserDressShop: public Ui_UserDressShop {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_USERDRESSSHOP_H
