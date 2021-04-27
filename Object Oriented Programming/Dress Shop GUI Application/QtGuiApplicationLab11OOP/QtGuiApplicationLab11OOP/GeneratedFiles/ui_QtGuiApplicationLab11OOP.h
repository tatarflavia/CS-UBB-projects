/********************************************************************************
** Form generated from reading UI file 'QtGuiApplicationLab11OOP.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_QTGUIAPPLICATIONLAB11OOP_H
#define UI_QTGUIAPPLICATIONLAB11OOP_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_QtGuiApplicationLab11OOPClass
{
public:
    QWidget *centralWidget;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QPushButton *AdministratorMode;
    QPushButton *UserMode;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *QtGuiApplicationLab11OOPClass)
    {
        if (QtGuiApplicationLab11OOPClass->objectName().isEmpty())
            QtGuiApplicationLab11OOPClass->setObjectName(QString::fromUtf8("QtGuiApplicationLab11OOPClass"));
        QtGuiApplicationLab11OOPClass->resize(683, 508);
        QtGuiApplicationLab11OOPClass->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(255, 0, 0, 255), stop:0.166 rgba(255, 255, 0, 255), stop:0.333 rgba(0, 255, 0, 255), stop:0.5 rgba(0, 255, 255, 255), stop:0.666 rgba(0, 0, 255, 255), stop:0.833 rgba(255, 0, 255, 255), stop:1 rgba(255, 0, 0, 255));\n"
""));
        centralWidget = new QWidget(QtGuiApplicationLab11OOPClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        verticalLayout = new QVBoxLayout(centralWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        AdministratorMode = new QPushButton(centralWidget);
        AdministratorMode->setObjectName(QString::fromUtf8("AdministratorMode"));
        AdministratorMode->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(152, 152, 152, 255), stop:1 rgba(255, 255, 255, 255));"));

        horizontalLayout->addWidget(AdministratorMode);

        UserMode = new QPushButton(centralWidget);
        UserMode->setObjectName(QString::fromUtf8("UserMode"));
        UserMode->setStyleSheet(QString::fromUtf8("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0, stop:0 rgba(152, 152, 152, 255), stop:1 rgba(255, 255, 255, 255));"));

        horizontalLayout->addWidget(UserMode);


        verticalLayout->addLayout(horizontalLayout);

        QtGuiApplicationLab11OOPClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(QtGuiApplicationLab11OOPClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 683, 26));
        QtGuiApplicationLab11OOPClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(QtGuiApplicationLab11OOPClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        QtGuiApplicationLab11OOPClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(QtGuiApplicationLab11OOPClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        QtGuiApplicationLab11OOPClass->setStatusBar(statusBar);

        retranslateUi(QtGuiApplicationLab11OOPClass);

        QMetaObject::connectSlotsByName(QtGuiApplicationLab11OOPClass);
    } // setupUi

    void retranslateUi(QMainWindow *QtGuiApplicationLab11OOPClass)
    {
        QtGuiApplicationLab11OOPClass->setWindowTitle(QApplication::translate("QtGuiApplicationLab11OOPClass", "QtGuiApplicationLab11OOP", nullptr));
        AdministratorMode->setText(QApplication::translate("QtGuiApplicationLab11OOPClass", "ADMINISTRATOR", nullptr));
        UserMode->setText(QApplication::translate("QtGuiApplicationLab11OOPClass", "USER", nullptr));
    } // retranslateUi

};

namespace Ui {
    class QtGuiApplicationLab11OOPClass: public Ui_QtGuiApplicationLab11OOPClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_QTGUIAPPLICATIONLAB11OOP_H
