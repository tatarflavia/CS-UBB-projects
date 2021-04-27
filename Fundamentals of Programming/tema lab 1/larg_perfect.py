#checks whether the input is a natural number
#if not, the message 'Invalid input' comes up
while True:
    try:
        x=int(input("Please give a natural number:"))
        if(isinstance(x,int)) and x>=0:
            break
        else:
            print('Invalid input')
    except ValueError:
        print('Invalid input')

#Function that generates the largest perfect number smallest than a given natural number x
    #input:x
    #preconditions: x-natural number
    #output:p
    #postconditions:p is the largest perfect number smaller than x
                    #p is a natural number
def larg_perfect(x):
    if x<=6:
        print("There is no such perfect number for "+str(x)+".")
    else:
        d=x #the number p will be the the largest perfect number smallest than x
        ok=1  #when ok becomes 0, that means the perfect number has been found
        while ok==1:
            while d>0 and ok==1:
            
                
                s=1 #s is the sum of the divisors of the number d
                for i in range(2,int(d/2)+1):
                    if d%i==0:
                        s=s+i
                if s==d:
                    if(d==x):
                        print("The number given which is "+str(x)+" is a perfect number.")
                        d=d-1
                    else:
                        print(str(d)+" is the largest perfect number smaller than "+str(x)+".")
                        ok=0
                        return d
                else: d=d-1
    return -1

larg_perfect(x)
            
def test_larg_perfect():
    assert larg_perfect(0)==-1
    assert larg_perfect(2)==-1
    assert larg_perfect(7)==6
    assert larg_perfect(27)==6
    assert larg_perfect(29)==28
    assert larg_perfect(6)==-1
    assert larg_perfect(28)==6
test_larg_perfect()
