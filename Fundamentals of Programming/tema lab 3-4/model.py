def CreateComplex(x, y):
    '''
    Function that creates a complex number formed by real part x and imaginary part y
    input:x,y
    preconditions:x,y-integers
    output:c
    postconditions:c-complex number where x=real part of c
                                          y=imaginary part of c
    '''
    return {'re': x, 'im': y}


def GetReal(c):
    '''
    Function that gets the real part of the complex number c
    input:c
    preconditions:c-complex number
    output:re
    postconditions:re is the real part of the comple number c
    '''
    return c['re']


def GetImg(c):
    '''
    Function that gets the imaginary part of the complex number c
    input:c
    preconditions:c-complex number
    output:im
    postconditions:im is the imaginary part of the comple number c
    '''
    return c['im']


def SetReal(c, x):
    '''
    Function that sets the real part of the complex number c to x
    input:c,x
    preconditions:c-complex number, x-integer
    output:cc
    postconditions:cc is the new complex number
    '''
    c['re'] = x
    return c


def SetImg(c, x):
    '''
    Function that sets the imaginary part of the complex number c to x
    input:c,x
    preconditions:c-complex number,x-integer
    output:cc
    postconditions:cc is the new complex number
    '''
    c['im'] = x
    return c
