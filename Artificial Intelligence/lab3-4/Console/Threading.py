from PyQt5.QtCore import Qt,QThread,pyqtSignal
import time

class MyThread(QThread):
    change_val=pyqtSignal(int)

    def run(self):
        count = 0
        while count < 5:
            time.sleep(1)
            print("A Increasing")
            count += 1


