/********************************************************************************
** Form generated from reading UI file 'Task_manager.ui'
**
** Created by: Qt User Interface Compiler version 5.12.3
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TASK_MANAGER_H
#define UI_TASK_MANAGER_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Task_managerClass
{
public:
    QWidget *centralWidget;
    QHBoxLayout *horizontalLayout_3;
    QListWidget *repo_list;
    QVBoxLayout *verticalLayout_2;
    QListWidget *total_duration_list;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label_priority;
    QLineEdit *priority_lineEdit;
    QPushButton *duration_button;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *Task_managerClass)
    {
        if (Task_managerClass->objectName().isEmpty())
            Task_managerClass->setObjectName(QString::fromUtf8("Task_managerClass"));
        Task_managerClass->resize(781, 517);
        centralWidget = new QWidget(Task_managerClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        horizontalLayout_3 = new QHBoxLayout(centralWidget);
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        repo_list = new QListWidget(centralWidget);
        repo_list->setObjectName(QString::fromUtf8("repo_list"));

        horizontalLayout_3->addWidget(repo_list);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        total_duration_list = new QListWidget(centralWidget);
        total_duration_list->setObjectName(QString::fromUtf8("total_duration_list"));

        verticalLayout_2->addWidget(total_duration_list);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        label_priority = new QLabel(centralWidget);
        label_priority->setObjectName(QString::fromUtf8("label_priority"));

        horizontalLayout_2->addWidget(label_priority);

        priority_lineEdit = new QLineEdit(centralWidget);
        priority_lineEdit->setObjectName(QString::fromUtf8("priority_lineEdit"));

        horizontalLayout_2->addWidget(priority_lineEdit);


        verticalLayout_2->addLayout(horizontalLayout_2);

        duration_button = new QPushButton(centralWidget);
        duration_button->setObjectName(QString::fromUtf8("duration_button"));

        verticalLayout_2->addWidget(duration_button);


        horizontalLayout_3->addLayout(verticalLayout_2);

        Task_managerClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(Task_managerClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 781, 26));
        Task_managerClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(Task_managerClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        Task_managerClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(Task_managerClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        Task_managerClass->setStatusBar(statusBar);

        retranslateUi(Task_managerClass);

        QMetaObject::connectSlotsByName(Task_managerClass);
    } // setupUi

    void retranslateUi(QMainWindow *Task_managerClass)
    {
        Task_managerClass->setWindowTitle(QApplication::translate("Task_managerClass", "Task_manager", nullptr));
        label_priority->setText(QApplication::translate("Task_managerClass", "Priority:", nullptr));
        duration_button->setText(QApplication::translate("Task_managerClass", "Show duration", nullptr));
    } // retranslateUi

};

namespace Ui {
    class Task_managerClass: public Ui_Task_managerClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TASK_MANAGER_H
