# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'dialog.ui'
#
# Created by: PyQt5 UI code generator 5.9.2
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(977, 741)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")
        self.inputMatrixDimEA = QtWidgets.QLineEdit(self.centralwidget)
        self.inputMatrixDimEA.setGeometry(QtCore.QRect(140, 0, 113, 22))
        self.inputMatrixDimEA.setObjectName("inputMatrixDimEA")
        self.label = QtWidgets.QLabel(self.centralwidget)
        self.label.setGeometry(QtCore.QRect(10, 0, 111, 21))
        self.label.setObjectName("label")
        self.eaAlgButton = QtWidgets.QPushButton(self.centralwidget)
        self.eaAlgButton.setGeometry(QtCore.QRect(20, 160, 221, 28))
        self.eaAlgButton.setObjectName("eaAlgButton")
        self.hillAlgButton = QtWidgets.QPushButton(self.centralwidget)
        self.hillAlgButton.setGeometry(QtCore.QRect(310, 160, 241, 28))
        self.hillAlgButton.setObjectName("hillAlgButton")
        self.label_4 = QtWidgets.QLabel(self.centralwidget)
        self.label_4.setGeometry(QtCore.QRect(10, 30, 121, 16))
        self.label_4.setObjectName("label_4")
        self.inputMutationEA = QtWidgets.QLineEdit(self.centralwidget)
        self.inputMutationEA.setGeometry(QtCore.QRect(140, 30, 113, 22))
        self.inputMutationEA.setObjectName("inputMutationEA")
        self.label_5 = QtWidgets.QLabel(self.centralwidget)
        self.label_5.setGeometry(QtCore.QRect(10, 60, 131, 16))
        self.label_5.setObjectName("label_5")
        self.inputCrossOverEA = QtWidgets.QLineEdit(self.centralwidget)
        self.inputCrossOverEA.setGeometry(QtCore.QRect(140, 60, 113, 22))
        self.inputCrossOverEA.setObjectName("inputCrossOverEA")
        self.label_6 = QtWidgets.QLabel(self.centralwidget)
        self.label_6.setGeometry(QtCore.QRect(10, 90, 111, 16))
        self.label_6.setObjectName("label_6")
        self.inputPopulationEA = QtWidgets.QLineEdit(self.centralwidget)
        self.inputPopulationEA.setGeometry(QtCore.QRect(140, 90, 113, 22))
        self.inputPopulationEA.setObjectName("inputPopulationEA")
        self.label_7 = QtWidgets.QLabel(self.centralwidget)
        self.label_7.setGeometry(QtCore.QRect(10, 120, 111, 16))
        self.label_7.setObjectName("label_7")
        self.inputTrialsEA = QtWidgets.QLineEdit(self.centralwidget)
        self.inputTrialsEA.setGeometry(QtCore.QRect(140, 120, 113, 22))
        self.inputTrialsEA.setObjectName("inputTrialsEA")
        self.validateTestEA = QtWidgets.QPushButton(self.centralwidget)
        self.validateTestEA.setGeometry(QtCore.QRect(80, 210, 93, 28))
        self.validateTestEA.setObjectName("validateTestEA")
        self.label_8 = QtWidgets.QLabel(self.centralwidget)
        self.label_8.setGeometry(QtCore.QRect(300, 130, 111, 16))
        self.label_8.setObjectName("label_8")
        self.label_2 = QtWidgets.QLabel(self.centralwidget)
        self.label_2.setGeometry(QtCore.QRect(300, 90, 111, 21))
        self.label_2.setObjectName("label_2")
        self.inputTrialsHill = QtWidgets.QLineEdit(self.centralwidget)
        self.inputTrialsHill.setGeometry(QtCore.QRect(430, 130, 113, 22))
        self.inputTrialsHill.setObjectName("inputTrialsHill")
        self.inputMatrixDimHill = QtWidgets.QLineEdit(self.centralwidget)
        self.inputMatrixDimHill.setGeometry(QtCore.QRect(430, 90, 113, 22))
        self.inputMatrixDimHill.setObjectName("inputMatrixDimHill")
        self.validateTestPSO = QtWidgets.QPushButton(self.centralwidget)
        self.validateTestPSO.setGeometry(QtCore.QRect(770, 270, 93, 28))
        self.validateTestPSO.setObjectName("validateTestPSO")
        self.psoAlgButton = QtWidgets.QPushButton(self.centralwidget)
        self.psoAlgButton.setGeometry(QtCore.QRect(680, 230, 241, 28))
        self.psoAlgButton.setObjectName("psoAlgButton")
        self.inputC1PSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputC1PSO.setGeometry(QtCore.QRect(810, 70, 113, 22))
        self.inputC1PSO.setObjectName("inputC1PSO")
        self.label_3 = QtWidgets.QLabel(self.centralwidget)
        self.label_3.setGeometry(QtCore.QRect(680, 10, 111, 21))
        self.label_3.setObjectName("label_3")
        self.label_9 = QtWidgets.QLabel(self.centralwidget)
        self.label_9.setGeometry(QtCore.QRect(680, 70, 131, 16))
        self.label_9.setObjectName("label_9")
        self.label_10 = QtWidgets.QLabel(self.centralwidget)
        self.label_10.setGeometry(QtCore.QRect(680, 130, 111, 16))
        self.label_10.setObjectName("label_10")
        self.inputMatrixDimPSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputMatrixDimPSO.setGeometry(QtCore.QRect(810, 10, 113, 22))
        self.inputMatrixDimPSO.setObjectName("inputMatrixDimPSO")
        self.inputPopSizePSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputPopSizePSO.setGeometry(QtCore.QRect(810, 130, 113, 22))
        self.inputPopSizePSO.setObjectName("inputPopSizePSO")
        self.label_11 = QtWidgets.QLabel(self.centralwidget)
        self.label_11.setGeometry(QtCore.QRect(680, 40, 121, 16))
        self.label_11.setObjectName("label_11")
        self.inputWPSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputWPSO.setGeometry(QtCore.QRect(810, 40, 113, 22))
        self.inputWPSO.setObjectName("inputWPSO")
        self.inputC2PSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputC2PSO.setGeometry(QtCore.QRect(810, 100, 113, 22))
        self.inputC2PSO.setObjectName("inputC2PSO")
        self.label_12 = QtWidgets.QLabel(self.centralwidget)
        self.label_12.setGeometry(QtCore.QRect(680, 100, 111, 16))
        self.label_12.setObjectName("label_12")
        self.inputTrialsPSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputTrialsPSO.setGeometry(QtCore.QRect(810, 170, 113, 22))
        self.inputTrialsPSO.setObjectName("inputTrialsPSO")
        self.label_13 = QtWidgets.QLabel(self.centralwidget)
        self.label_13.setGeometry(QtCore.QRect(680, 170, 111, 16))
        self.label_13.setObjectName("label_13")
        self.inputSizeOfNeighbourhoodPSO = QtWidgets.QLineEdit(self.centralwidget)
        self.inputSizeOfNeighbourhoodPSO.setGeometry(QtCore.QRect(810, 200, 113, 22))
        self.inputSizeOfNeighbourhoodPSO.setText("")
        self.inputSizeOfNeighbourhoodPSO.setObjectName("inputSizeOfNeighbourhoodPSO")
        self.label_14 = QtWidgets.QLabel(self.centralwidget)
        self.label_14.setGeometry(QtCore.QRect(670, 200, 131, 16))
        self.label_14.setObjectName("label_14")
        self.solutionShowWidget = QtWidgets.QListWidget(self.centralwidget)
        self.solutionShowWidget.setGeometry(QtCore.QRect(30, 380, 451, 291))
        self.solutionShowWidget.setObjectName("solutionShowWidget")
        self.label_15 = QtWidgets.QLabel(self.centralwidget)
        self.label_15.setGeometry(QtCore.QRect(30, 340, 251, 16))
        self.label_15.setObjectName("label_15")
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QtWidgets.QMenuBar(MainWindow)
        self.menubar.setGeometry(QtCore.QRect(0, 0, 977, 26))
        self.menubar.setObjectName("menubar")
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.label.setText(_translate("MainWindow", "matrix dimensions:"))
        self.eaAlgButton.setText(_translate("MainWindow", "EvolutionaryAlgorithm"))
        self.hillAlgButton.setText(_translate("MainWindow", " HillClimbingAlgorithm(using greedy)"))
        self.label_4.setText(_translate("MainWindow", "mutation probabilty:"))
        self.label_5.setText(_translate("MainWindow", "crossover probability:"))
        self.label_6.setText(_translate("MainWindow", "population size:"))
        self.label_7.setText(_translate("MainWindow", "number of trials:"))
        self.validateTestEA.setText(_translate("MainWindow", "Validate Test"))
        self.label_8.setText(_translate("MainWindow", "number of trials:"))
        self.label_2.setText(_translate("MainWindow", "matrix dimensions:"))
        self.validateTestPSO.setText(_translate("MainWindow", "Validate Test"))
        self.psoAlgButton.setText(_translate("MainWindow", "Particle Swarm Optimization Algorithm"))
        self.label_3.setText(_translate("MainWindow", "matrix dimensions:"))
        self.label_9.setText(_translate("MainWindow", "cognitive coeff(c1):"))
        self.label_10.setText(_translate("MainWindow", "population size:"))
        self.label_11.setText(_translate("MainWindow", "inertial coeff(w):"))
        self.label_12.setText(_translate("MainWindow", "social coeff(c2):"))
        self.label_13.setText(_translate("MainWindow", "number of trials:"))
        self.label_14.setText(_translate("MainWindow", "size of neighbourhood:"))
        self.label_15.setText(_translate("MainWindow", "The solution is:"))

