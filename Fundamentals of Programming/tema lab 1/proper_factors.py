#checks whether the input given is a natural number
#if not, the message "Invalid input" appears

list = []

while True:
    try:
        x=int(input("Please give a natural number:"))
        if(isinstance(x,int)) and x>=0:
            break
        else:
            print('Invalid input')
    except ValueError:
        print('Invalid input')


#Function that determinates the product of all the proper factors of a given natural number
    #input:x
    #preconditions:x-natural number
    #output:p
    #postconditions: p is the product of all proper factors of x
                    #p is a natural number
def proper_factors(x):  
    if x<=0: return -1
    p = 1
    d=0 #d counts the number of factors because the number may be prime
    print("Here is the list of the proper factors of " +str(x)+":")

    for i in range(2, int(x/2)+1):
        if x % i == 0:
            print(str(i) + " is a proper factor of " +str(x)+".")
            p = p * i 
            d=d+1
    if d==0: print(str(x)+" has no proper factors.")
    print("In the following step the product of " +str(x)+" will appear:")
    if p==1:
        if x<=1: print(str(x)+" has no proper factors and is not a prime number.")
        else: print(str(x)+" is a prime number.")
    else:
        print(str(p) + " is the product of the proper factors of " +str(x)+".")
        return p
    
proper_factors(x)





#test function: 
def test_proper_factors():
    assert proper_factors(12)==144
    assert proper_factors(6)==6
    assert proper_factors(8)==8
    assert proper_factors(-1)==-1
    assert proper_factors(0)==-1
test_proper_factors()
