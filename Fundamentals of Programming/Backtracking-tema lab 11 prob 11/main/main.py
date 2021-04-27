'''
Created on 15 ian. 2019

@author: Armin
'''

def suma(m,i,j):
    #Function that returns true if absolute value of sum i+j >= m
    #input:m,i,j
    #precond:m,i,j-natural numbers
    #output:True or False
    #postcond:True if absolute value of sum i+j>=m, False otherwise
    
    if abs(i-j)>=m:
        return True
    return False

def Backtracing(m, n):
    #Function that returns all possible modalities the numbers from 1 to n, such that suma function is true everytime between 2 consecutives
    #input:m,n
    #precond:m,n-natural numbers
    #output:lista
    #postcond:lista is a list that contains all possible modalities from 1 to n, such that suma function is true everytime between 2 consecutives
    lista=[]
    for i in range(1,n):
        index=[i]
        for j in range(i+1,n+1):
            if suma(m, index[len(index)-1], j):
                index.append(j)
        if len(index)>=2:
            lista.append(index)
    return lista


    

def start():
    while True:
        m=input("Please give a natural number m:")
        n=input("Please give a natural number for n:")
        try:
            m=int(m)
            n=int(n)
            lista=Backtracing(m,n)
            if len(lista)==0:
                print("No solution!")
                return
            else:
                print("The result is:")
                for i in range(0,len(lista)):
                    print(str(lista[i]))
                    
                return
        except ValueError:
            print("Please give natural numbers!Try again!")
            
#start for the function that asks for the inputs            
start()
        
def sum_test():
    #test function for the suma function 
    assert suma(3,7,8)==False
    assert suma(2,9,0)==True
    assert suma(4,0,10)==True
    assert suma(2,8,9)==False
    assert suma(5,100,10)==True
    assert suma(7,90,0)==True
sum_test()
        
    
def interative_test():
    #test function for the iterative method which is the Backtracking function
    assert Backtracing(3, 8)==[[1, 4, 7], [2, 5, 8], [3, 6], [4, 7], [5, 8]]
    assert Backtracing(4, 5)==[[1, 5]]
    assert Backtracing(4, 10)==[[1, 5, 9], [2, 6, 10], [3, 7], [4, 8], [5, 9], [6, 10]]
    assert Backtracing(6,9)==[[1, 7], [2, 8], [3, 9]]
    assert Backtracing(4,9)==[[1, 5, 9], [2, 6], [3, 7], [4, 8], [5, 9]]
    assert Backtracing(9,40)==[[1, 10, 19, 28, 37], [2, 11, 20, 29, 38], [3, 12, 21, 30, 39], [4, 13, 22, 31, 40], [5, 14, 23, 32], [6, 15, 24, 33], [7, 16, 25, 34], [8, 17, 26, 35], [9, 18, 27, 36], [10, 19, 28, 37], [11, 20, 29, 38], [12, 21, 30, 39], [13, 22, 31, 40], [14, 23, 32], [15, 24, 33], [16, 25, 34], [17, 26, 35], [18, 27, 36], [19, 28, 37], [20, 29, 38], [21, 30, 39], [22, 31, 40], [23, 32], [24, 33], [25, 34], [26, 35], [27, 36], [28, 37], [29, 38], [30, 39], [31, 40]]

    
interative_test()