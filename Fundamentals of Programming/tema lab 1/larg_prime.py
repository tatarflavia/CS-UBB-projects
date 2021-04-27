
#Checks whether the input given is a natural number
#If not, the message 'Invalid input' comes up 
while True:
    try:
        x=int(input("Please give a number:"))
        if(isinstance(x,int)) and x>=0:
            break
        else:
            print('Invalid input')
    except ValueError:
        print('Invalid input')




#Function that finds the first prime number bigger than the given number x
        #input:x
        #preconditions:x-natural number
        #output:d
        #postconditions: d is the first prime number bigger than x
                        #d is a natural number
def larg_prime(x):
    if x<0: return -1
    d=x+1 #the number d will be the first prime number bigger than the given number x after the function is over
    ok=1
    while ok==1:
        if d<2:
            d=d+1
        else:
                if d==2:
                    ok=0
                    print(str(d)+" is the first prime number after the given number.")
                    return d
                else:
                    i=2
                    c=0
                    while i*i<=d:
                            if d%i==0:
                                c=c+1
                            i=i+1
                    if c==0:
                        ok=0

                        print(str(d) + " is the first prime number after "+str(x)+".")
                        return d
                    else: d=d+1
    
larg_prime(x)

def test_larg_prime():
    assert larg_prime(5)==7
    assert larg_prime(-3)==-1
    assert larg_prime(9)==11
    assert larg_prime(4)==5
    assert larg_prime(25)==29
    assert larg_prime(0)==2
    assert larg_prime(1)==2

test_larg_prime()
      
