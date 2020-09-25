import numpy as np
import matplotlib.pyplot as plot

class Console(object):
    def __int__(self):
        pass

    def normalDist(self):
        x = int(input("Choose an upper bound:"))
        mean = 0
        std = 1
        array = np.random.normal(0, 1, x)
        #mean,deviation,and upper bound
        # print(array)
        # plot.plot(array)
        # plot.show()

        count, bins, ignored = plot.hist(array, 30, density=True)
        plot.plot(bins, 1 / (std * np.sqrt(2 * np.pi)) *
                  np.exp(- (bins - mean) ** 2 / (2 * std ** 2)),
                  linewidth=2, color='r')
        plot.show()

    def unifDistr(self):
        x = int(input("Choose an upper bound:"))
        array2 = np.random.uniform(0, x, x)
        # print(array2)

        count2, bins2, ignored2 = plot.hist(array2, 15, density=True)
        plot.plot(bins2, np.ones_like(bins2), linewidth=2, color='r')
        plot.show()


    def menu(self):
        print("The menu of options is:")
        print("1.Normal distibution")
        print("2.Uniform distribution")
        print("0.Exit")

    def runApp(self):
        while True:
            self.menu()
            try:
                choice=int(input("Please choose a distribution:"))
                if choice==1:
                    self.normalDist()
                elif choice==2:
                    self.unifDistr()
                elif choice==0:
                    break
                else :
                    print("Unrecognized.")
            except ValueError:
                print("Try a number!")


